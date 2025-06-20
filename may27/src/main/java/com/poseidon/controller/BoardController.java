package com.poseidon.controller;

import java.util.List;

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
import com.poseidon.service.BoardService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class BoardController {

	private final BoardService boardService;

	@GetMapping("/detail")
	public String detail(
			@RequestParam(name = "bno", required = true) int bno,
			@RequestParam(name = "pageNo", required = true) int pageNo, Model model) {
		BoardDTO detail = boardService.detail(bno);
		model.addAttribute("detail", detail);
		
		// 댓글처리
		if (detail.getCommentCount() > 0) {
			List<CommentDTO> commlist = boardService.commentList(bno);
		}
		
		model.addAttribute("pageNo", pageNo);
		return "detail";
	}

	@Secured({ "ROLE_USER" })
	@PostMapping("/liekUp")
	public @ResponseBody void liekUp(@RequestBody int bno) {
		boardService.liekUp(bno);
	}
}