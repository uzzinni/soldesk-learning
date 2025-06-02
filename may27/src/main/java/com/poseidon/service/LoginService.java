package com.poseidon.service;

import java.util.Map;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.poseidon.dao.LoginDAO;
import com.poseidon.dto.JoinDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginService {
	
	private final LoginDAO loginDAO;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	public Map<String, Object> login(Map<String, Object> login) {
		Map<String, Object> result = loginDAO.login(login);
		boolean check = bCryptPasswordEncoder.matches((String)login.get("pw"), (String)result.get("user_pw"));
		System.out.println("check >>. " + check);
		return result;
	}

	public void join(JoinDTO dto) {
		dto.setPw(bCryptPasswordEncoder.encode(dto.getPw()));
		//$2a$10$VZiI.t/siCWdw57I5dIQKe6iAX1zKaMb6Rd.odfageFRCZxYik5Mm
		//System.out.println(bCryptPasswordEncoder.matches("0000", pw));
		System.out.println(dto.toString());
		loginDAO.join(dto);
	}

	public boolean checkId(String id) {
		int result = loginDAO.checkId(id);
		if(result == 0) {
			return true;
		} else {			
			return false;
		}
	}


}
