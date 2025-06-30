package com.poseidon.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class IPLog {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ino;
	
	@Column
	private String iip;
	
	@Column
	private String iurl;
	
	@Column(columnDefinition = "LONGTEXT")
	private String idata;
	
	@CreationTimestamp
	private LocalDateTime idate = LocalDateTime.now();
}
