package com.poseidon.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class IndexController {

	
	@GetMapping("/")  // http://localhost/
	public String index(Model model) {
		model.addAttribute("text", "컨트롤러에서 보내온 메시지");
		model.addAttribute("name", "홍길동");
		
		List<String> list = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			list.add("홍길동" + i);
		}
		model.addAttribute("list", list);
		
		return "index"; //index.html
	}
	
}
