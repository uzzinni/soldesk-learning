package com.poseidon.entity;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "jpaboard")
public class Board {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bno;
	
	@Column(length = 50)
	private String title;
	
	@Column(columnDefinition = "LONGTEXT", nullable = false)
	private String content;
	
	@ColumnDefault("1")
	private int bread;
	
	@CreationTimestamp
	private LocalDateTime date;
	
	
	//글쓴이
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "mname")
	private Member member;
	
 	//댓글 연결하기
	@OneToMany(mappedBy = "board", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@OrderBy("id asc")
	private List<Comment> commentList;
	

}
