package com.poseidon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poseidon.entity.Board;
import com.poseidon.entity.Comment;

public interface JpaCommentRepository extends JpaRepository<Comment, Integer>{

	List<Comment> findByBoard(Board board);

}
