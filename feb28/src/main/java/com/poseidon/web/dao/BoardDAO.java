package com.poseidon.web.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.poseidon.web.dto.BoardDTO;

@Repository
public class BoardDAO {

	@Autowired
	private SqlSession sqlSession;
	
	public List<BoardDTO> list() {
		return sqlSession.selectList("board.boardList");
	}

	public void write(BoardDTO dto) {
		sqlSession.insert("board.write", dto);
		// 저장 sqlSession.insert("sql", 파라미터);
		// 수정 sqlSession.update("sql", 파라미터);
		// 삭제 sqlSession.delete("sql", 파라미터);
		// 조회 sqlSession.select("sql");
		// 조회 sqlSession.select("sql", 파라미터);
		// 조회 sqlSession.selectList("sql");
		// 조회 sqlSession.selectList("sql", 파라미터);
		
	}

	public void write(Map<String, Object> map) {
		sqlSession.insert("board.write2", map);
	}

	public void write1(BoardDTO dto) {
		sqlSession.insert("board.write1", dto);
	}

	public BoardDTO detail(int board_no) {
		return sqlSession.selectOne("board.detail", board_no);
	}

	// 진짜 삭제하는 것이 아니라 데이터베이스에 board_del 값을 0으로 변경한다.
	public int del(BoardDTO dto) {
		return sqlSession.update("board.del", dto);
	}

	public BoardDTO update(BoardDTO dto) {
		return sqlSession.selectOne("board.update", dto);
	}
		
	
}
