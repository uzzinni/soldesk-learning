package com.poseidon.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.poseidon.web.dto.BoardDTO;
import com.poseidon.web.service.BoardService;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;

	// 보드 화면 출력 /board
	@GetMapping("/board")
	public String board(Model model) {
		//값 전달하기 : Model
		String name = "홍길동";
		model.addAttribute("name1", name); //key, value
		//서비스에게 일 시키기
		List<BoardDTO> list = boardService.list();
		model.addAttribute("list", list);
		return "board"; // board.jsp
	}
	
	// 글쓰기 페이지 열어주는 
	@GetMapping("/write")
	public String write() {
		return "write";
	} 
	
	// 값을 가지고 옵니다 : 글쓰기를 눌렀을 때 동작
	@PostMapping("/write1")
	public String write(HttpServletRequest request) {
		String title = request.getParameter("board_title");
		String content = request.getParameter("board_content");
		System.out.println("제목 : " + title);
		System.out.println("본문 : " + content);
		return "redirect:/board";
	}
	
	@PostMapping("/write2")
	public String write(BoardDTO dto) {
		// System.out.println("제목 : " + dto.getBoard_title());
		// System.out.println("본문 : " + dto.getBoard_content());
		// 서비스에게 일 시키기
		boardService.write(dto);
		return "redirect:/board";
	}
	
	@PostMapping("/write")
	public String write(@RequestParam Map<String, Object> map) {
		// System.out.println("제목 : " + map.get("board_title"));
		// System.out.println("본문 : " + map.get("board_content"));
		// System.out.println("map : " + map);
		// 서비스에게 일 시키기
		// boardService.write(dto);
		boardService.write(map);
		return "redirect:/board";
	}
	
	
}
