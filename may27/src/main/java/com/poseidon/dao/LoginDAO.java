package com.poseidon.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.poseidon.dto.JoinDTO;

@Repository
@Mapper
public interface LoginDAO {

	Map<String, Object> login(Map<String, Object> login);

	void join(JoinDTO dto);

	int checkId(String id);

}
