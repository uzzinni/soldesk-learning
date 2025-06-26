package com.poseidon.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommentDTO {
	private int cno;
	private int bno;
	private String ccomment;
	private String name;
	private LocalDateTime cdate;
	private int clike;
	private String ip;			 // ip
	private String id;			 // id
}