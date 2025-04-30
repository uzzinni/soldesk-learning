package com.poseidon.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poseidon.entitiy.GuestBook;

public interface GuestBookRepository extends JpaRepository<GuestBook, Integer>{

}
