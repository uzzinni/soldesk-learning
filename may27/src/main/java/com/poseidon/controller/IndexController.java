package com.poseidon.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.poseidon.dto.BoardDTO;
import com.poseidon.service.BoardService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class IndexController {
	
	private final BoardService boardService;

	@GetMapping({"/", "/index"})
	public String index() {
		return "index";
	}
	
	//이번은 mybatis로 테스트해봅니다.
	@GetMapping("/board")
	public String board(Model model) {
		//데이터베이스에서 데이터를 불러오기 = board테이블에서
		// DB -> xml -> dao -> service -> controller -> html
		int count = boardService.count();
		model.addAttribute("count", count);
		model.addAttribute("test", "테스트"); // 컨트롤러에서 타임리프로 데이터를 보낼 때 사용.
		
		List<BoardDTO> list = boardService.boardList();
		model.addAttribute("list", list);
		
		return "board";
	}
}