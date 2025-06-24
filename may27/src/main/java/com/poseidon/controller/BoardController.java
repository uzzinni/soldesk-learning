package com.poseidon.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.poseidon.dto.BoardDTO;
import com.poseidon.dto.CommentDTO;
import com.poseidon.dto.CommentRequest;
import com.poseidon.dto.WriteDTO;
import com.poseidon.service.BoardService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class BoardController {

	private final BoardService boardService;

	// write 시큐리티가 제어하게 연습하기
	@Secured({ "ROLE_USER", "ROLE_ADMIN" }) // SecurityConfig에 추가해준 후 사용 가능
	@GetMapping("/write")
	public String write() {
		return "write";
	}

	// 글쓰기에서 저장하기 누르면 post로 갑니다.
	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	@PostMapping("/write")
	public String write(WriteDTO writeDTO) {
		// 서비스와 연결하기
		boardService.write(writeDTO);

		return "redirect:/board";
	}

	// 이번은 mybatis로 테스트해봅니다.
	@GetMapping("/board") // http://locahost/board 이런 호출도 허용해주기 위해서
	public String board(@RequestParam(value = "pageNo", required = false, defaultValue = "1") int pageNo, Model model) {
		// 시큐리티 사용시 사용자 로그인 정보 얻어오기 연습
		/*
		 * String id; // 로그인한 아이디 String role; // 권한
		 * 
		 * id = SecurityContextHolder.getContext().getAuthentication().getName();
		 * //System.out.println("로그인한 아이디 >>> " + id);
		 * 
		 * Authentication authentication =
		 * SecurityContextHolder.getContext().getAuthentication();
		 * //System.out.println(authentication.getAuthorities()); Collection<? extends
		 * GrantedAuthority> authorities = authentication.getAuthorities(); Iterator<?
		 * extends GrantedAuthority> i = authorities.iterator(); GrantedAuthority auth =
		 * i.next(); role = auth.getAuthority();
		 */
		// System.out.println("role >>> " + role);

		// 데이터베이스에서 데이터를 불러오기 = board테이블에서
		// DB -> xml -> dao -> service -> controller -> html
		// mybatis -> jpa로 변경합니다.
		/*
		 * int count = boardService.count(); model.addAttribute("count", count);
		 * model.addAttribute("test", "테스트"); // 컨트롤러에서 타임리프로 데이터를 보낼 때 사용.
		 * 
		 * List<BoardDTO> list = boardService.boardList(); model.addAttribute("list",
		 * list);
		 */
		// jpa 사용하기 // jpa 페이징
		Page<BoardDTO> list = boardService.list(pageNo);
		model.addAttribute("list", list); // jpa paging을 사용합니다.
		model.addAttribute("pageNo", pageNo); // 현 페이지 번호를 다시 보냅니다.

		return "board";
	}

	// 2025-06-04
	// board2, ajax로 json데이터를 받아 화면에 출력하기
	@GetMapping("/board2")
	public String board2() {
		return "board2";
	}

	// ajaxBoard
	@PostMapping("/ajaxBoard")
	public @ResponseBody Map<String, Object> ajaxBoard(@RequestParam("pageNo") int pageNo) {
		System.out.println("pageNo >>> " + pageNo);

		List<Map<String, Object>> list = boardService.ajaxList(pageNo);
		System.out.println(list);
		// [{board_like=0, board_title=1111111111, board_date=2025-03-26 21:14:43.0,
		// board_no=52, user_no=1}, {}, {} ]
		// { list : [{board_like=0, board }] }
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("list", list);
		result.put("totalCount", 55);
		result.put("pageNo", pageNo);

		System.out.println("result >>> " + result);

		return result;
	}

	@GetMapping("/detail")
	public String detail(@RequestParam(name = "bno", required = true) int bno,
			@RequestParam(name = "pageNo", required = true) int pageNo, Model model) {
		// model.addAttribute("commentRequest", new CommentRequest());
		BoardDTO detail = boardService.detail(bno);
		model.addAttribute("detail", detail);

		// 댓글처리
		if (detail.getCommentCount() > 0) {
			List<CommentDTO> commlist = boardService.commentList(bno);
			model.addAttribute("commlist", commlist);
		}

		model.addAttribute("pageNo", pageNo);
		return "detail";
	}

	/*
	 * @Secured({ "ROLE_USER" })
	 * 
	 * @PostMapping("/liekUp") public @ResponseBody void liekUp(@RequestBody int
	 * bno) { boardService.liekUp(bno); }
	 */

	// 댓글 쓰기
	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	@PostMapping("/comment")
	public String comment(CommentRequest commentRequest) {
		System.out.println(commentRequest);
		// 데이터베이스에 저장하는 작업
		CommentDTO dto = boardService.commentWrite(commentRequest);
		if (dto.getCno() < 0) { // 임시
			return "error";
		}
		return "redirect:/detail?bno=" + commentRequest.getBno() + "&pageNo=1";
	}

}