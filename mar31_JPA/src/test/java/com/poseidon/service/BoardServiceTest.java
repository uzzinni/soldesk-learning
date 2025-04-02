package com.poseidon.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.poseidon.entity.Board;

class BoardServiceTest {

	@Autowired
	private BoardService boardService;
	
	@Test
	@DisplayName("detail 값이 오는지 검사해보기")
	void test() {
		//fail("Not yet implemented");
		//Optional<Board> detail = boardService.detail(15);
		Board detail = boardService.detail(15).get();
		//System.out.println(detail.get());
		//assertNotNull(detail);
		assertNull(detail);
	}
	
	@Test
	@DisplayName("데이터 입력 테스트")
	void save() {
		Board board = Board.builder().btitle("테스트").bcontent("테스트합니다").name("테스트").build();
		//저장하기
		boardService.insert(board);
		System.out.println("bno : " + board.getBno());
		assertEquals("테스트", board.getName());
	}
	
	@Test
	@DisplayName("데이터 가져오기")
	void findAll() {
		List<Board> list = boardService.findAll();
		System.out.println(list);
		List<Board> list2 = boardService.findAll();
		//assertSame(list, list2);		//둘 다 같은 객체?
		assertEquals(list, list2);		// 값이 같은지?
		
		//데이터가 있는 것
		//Board detail = boardService.detail(61).get();
		//데이터가 없을 경우 대비
		Board detail = boardService.detail(10).orElseThrow(() -> new IllegalAccessError("예외 발생"));
		// Board detail = boardService.detail(10).orElseThrow(IllegalAccessError::new);
		assertThat(detail.getName()).isEqualTo("테스트");
		
		// 콜론 두개? IllegalAccessError::new
		/*
		 * 정식 이름은 메소드 참조 표현식이라고 한다.
		 * 람다식에서 파라미터를 중복해서 사용하지 않고 표현
		 * 사용 방법
		 * 인스턴스 :: 메소드명 or new
		 * 
		 * orElse : 파라미터로 값을 필요로 합니다. 값이 미리 존재하는 경우 사용
		 * 
		 * orElseGet : 파라미터로 함수를 필요로합니다. 값이 없을 때 사용, 거의 대부분은 이 메소드 사용
		 * 
		 * orElseThrow : 값이 null일 경우 예외 처리합니다.
		 * 
		 * */
		
		
	}
	
}
