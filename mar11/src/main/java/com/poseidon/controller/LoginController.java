package com.poseidon.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
//@RequestMapping("/login")
public class LoginController {
	
	//http://localhost/login/login
	@GetMapping("/login")
	public String login() {
		System.out.println("요청이 들어왔습니다 =========");
		return "login";
	}
	
	@PostMapping("/login")
	public String login(@RequestParam Map<String, Object> map) {
		System.out.println(map);
		return "redirect:/board";
	}
	
	/* 두 번째 방법
	@PostMapping("/login")
	   public String login(@RequestParam("id") String id, @RequestParam("pw") String pw) { 
	      System.out.println(id);
	      System.out.println(pw);
	      return "redirect:/board";
	   }
	*/
	
	//http://localhost/login/logout
	@GetMapping("/logout")
	public String logout() {
		return "login/logout";
	}
	
}
