package com.poseidon.controller;

import java.util.List;
import java.util.Optional;

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
	
	
	//insert 예시
	@GetMapping("/insert")
	public String insert() {
		boardService.insert();
		return "redirect:/board";
	}
	
	
	//delete 예   http://localhost/delete?bno=1
	@GetMapping("/delete")
	public String delete(Board board) {
		//@RequestParam(name="bno", required = true) int bno
		//System.out.println(bno);
		//boardService.delete(bno);
		System.out.println(board.getBno());
		boardService.delete(board);
		return "redirect:/board";
	}
	
	@ResponseBody
	@GetMapping("/api")
	public List<Board> api(){
		return boardService.findAll();
	}
	
	//jackson
	//gson
	
	// http://localhost/detail?bno=57&pageNo=1
	@GetMapping("/detail")
	public String detail(@RequestParam(name="bno", required = true) int bno
					   , @RequestParam(name="pageNo", required = false, defaultValue = "1") int pageNo
					   , Model model){
		System.out.println(bno);
		System.out.println(pageNo);
		
		Optional<Board> detail = boardService.detail(bno);
		// System.out.println(detail.isEmpty()); // false
		// System.out.println(detail.isPresent()); //true
		
		if(detail.isPresent()) {
			model.addAttribute("detail", detail.get()); 
			return "detail";
		} else {			
			return "error";
		}
		
	}
	
	
}

// 정렬   ctrl + shift + F
// import ctrl + shift + O
/*
 * insert -> save(Entity)
 * update -> save(Entity)
 * select -> findById(키타입), getOne(키타입), findAll()
 * delete -> deleteById(키타입), delete(Entity)
 * 
 * 
 * 
 * Repository
 * 	    ↑
 * CrudRepository
 * 		↑
 * JpaRepository
 * 	 	↑	
 * BoardRepository
 * 
 * 
 * 
 * 
 *      //Optional 랩퍼 클래스
		Board board = new Board();
		//Optional<Board> ob = Optional.empty(); 빈 값 대입
		Optional<Board> ob = Optional.of(board); 실제 값 대입
		System.out.println(ob.isEmpty()); 비어있는지 물어보기
		System.out.println(ob.isPresent()); 값이 있는지 물어보기
		
		Board returnB = ob.get(); 값 가져오기
 */

