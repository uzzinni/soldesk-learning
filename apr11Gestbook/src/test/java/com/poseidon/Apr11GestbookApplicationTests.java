package com.poseidon;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.poseidon.entitiy.GuestBook;
import com.poseidon.repository.GuestBookRepository;

@SpringBootTest
class Apr11GestbookApplicationTests {

	@Autowired
	private GuestBookRepository repository;
	
	@Test
	void contextLoads() {
	}
	
	@Test
	@DisplayName("데이터 입력하기")
	public void insertData() {
		for (int i = 1; i <= 100; i++) {
			GuestBook guestBook = GuestBook.builder()
					.title(i + "제목")
					.content( i + "본문내용")
					.wirter("작성자" + i)
					.build();
			//저장 save();
			System.out.println(repository.save(guestBook));
		}
	}

}
