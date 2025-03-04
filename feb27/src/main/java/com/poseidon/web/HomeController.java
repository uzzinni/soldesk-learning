package com.poseidon.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
/*
 * MVC프로젝트
 * Model = 데이터
 * View = 보여지는 것. 화면
 * Controller = 흐름제어
 * */

import com.poseidon.web.dto.BoardDTO;
import com.poseidon.web.service.BoardService;

@Controller  // 컨트롤러
public class HomeController {
	
	@Autowired
	private BoardService boardService;
	
	@RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		return "home"; // home.jsp
	}
	
	//@RequestMapping(value = "/board" , method = RequestMethod.GET)
	@GetMapping("/board")
	public String board(Model model) {
		//모델사용해보기
		String name = "홍길동";
		model.addAttribute("name1", name);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", name);
		map.put("age", 120);
		
		model.addAttribute("title", map);
		
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < 10; i++) {
			list.add(i * 100);
		}
		model.addAttribute("list", list);
		
		//서비스가 작업해 온 결과물을 jsp로 보내기
		List<BoardDTO> board = boardService.boardList();
		model.addAttribute("board", board); //key, value		
		return "board"; // board.jsp
	}
	
	
	@PostMapping("/board")
	public String board2() {
		return "board";
	}
	
	
	
	
	
	
	
	
	
	
	
}
