package com.poseidon.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.poseidon.entity.Board;
import com.poseidon.repository.BoardRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {

	private final BoardRepository boardRepository;

	public List<Board> findAll() {
		return boardRepository.findAll();
	}

	public void insert() {
		// 레코드 생성
		Board board = new Board("insert연습입니다.", "인서트 연습 본문입니다.", "임길동");
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
	}

	public void delete(Board board) {
		//boardRepository.deleteById(bno);	//PK
		boardRepository.delete(board);		//entity
		
	}

}

//Web ---> Controller ---> Service ---> Repository --> DB
