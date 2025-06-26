package com.poseidon.service;

import org.springframework.stereotype.Service;

import com.poseidon.dto.BoardDTO;
import com.poseidon.dto.CommentDTO;
import com.poseidon.entity.Board;
import com.poseidon.entity.Comment;
import com.poseidon.util.Util;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ConvertService {
	private final Util util;
	
	public CommentDTO entityToCommentDTO(Comment comm) {
		CommentDTO dto = CommentDTO.builder()
				.cno(comm.getCno())
				.bno(comm.getBoard().getBno())
				.ccomment(comm.getCcomment())
				.cdate(comm.getCdate())
				.name(comm.getMember().getMname())
				.clike(comm.getClike())
				.ip(util.ipMasking(comm.getIp()))
				.id(comm.getMember().getMid())  // id 추가
				.build();		
		return dto;
	}

	public BoardDTO entityToDto(Board board) {
		BoardDTO dto = new BoardDTO();
		dto.setBoard_no(board.getBno());
		dto.setBoard_title(board.getTitle());
		dto.setBoard_content(board.getContent());
		dto.setName(board.getMember().getMname());  // 사용자 정보에서 name가져오기
		dto.setBoard_date(board.getDate());
		dto.setBoard_read(board.getBread());
		dto.setIp(util.ipMasking(board.getIp()));
		dto.setCommentCount(board.getCommentList().size());
		dto.setId(board.getMember().getMid());  // 사용자 정보에서 id가져오기
		return dto;
	}
}