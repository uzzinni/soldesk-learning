package com.poseidon.service;

import java.util.Map;

import org.apache.commons.mail.EmailException;
import org.springframework.stereotype.Service;

import com.poseidon.util.Email;
import com.poseidon.util.Util;

@Service
public class MailService {

	public void sendMail(Map<String, Object> map) throws EmailException {
		
		Email email = new Email();
		//text 형식일 경우
		/*
		email.mailSender(
				String.valueOf(map.get("email")), 
				String.valueOf(map.get("title")), 
				String.valueOf(map.get("content"))
				);
		*/
		
		//html
		String html = "<div>"
				+ "<h1>아래 번호를 확인해주세요</h1>"
				+ "<h3>인증번호 : " + Util.randomNumber() + "</h3>"
				+ "<hr>"
				+ (String) map.get("content")
				+ "<br>"
				+ "<img src='http://localhost/img/test.png'>"
				+ "</div>";
		email.htmlMailSender(String.valueOf(map.get("email")), String.valueOf(map.get("title")), html);
		
	}

}
