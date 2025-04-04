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
	
	public List<Map<String, Object>> board(Map<String, Object> map) {
		return sqlSession.selectList("sample.board", map);
	}

	public int getBoardCount(Map<String, Object> map) {
		return sqlSession.selectOne("sample.getBoardCount", map);
	}
	
}