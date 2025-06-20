package com.poseidon.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poseidon.entity.Comment;

public interface JpaCommentRepository extends JpaRepository<Comment, Integer>{

}
