package com.poseidon;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginDTO {
	@NotBlank(message = "아이디를 입력해주세요.")
	@Size(min = 3, message = "아이디는 3글자 이상입니다.")
	private String id;
	
	@NotBlank(message = "암호를 입력해주세요.")
	@Size(min=3, max = 20, message = "암호는 최소 ㅁ")
	private String pw;
}
