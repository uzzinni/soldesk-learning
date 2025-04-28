package com.poseidon.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;

@Component
public class Util {

	// 오늘 날짜 뽑기 -> 2025-04-25
	public String today() {
		return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	}

}
