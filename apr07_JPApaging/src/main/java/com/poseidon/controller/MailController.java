package com.poseidon.controller;

import java.util.Map;

import org.apache.commons.mail.EmailException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.poseidon.service.MailService;
import com.poseidon.util.Util;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MailController {
	
	private final MailService mailService;
	
	@GetMapping("/mail")
	public String mail() {
		for(int i = 0; i < 100; i++) {
			System.out.println(Util.randomNumber());
		}
		
		//화면만 호출
	    return "mail";
	}
	
	@PostMapping("/mail")
	public String mail(@RequestParam Map<String, Object> map) throws EmailException {
		//System.out.println("사용자 정보 : " + map);
		mailService.sendMail(map);
		return "mail";
	}

}
