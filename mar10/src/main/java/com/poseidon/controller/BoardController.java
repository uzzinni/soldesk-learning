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
		model.addAttribute("list", list);
		
		return "board";
	}
	
	@GetMapping("/detail/{no}")
	public String detail(Model model, @PathVariable("no") int no) {
		System.out.println("경로로 잡아온 값 : ");	
		return "detail";
	}
}



// BoardController -> BoardService -> BoardDAO -> mapper
// 
