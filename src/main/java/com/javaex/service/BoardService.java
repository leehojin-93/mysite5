package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BoardDao;
import com.javaex.vo.BoardVo;

@Service
public class BoardService {

	@Autowired
	private BoardDao boardDao;

	public List<BoardVo> boardList(String keyword) {
//		boardDao.countList();
		return boardDao.boardList(keyword);
	}

	public BoardVo getBoard(int no) {
		boardDao.countHit(no);

		return boardDao.getBoard(no);
	}
	
	public int write(BoardVo boardVo) {
		
		return boardDao.insert(boardVo);
	}
	
	public int update(BoardVo boardVo) {
		
		return boardDao.update(boardVo);
	}
	
	public int delete(int no) {
		
		return boardDao.delete(no);
	}

}
