package com.poseidon.service;

import org.springframework.stereotype.Service;

import com.poseidon.dao.TestDAO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TestService {

	private final TestDAO testDAO;
	
	public int toSize() {
		return testDAO.toSize();
	}
}
