package com.poseidon.controller;

import java.util.Map;

import org.apache.commons.mail.EmailException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MailController {
	
	@Value("${spring.mail.username}")
	private String email;
	
	private final JavaMailSender emailSender;
	
	@GetMapping("/mail")
	public String mail() throws EmailException {
		//화면만 호출
	    return "mail";
	}
	
	@PostMapping("/mail")
	public String mail(@RequestParam Map<String, Object> map) {
		//email, title, content
		//메일 객체 만들고 전송합니다.
		return "";
	}
	
	
	// spring boot starter mail 이용하기
	@GetMapping("/mail2")
	public String mail2() {
		SimpleMailMessage smm = new SimpleMailMessage();
		smm.setFrom("yujin7154@gmail.com");
		smm.setTo("yoojin02wj@naver.com");
		smm.setSubject("스타터 메일로 보내봅니다");
		smm.setText("잘 가나요?");
		emailSender.send(smm);
		
		return "mail";
	}
}
