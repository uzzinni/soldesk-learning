package com.poseidon.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.poseidon.util.Util;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AirController {
	
	private final Util util;

	@Value("${air.url}")
	private String url;

	@Value("${air.key}")
	private String key;

	@GetMapping("/air")
	public String air(Model model) throws IOException {
		// System.out.println("url : " + url);
		// System.out.println("key : " + key);
		StringBuilder sb = new StringBuilder(url);
		sb.append("?serviceKey=" + key);   //물음표는 URL뒤에 파라미터 적기 위해서 처음 시작할 때 
		sb.append("&returnType=json");
		sb.append("&searchDate=" + util.today()); //여기는 오늘 날짜. 여기도 변수처리 하겠습니다.
		
		URL url = new URL(sb.toString());
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/json");
		
		BufferedReader rd;
		if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		} else {
			rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		}
		
		StringBuilder result = new StringBuilder();
		String line;
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}
		
		rd.close();
		conn.disconnect();
		//System.out.println("최종결과 : " + result.toString());
		
		ObjectMapper objectMapper= new ObjectMapper();
		JsonNode jsonNode = objectMapper.readTree(result.toString());
		//System.out.println("결과 : " + jsonNode.get("response").get("body").get("items"));
		
		//map타입으로 변경해서 사용하겠습니다.
		Map<String, Object> map = objectMapper.readValue(
				jsonNode.get("response").get("body").get("items").get(0).toString(),
				new TypeReference<Map<String, Object>>(){}
				);
		
		//System.out.println("map : " + map);
		String informGrade = (String) map.get("informGrade");
		//System.out.println("informGrade : " + informGrade);
		//아래 이모지 넣을 리스트
		List<String> list = new ArrayList<String>();
		
		String[] data = informGrade.split(",");
		for (int i = 0; i < data.length; i++) {
			String[] item = data[i].split(" : "); // 서울, 보통
			// 윈도우키 + . 이모지가 나와요 // 보통😊, 좋음😁, 나쁨😒
			list.add(item[0] + " " 
					+ (item[1].equals("보통") ? "😊" : item[1].equals("좋음") ? "😁" : "😒")
					);
		}
		model.addAttribute("list", list);
				
		model.addAttribute("map", map);
		
		return "air"; // air.jsp
	}

	@ResponseBody
	@GetMapping("/air2")
	public String air2() throws IOException {
		StringBuilder urlBuilder = new StringBuilder(url); /* URL */
		urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=" + key); /* Service Key */
		urlBuilder.append("&" + URLEncoder.encode("returnType", "UTF-8") + "="
				+ URLEncoder.encode("json", "UTF-8")); /* xml 또는 json */
		urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "="
				+ URLEncoder.encode("100", "UTF-8")); /* 한 페이지 결과 수(조회 날짜로 검색 시 사용 안함) */
		urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "="
				+ URLEncoder.encode("1", "UTF-8")); /* 페이지번호(조회 날짜로 검색 시 사용 안함) */
		urlBuilder.append("&" + URLEncoder.encode("searchDate", "UTF-8") + "="
				+ URLEncoder.encode("2025-04-25", "UTF-8")); /* 통보시간 검색(조회 날짜 입력이 없을 경우 한달동안 예보통보 발령 날짜의 리스트 정보를 확인) */
		urlBuilder.append("&" + URLEncoder.encode("InformCode", "UTF-8") + "="
				+ URLEncoder.encode("PM10", "UTF-8")); /* 통보코드검색(PM10, PM25, O3) */
		URL url = new URL(urlBuilder.toString());
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/json");
		System.out.println("Response code: " + conn.getResponseCode());
		BufferedReader rd;
		if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		} else {
			rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		}
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = rd.readLine()) != null) {
			sb.append(line);
		}
		rd.close();
		conn.disconnect();
		System.out.println(sb.toString());
		return sb.toString();
	}

}
