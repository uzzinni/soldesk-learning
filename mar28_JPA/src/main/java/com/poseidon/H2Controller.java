package com.poseidon;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class H2Controller {
	//H2Service 
	//@Autowired -> 생성자 주입
	private final H2Service h2Service;
	
	@GetMapping({"/", "/board"})
	public ModelAndView board() {
		List<Board> list = h2Service.findAll();
		//System.out.println("list : " + list);
		ModelAndView mv = new ModelAndView("board");
		mv.addObject("list", list);
		return mv;
	}
	
}
