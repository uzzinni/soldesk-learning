package com.poseidon.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor // 값없이 생성해줘. 
@AllArgsConstructor // 모든 요소를 포함하는 생성자 생성
public class CommentDTO {	
	private int cno;
	private int bno;
	private String ccomment;
	private String name;
	private LocalDateTime cdate;
	private int clike;
	private String ip;			 // ip
	private String id;			 // id
	private int pageNo;
}