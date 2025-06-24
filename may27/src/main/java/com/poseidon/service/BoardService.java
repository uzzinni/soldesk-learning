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
import com.poseidon.dto.CommentRequest;
import com.poseidon.dto.WriteDTO;
import com.poseidon.entity.Board;
import com.poseidon.entity.Comment;
import com.poseidon.entity.Member;
import com.poseidon.repository.JpaCommentRepository;
import com.poseidon.repository.JpaboardRepository;
import com.poseidon.repository.JpamemberRepository;
import com.poseidon.util.Util;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {
	
	private final BoardDAO boardDAO;
	private final Util util;
	
	//그 다음, DAO랑 연결합니다.
	//JpaboardRepository 만들어서 연결합니다.
	private final JpaboardRepository jpaboardRepository;
	private final JpamemberRepository jpamemberRepository;
	private final JpaCommentRepository jpaCommentRepository;
	
	
	public static CommentDTO entityToCommentDTO(Comment comm) {
		CommentDTO dto = CommentDTO.builder()
				.cno(comm.getCno())
				.bno(comm.getBoard().getBno())
				.ccomment(comm.getCcomment())
				.cdate(comm.getCdate())
				.name(comm.getMember().getMname())
				.clike(comm.getClike())
				.build();
		return dto;
	}

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
		//System.out.println("cuds.getID() >>> " + cuds.getID());
		//System.out.println("cuds.getUsername() >>> " + cuds.getUsername());
		//System.out.println("cuds.getPassword() >>> " + cuds.getPassword());
		
		//member엔티티 만들기 -> Repository 연결하기
		Optional<Member> member = jpamemberRepository.findByMid(cuds.getID()); // 데이터베이스에 물어봅니다. 고쳐야 합니다.
				
		Board board = Board.builder()
				.bno(0)
				.title(writeDTO.getTitle())
				.content(writeDTO.getContent())
				.member(member.get()) // Member Entity객체입니다.
				.build();
		jpaboardRepository.save(board);
		
		return null; // 고쳐야 합니다.
	}
	
	// Transactional : 스프링 프레임워크 트랜젝션을 사용하겠다고 선언하는 어노테이션
	// 한 자원에 대해서 여러 작업을 묶어서 처리하기.
	// 여러 메소드를 만들어 하나의 @Transactional 하위에서 작업하면 모두 하나의 묶음으로 처리
	// 메소드 중 한 메소드가 문제 발생시, 전체 취소(롤백) 가능
	
	@Transactional
	public BoardDTO detail(int bno) {
		Optional<Board> board = jpaboardRepository.findByBno(bno);
		if(board.isPresent()) {
			Board detail = board.get();
			detail.setBread(detail.getBread() + 1);
			// jpaboardRepository.save(detail); // 읽음 수 올리기
		}
		BoardDTO dto = entityToDto(board.get());
		return dto;
	}


	
	/*
	 * public void liekUp(int bno) { Optional<Board> detail =
	 * jpaboardRepository.findByBno(bno); if(detail.isPresent()) { Board board =
	 * detail.get(); board.setBread(detail.get().getBread() + 1); //
	 * jpaboardRepository.save(board); } }
	 */

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
		//Optional<Board> board = jpaboardRepository.findByBno(bno); //데이터베이스 접근하지 않게 하려면?		
		//List<Comment> commentList = jpaCommentRepository.findByBoardOrderByCnoDesc(board.get());
		List<Comment> commentList = jpaCommentRepository.findByBoardOrderByCnoDesc(Board.builder().bno(bno).build());
		List<CommentDTO> commList = commentList.stream().map(BoardService::entityToCommentDTO).collect(Collectors.toList());
		return commList;
	}

	public CommentDTO commentWrite(CommentRequest commentRequest) {
		// bno, comment ==== 사용자 ID가 오게
		//String id = util.getId();
		//System.out.println("getId() : " + id);
		//Optional<Member> member = jpamemberRepository.findByMid(id);
		//System.out.println(member);
		
		Member member = util.getMember();
		//System.out.println(member);
		
		//댓글 Entity만들기
		Comment comment = new Comment();
		// 엔터처리 해주세요. \n\r -> <br>
		String reText = commentRequest.getComment().replaceAll("\r\n", "<br>");
		comment.setCcomment(reText); 
		comment.setMember(member);
		Board board = Board.builder().bno(commentRequest.getBno()).build(); // 여기에 글 번호만 저장했습니다.
		comment.setBoard(board);
		
		//데이터베이스에 저장하기
		comment = jpaCommentRepository.save(comment);
		System.out.println("save 후 출력 " + comment);
		//CommentDTO(cno=5, bno=135, ccomment=111, name=애플, cdate=2025-06-24T14:36:39.954998, clike=0)
		CommentDTO dto = CommentDTO.builder().build(); 
		if(comment.getCno() > 0) {
			dto.setCno(comment.getCno());
		}
		return dto;
	}

}
