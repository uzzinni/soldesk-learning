package com.poseidon.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.poseidon.dao.BoardDAO;
import com.poseidon.dto.BoardDTO;
import com.poseidon.dto.WriteDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {
	
	private final BoardDAO boardDAO; 
	//그 다음, DAO랑 연결합니다.
	
	public int count() {
		return boardDAO.count();
	}
	
	public List<BoardDTO> boardList(){
		return boardDAO.boardList();
	}

	public List<Map<String, Object>> ajaxList(int pageNo) {
		return boardDAO.ajaxList(pageNo - 1);
	}

	public WriteDTO write(WriteDTO writeDTO) {
		// dto -> jpa entity로 변환작업
		
		return null;
	}
}
