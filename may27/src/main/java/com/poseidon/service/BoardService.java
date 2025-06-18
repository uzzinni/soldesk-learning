package com.poseidon.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.tomcat.jni.Sockaddr;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.poseidon.dao.BoardDAO;
import com.poseidon.dto.BoardDTO;
import com.poseidon.dto.WriteDTO;
import com.poseidon.entity.Board;
import com.poseidon.entity.Member;
import com.poseidon.repository.JpaboardRepository;
import com.poseidon.repository.JpamemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {
	
	private final BoardDAO boardDAO; 
	//그 다음, DAO랑 연결합니다.
	//JpaboardRepository 만들어서 연결합니다.
	private final JpaboardRepository jpaboardRepository;
	private final JpamemberRepository jpamemberRepository;
	
	// 변환
	private static BoardDTO entityToDTO(Board board) { //엔티티를 DTO로 변환하기
		BoardDTO dto = new BoardDTO();
		dto.setBoard_no(board.getBno());
		dto.setBoard_title(board.getTitle());
		dto.setBoard_content(board.getContent());
		dto.setName(board.getMember().getMname()); // 여기는 수정해야 합니다.
		dto.setBoard_date(board.getDate());
		dto.setBoard_read(board.getBread()); //여기도 수정해야 합니다.
		
		return dto;
	}
	
	public int count() {
		return boardDAO.count();
	}
	
	public List<BoardDTO> boardList(){
		return boardDAO.boardList();
	}

	public List<Map<String, Object>> ajaxList(int pageNo) {
		return boardDAO.ajaxList(pageNo - 1);
	}

	public WriteDTO write(WriteDTO writeDTO) {
		// dto -> jpa entity로 변환작업
		
		//로그인한 사용자 정보 가져오기
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails cuds = (CustomUserDetails) authentication.getPrincipal(); // 로그인한 사용자 정보가 들어있어요. 
		//아래 정보 출력해볼게요
		System.out.println("cuds.getID() >>> " + cuds.getID());
		System.out.println("cuds.getUsername() >>> " + cuds.getUsername());
		System.out.println("cuds.getPassword() >>> " + cuds.getPassword());
		
		//member엔티티 만들기 -> Repository 연결하기
		Optional<Member> member = jpamemberRepository.findByMid(cuds.getID()); // 데이터베이스에 물어봅니다.
				
		Board board = Board.builder()
				.bno(0)
				.title(writeDTO.getTitle())
				.content(writeDTO.getContent())
				.member(member.get()) // Member Entity객체입니다.
				.build();
		jpaboardRepository.save(board);
		
		return null;
	}

	public Page<BoardDTO> list(int pageNo) {
		// 정렬
		List<Sort.Order> sorts = new ArrayList<Sort.Order>();
		sorts.add(Sort.Order.desc("bno"));  //정렬이 더 필요하시다면 sorts.add()로 추가합니다.
		// 페이징
		Pageable pageable = PageRequest.of(pageNo - 1, 10, Sort.by(sorts));
		Page<Board> list = jpaboardRepository.findAll(pageable);
		//System.out.println("결과 >>> " + list.getContent().get(0));
		// 변환 : 엔티티를 DTO로 변환하기 :: 
		List<BoardDTO> dtoList = list.getContent().stream().map(BoardService::entityToDTO).collect(Collectors.toList());
		//위 문장을 아래 for문으로 풀어본 것 입니다. 둘 중 하나만 사용하세요.
		/*
		for (int i = 0; i < list.getContent().size(); i++) {
			Board b = list.getContent().get(i);
			BoardDTO dto = BoardService.entityToDTO(b);
			dtoList.add(dto);
		}
		*/
		
		// list -> page 변환하기                      (변경한 리스트, 페이징 정보, 총 갯수)
		Page<BoardDTO> result = new PageImpl<BoardDTO>(dtoList, pageable, list.getTotalElements());
		return result;
	}
}
