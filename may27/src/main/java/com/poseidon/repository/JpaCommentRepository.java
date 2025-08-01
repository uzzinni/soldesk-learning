package com.poseidon.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.poseidon.entity.Board;
import com.poseidon.entity.Comment;

public interface JpaCommentRepository extends JpaRepository<Comment, Integer>{

	List<Comment> findByBoard(Board board);

	List<Comment> findByBoardOrderByCnoDesc(Board board);

	Optional<Comment> findByCno(int cno);

}
