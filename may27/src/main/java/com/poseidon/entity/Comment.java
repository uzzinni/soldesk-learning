package com.poseidon.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "jpacomment")
public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cno;
	
	//보드와 연결하기
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "bno")
	private Board board;
	
	//글쓴이와 연결하기
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "mname")
	private Member member;

	@Column(columnDefinition = "LONGTEXT", nullable = false)
	private String ccomment;
	
	@CreationTimestamp
	private LocalDateTime cdate;
	
	@ColumnDefault("1")
	private int clike;
	
	//ip
	@Column(length = 50)
	private String ip;
}
