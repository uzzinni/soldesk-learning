package com.poseidon.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class BoardDTO {
	private int board_no, user_no, board_like;
	private String board_title, board_content, user_name, user_id;
	private LocalDateTime board_date;
}
