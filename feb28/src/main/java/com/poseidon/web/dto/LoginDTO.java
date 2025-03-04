package com.poseidon.web.dto;

import lombok.Data;

// 한줄
/*
 * 어노테이션
 * @Controller : 컨트롤러 기능
 * @Service		: 서비스 기능
 * @Repository  : DAO기능
 * @Component   : 그 외
 */

@Data
public class LoginDTO {
	private int count;
	private String user_id, user_pw, user_name;
	// 더 만들어주겠습니다.
	// 2024-03-04 ChatGPT 활용
}
