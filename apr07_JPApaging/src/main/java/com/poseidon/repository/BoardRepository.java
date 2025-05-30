package com.poseidon.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.poseidon.entity.Board;

//T = Entity, ID = @Id = PK
@Repository
public interface BoardRepository extends JpaRepository<Board, Integer> {

	Page<Board> findAllByOrderByBnoDesc(Pageable pageable);

	// ?
}
