package com.poseidon.util;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.SimpleEmail;

public class Email {
	//email 정보는 여기에 static으로 만들겠습니다.
	static String emailAddr = "yujin7154@gmail.com";	//보내는 이메일
	static String passwd = "kiah nypy ikqb kxhy";	// 앱 비밀번호
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

	    mail.addTo(email);
	    mail.setSubject(title);
	    mail.setMsg(content);
	    
	    mail.send();	// 발송	
	}
	
	//html 사용해보기
	public void htmlMailSender(String email, String title, String content) throws EmailException {
		HtmlEmail mail = new HtmlEmail();
		mail.setCharset("UTF-8");
		mail.setDebug(false);
		mail.setHostName(hostName);
		mail.setSmtpPort(port);
		mail.setAuthentication(emailAddr, passwd);
		mail.setStartTLSEnabled(true);
		mail.setFrom(emailAddr, name);
		
		//대입
		mail.addTo(email);
		mail.setSubject(title);
		//mail.setMsg(content);		//이전 방법
		mail.setHtmlMsg(content);	//html tag를 사용할 수 있는 방법
		
		//파일 첨부하기
		EmailAttachment file = new EmailAttachment();
		
		file.setPath("c:\\temp\\swagger.pdf");
		mail.attach(file);
		mail.send();
	}
}
