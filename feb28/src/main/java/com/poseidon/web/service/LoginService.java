package com.poseidon.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poseidon.web.dao.LoginDAO;
import com.poseidon.web.dto.LoginDTO;

@Service
public class LoginService {

	@Autowired
	private LoginDAO loginDAO;
	
	
	public LoginDTO login(LoginDTO dto) {
		//메소드 login은 LoginDTO(user_id, user_pw)가 들어와야 합니다.
		return loginDAO.login(dto);
	}
	
}


// 연결하기 지금은 @Autowired로 사용합니다.
//  컨트롤러      서비스     DAO
// Controller -> Service -> Repository -> mybatis

