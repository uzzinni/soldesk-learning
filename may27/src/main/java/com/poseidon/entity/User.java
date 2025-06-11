package com.poseidon.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Entity
@Table(name = "user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int user_no;
	
	@Column(length = 15, unique = true)
	private String user_id;
	
	@Column(length = 70)
	private String user_pw;
	
	@Column(length = 15)
	private String user_name;
	
	@Column(length = 25)
	private String user_email;
	
	@CreationTimestamp
	private LocalDateTime user_date;
	
	@ColumnDefault(value = "1")
	@Column(nullable = false)
	@Builder.Default      //Builder를 이용하게 되면 null/0이 들어가는 것을 방지.
	private int user_del = 1; // 1
	
	@ColumnDefault(value = "ROLE_USER")
	@Column(nullable = false)
	@Builder.Default
	private String user_role = "ROLE_USER"; // ROLE_USER
	
}
