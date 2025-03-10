package com.poseidon.dto;

import lombok.Data;

@Data
public class BoardDTO {
	private int board_no, user_no, board_like;
	private String board_title, board_content, board_date, user_name, user_id;
}
