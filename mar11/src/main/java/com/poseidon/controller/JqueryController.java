package com.poseidon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class JqueryController {
	
	@GetMapping("/jq")
	public String jquery() {
		return "jquery";
	}
	
	@GetMapping("/jq2")
	public String jquery2() {
		return "jquery2";
	}
}
