package com.poseidon.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.poseidon.entity.Board;
import com.poseidon.service.BoardService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

// 2025-03-31 웹 시스템 구축 : H2 + hikari + JPA
@Tag(name = "BoardController", description = "보드 관련 기능이 있습니다.")
@Controller
@RequiredArgsConstructor
public class BoardController {

	private final BoardService boardService;
		
	@Operation(summary = "보드 출력", description = "게시판을 출력하는 코드입니다.")
	@GetMapping({"/", "/board"})
	public String board(Model model, @RequestParam(name="pageNo", required = false, defaultValue="1") int pageNo) {
		// 페이지 번호 가져오기 = pageNo                  http://localhost/board?pageNo=1
		//                                                http://localhost/board
		//                                                http://localhost/
		
		System.out.println("클라이언트에서 오는 pageNo : " + pageNo);
		
		// DB에 데이터 불러오기
		// List<Board> List = boardService.findAll();
		Page<Board> List = boardService.list(pageNo);
		// model에 붙이기
		model.addAttribute("list", List);
		model.addAttribute("pageNo", pageNo);
				
		return "board";
	}
	
	
	//insert 예시
	@Operation(summary = "게시글 추가", description = "게시글이 없어서 게시글 10개 생성합니다.")
	@GetMapping("/insert")
	public String insert() {
		boardService.insert();
		return "redirect:/board";
	}
	
	
	//delete 예   http://localhost/delete?bno=1
	@Operation(summary = "게시글 삭제", description = "게시글을 삭제합니다.")
	@GetMapping("/delete")
	public String delete(Board board) {
		//@RequestParam(name="bno", required = true) int bno
		//System.out.println(bno);
		//boardService.delete(bno);
		System.out.println(board.getBno());
		boardService.delete(board);
		return "redirect:/board";
	}
	
	@Operation(summary = "연습 API", description = "게시글을 json으로 변환하여 출력합니다.")
	@ResponseBody
	@GetMapping("/api")
	public List<Board> api(){
		return boardService.findAll();
	}
	
	//jackson
	//gson
	
	// http://localhost/detail?bno=57&pageNo=1
	@Operation(summary = "게시글 상세보기", description = "bno와 pageNo가 들어오면 데이터베이스에서 글 하나 출력합니다.")
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

