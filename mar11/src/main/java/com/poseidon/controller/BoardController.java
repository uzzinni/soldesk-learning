package com.poseidon.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.poseidon.dto.BoardDTO;
import com.poseidon.service.BoardService;

import jakarta.servlet.http.HttpSession;

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
		
		//2025-03-18 댓글이 있다면 댓글 불러오기
		// 1 : viewboard에 댓글의 수를 저장하고, dto에서 댓글 수를 검사 -> 0이 아니면 댓글 불러오기 -> 모델에 담기
		// System.out.println("dto가 가져온 댓글 수는 : " + detail.getCommentcount());
		
		// 2 : no ->  댓글 테이블에 데이터가 있는지 물어보기 -> 모델에 붙이기
		List<Map<String, Object>> commentList = boardService.commentList(no);
		// if(commentList.size() > 0) { //댓글이 하나라도 있다면 타임리프에게 보내기 , if문 제거합니다.
		model.addAttribute("commentList", commentList);
		// }
		
		//System.out.println(detail);
		return "detail";
	}
	
	// http://localhost/article/011/0004459968
	@GetMapping("/article/{company}/{no}")
	public String article(@PathVariable("company") int com, @PathVariable("no") int no) {
		return "";
	}
	
	// 2025-03-18화요일 : 웹 시스템 구축
	//댓글은 글번호, 댓글내용, 사용자id
	@PostMapping("/comment")
	public String comment(@RequestParam Map<String, Object> map, HttpSession session) {
		//세션 들어오는지 검사
		String id = String.valueOf(session.getAttribute("user_id"));
		//String id = (String) session.getAttribute("user_id");
		map.put("user_id", id);
		System.out.println(map); // {comment=댓글을 씁니다, board_no=45, user_id=poseidon}
		
		int result = boardService.comment(map);
		System.out.println("댓글쓰기 결과 : " + result);
		
		return "redirect:/detail/"+map.get("board_no"); // localhost/detail/45
	} 
	
	
	
	//데이터베이스에 저장하기
	// 1. 테이블 만들기 + fk
	/*
		create table comment( 
		c_no			INT 				NOT NULL auto_increment,
 		c_comment 	varchar(500)	NOT NULL,
 		c_date 		DATETIME 		NOT NULL default current_timestamp,
 		user_no		int(11) 			NOT NULL,
 		c_like		int(11) 			NULL default(0),
 		c_del			int(1) 			NULL default(1),
 		PRIMARY KEY (c_no),
 		CONSTRAINT FK_user FOREIGN KEY (user_no) REFERENCES user (user_no) ON UPDATE NO ACTION ON DELETE NO ACTION
 		);
	 */
	
	//글쓰기 화면 열어주기
	@GetMapping("/write") 
	public String write(HttpSession session) {
		
		if(session.getAttribute("user_id") != null) {			
			return "write";
		} else {
			return "redirect:/login";
		}
	}
	
	//RequestParam : url쿼리, form을 전송받을 때 
	//RequestBody : ajax, json 데이터 추출할 때. Rest API
	@PostMapping("/write")
	public String write(@RequestParam Map<String, Object> map) {
		//System.out.println("글쓰기에서 오는 값 : " + map); // title, content
		
		int result = boardService.write(map);
		//System.out.println("저장완료  : " + map); //title, content, ?, ?
		
		return "redirect:/detail/"+map.get("board_no"); //   http://localhost/detail/46
	}
	
	@PostMapping("/deletePost")
	public String deletePost(@RequestParam Map<String, Object> map) {
		//System.out.println(map);
		int result = boardService.deletePost(map);
		return "redirect:/board";
	}
	
}
