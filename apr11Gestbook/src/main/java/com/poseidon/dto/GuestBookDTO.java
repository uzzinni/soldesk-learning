package com.poseidon.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GuestBookDTO {
	private int gno;
	private String title, content, writer;
	private LocalDateTime regDate, modDate;
}
// Entity와 DTO로 분리하는 이유
/* 
 * DTO는 view(사용자 인터페이스)와 Controller 간의 인터페이스 역할
 * Entity는 Model(데이터베이스)의 역할을 한다. 
 * 둘을 분리하여 MVC패턴을 적용하여 코드의 유지보수를 용이하게 한다.
 * 
 * DTO는 서버에서 클라이언트에 데이터를 전송할 용도로 사용
 * 
 * 
 * 
 * 
 * 1. MVC패턴에서 View와 Model을 분리
 * 2. 필요한 데이터만 전송하기 가능
 * 3. Entity 구조변경시에도 문제 없음
 * 4. 순환참조 방지하기
 */