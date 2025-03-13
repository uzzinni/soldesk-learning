package com.poseidon.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface LoginDAO {
	//@Select("SELECT * FROM user WHERE user_id=#{id} AND user_pw=#{pw}")
	Map<String, Object> login(Map<String, Object> map);
}
