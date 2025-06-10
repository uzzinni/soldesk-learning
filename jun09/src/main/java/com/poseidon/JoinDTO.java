package com.poseidon;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JoinDTO {

	private int no;
	
	@NotEmpty(message = "아이디는 필수 입력 항목입니다.")
	@Size(min = 5, message = "아이디는 5글자 이상이어야 합니다.")
	private String id;
	
	@NotEmpty(message = "비밀번호를 입력하세요.")
	@Size(min = 8, message = "비밀번호는 최소 8자 이상이어야 합니다.")
	private String pw;
	
	@NotEmpty(message = "닉네임을 입력하세요.")
	@Size(min = 3, message = "닉네임은 3글자 이상이어야 합니다.")
	private String nickname;
}
