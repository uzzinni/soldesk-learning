package com.poseidon.dto;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class KmaDTO {
	private int kno;
	private double avgTa, minTa, maxTa;
	private LocalDate tm;
	
}
// 테이블명 kma
// 컬럼은 위 private 값으로 해주세요.