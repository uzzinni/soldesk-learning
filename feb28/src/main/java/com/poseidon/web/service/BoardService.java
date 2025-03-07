package com.poseidon.web.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poseidon.web.dao.BoardDAO;
import com.poseidon.web.dto.BoardDTO;
import com.poseidon.web.util.Util;

@Service
public class BoardService {

	@Autowired
	private BoardDAO boardDAO;
	
	@Autowired
	private Util util;
	
	public List<BoardDTO> list(){
		return boardDAO.list();
	}

	public void write(BoardDTO dto) {
		// 사용자 번호 적어주기 -> 나중에는 세션을 활용합니다.
		dto.setUser_no(1);
		boardDAO.write(dto);
	}

	public void write(Map<String, Object> map) {
		map.put("user_no", 1);
		boardDAO.write(map);
	}

	public void write1(BoardDTO dto) {
		boardDAO.write1(dto);
	}

	public BoardDTO detail(int board_no) {
		//필요 로직이 있다면 적어주세요.
		return boardDAO.detail(board_no);
	}
	
	public int del(BoardDTO dto) {
		return boardDAO.del(dto);
	}

	public BoardDTO update(BoardDTO dto) {
		return boardDAO.update(dto);
	}

	public void update2(BoardDTO dto) {
		//제목에 html <,> 특수기호로 변경해주세요
		dto.setBoard_title(util.htmlTag(dto.getBoard_title()));
		
		//본문 내용에 <,> 특수기호로 변경해주세요
		dto.setBoard_content(util.htmlTag(dto.getBoard_content()));
		
		//본문 내용에 엔터를 <br>로 변경해주세요
		dto.setBoard_content(util.newLine(dto.getBoard_content()));
		
		boardDAO.update2(dto);
	}
}
