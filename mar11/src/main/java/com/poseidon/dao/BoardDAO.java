package com.poseidon.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.poseidon.dto.BoardDTO;

@Repository
@Mapper
public interface BoardDAO {

	List<BoardDTO> boardList();

	BoardDTO detail(BoardDTO dto);

	@Insert("INSERT INTO comment (board_no, c_comment, user_no) "
			+ "VALUES (#{board_no}, #{comment}, (SELECT user_no FROM user WHERE user_id=#{user_id}))")
	int comment(Map<String, Object> map);

	@Select("SELECT * FROM viewcomment WHERE board_no=#{no}")
	List<Map<String, Object>> commentList(int no);

}
