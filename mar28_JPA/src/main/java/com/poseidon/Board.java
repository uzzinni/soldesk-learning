package com.poseidon;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "board2")
public class Board {
	@Id // PK
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bno;
	
	@Column(length=50)
	private String btitle;
	
	@Column(columnDefinition="mediumtext")
	private String bcontent;
	
	@Column(name="boarddate", columnDefinition = "timestamp default current_timestamp")
	private LocalDateTime bdate;
		
	@Column(nullable = false)
	private String name;
	
	
	
}
