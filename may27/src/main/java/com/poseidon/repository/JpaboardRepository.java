package com.poseidon.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poseidon.entity.Board;

public interface JpaboardRepository extends JpaRepository<Board, Integer>{

	Optional<Board> findByBno(int bno);

	void delete(Board board);
	
	
}
