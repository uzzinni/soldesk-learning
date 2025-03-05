package com.poseidon.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.poseidon.web.dto.LoginDTO;
import com.poseidon.web.service.LoginService;

@Controller
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	
	// 페이지만 오픈 GET -> GetMapping
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	// 숨겨서 전송 POST -> PostMapping
	@PostMapping("/login")
	public String login(HttpServletRequest request) {
		// 사용자가 보내온 username, password하고 잡기
		String user_id = request.getParameter("username");
		String user_pw = request.getParameter("password");
		// LoginDTO만들기
		LoginDTO dto = new LoginDTO();
		dto.setUser_id(user_id);
		dto.setUser_pw(user_pw);
		
		
		LoginDTO result = loginService.login(dto);
		//System.out.println(result.getCount());
		//System.out.println(result.getUser_name());
		
		if(result.getCount() == 1) {
			//정상 로그인 -> 세션만들기, /board로 보내기
			//세션만들기 : 세션 객체
			HttpSession session = request.getSession();
			//세션 만들기 : 세션에 값 올리기
			session.setAttribute("user_name", result.getUser_name()); //이름, 값
			session.setAttribute("user_id", user_id);
			
			//확인해보기
			System.out.println(session.getAttribute("user_id"));
			
			return "redirect:/board";
		} else {
			//비정상 로그인 -> /login로 보내기
			return "redirect:/login?login=failed"; //get방식의 /login으로 가
		}
		
	}
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		//세션 삭제
		HttpSession session = request.getSession();
		session.invalidate();//삭제
		//session.removeAttribute("user_id");
		//session.removeAttribute("user_name");
		/*
		 * 세션 삭제
		 * session.invalidate() : 세션을 모두 삭제하고 제거 
		 * session.removeAttribute(name) : 해당 name을 삭제 
		 * removeAttribute()로 삭제하면 httpSession은 남아 있음
		 */
		
		
		return "redirect:/login";
	}
	
	
	
}
// 2025-03-05 웹페이지 화면 구축
// ECMA script = java script       .js

// node.js ---- TypeScript
// 추론타입 var name = 10;
// 자바스크립트 확장 .jsx

// 바닐라 스크립트














