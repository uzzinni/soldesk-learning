package com.poseidon.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.poseidon.dto.BoardDTO;

@Repository
@Mapper
public interface BoardDAO {

	int count();
	
	List<BoardDTO> boardList();

	List<Map<String, Object>> ajaxList(int pageNo);
}

/*
 mapper에서 CDATA
 <![CDATA[ 
 	이 안에 작성된 내용은 xml 파셔가 해석하지 않아요.
 	주로 특수문자 ( > , < , &, "", '' 등)을 xml로 인지 하지 않고
 	SQL의 크다 작다로 인지하게 합니다. 	
 ]>
  
 
 */