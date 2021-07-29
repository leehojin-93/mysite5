package com.javaex.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BoardDao;
import com.javaex.vo.BoardVo;

@Service
public class BoardService {

	@Autowired
	private BoardDao boardDao;

	public Map<String, Object> boardMap(String keyword) {
//		int countList = boardDao.countList(keyword);
//		List<BoardVo> boardList = boardDao.boardList(keyword);
		
		Map<String, Object> boardMap = new HashMap<String, Object>();
		boardMap.put("countList", boardDao.countList(keyword));
		boardMap.put("boardList", boardDao.boardList(keyword));
		
		return boardMap;
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
