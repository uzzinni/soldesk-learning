package com.poseidon.dto;

import lombok.Data;

@Data
public class LoginDTO {
	private int uno;
	private String id;
	private String pw;
	private String name;
	private String email;
	private String role; //권한 저장하는 변수 ROLE_USER/ROLE_ADMIN
	
}