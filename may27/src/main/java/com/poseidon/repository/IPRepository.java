package com.poseidon.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poseidon.entity.IPLog;

public interface IPRepository extends JpaRepository<IPLog, Integer>{

}
