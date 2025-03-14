package com.poseidon.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.poseidon.service.LoginService;

import jakarta.servlet.http.HttpSession;

@Controller
//@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	private LoginService loginService;
		
	//  http://localhost/login
	@GetMapping("/login")
	public String login() {
		System.out.println(" 요청이 들어왔습니다 ============= ");
		return "login";
	}
	
	@PostMapping("/login")
	public String login(HttpSession session, @RequestParam Map<String, Object> map) {

		Map<String, Object> result = loginService.login(map);

		// (long) result.get("count") == 1
		// Integer.parseInt(String.valueOf(result.get("count"))) == 1 
		if(Integer.parseInt(String.valueOf(result.get("count"))) == 1) {	// 아이디, 암호가 일치합니다.

			// session만들기   user_name, user_id
			session.setAttribute("user_name", result.get("user_name"));
			session.setAttribute("user_id", map.get("id"));
			// /board로 가기
			return "redirect:/board";
		} else {	// 아이디, 암호가 일치하지 않습니다.

			// /login으로 이동하기
			return "redirect:/login"; // get
		}
		
	}
	
	// 2025-03-13 웹페이지 화면 구축
	
	/*
	 * @PostMapping("/login") 
	 * public String login(@RequestParam("id") String id, @RequestParam("pw") String pw) {
	 * 		System.out.println(id);
	 * 		System.out.println(pw);
	 * 		return "redirect:/board";
	 * 
	 * }
	 * 	 */
	
	//http://localhost/logout
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/login";
	}
	
}
