package com.poseidon;

// 2025-06-09 데이터 분석을 위한 텍스트 마이닝 구측 실습
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class LoginController {
	private final LoginService loginService;
	
	@GetMapping("/board")
	public String board() {
		return "board";
	}

	// 화면 보여주기 -> 결과
	@GetMapping("/login")
	public String login(@ModelAttribute("loginDTO") LoginDTO loginDTO, 
			@RequestParam(value = "error", required = false) String error,
			Model model) {
		if(error != null) {
			model.addAttribute("loginError", "아이디 또는 비밀번호가 올바르지 않습니다.");
		}
		return "login";
	}

	@PostMapping("/login")
	public String login(@Valid @ModelAttribute("loginDTO") LoginDTO loginDTO,
			BindingResult bindingResult,
			Model model) {
		if(    bindingResult.hasErrors()   ) {
			return "login";
		}
		
		//데이터베이스에 아이디와 비밀번호를 보내서 검사하기 
		
		return "redirect:/board";
	}

	@GetMapping("/join")
	public String join(Model model) {
		model.addAttribute("joinDTO", new JoinDTO());
		return "join";
	}
	
	@PostMapping("/join")
	public String join(@Valid JoinDTO joinDTO, BindingResult bindingResult, Model model) {
		System.out.println(joinDTO);
		System.out.println(bindingResult.hasErrors());
		
		if(bindingResult.hasErrors()) {
			return "join";
		}
		
		//서비스로 보내기 LoginService
		if(loginService.isCheckID(joinDTO.getId())) {
			bindingResult.addError(new FieldError("joinDTO", "id", "중복된 아이디입니다."));
			// 특정 필드에 에러를 유발시키려면 FieldError객체를 만들어 주어야 함.
			// new FieldError("객체명(form에 적은 th:object)","필드명(변수명)","에러내용")
			// 이렇게 하시면 됩니다.
			return "join";
		}
		
		loginService.join(joinDTO);
		
		return "redirect:/board";
	}
	
}






