package com.poseidon.controller;

import org.springframework.web.bind.annotation.GetMapping;
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
	
}
