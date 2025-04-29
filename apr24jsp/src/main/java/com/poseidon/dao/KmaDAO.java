package com.poseidon.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.poseidon.dto.KmaDTO;

// 기상정보 중 최고, 최저, 평균, 날짜 데이터를 입력합니다.
@Repository
@Mapper
public interface KmaDAO {

	public int kmaInsert(List<KmaDTO> list);
	
	public void kmaClear();
	
	public List<KmaDTO> kmaSelect();
}


