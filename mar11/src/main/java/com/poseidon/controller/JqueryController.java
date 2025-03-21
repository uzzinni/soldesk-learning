package com.poseidon.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.poseidon.service.BoardService;

import jakarta.servlet.http.HttpSession;

@Controller
public class JqueryController {
	
	@Autowired
	private BoardService boardService;

	//localhost/jq1
	//localhost/jq2
	@GetMapping("/jq{no}")
	public String jquery(@PathVariable("no") int no) {
		return "jquery"+no;
		//jquery1.html
		//jquery2.html
	}
	
	//ajax용 /ajaxEx?test=test
	@ResponseBody
	@GetMapping("/ajaxEx")
	public String ajaxEx(@RequestParam("test") String test) {
		//System.out.println("test : " + test);
		return "Ok!";
	}
	
	@PostMapping("/ajaxEx")
	public @ResponseBody Map<String, Object> ajaxEx(HttpSession session, @RequestBody HashMap<String, Object> map) {
		//if검사
		//map에 세션을 추가해주세요. -> user_id
		map.put("user_id", session.getAttribute("user_id"));
		//System.out.println("map :" + map);// board_no, comment, user_id
		
		//int result = boardService.comment(map);
		Map<String, Object> result = boardService.commentAJAX(map);
		//System.out.println("result : " + result); // map
		
		return result; // json을 출력합니다.
	}
	
	
}
