package com.poseidon.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardDTO {

	private int board_no;
	private String board_title;
	private String board_content;
	private int user_no;
	private LocalDateTime board_date;
	private int board_like;
	private int board_del;
}
