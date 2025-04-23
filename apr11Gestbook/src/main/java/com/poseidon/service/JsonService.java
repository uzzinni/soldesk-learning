package com.poseidon.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.poseidon.dto.HumanDTO;

@Service
public class JsonService {

	public String json() {
		ObjectMapper mapper = new ObjectMapper();  // { "result" : [{},{}] }
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>(); // []
		Map<String, Object> ele = new HashMap<String, Object>(); // {내부}
		ele.put("name", "홍길동"); // {"name" : "홍길동", 'age' : 23, "addr" : "서울" }
		ele.put("age", 23);
		ele.put("addr", "서울");
		list.add(ele); //첫번째 값을 리스트에 붙이기
		
		ele = new HashMap<String, Object>(); //새로운 객체
		ele.put("name", "임길동"); // {"name" : "임길동", "age" : 200, "addr" : "한양" }
		ele.put("age", 200);
		ele.put("addr", "한양");
		list.add(ele); //첫번째 값을 리스트에 붙이기
		
		// 조립
		Map<String, List<Map<String, Object>>> result = new HashMap<String, List<Map<String,Object>>>();
		result.put("result", list);
		
		// json으로 변환하기
		JsonNode json = mapper.convertValue(result, JsonNode.class); // map to json
		
		//return json.toString();
		return json.toPrettyString();
	}

	public String add(int fir, int sec, String key) {
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> result = new HashMap<String, Object>();
		
		// key가 01234567이야?
		if(key.equals("01234567")) {
			result.put("result", fir + sec);			
		} else {
			result.put("result", "error");
		}
		
		JsonNode json = mapper.convertValue(result, JsonNode.class);
		return json.toPrettyString();
	}

	@SuppressWarnings("unchecked")
	public String simpleJson() {
		JSONArray list = new JSONArray(); // []
		JSONObject obj = new JSONObject(); //배열 속 요소1
		obj.put("name", "홍길동");
		obj.put("age", 20);
		obj.put("addr", "서울");
		list.add(obj);
		
		JSONObject obj2 = new JSONObject(); // 요소2
		obj2.put("name", "김길동");
		obj2.put("age", 200);
		obj2.put("addr", "한양");
		list.add(obj2);
		
		//{"result" : [ {}, {}]}
		JSONObject result = new JSONObject();
		result.put("result", list);
		
		return result.toJSONString();
	}

	public String map2json() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("bno", 152);
		map.put("btitle", "글 제목입니다");
		map.put("bdate", "2024-09-03");
		map.put("mname", "포세이돈");
		map.put("blike", 100);
		map.put("bcount", 195);
		
		JSONObject result = new JSONObject(map);
		//result.put("board", map);
		
		return result.toJSONString();
	}
	//{"board":{"bno":152,"bdate":"2024-09-03","btitle":"글 제목입니다","mname":"포세이돈","blike":100,"bcount":195}}
	//{"bno":152,"bdate":"2024-09-03","btitle":"글 제목입니다","mname":"포세이돈","blike":100,"bcount":195}
	
	public String list2json() {
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		for (int i = 1; i <= 10; i++) {
			Map<String, Object> ele = new HashMap<String, Object>();
			ele.put("bno", i);
			ele.put("btitle", i + "");
			ele.put("bdate", "2024-09-03");
			ele.put("mname", "포세이돈");
			ele.put("blike", i * 10);
			ele.put("bcount", i * 25);
			list.add(ele);
		}
		
		JSONObject result = new JSONObject();
		result.put("board", list);
		
		return result.toJSONString();
	}
	
	public String readJson() throws IOException {
		URL url = new URL("http://localhost/json");
		HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
		BufferedReader br = 
				new BufferedReader(new InputStreamReader(httpConn.getInputStream()));
		
		String line;
		String result = "";
		while ((line = br.readLine()) != null) {
			result += line;
		}
		
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> map = mapper.readValue(result, Map.class);
		
		System.out.println("map에 저장 완료" + map);
		// map에 저장 완료{result=[{name=홍길동, addr=서울, age=23}, {name=임길동, addr=한양, age=200}]}
		
		List<Map<String, Object>> list = (List<Map<String, Object>>) map.get("result");
		System.out.println("map.get(\"result\")" + list);
		//map.get("result")   [{name=홍길동, addr=서울, age=23}, {name=임길동, addr=한양, age=200}]
		
		for (Map<String, Object> map2 : list) {
			System.out.println("name : " + map2.get("name"));
			System.out.println("age : " + map2.get("age"));
			System.out.println("addr : " + map2.get("addr"));
			System.out.println("");
		}
		
		return "변환 완료";
	}
	
	public String gson() {
		Gson gson = new Gson();
		
		// json to object
		String json = "{\"name\":\"홍길동\",\"age\":20,\"addr\":\"서울\"}";
		Map<String, Object> json2map = gson.fromJson(json, Map.class);
		System.out.println("map으로 변환" + json2map);
		
		HumanDTO dto = gson.fromJson(json, HumanDTO.class);
		System.out.println("dto : " + dto);
		
		// object to json
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", "홍길동");
		map.put("age", 20);
		map.put("addr", "서울");
		
		JsonObject jo = new JsonObject();
		jo.addProperty("name", "홍길동");
		jo.addProperty("age", 20);
		jo.addProperty("addr", "서울");
		
		return gson.toJson(jo);
		
	}
	
	
	
	
}
