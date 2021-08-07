package com.javaex.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BoardVo;

@Repository
public class BoardDao {

	@Autowired
	private SqlSession sqlSession;
	
	// paging practice - list
	public List<BoardVo> boardList2(Map<String, Object> pageInfo) {
		System.out.println("boardDao: boardList2;");
		
		return sqlSession.selectList("board.boardList2", pageInfo);
	}
	
	// paging practice - listCount
	public int selectTotalCnt(String keyword) {
		System.out.println("boardDao: selectTotalCnt;");
		
		return sqlSession.selectOne("board.selectTotalCnt", keyword);
	}

	public List<BoardVo> boardList(String keyword) {
		List<BoardVo> bList = sqlSession.selectList("board.boardList", keyword);

		return bList;
	}
	
	public int listCount(String keyword) {
		return sqlSession.selectOne("board.listCount", keyword);
	}

	public int write(BoardVo boardVo) {
		
		return sqlSession.insert("board.write", boardVo);
	}
	
	public int countHit(int no) {
		return sqlSession.update("board.countHit", no);

	}

	public BoardVo getBoard(int no) {
		return sqlSession.selectOne("board.getBoard", no);

	}
	
	public int modify(BoardVo boardVo) {
		
		return sqlSession.update("board.modify", boardVo);
	}
	
	public int delete(BoardVo boardVo) {
		
		return sqlSession.delete("board.delete", boardVo);
	}

}
