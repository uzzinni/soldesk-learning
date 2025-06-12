package com.poseidon.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.poseidon.dto.BoardDTO;
import com.poseidon.dto.WriteDTO;
import com.poseidon.service.BoardService;
import com.poseidon.service.CustomUserDetails;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class IndexController {
	
	private final BoardService boardService;

	// write 시큐리티가 제어하게 연습하기
	@Secured({"ROLE_USER", "ROLE_ADMIN"}) // SecurityConfig에 추가해준 후 사용 가능
	@GetMapping("/write")
	public String write() {
		return "write";
	}
	
	//글쓰기에서 저장하기 누르면 post로 갑니다.
	@Secured({"ROLE_USER", "ROLE_ADMIN"}) 
	@PostMapping("/write")
	public String write(WriteDTO writeDTO) {
		System.out.println("write Post >>> " + writeDTO);
		
		// 이녀석도 실은 서비스에서 작업해야 합니다.
		CustomUserDetails cud = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		System.out.println("write Post 아이디 >>> " +  cud.getUsername()); //아이디
		
		//아이디를 dto에 저장하기
		writeDTO.setId(cud.getUsername()); //서비스에서 entity로 변경하는 작업
		//서비스로 보내기
		writeDTO = boardService.write(writeDTO);
		System.out.println("저장된 bno는 >>> " + writeDTO.getNo());
		
		
		Collection<? extends GrantedAuthority> auth = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		Iterator<? extends GrantedAuthority> i = auth.iterator();
		GrantedAuthority grant = i.next();
		System.out.println("write Post 권한 >>> "  + grant.getAuthority()); //권한
		
		return "redirect:/board";
	}
	
	
	
	
	@GetMapping({"/", "/index"})
	public String index() {
		return "index";
	}
	
	//이번은 mybatis로 테스트해봅니다.
	@GetMapping("/board")
	public String board(Model model) {
		//시큐리티 사용시 사용자 로그인 정보 얻어오기 연습
		String id;     // 로그인한 아이디
		String role;   // 권한
		
		id = SecurityContextHolder.getContext().getAuthentication().getName();
		System.out.println("로그인한 아이디 >>> " + id);
				
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		System.out.println(authentication.getAuthorities());
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		Iterator<? extends GrantedAuthority> i = authorities.iterator();
		GrantedAuthority auth = i.next();
		role = auth.getAuthority();
		System.out.println("role >>> " + role);
		
		
		
		//데이터베이스에서 데이터를 불러오기 = board테이블에서
		// DB -> xml -> dao -> service -> controller -> html
		int count = boardService.count();
		model.addAttribute("count", count);
		model.addAttribute("test", "테스트"); // 컨트롤러에서 타임리프로 데이터를 보낼 때 사용.
		
		List<BoardDTO> list = boardService.boardList();
		model.addAttribute("list", list);
		
		return "board";
	}
	
	
	//2025-06-04
	//board2, ajax로 json데이터를 받아 화면에 출력하기
	@GetMapping("/board2")
	public String board2() {
		return "board2";
	}
	
	//ajaxBoard
	@PostMapping("/ajaxBoard")
	public @ResponseBody Map<String, Object> ajaxBoard(@RequestParam("pageNo") int pageNo) {
		System.out.println("pageNo >>> " + pageNo);
		
		List<Map<String, Object>> list = boardService.ajaxList(pageNo);
		System.out.println(list);
		// [{board_like=0, board_title=1111111111, board_date=2025-03-26 21:14:43.0, board_no=52, user_no=1}, {}, {} ]
		// { list : [{board_like=0, board }] }
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("list", list);
		result.put("totalCount", 55);
		result.put("pageNo", pageNo);
		
		System.out.println("result >>> "+ result); 
		
		return result;
	}
}



// 2025-05-30 데이터 분석을 위한 텍스트 마이닝 구축 실습
// 시큐리티

/*
 * 
 * 
 *    지금까지 우리가 했던 방식
 *    
 *    사용자 --------> controller -> service -> dao -> ORM(mybatis)--->    ----------> DB
 *    
 *    
 *    디비 암호화											암호화 구간
 *     											------------------------------------------
 *    사용자 --------> controller -> service -> dao -> ORM(mybatis)---> --------------> DB
 * 
 * 
 * 
 *    스프링 시큐리티  -------------------------------------------------------------------
 *    사용자 --------> controller -> service -> dao -> ORM(mybatis)---> --------------> DB
 * */


