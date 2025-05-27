package com.poseidon.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.poseidon.dto.BoardDTO;

@Repository
@Mapper
public interface BoardDAO {

	int count();
	
	List<BoardDTO> boardList();
}
