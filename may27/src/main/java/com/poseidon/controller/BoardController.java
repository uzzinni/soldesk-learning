package com.poseidon.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.json.simple.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.poseidon.dto.BoardDTO;
import com.poseidon.dto.CommentDTO;
import com.poseidon.dto.CommentRequest;
import com.poseidon.dto.WriteDTO;
import com.poseidon.service.BoardService;
import com.poseidon.util.Util;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class BoardController {

	private final BoardService boardService;
	private final Util util;
	
	// 댓글 좋아요 올리기
	@Secured("ROLE_USER")
	@PostMapping("/clike")
	public @ResponseBody String clike(@RequestBody int no) {
		JSONObject json = new JSONObject();
		json.put("result", "1");
		System.out.println(no);
		
		return json.toJSONString();
	}
	
	
	@Secured("ROLE_USER")
	@PostMapping("/updateComm")
	public String updateComm(CommentDTO dto) {
		System.out.println(dto);
		//CommentDTO(cno=39, bno=87, ccomment=1234567, name=null, cdate=null, clike=0, ip=null, id=null, pageNo=5)
		boardService.updateComm(dto);
		//페이지 이동도 만들어 주세요.
		return "redirect:/detail?bno="+dto.getBno()+"&pageNo="+dto.getPageNo();
		
	}
/*
 * 오버라이드 오버라이딩         오버로드 오버로딩
같은 이름의 메소드를 파라미터만 다르게 하여 여러개 만들어 주는 기술 : 
부모에 만들어진 메소드를 자식 클래스에서 재정의해서 사용하는 기술   : @Override
*/	
	
	
	
	@Secured("ROLE_USER")
	@GetMapping("/updateComm")
	public String updateComm(@RequestParam("cno") int cno, @RequestParam("bno") int bno, 
			@RequestParam("pageNo") int pageNo, Model model) {
		//1. 새로운 페이지에 댓글 수정을 만들어주기
		//		1. 데이터베이스에 물어보기 -> Dto -> updatecomm.html 화면에 출력하기
		Optional<CommentDTO> dto = boardService.updateComm(cno);
		if (dto.isPresent()){
			//모델에 담기
			//pageNo도 dto에 담아주겠습니다.
			dto.get().setPageNo(pageNo);
			model.addAttribute("dto", dto.get());
			return "updatecomm";
		} else {
			return "error";
		}
		//2. 상세보기 화면에서 댓글 수정을 하기
		//return "redirect:/detail?bno="+bno+"&pageNo="+pageNo;
	}
	
	
	@Secured("ROLE_USER")
	@GetMapping("/update")
	public String update(@RequestParam("bno") int bno, @RequestParam("pageNo") int pageNo, Model model) {
		Optional<BoardDTO> dto = boardService.detail(bno);
		// 로그인 한 사람의 글인지 검사 필요. 로그인정보값 dto안에 id 비교하기
		//System.out.println(dto.isEmpty());
		//System.out.println(dto.isPresent());
		//System.out.println(dto.get());
		if(dto.isPresent() && (util.getId()).equals(dto.get().getId())){			
			//dto에 값이 있어야 하고, dto에 id와 로그인한 사람의 id가 일치해야 통과함.
			model.addAttribute("update", dto.get());
			//pageNo 모델에 담기
			model.addAttribute("pageNo", pageNo);
			return "update";
		} else {
			return "error";
		}
	}
	
	@Secured("ROLE_USER")
	@PostMapping("/update")
	public String update(WriteDTO dto) {
		// boardService -> entity로 변경 -> repository.update()
		int result = boardService.updatePost(dto);
		return "redirect:/board";
	}
	
	
	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	@PostMapping("/deleteComm")
	public String deleteComm(@RequestParam("cno") int cno, 
			@RequestParam("bno") int bno, @RequestParam("pageNo") int pageNo) {
		//System.out.println("cno    : >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> " + cno);
		//System.out.println("bno    : >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> " + bno);
		//System.out.println("pageNo : >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> " + pageNo);
		int result = boardService.deleteComm(cno);
		if(result == 1) {
			return "redirect:/detail?bno="+bno+"&pageNo="+pageNo; // 이렇게 두 값을 보내려면 두개 더 받아오셔야 합니다.			
		} else {
			return "error";
		}
	}
	
	// post /deletePost 글 삭제하기
	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	@PostMapping("/deletePost")
	public String deletePost(@RequestParam("bno") int bno) {
		//System.out.println("bno >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> " + bno);
		int result = boardService.deletePost(bno);
		//System.out.println("처리결과 >>> " + result);
		if (result == 0){
			return "error";
		}
		return "redirect:/board";
	}
	
	

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

		return "board"; //다시 읽어오기
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
		//System.out.println("pageNo >>> " + pageNo);

		List<Map<String, Object>> list = boardService.ajaxList(pageNo);
		//System.out.println(list);
		// [{board_like=0, board_title=1111111111, board_date=2025-03-26 21:14:43.0,
		// board_no=52, user_no=1}, {}, {} ]
		// { list : [{board_like=0, board }] }
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("list", list);
		result.put("totalCount", 55);
		result.put("pageNo", pageNo);

		//System.out.println("result >>> " + result);

		return result;
	}

	@GetMapping("/detail")
	public String detail(@RequestParam(name = "bno", required = true) int bno,
			@RequestParam(name = "pageNo", required = true) int pageNo, Model model) {
		// model.addAttribute("commentRequest", new CommentRequest());
		Optional<BoardDTO> detail = boardService.detail(bno); //여기 수정하고 시작하겠습니다.
		if(detail.isPresent()) {
			model.addAttribute("detail", detail.get());// bno=0이라면 여기로 들어오지 못합니다.
			// 댓글처리
			if (detail.get().getCommentCount() > 0) {
				List<CommentDTO> commlist = boardService.commentList(bno);
				model.addAttribute("commlist", commlist);
			}
			model.addAttribute("pageNo", pageNo);
			return "detail";			
		} else {
			return "error";
		}
	}

	/*
	 * @Secured({ "ROLE_USER" })
	 * @PostMapping("/liekUp")
	 * public @ResponseBody void liekUp(@RequestBody int bno) {
	 * boardService.liekUp(bno);
	 * }
	 */

	// 댓글 쓰기
	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	@PostMapping("/comment")
	public String comment(CommentRequest commentRequest) {
		//System.out.println(commentRequest);
		// 데이터베이스에 저장하는 작업
		CommentDTO dto = boardService.commentWrite(commentRequest);
		if (dto.getCno() < 0) { // 임시
			return "error";
		}
		return "redirect:/detail?bno=" + commentRequest.getBno() + "&pageNo="+commentRequest.getPageNo();
	}

}