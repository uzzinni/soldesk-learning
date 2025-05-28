package com.poseidon.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.GetMapping;

public class ExceptionHandlingController implements ErrorController {
	//에러페이지 
	@GetMapping(value = "/error")
	public String handleError() {
		return "error";
	}
}
