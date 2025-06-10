package com.poseidon;

import org.springframework.stereotype.Service;

@Service
public class LoginService {

	//아이디 검사
	public boolean isCheckID(String id) {
		if(id.equals("poseidon")) {
			return true;
		}
		return false;
	}
	
	
	// 저장
	public void join(JoinDTO joinDTO) {
		System.out.println("저장됨");
		System.out.println("service >>> " + joinDTO);
		
	}
}
