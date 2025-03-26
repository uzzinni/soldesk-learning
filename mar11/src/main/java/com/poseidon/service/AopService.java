package com.poseidon.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poseidon.dao.AopDAO;

@Service
public class AopService {
	
	@Autowired
	private AopDAO aopDAO;

	public void sevelog(Map<String, Object> map) {
		aopDAO.savelog(map);
	}
	
}
