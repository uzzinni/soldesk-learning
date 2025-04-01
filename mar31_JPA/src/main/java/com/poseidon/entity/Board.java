package com.poseidon.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "board2")
@NoArgsConstructor
public class Board {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bno;

	@Column(length = 50)
	private String btitle;

	@Column(columnDefinition = "mediumtext")
	private String bcontent;

	@Column(name = "boarddate", columnDefinition = "timestamp default current_timestamp")
	private LocalDateTime bdate;

	@Column(nullable = false)
	private String name;
	
	@Builder
	public Board(String btitle, String bcontent, String name){
		this.btitle = btitle;
		this.bcontent = bcontent;
		this.name = name;
		this.bdate = LocalDateTime.now(); // 생성시간 저장
	}
	
}

// @NoArgsConstructor : 파라미터가 없는 기본 생성자 자동생성
// @AllArgsConstructor : 모든 필드를 매개변수로 가지는 생성자
// 롬복 빌더 패턴

/*
 * Board board = new Board("title", "content", "name");
 * 
 * Board board = Board.builder()
 * 					.btitle("title")
 * 					.bcontent("content")
 * 					.name("name")
 * 					.build();
 * 
 */
// Entity 영속성




