package com.poseidon.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	
	//2025-03-11 웹페이지 화면 구축
	//     http://localhost/detail?board_no=36
	//     http://localhost/detail/36
	@GetMapping("/detail/{no}")
	public String detail(Model model, @PathVariable("no") int no) {
		//DB에서 값 읽어오기
		BoardDTO detail = boardService.detail(no);
		
		//model에 붙이기 
		model.addAttribute("detail", detail);
		//System.out.println(detail);
		return "detail";
	}
	
	// http://localhost/article/011/0004459968
	@GetMapping("/article/{company}/{no}")
	public String article(@PathVariable("company") int com, @PathVariable("no") int no) {
		return "";
	}
	
	//댓글은 글번호, 댓글내용, 사용자id
	@PostMapping("/comment")
	public String comment(@RequestParam Map<String, Object> map) {
		System.out.println(map); // {comment=데이터가 가나요?, board_no=45}
		//데이터베이스에 저장하기
		// 1. 테이블 만들기 + fk
		/*
		 * create  table comment( 
		 * 		c_no		int NOT NULL auto_increment,
		 * 		c_comment 	varchar(500) NOT NULL,
		 * 		c_date 		datetime NOT NULL default current_timestamp,
		 * 		user_no		int(11) NOT NULL,
		 * 		c_like		int(11) NULL default(0),
		 * 		c_del		int(1) NULL default(1),
		 * 		PRIMARY KEY (c_no),
		 * 		CONSTRAINT FK_user FOREIGN KEY (user_no) REFERENCES user (user_no) ON UPDATE NO ACTION ON DELETE NO ACTION
		 * );
		 */
		return "redirect:/detail/"+map.get("board_no"); // localhost/detail/45
	} 
	
}
