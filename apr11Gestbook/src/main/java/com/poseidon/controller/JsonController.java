package com.poseidon.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

//@Controller
@RestController		// view가 없어요 = api
public class JsonController {
	
	@GetMapping("/json")
	public @ResponseBody String json() {
		ObjectMapper mapper = new ObjectMapper();	// { "result" : [{}, {}] }
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();	// []
		Map<String, Object> ele = new HashMap<String, Object>();	// {내부}
		ele.put("name", "홍길동");
		ele.put("age", 23);
		ele.put("addr", "서울");
		list.add(ele);	// 첫번째 값을 리스트에 붙이기
	
		ele = new HashMap<String, Object>();
		ele.put("name", "임길동");
		ele.put("age", 200);
		ele.put("addr", "한양");
		list.add(ele);	// 첫번째 값을 리스트에 붙이기
		
		// 조립
		Map<String, List<Map<String, Object>>> result = new HashMap<String, List<Map<String, Object>>>();
		result.put("result", list);
		
		// json으로 변환하기
		JsonNode json = mapper.convertValue(result, JsonNode.class);	// map to json

		return json.toString();
	}
}
