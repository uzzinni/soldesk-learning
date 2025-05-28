package com.poseidon.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.poseidon.dao.LoginDAO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginService {
	
	private final LoginDAO loginDAO;

	public Map<String, Object> login(Map<String, Object> login) {
		return loginDAO.login(login);
	}

}
