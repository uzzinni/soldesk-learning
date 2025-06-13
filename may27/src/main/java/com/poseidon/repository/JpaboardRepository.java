package com.poseidon.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poseidon.entity.Board;

public interface JpaboardRepository extends JpaRepository<Board, Integer>{
	
	

}
