package com.poseidon.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

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
}
