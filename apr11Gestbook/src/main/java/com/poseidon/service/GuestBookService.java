package com.poseidon.service;

import com.poseidon.dto.GuestBookDTO;
import com.poseidon.dto.PageRequestDTO;
import com.poseidon.dto.PageResultDTO;
import com.poseidon.entitiy.GuestBook;

public interface GuestBookService {
	//추상 메소드
	PageResultDTO<GuestBookDTO, GuestBook> getList(PageRequestDTO pageRequestDTO);

	
	//새로운 방법 : 인터페이스는 추상메소드만 가질 수 있어요. default 메소드로 작성하면 일반 메소드도 작성할 수 있어요. java8
	// 엔티티를 DTO로 변경하는 메소드
	default GuestBookDTO entityToDto(GuestBook entity) {
		GuestBookDTO dto = GuestBookDTO.builder()
				.gno(entity.getGno())
				.title(entity.getTitle())
				.content(entity.getContent())
				.writer(entity.getWirter())
				.regDate(entity.getRegDate())
				.modDate(entity.getModDate())
				.build();
		return dto;
	}
	
	
	//DTO를 Entity로 변경하는 메소드
	default GuestBook dtoToEntity(GuestBookDTO dto) {
		GuestBook entity = GuestBook.builder()
				.gno(dto.getGno())
				.title(dto.getTitle())
				.content(dto.getContent())
				.wirter(dto.getWriter())
				.build();
		return entity;
	}
}
