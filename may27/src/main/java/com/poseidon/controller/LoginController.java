package com.poseidon.controller;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.id.IntegralDataTypeHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.poseidon.service.LoginService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class LoginController {
	
	private final LoginService loginService;

	//@GetMapping
	//@PostMapping
	//@PutMapping
	//@DeleteMapping
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "login";
	}
	
	@GetMapping("/join")
	public String join() {
		return "join";
	}
	
	@PostMapping("/join")
	public String join(@RequestParam("id") String id, @RequestParam("pw") String pw) {
		System.out.println(id);
		System.out.println(pw);
		
		return "redirect:/login";
	}

	//사용자가 입력한 아이디/비밀번호와 데이터베이스의 값을 비교하는 로그인 메소드
	// form tag
	@PostMapping("/login")
	public String login(@RequestParam(name = "id", required = true) String id, 
					    @RequestParam(value = "pw", required = true) String pw) {
		
		//System.out.println("id >>> " + id);
		//System.out.println("pw >>> " + pw);
		// Map
		Map<String, Object> login = new HashMap<String, Object>();
		login.put("id", id);
		login.put("pw", pw);
		
		Map<String, Object> result = loginService.login(login);
		//System.out.println("result >>> " + result);
		
		if(Integer.parseInt((String.valueOf(result.get("count")))) == 1) {
			//정상로그인 : 올바른 id, pw를 입력함.
			// 세션만들기
			// Request 만들기 ===== 조금 길어요
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
			HttpSession session = request.getSession();
			//세션만들기
			session.setAttribute("name", result.get("user_name"));
			// 페이지 이동
			return "redirect:/board";
		}
		return "login";
	}
	
	//로그아웃
	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		//세션 삭제
		HttpSession session = request.getSession();
		session.invalidate();
		return "redirect:/login";
	}
	
	
	
	
}



