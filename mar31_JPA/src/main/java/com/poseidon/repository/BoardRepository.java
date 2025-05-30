package com.poseidon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.poseidon.entity.Board;

@Repository
public interface BoardRepository extends JpaRepository<Board, Integer> {

}
