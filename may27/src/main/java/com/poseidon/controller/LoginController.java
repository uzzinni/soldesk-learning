package com.poseidon.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.poseidon.dto.JoinDTO;
import com.poseidon.dto.LoginDTO;
import com.poseidon.entity.Member;
import com.poseidon.service.CustomUserDetails;
import com.poseidon.service.LoginService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class LoginController {
	
	private final LoginService loginService;

	//@GetMapping
	//@PostMapping
	//@PutMapping
	//@DeleteMapping
	
	//  /info
	@Secured("ROLE_USER")
	@GetMapping("/info")
	public String info(Model model) {
		// 저장된 정보 가져오기
		CustomUserDetails cud =
				(CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		 
		//System.out.println("로그인 사용자의 아이디 >>> " + cud.getID());
		//데이터베이스에 물어보기 = model
		LoginDTO loginDTO = loginService.info(cud.getID());
		
		model.addAttribute("info", loginDTO);
		Member member = cud.getMember(); 
		model.addAttribute("info2", member);
		return "info";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "login";
	}
	
	@GetMapping("/join")
	public String join() {
		return "join";
	}
	
	@PostMapping("/join")
	public String join(JoinDTO dto) {
		//System.out.println(dto.getId());
		//System.out.println(dto.getPw());
		//System.out.println(dto.getName());
		//System.out.println(dto.getEmail());
		
		loginService.join(dto);
		
		return "redirect:/login";
	}

	//사용자가 입력한 아이디/비밀번호와 데이터베이스의 값을 비교하는 로그인 메소드
	// form tag
	/*
	@PostMapping("/login")
	public String login(@RequestParam(name = "id", required = true) String id, 
					    @RequestParam(value = "pw", required = true) String pw) {
		
		System.out.println("id >>> " + id);
		System.out.println("pw >>> " + pw);
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
	*/
	
	
	// 아이디 검사하는 ajax
	@PostMapping("/checkId")
	@ResponseBody // json 형태로 응답합니다.
	public Map<String, Boolean> checkID(@RequestParam("id") String id) {
		//System.out.println("ajax >>> " + id);
		//데이터베이스에 전송
		// { available :false }
		Map<String, Boolean> response = new HashMap<String, Boolean>();
		
		//데이터베이스에 물어보기
		boolean checkId = loginService.checkId(id);
		
		response.put("available", checkId); // 아이디 있음
		//System.out.println(response);
		return response;
		
	}
	
	
}



