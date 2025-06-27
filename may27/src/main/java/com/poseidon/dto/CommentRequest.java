package com.poseidon.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

// 댓글쓰기 유효성 검사용
@Data
public class CommentRequest {
	private int bno;
	
	private int pageNo;
	
	@NotBlank(message = "댓글을 입력하세요.")
	private String comment;
}
// pageNo