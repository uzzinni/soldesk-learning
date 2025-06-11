package com.poseidon.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poseidon.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
}
