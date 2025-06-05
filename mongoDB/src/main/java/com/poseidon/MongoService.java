package com.poseidon;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MongoService {

	private final MongoDBRepository mongoDBRepository;
	
	public void save(Board board) {
		mongoDBRepository.save(board);
	}

	public List<Board> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
