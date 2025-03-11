package com.poseidon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.poseidon.dto.BoardDTO;
import com.poseidon.service.BoardService;

@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	@GetMapping("/board")
	public String board(Model model) {
		
		List<BoardDTO> list = boardService.boardList();
		// System.out.println(list);
		model.addAttribute("list", list);
		return "board";
	}
	
	//2025-03-11 웹 페이지 화면 구축
	//http://localhost/detail?board_no=36
	
	@GetMapping("/detail/{no}")
	public String detail(Model model, @PathVariable("no") int no) {
		System.out.println("detail : " + no);
		//DB에서 값 읽어오기
		
		BoardDTO detail = boardService.detail(no);
		
		//model에 붙이기
		model.addAttribute("detail", detail);
		System.out.println(detail);
		return "detail";
	}
	
	//http://localhost/article/011/0004459968
	
	
}
