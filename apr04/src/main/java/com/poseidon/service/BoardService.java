package com.poseidon.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poseidon.dao.BoardDAO;

@Service
public class BoardService {

	@Autowired
	private BoardDAO boardDAO;

	public List<Map<String, Object>> board(Map<String, Object> map) throws Exception {
		return boardDAO.board(map);
	}

	public int getBoardCount(Map<String, Object> map) {
		return boardDAO.getBoardCount(map);
	}

}
