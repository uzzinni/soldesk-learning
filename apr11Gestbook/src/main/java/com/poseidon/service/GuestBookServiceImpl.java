package com.poseidon.service;

import java.util.function.Function;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.poseidon.dto.GuestBookDTO;
import com.poseidon.dto.PageRequestDTO;
import com.poseidon.dto.PageResultDTO;
import com.poseidon.entitiy.GuestBook;
import com.poseidon.repository.GuestBookRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GuestBookServiceImpl implements GuestBookService {
	
	private final GuestBookRepository repository;

	@Override
	public PageResultDTO<GuestBookDTO, GuestBook> getList(PageRequestDTO pageRequestDTO) {
		Pageable pageable = pageRequestDTO.getPageable(Sort.by("gno").descending());
		Page<GuestBook> result = repository.findAll(pageable);
		Function<GuestBook, GuestBookDTO> fn = (entity -> entityToDto(entity));
		
		return new PageResultDTO<GuestBookDTO, GuestBook>(result, fn);
	}
	
}
