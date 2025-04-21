package com.poseidon.entitiy;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

@MappedSuperclass // 테이블 생성하지 않게 하기
@EntityListeners(value= {AuditingEntityListener.class}) //Entity 객체가 생성/변경되는 것을 감지
@Getter
public class BaseEntity {
	@CreatedDate
	@Column(name="regdate", updatable = false)
	private LocalDateTime regDate; //등록 날짜
	
	@LastModifiedDate
	@Column(name="moddate")
	private LocalDateTime modDate; // 수정 날짜
	
}
