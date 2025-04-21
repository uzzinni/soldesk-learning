package com.poseidon.entitiy;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="iplog")
public class Iplog {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ino;
	
	@Column(columnDefinition = "TEXT")
	private String idata;
	
	//idate
	@CreationTimestamp
	private LocalDateTime idate;
	
	//iip
	@Column(columnDefinition = "TEXT")
	private String iip;
	
	//iurl
	@Column(columnDefinition = "TEXT")
	private String iurl;
}
