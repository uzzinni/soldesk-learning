package com.poseidon.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poseidon.common.CommandMap;
import com.poseidon.service.BoardService;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	

	@RequestMapping(value = "/board.do")
	public String board(Model model, CommandMap map) throws Exception {
		System.out.println("오는 파라미터 : " + map.getMap());
		
		int currentPageNo = 1;
		if(map.getMap().containsKey("currentPageNo")) {
			currentPageNo = Integer.parseInt(String.valueOf(map.get("currentPageNo")));
		}
		if(currentPageNo < 1) {
			currentPageNo = 1;
		}
		
		/* 페이징 작업 */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(currentPageNo);	//현재 페이지 번호
		paginationInfo.setRecordCountPerPage(10);	// 한 페이지에 게시되는 게시물 건수
		paginationInfo.setPageSize(10);	//페이징 리스트 사이즈
		
		int firstRecordIndex = paginationInfo.getFirstRecordIndex();	// limit 0, 10;
		int recordCountPerPage = paginationInfo.getRecordCountPerPage();
		
		map.put("firstIndex", firstRecordIndex);
		map.put("recordCountPerPage", recordCountPerPage);
		
		List<Map<String, Object>> list = boardService.board(map.getMap());
		
		int totalRecordCount = boardService.getBoardCount(map.getMap());	// 전체 글 수 뽑아오기
		paginationInfo.setTotalRecordCount(totalRecordCount);	// 전체 게시물 건수
		
		model.addAttribute("paginationInfo", paginationInfo);
		model.addAttribute("board", list);
		
		return "board";
	}
	
	
}
