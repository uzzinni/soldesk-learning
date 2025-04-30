package com.poseidon.controller;

import java.io.IOException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.poseidon.service.JsonService;
import lombok.RequiredArgsConstructor;

//@Controller
@RestController // view가 없어요 = api
@RequiredArgsConstructor
public class JsonController {
	
	private final JsonService jsonService;

	@GetMapping("/json")
	public String json() {
		return jsonService.json(); // 서비스로 이동
	}
	
	// 요청 localhost/add?fir=10&sec=15&key=01234567
	// 결과 {result:25}
	@GetMapping("/add")
	public String add(@RequestParam("fir") int fir, 
			@RequestParam("sec") int sec,
			@RequestParam("key") String key       ) {
		System.out.println("fir : " + fir);
		System.out.println("sec : " + sec);
		System.out.println("key : " + key);
		return jsonService.add(fir, sec, key);
	}
	
	@GetMapping("/simpleJson")
	public String simpleJson() {
		return jsonService.simpleJson();
	}
	
	@GetMapping("/map2json")
	public String map2json() {
		return jsonService.map2json();
	}
	
	@GetMapping("/list2json")
	public String list2json() {
		return jsonService.list2json();
	}
	
	@GetMapping("/read-json")
	public String readJson() throws IOException {
		return jsonService.readJson();
	}
	
	@GetMapping("/gson")
	public String gson() {
		return jsonService.gson();
	}
}
