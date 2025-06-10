package com.poseidon.dao;

import java.util.Map;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.poseidon.dto.JoinDTO;
import com.poseidon.dto.LoginDTO;

@Repository
@Mapper
public interface LoginDAO {

	Map<String, Object> login(Map<String, Object> login);

	void join(JoinDTO dto);

	int checkId(String id);

	// 로그인 하는 메소드 =  아이디와 일치하는 정보 가져오기.
	Optional<LoginDTO> login(String id);
}