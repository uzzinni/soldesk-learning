package com.poseidon.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poseidon.dao.BoardDAO;
import com.poseidon.dto.BoardDTO;

@Service
public class BoardService {

		@Autowired
		private BoardDAO boardDAO;

		public List<BoardDTO> boardList() {
			return boardDAO.boardList();
		}

		public BoardDTO detail(int no) {
			BoardDTO dto = new BoardDTO();
			dto.setBoard_no(no);
			return boardDAO.detail(dto);
		}

		public int comment(Map<String, Object> map) {
			return boardDAO.comment(map);
		}

		public List<Map<String, Object>> commentList(int no) {
			return boardDAO.commentList(no);
		}
		
		
}
