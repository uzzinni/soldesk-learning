package com.poseidon.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface LoginDAO {

	Map<String, Object> login(Map<String, Object> login);

}
