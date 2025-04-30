package com.poseidon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poseidon.entitiy.Iplog;

public interface ExcelRepository extends JpaRepository<Iplog, Integer> {

	List<Iplog> findAllByOrderByInoDesc();
	// SELECT * FROM iplog ORDER BY ino DESC;

}
