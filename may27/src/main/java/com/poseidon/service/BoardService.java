package com.poseidon.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

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
import com.poseidon.dto.CommentDTO;
import com.poseidon.dto.WriteDTO;
import com.poseidon.entity.Board;
import com.poseidon.entity.Comment;
import com.poseidon.entity.Member;
import com.poseidon.repository.JpaCommentRepository;
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
	private final JpaCommentRepository jpaCommentRepository;

	public static BoardDTO entityToDto(Board board) {
		BoardDTO dto = new BoardDTO();
		dto.setBoard_no(board.getBno());
		dto.setBoard_title(board.getTitle());
		dto.setBoard_content(board.getContent());
		dto.setName(board.getMember().getMname());
		dto.setBoard_date(board.getDate());
		dto.setBoard_read(board.getBread());
		dto.setCommentCount(board.getCommentList().size());
		return dto;
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

	public BoardDTO detail(int bno) {
		Optional<Board> board = jpaboardRepository.findByBno(bno);
		if(board.isPresent()) {
			Board detail = board.get();
			detail.setBread(detail.getBread() + 1);
			jpaboardRepository.save(detail); // 읽음 수 올리기
		}
		BoardDTO dto = entityToDto(board.get());
		return dto;
	}

	public void liekUp(int bno) {
		Optional<Board> detail = jpaboardRepository.findByBno(bno);
		if(detail.isPresent()) {
			Board board = detail.get(); 
			board.setBread(detail.get().getBread() + 1);
			jpaboardRepository.save(board);
		}
	}

	public Page<BoardDTO> list(int pageNo) {
		List<Sort.Order> sorts = new ArrayList<>();
		sorts.add(Sort.Order.desc("bno"));
		Pageable pageable = PageRequest.of(pageNo-1, 10, Sort.by(sorts)); //옵션
		Page<Board> list = jpaboardRepository.findAll(pageable); // 실제 데이터베이스에서 읽어오는 작업
		
		List<BoardDTO> dtoList = list.getContent().stream().map(BoardService::entityToDto).collect(Collectors.toList());
		
		return new PageImpl<>(dtoList, pageable, list.getTotalElements());
	}


	public List<CommentDTO> commentList(int bno) {
		// bno를 가지고 있는 댓글 리스트 뽑기
		// JpaCommentRepository 만들어주기
		//Board board = Board.builder().bno(bno).build();
		Optional<Board> board = jpaboardRepository.findByBno(bno);
		
		List<Comment> commentList = board.get().getCommentList();
		//System.out.println(commentList.size());
		return null;
	}

}
