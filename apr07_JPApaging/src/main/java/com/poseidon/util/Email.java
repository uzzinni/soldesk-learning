package com.poseidon.util;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class Email {
	//email 정보는 여기에 static으로 만들겠습니다.
	static String emailAddr = "yujin7154@gmail.com";	//보내는 이메일
	static String passwd = "";	// 앱 비밀번호
	static String hostName = "smtp.gmail.com";	//고정
	static String name = "[Admin]";
	static int port = 587;	//고정
	
	//mailSender(주소, 제목, 본문내용) 메소드를 만들겠습니다.
	public void mailSender(String email, String title, String content) throws EmailException {
		SimpleEmail mail = new SimpleEmail();
	    mail.setCharset("UTF-8");
	    mail.setDebug(true);
	    mail.setHostName(hostName);
	    mail.setSmtpPort(port);
	    mail.setAuthentication(emailAddr, passwd);
	    mail.setStartTLSEnabled(true);
	    mail.setFrom(emailAddr, name);      
	    mail.addTo("yoojin02wj@naver.com");   
	    mail.setSubject("가입을 환영합니다.");
	    mail.setMsg("본문 내용입니다. 탈퇴 시 뼈와 살이 분리됩니다.");
	    mail.send();	// 발송
		
	}
}
