package com.poseidon.entitiy;

import java.time.LocalDate;

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
//{끝주소=14.128.255.255, 국가=대한민국, 프리픽스(/24)=/17, 할당일자=2010.09.15, 시작주소=14.128.128.0}
//	 		PK			국가		시작주소	   끝주소			프리픽스(/24)	할당일자
//타입		INTEGER		VARCHAR(10), VARCHAR(20), 	VARCHAR(20), 	VARCHAR(5),		LocalDate
//컬럼명	ino			nation		startIP			endIP			prefix			date

@Entity
@Data
@Table(name="iptable")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Excel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ino;
	
	@Column(length = 10)
	private String nation;
	
	@Column(length = 20)
	private String startIP;
	
	@Column(length = 20)
	private String endIP;
	
	@Column(length = 5)
	private String prefix;
	
	@Column
	private LocalDate date;
	
}