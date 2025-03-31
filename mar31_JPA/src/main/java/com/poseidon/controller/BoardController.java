package com.poseidon.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.poseidon.entity.Board;
import com.poseidon.service.BoardService;

import lombok.RequiredArgsConstructor;

// 2025-03-31 웹 시스템 구축 : H2 + hikari + JPA
@Controller
@RequiredArgsConstructor
public class BoardController {
	
	private final BoardService boardService;
	
	@GetMapping({"/", "/board"})
	public String board(Model model) {
		// DB에 데이터 불러오기
		List<Board> List = boardService.findAll();
		// model에 붙이기
		model.addAttribute("list", List);
		
		return "board";
	}
	
	// insert 예시
	@GetMapping("/insert")
	public String insert() {
		boardService.insert();
		return "redirect:/board";
	}
	
	//delete 예
	@GetMapping("/delete") 
	public String delete(Board board) {
		System.out.println(board.getBno());
		boardService.delete(board);
		return "redirect:/board";
	}
	
	@ResponseBody
	@GetMapping("/api")
	public List<Board> api() {
		return boardService.findAll();
	}
	
	
	
	
	
}
