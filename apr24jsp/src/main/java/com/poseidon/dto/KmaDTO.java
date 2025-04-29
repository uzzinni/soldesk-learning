package com.poseidon.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KmaDTO {
	private int kno;
	private Double avgTa, minTa, maxTa;
	private LocalDate tm;
}
// 테이블명 kma
// 컬럼은 위 private 값으로 해주세요.


//tm":"2025-04-27"
//avgTa":"14.9","minTa":"10.7","minTaHrmt":"0245","maxTa":"19.1",


/*
CREATE TABLE `kma` (
	`kno` INT NOT NULL AUTO_INCREMENT,
	`tm` DATE NOT NULL,
	`avgTa` DOUBLE NOT NULL,
	`minTa` DOUBLE NOT NULL,
	`maxTa` DOUBLE NOT NULL,
	PRIMARY KEY (`kno`)
)
COMMENT='기상청 정보 저장하기\r\n반복문'
COLLATE='utf8mb4_general_ci'
;


 */