package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BoardVo;

@Repository
public class BoardDao {

	@Autowired
	private SqlSession sqlSession;

	public List<BoardVo> boardList(String keyword) {
		List<BoardVo> bList = sqlSession.selectList("board.boardList", keyword);

		return bList;
	}
	
	public int countList() {
		
		return sqlSession.selectOne("board.countList");
	}

	public int countHit(int no) {
		return sqlSession.update("board.countHit", no);

	}

	public BoardVo getBoard(int no) {
		return sqlSession.selectOne("board.getBoard", no);

	}
	
	public int insert(BoardVo boardVo) {
		
		return sqlSession.insert("board.insert", boardVo);
	}
	
	public int update(BoardVo boardVo) {
		
		return sqlSession.update("board.update", boardVo);
	}
	
	public int delete(int no) {
		
		return sqlSession.delete("board.delete", no);
	}

}
