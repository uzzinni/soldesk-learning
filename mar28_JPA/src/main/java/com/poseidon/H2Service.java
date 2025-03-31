package com.poseidon;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class H2Service {
	
	//repository
	private final H2Repository h2Repository;

	public List<Board> findAll() {
		return h2Repository.findAll();
	}

}
