package com.poseidon.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.poseidon.dao.KmaDAO;
import com.poseidon.dto.KmaDTO;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class KmaService {
	@Value("${kma.url}")
	private String url;

	@Value("${air.key}")
	private String key;

	private final KmaDAO kmaDAO;
	
	private static KmaDTO map2Dto(Map<String, Object> map) {
		KmaDTO dto = KmaDTO.builder()
				.tm(LocalDate.parse((String)map.get("tm"),DateTimeFormatter.ISO_DATE))
				.avgTa(Double.parseDouble((String) map.get("avgTa")))
				.minTa(Double.parseDouble((String) map.get("minTa")))
				.maxTa(Double.parseDouble((String) map.get("maxTa")))
				.build();
		return dto;
	}
	
	public int kmaToDB() throws IOException {
		StringBuilder urlBuilder = new StringBuilder(url); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "="+key); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호 Default : 1*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("365", "UTF-8")); /*한 페이지 결과 수 Default : 10*/
        urlBuilder.append("&" + URLEncoder.encode("dataType","UTF-8") + "=" + URLEncoder.encode("JSON", "UTF-8")); /*요청자료형식(XML/JSON) Default : XML*/
        urlBuilder.append("&" + URLEncoder.encode("dataCd","UTF-8") + "=" + URLEncoder.encode("ASOS", "UTF-8")); /*자료 분류 코드(ASOS)*/
        urlBuilder.append("&" + URLEncoder.encode("dateCd","UTF-8") + "=" + URLEncoder.encode("DAY", "UTF-8")); /*날짜 분류 코드(DAY)*/
        urlBuilder.append("&" + URLEncoder.encode("startDt","UTF-8") + "=" + URLEncoder.encode("20240429", "UTF-8")); /*조회 기간 시작일(YYYYMMDD)*/
        urlBuilder.append("&" + URLEncoder.encode("endDt","UTF-8") + "=" + URLEncoder.encode("20250428", "UTF-8")); /*조회 기간 종료일(YYYYMMDD) (전일(D-1)까지 제공)*/
        urlBuilder.append("&" + URLEncoder.encode("stnIds","UTF-8") + "=" + URLEncoder.encode("108", "UTF-8")); /*종관기상관측 지점 번호 (활용가이드 하단 첨부 참조)*/
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        //System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
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
        //System.out.println(sb.toString());
        // jackson을 이용해서 정리합니다.
        // sb(String) -> Json -> DTO
        ObjectMapper objMapper = new ObjectMapper();
        JsonNode jsonNode = objMapper.readTree(sb.toString());
        List<Map<String, Object>> list = objMapper.readValue(
        						jsonNode.get("response").get("body").get("items").get("item").toString(),
        						new TypeReference<List<Map<String, Object>>>() {}
        					);
        // 데이터를 DTO로 변경합니다.
        System.out.println(list);
        System.out.println(list.size());
        // DAO에게 일 시키기는 여기에...
        List<KmaDTO> dtoList = list.stream().map(KmaService::map2Dto).collect(Collectors.toList());
        System.out.println(dtoList);
        System.out.println(dtoList.size());
               
        //DAO에게 일 시키기
        kmaDAO.kmaClear();
        int result = kmaDAO.kmaInsert(dtoList);
        System.out.println("result : " + result);
		return result;
	}
	
	public List<KmaDTO> kmaSelect() {
		return kmaDAO.kmaSelect();
	}
	
}
