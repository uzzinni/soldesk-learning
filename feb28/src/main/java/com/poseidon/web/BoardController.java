package com.poseidon.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

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
	public String write1(@SessionAttribute(name = "user_id", required = false) String user_id) {
		System.out.println("어노테이션으로 표시한 세션 : " + user_id);
		
		if(user_id != null) {
			return "write";
		} else {
			return "redirect:/login";
		}
	} 
	
	// 값을 가지고 옵니다 : 글쓰기를 눌렀을 때 동작
	// 아래 조건을 바꿔주세요. 1. 어노테이션으로 작업하는 모양으로 변경해주세요.
	// 세션 검사해주세요.
	@PostMapping("/write1")
	public String write(HttpServletRequest request, @SessionAttribute(name = "user_id", required = false) String user_id) {
		if(user_id != null) {
			String title = request.getParameter("board_title");
			String content = request.getParameter("board_content");
		
			//DTO 만들기
			BoardDTO dto = new BoardDTO();
			dto.setBoard_title(title);
			dto.setBoard_content(content);
			dto.setUser_id(user_id);
			boardService.write1(dto);
			
			return "redirect:/board";
		} else {
			return "redirect:/login";
		}		
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
	
	// ./detail?board_no=32
	@GetMapping("/detail")
	public String detail(Model model,
			@RequestParam(value = "board_no", required = true, defaultValue = "1") int board_no ) {
		//HttpServletRequest request 사용할 때
		//System.out.println(request.getParameter("board_no"));
		//System.out.println(board_no);
		
		// boardService가 일하게 하기
		BoardDTO detail = boardService.detail(board_no);
		model.addAttribute("detail", detail);
		return "detail";
	}
	
	
}
