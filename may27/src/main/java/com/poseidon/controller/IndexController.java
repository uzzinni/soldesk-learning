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



// 2025-05-30 데이터 분석을 위한 텍스트 마이닝 구축 실습
// 시큐리티

/*
 * 
 * 
 *    지금까지 우리가 했던 방식
 *    
 *    사용자 --------> controller -> service -> dao -> ORM(mybatis)--->    ----------> DB
 *    
 *    
 *    디비 암호화											암호화 구간
 *     											------------------------------------------
 *    사용자 --------> controller -> service -> dao -> ORM(mybatis)---> --------------> DB
 * 
 * 
 * 
 *    스프링 시큐리티  -------------------------------------------------------------------
 *    사용자 --------> controller -> service -> dao -> ORM(mybatis)---> --------------> DB
 * */


