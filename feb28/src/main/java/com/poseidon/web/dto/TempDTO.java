package com.poseidon.web.dto;

import lombok.Data;

@Data
public class TempDTO {
	private int no, u_no, like;
	private String title, content, date, u_name, u_id;
}
