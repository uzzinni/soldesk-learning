package com.poseidon.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface AopDAO {
	
	@Insert("INSERT INTO iplog (iip, iurl, idata) VALUES (#{ip}, #{uri}, #{data})")
	public void saveLog(Map<String, Object> map);
	
}
