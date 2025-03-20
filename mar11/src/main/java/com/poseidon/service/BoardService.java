package com.poseidon.service;

import java.util.HashMap;
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

		public Map<String, Object> commentAJAX(HashMap<String, Object> map) {
			//1. 데이터 저장하기
			boardDAO.commentAJAX(map); // 댓글 저장
			// c_no 가져오기
			System.out.println("service에서 찍어봅니다." + map);
			// 2. 데이터 조회하기 detail
			Map<String, Object> result =  boardDAO.commentDetailAjax(map); // 댓글 하나 불러오기
			return result;
		}
		
		
}
