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

	private final JpaboardRepository jpaboardRepository;
	private final JpamemberRepository jpamemberRepository;
	private final JpaCommentRepository jpaCommentRepository;
	private final ConvertService convertService;
	// 여기 있던 static 메소드를 위 convertService로 이사.

	public List<Map<String, Object>> ajaxList(int pageNo) {
		return boardDAO.ajaxList(pageNo - 1);
	}

	public WriteDTO write(WriteDTO writeDTO) {
		// 로그인한 사용자 정보 가져오기
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails cuds = (CustomUserDetails) authentication.getPrincipal(); // 로그인한 사용자 정보가 들어있어요.
		// 아래 정보 출력해볼게요
		// System.out.println("cuds.getID() >>> " + cuds.getID());
		// System.out.println("cuds.getUsername() >>> " + cuds.getUsername());
		// System.out.println("cuds.getPassword() >>> " + cuds.getPassword());

		// member엔티티 만들기 -> Repository 연결하기
		Optional<Member> member = jpamemberRepository.findByMid(cuds.getID()); // 데이터베이스에 물어봅니다. 고쳐야 합니다.

		Board board = Board.builder().bno(0).title(writeDTO.getTitle()).content(writeDTO.getContent())
				.member(member.get()) // Member Entity객체입니다.
				.ip(util.getIP()) // 아이피 추가
				.build();
		jpaboardRepository.save(board);

		return null; // 고쳐야 합니다.
	}

	// Transactional : 스프링 프레임워크 트랜젝션을 사용하겠다고 선언하는 어노테이션
	// 한 자원에 대해서 여러 작업을 묶어서 처리하기.
	// 여러 메소드를 만들어 하나의 @Transactional 하위에서 작업하면 모두 하나의 묶음으로 처리
	// 메소드 중 한 메소드가 문제 발생시, 전체 취소(롤백) 가능

	@Transactional
	public Optional<BoardDTO> detail(int bno) {
		Optional<Board> board = jpaboardRepository.findByBno(bno);
		Optional<BoardDTO> dto = Optional.ofNullable(null); // null값 대입
		// System.out.println("서비스에서 찍어봄 " + dto.isEmpty());
		// System.out.println("서비스에서 찍어봄 " + dto.isPresent());

		if (board.isPresent()) {
			Board detail = board.get();
			detail.setBread(detail.getBread() + 1);
			// jpaboardRepository.save(detail); // 읽음 수 올리기
			BoardDTO convertDto = convertService.entityToDto(board.get());
			dto = Optional.of(convertDto); // 실제 값 대입
		}
		return dto;
	}
	//jpa save 동작방식
	/*
	1. 엔티티에 @Id가 없으면(기본값이거나 null일경우) insert로 동작합니다.

	2. 엔티티에 @Id가 있으면 UPDATE로 동작합니다.
		@Id로 SELECT로 질의를 던집니다. -> 해당 데이터가 있으면(실제 데이터가 있으면) UPDATE로 동작합니다.(marge)
		 								-> 해당 데이터가 없으면 INSERT로 동작합니다.
		 								
	=================================================================
	@transactional 어노테이션이 있으면 영속성 관리를 시작함.
	로드된 값을 변경하게 되면 기존의 값(스넵샷)과 비교하여 메소드 종료시 변경값을 반영함.
	dirty-checking (변경을 감지하여 UPDATE 수행합니다.)
	
	결론 : save로 INSERT/UPDATE를 수행함.
	
	명령어			기능
	--------------------------------------------------------------------------------
	save(엔티티)  	엔티티 저장/수정, @Id값의 유무로 결정함.
	delete(엔티티)	엔티티로 삭제.
	count()			엔티티 수
	existsById(id)	해당 id가 있는지 boolean값 반환
	findAll()		모든 엔티티 조회하기
	findById(id)	ID기준으로 조회하기, 결과는 Optional<엔티티>로 출력
	findTop10ByBnoOrderByBnoDesc
	@Query			쿼리문 작성하기
					@Query("SELECT * FROM jpaboard ORDER BY bno DESC LIMIT 0, 10")
					List<Member> findTop10ByBnoOrderByBnoDesc
					
					@Query("SELECT * FROM jpaboard WHERE mname = :mname")
					List<Member> findByMname(@param("mname") String mname)
					
	 */

	

	/*
	 * public void liekUp(int bno) { Optional<Board> detail =
	 * jpaboardRepository.findByBno(bno); if(detail.isPresent()) { Board board =
	 * detail.get(); board.setBread(detail.get().getBread() + 1); //
	 * jpaboardRepository.save(board); } }
	 */

	public Page<BoardDTO> list(int pageNo) {
		List<Sort.Order> sorts = new ArrayList<>();
		sorts.add(Sort.Order.desc("bno"));
		Pageable pageable = PageRequest.of(pageNo - 1, 10, Sort.by(sorts)); // 옵션
		Page<Board> list = jpaboardRepository.findAll(pageable); // 실제 데이터베이스에서 읽어오는 작업
		List<BoardDTO> dtoList = list.getContent().stream().map(convertService::entityToDto)
				.collect(Collectors.toList());
		return new PageImpl<>(dtoList, pageable, list.getTotalElements());
	}

	public List<CommentDTO> commentList(int bno) {
		// bno를 가지고 있는 댓글 리스트 뽑기
		// JpaCommentRepository 만들어주기
		// Board board = Board.builder().bno(bno).build();
		// Optional<Board> board = jpaboardRepository.findByBno(bno); //데이터베이스 접근하지 않게
		// 하려면?
		// List<Comment> commentList =
		// jpaCommentRepository.findByBoardOrderByCnoDesc(board.get());
		List<Comment> commentList = jpaCommentRepository.findByBoardOrderByCnoDesc(Board.builder().bno(bno).build());
		List<CommentDTO> commList = commentList.stream().map(convertService::entityToCommentDTO)
				.collect(Collectors.toList());
		return commList;
	}

	public CommentDTO commentWrite(CommentRequest commentRequest) {
		// String id = util.getId();
		// System.out.println("getId() : " + id);
		// Optional<Member> member = jpamemberRepository.findByMid(id);
		// System.out.println(member);

		Member member = util.getMember();
		// System.out.println(member);

		// 댓글 Entity만들기
		Comment comment = new Comment();
		// 엔터처리 해주세요. \n\r -> <br>
		String reText = commentRequest.getComment().replaceAll("\r\n", "<br>");
		comment.setCcomment(reText);
		comment.setMember(member);
		Board board = Board.builder().bno(commentRequest.getBno()).build(); // 여기에 글 번호만 저장했습니다.
		comment.setBoard(board);
		comment.setIp(util.getIP()); // 아이피 추가 : 빌더 사용하실거면 통일하시는 게 편합니다.

		// 데이터베이스에 저장하기
		comment = jpaCommentRepository.save(comment);
		// System.out.println("save 후 출력 " + comment);
		// CommentDTO(cno=5, bno=135, ccomment=111, name=애플,
		// cdate=2025-06-24T14:36:39.954998, clike=0)
		CommentDTO dto = CommentDTO.builder().build();
		if (comment.getCno() > 0) {
			dto.setCno(comment.getCno());
		}
		return dto;
	}

	public int deletePost(int bno) {
		Optional<Board> board = jpaboardRepository.findByBno(bno);
		// String id = util.getMember().getMid();
		if (board.isPresent() && util.idCheck(board.get().getMember())) { // bno에 해당하는 게시글이 있을 때
			jpaboardRepository.deleteById(bno); // 내가 쓴 글만 지우기 위해서
			// 댓글이 있을 경우 삭제 불가.
			return 1;
		}
		return 0;
	}

	public int deleteComm(int cno) {
		int result = 0;
		Optional<Comment> comment = jpaCommentRepository.findByCno(cno);
		if (comment.isPresent() && util.idCheck(comment.get().getMember())) {
			jpaCommentRepository.deleteById(cno);
			result = 1;
		}
		return result;
	}

	@Transactional
	public int updatePost(WriteDTO dto) {
		// 먼저 데이터 불러오기
		Optional<Board> data = jpaboardRepository.findByBno(dto.getNo());
		if (data.isPresent() && util.idCheck(data.get().getMember())) {
			// 변경 데이터 입력하기
			Board update = data.get();
			update.setTitle(dto.getTitle());
			update.setContent(dto.getContent());
			update.setIp(util.getIP());
			// 저장하기
			// jpaboardRepository.save(update); //위 어노테이션 있으니 이렇게 해도 되는지 테스트 해보기
			return 1;
		} else {
			return 0;
		}
	}

	public Optional<CommentDTO> updateComm(int cno) { // 댓글 수정화면으로 데이터 붙이기 
		Optional<CommentDTO> comment = Optional.ofNullable(null);
		if (jpaCommentRepository.existsById(cno)) {
			Optional<Comment> comm = jpaCommentRepository.findById(cno);
			if(comm.isPresent() && util.getId().equals(comm.get().getMember().getMid())) {
				CommentDTO dto = convertService.entityToCommentDTO(comm.get());				
				// converter사용합니다. 해당 서비스 가서 수정해야 합니다.
				String reText = (dto.getCcomment()).replaceAll("<br>", "\r\n");
				dto.setCcomment(reText);
				comment = Optional.of(dto);
			}
		}		
		return comment;
	}
	
	@Transactional
	public void updateComm(CommentDTO dto) { // 사용자가 댓글을 수정하고 DB에 저장하기 직전
		// 변환작업
		 Optional<Comment> comment = jpaCommentRepository.findById(dto.getCno());
		// 위 updatPost보시면 됩니다.
		 // 단, <br>작업도 해주셔야 합니다.
		 Comment data = comment.get();
		 data.setCcomment(dto.getCcomment().replaceAll("\r\n", "<br>"));
		 //리턴이 없습니다. 
	}
}
