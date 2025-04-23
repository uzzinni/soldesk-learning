package com.poseidon.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

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

	
}
