package com.poseidon.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BoardDAO{

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	public List<Map<String, Object>> board() {
		return sqlSession.selectList("sample.board");
	}

}