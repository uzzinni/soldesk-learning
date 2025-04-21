package com.poseidon.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Member {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int mno;
	
	@Column(length = 15)
	private String mname;
	
	@Column(length = 20)
	private String mid;
	
	@Column(length = 200)
	private String mpw;
	
	@Column(length = 40)
	private String memail;
	
	@Column(columnDefinition = "timestamp default current_timestamp")
	private LocalDateTime mdate = LocalDateTime.now();
}
