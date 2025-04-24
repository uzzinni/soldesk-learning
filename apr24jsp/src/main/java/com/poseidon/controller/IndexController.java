package com.poseidon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.poseidon.service.TestService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class IndexController {
	
	private final TestService testService;

	@GetMapping({"/", "/index"})
	public String index(Model model) {
		model.addAttribute("name", "홍길동");
		model.addAttribute("toSize", testService.toSize());
		return "index";
	}
}
