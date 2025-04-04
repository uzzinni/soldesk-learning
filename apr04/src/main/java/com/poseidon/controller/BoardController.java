package com.poseidon.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poseidon.service.BoardService;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	

	@RequestMapping(value = "/board.do")
	public String board(Model model) throws Exception {;
		List<Map<String, Object>> list = boardService.board();
		
		System.out.println(list);
		model.addAttribute("board", list);
		return "board";
	}

	
}
