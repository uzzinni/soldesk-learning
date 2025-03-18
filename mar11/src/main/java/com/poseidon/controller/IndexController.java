package com.poseidon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {

	@GetMapping({ "/", "/index" })
	public String index() {
		return "index"; // index.html
	}
	
	
	@GetMapping("/json")
	public @ResponseBody String json() {
		return "{'name' : '홍길동', 'age' : 10}";
		
	}
	
}
