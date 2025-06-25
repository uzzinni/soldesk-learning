package com.poseidon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.poseidon.util.Util;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class IndexController {
	private final Util util;

	@GetMapping({ "/", "/index" })
	public String index(Model model) {
		String ip = util.ipMasking();
		model.addAttribute("ip", ip);
		return "index";
	}
	
}