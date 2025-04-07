package com.poseidon.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.poseidon.entity.Board;
import com.poseidon.repository.BoardRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {

	private final BoardRepository boardRepository;

	// 이건 이제 안 써요. 2025-04-07 : paging추가하면서 사용 안 함
	public List<Board> findAll() {
		return boardRepository.findAll();
	}

	public void insert() {
		// 레코드 생성
		//Board board = new Board("insert연습입니다.", "인서트 연습 본문입니다.", "임길동");
		//빌더 
		Board board = Board.builder()
						.btitle("insert연습입니다.")
						.bcontent("인서트 연습 본문입니다.")
						.name("임길동")
						.build();
		
		// repository에게 일 시키기
		// boardRepository.save(board);
		// 여러 데이터 저장하기
		List<Board> list = new ArrayList<>();
		list.add(board);
		list.add(new Board("insert두번째","2번째 본문","김길동"));
		list.add(new Board("insert세번째","3번째 본문","이길동"));
		list.add(new Board("insert4번째","4번째 본문","사길동"));
		list.add(new Board("insert5두번째","5번째 본문","오길동"));
		list.add(new Board("insert6번째","6번째 본문","육길동"));
		list.add(new Board("insert7번째","7번째 본문","최길동"));
		list.add(new Board("insert8번째","8번째 본문","예길동"));
		list.add(new Board("insert9번째","9번째 본문","구길동"));
		list.add(new Board("insert10번째","10번째 본문","신길동"));
		
		boardRepository.saveAll(list);
		
		list.forEach(l -> System.out.println(l.getBno()));
	}

	public void delete(Board board) {
		//boardRepository.deleteById(bno); // PK=int
		boardRepository.delete(board); // entity
	}

	public Optional<Board> detail(int bno) {
		return boardRepository.findById(bno);
	}

	public void insert(Board board) {
		boardRepository.save(board);
	}

	//  JPA 페이지 사용해보기 입니다. 
	public Page<Board> list(int pageNo) {
		// 1. 정렬옵션 사용해보기 ORDER BY bno
		List<Sort.Order> sorts = new ArrayList<Sort.Order>();
		sorts.add(Sort.Order.desc("bno"));
		
		// 2. pageable 사용해보기 LIMIT
		Pageable pageable = PageRequest.of(pageNo - 1, 10, Sort.by(sorts)); // 0 * 10  = 0
		//                                 페이지번호, 한 페이지에 보여줄 페이지 수, 정렬옵션
		
		//return boardRepository.findAllByOrderByBnoDesc(pageable); 
		return boardRepository.findAll(pageable); 
	}

}

//Web ---> Controller ---> Service ---> Repository --> DB
