package com.poseidon.controller;

import javax.mail.MessagingException;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MailController {
	
	@GetMapping("/main")
	public void mail() throws MessagingException, EmailException {
		String emailAddr = "";	// email 주소
		String passwd = "";	//암호
		String hostName = "smtp.gmail.com";
		String name = "[admin]";
		int port = 587;	//포트 번호
		
		SimpleEmail mail = new SimpleEmail();
	      mail.setCharset("UTF-8");
	      mail.setDebug(true);
	      mail.setHostName(hostName);
	      mail.setSmtpPort(port);
	      mail.setAuthentication(emailAddr, passwd);
	      mail.setStartTLSEnabled(true);
	      mail.setFrom(emailAddr, name);      
	      mail.addTo("");   
	      mail.setSubject("가입을 환영합니다.");
	      mail.setMsg("본문 내용입니다. 탈퇴 시 뼈와 살이 분리됩니다.");
	      mail.send();
	}
}
