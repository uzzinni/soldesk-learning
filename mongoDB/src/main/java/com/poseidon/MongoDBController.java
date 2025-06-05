package com.poseidon;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MongoDBController {
	
	private final MongoService mongoService;

	@GetMapping("/board")
	public String board(Model model) {
		List<Board> list = mongoService.findAll();
		return "board";
	}
	
	@GetMapping("/insert")
	public String insert(Board board) {
		board.setTitle("인서트");
		board.setContent("인서트 연습");
		board.setName("홍길동");
		
		mongoService.save(board);
		
		return "";
	}
}
