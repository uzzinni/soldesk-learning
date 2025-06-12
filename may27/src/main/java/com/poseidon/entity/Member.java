package com.poseidon.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// 기존 user테이블은 마이바티스용(언터바 있음)
// 그래서 새로 만들어보겠습니다. ㅠㅠ
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "jpamember")
public class Member {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int mno;
	
	@Column(unique = true)
	private String mid;
	
	@Column(length = 5, nullable = false)
	private String mname;
	
	private String mpw;
	private String memail;
	
	@CreationTimestamp
	private LocalDateTime mdate;
	
	private String mrole;
}
