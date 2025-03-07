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
import org.springframework.web.bind.annotation.SessionAttribute;

import com.poseidon.web.dto.BoardDTO;
import com.poseidon.web.dto.TempDTO;
import com.poseidon.web.service.BoardService;
import com.poseidon.web.util.Util;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@Autowired
	private Util util;

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
		//HttpSession session = request.getSession();
		//if(session.getAttribute("user_id") != null) { //로그인 즉, user_id가 있는지 검사하기
		//	return "write";			
		//} else {
		//	return "redirect:/login";
		//}
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
	public String write(HttpServletRequest request,
						@SessionAttribute(name = "user_id", required = false) String user_id) {
		
		if(user_id != null) {
			String title = request.getParameter("board_title");
			String content = request.getParameter("board_content");
			//특수기호 < &lt; > &gt;
			title = util.htmlTag(title);
			//줄바꿈 처리
			content = util.htmlTag(content);
			
			//DTO 만들기
			BoardDTO dto = new BoardDTO();
			dto.setBoard_title(util.htmlTag(title));
			dto.setBoard_content(util.newLine(content));
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
	
	// http://localhost:8080/web/detail?board_no=32
	// http://localhost:8080/web/detail
	@GetMapping("/detail")
	public String detail(Model model, @RequestParam("board_no") int board_no) {
	    BoardDTO dto = new BoardDTO();
	    dto.setBoard_no(board_no);  // ✅ DTO 객체에 board_no 값 저장
	    BoardDTO detail = boardService.detail(dto);  // ✅ BoardDTO 객체를 넘김
	    model.addAttribute("detail", detail);
	    return "detail";
	}
	
	//post 방식 /del
	@PostMapping("/del")
	public String delete(
			@RequestParam(name = "board_no", required = true) int board_no,  
			@SessionAttribute(name = "user_id", required = false) String user_id) {
		//BoardDTO dto를 파라미터로 받아서 처리하는 방법
		//System.out.println(dto.getBoard_no());
		if(user_id != null) {
			
			// BoardDTO에 담기
			BoardDTO dto = new BoardDTO();
			dto.setBoard_no(board_no);
			dto.setUser_id(user_id);
			//Service에게 일 시키기 
			int result = boardService.del(dto);
			if(result == 1) {
				return "redirect:/board";
				
			} else {
				return "error";				
			}
			
		} else {
			//로그인 하지 않았을 때
			return "redirect:/login";
		}
	}

	//글 수정하기 http://localhost:8080/update?board_no=41
	@GetMapping("/update")
	public String update(Model model, 
			@RequestParam(name = "board_no", required = true) int board_no,  
			@SessionAttribute(name = "user_id", required = false) String user_id) {
		if(user_id != null) {
			//DTO에 값 넣기
			BoardDTO dto = new BoardDTO();
			dto.setBoard_no(board_no);
			dto.setUser_id(user_id);
			
			BoardDTO result = boardService.update(dto);
			
			// content에 있는 <br>을 다시 원래대로 복구하기
			result.setBoard_content(util.renewLine(result.getBoard_content()));
			
			//정확하게 왔다면 model에 붙이기
			model.addAttribute("update", result);
						
			return "update";
		} else {			
			return "redirect:/login"; //로그인 값이 없을때
		}
	}

	// 2025-03-07 웹페이지 화면 구축
	//사용자가 글 수정을 완료하고 저장하기를 눌렀을 때  /update post 
	@PostMapping("/update")
	public String update(BoardDTO dto, @SessionAttribute(name = "user_id", required = false) String user_id) {
		if(user_id != null) {
			dto.setUser_id(user_id);
			boardService.update2(dto); //이름 중복 : 진짜 수정된 값 저장하기
			return "redirect:/detail?board_no=" + dto.getBoard_no();
		} else {			
			return "redirect:/login"; 
		}
	}
	
	//ResultMap 사용해보기
	//TempDTO 만들어서 사용하겠습니다.
	@GetMapping("/temp")
	public String temp(Model model,
         @RequestParam(value = "board_no", required = true, defaultValue = "1") int board_no) {
		// 1. TempDTO 만들기
		TempDTO dto = new TempDTO();
		dto.setNo(board_no);
		
		TempDTO result = boardService.temp(dto);
		model.addAttribute("detail", result);
		return "temp";
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
}