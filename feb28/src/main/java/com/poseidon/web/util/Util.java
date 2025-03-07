package com.poseidon.web.util;

import org.springframework.stereotype.Component;

@Component
public class Util {
	// 엔터키 처리
	public String newLine(String str) {
		//줄바꿈 = 엔터
		// WINDOWS	\r\n
		// MAC		\r
		// UNIX		\n
		
		//시스템별 개행문자
		//System.lineSeparator();
		//System.getProperty("line.separator");
		
		return str.replaceAll(System.getProperty("line.separator"), "<br>");  // 시스템에 엔터를 가져와서 <br>로
		// return str.replaceAll("\n|\r\n|\r", "<br>");  // | or
	}
	
	public String renewLine(String str) {
	      return str.replaceAll("<br>", System.getProperty("line.separator"));
	   }

	// <, > 처리
	public String htmlTag(String str) {
		return str.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
	}
}
