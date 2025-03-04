package com.poseidon.web.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.poseidon.web.dto.LoginDTO;

@Repository
public class LoginDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
	//loginMapper.xml
	
	//로그인 작업을 하는 메소드 : LoginDTO(안에는 user_id, user_pw)
	public LoginDTO login(LoginDTO dto) {
		return sqlSession.selectOne("login.login", dto); //네임스페이스.id, sql에서 사용할 값
	}
	

}


