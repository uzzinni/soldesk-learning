package com.poseidon.controller;

import java.time.LocalDateTime;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.poseidon.dto.GuestBookDTO;
import com.poseidon.dto.PageRequestDTO;
import com.poseidon.dto.PageResultDTO;
import com.poseidon.entitiy.GuestBook;
import com.poseidon.service.GuestBookService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/guestbook")
@Controller
@RequiredArgsConstructor // 생성자 주입
public class GuestBookController {
	
	//서비스 연결하기
	private final GuestBookService service; //final 선언

	// http://localhost/
	// http://localhost/list
	
	// http://localhost/guestbook/
	// http://localhost/guestbook/list
	@GetMapping("/")
	public String index() {
		return "redirect:/guestbook/list";
	}
	
	//리스트 형태 
	@GetMapping("/list")
	public String list(PageRequestDTO pageRequestDTO, Model model) {
		PageResultDTO<GuestBookDTO, GuestBook> pageable = service.getList(pageRequestDTO);
		model.addAttribute("result", pageable);
		return "list";
	}
	
	//http://localhost/guestbook/read?gno=200&page=1
	@GetMapping("/read")
	public String read(@RequestParam(name = "gno") int gno, @ModelAttribute("requestDTO") PageRequestDTO pageRequestDTO, Model model) {
		System.out.println("gno : " + gno);
		System.out.println("requestDTO : " + pageRequestDTO);
		return "read";
	}
	
	//@Scheduled(fixedDelay = 2000) // 2초 후 실행
	@Scheduled(cron="0 */10 * * * *", zone = "Asia/Seoul")
	public void print2Second() {
		String time = LocalDateTime.now().toString();
		System.out.println("daytime : " + time);
	}
	// 평일 17:40~22:40까지 40분 마다 호출
	@Scheduled(cron="0 40 17-22 * * 1-5", zone = "Asia/Seoul")
	public void print3() {
		System.out.println("수업시작입니다");
	}
	
	
}
