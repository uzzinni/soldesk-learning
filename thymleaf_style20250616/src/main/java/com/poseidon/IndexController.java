package com.poseidon;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
	
	@GetMapping({"/","/index"})
	public String index(Model model) {
		model.addAttribute("title", "index page");
		System.out.println("index");
		return "index";
	}
	
	@GetMapping("/main")
	public String main(Model model) {
		model.addAttribute("title", "main page");
		System.out.println("main");
		return "main";
	}
}
