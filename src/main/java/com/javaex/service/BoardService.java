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
		boardMap.put("listCount", boardDao.listCount(keyword));
		boardMap.put("boardList", boardDao.boardList(keyword));
		
		return boardMap;
	}

	public int write(BoardVo boardVo) {
		
		return boardDao.write(boardVo);
	}
	
	public BoardVo getBoardRead(int no) {
		boardDao.countHit(no);

		return boardDao.getBoard(no);
	}
	
	public BoardVo getBoardModifyForm(int no) {
		
		return boardDao.getBoard(no);
	}
	
	public int update(BoardVo boardVo) {
//		Map<String, Object> boardModifyMap = new HashMap<String, Object>();
//		boardModifyMap.put("no", boardVo.getNo());
//		boardModifyMap.put("title", boardVo.getTitle());
//		boardModifyMap.put("content", boardVo.getContent());
		
		return boardDao.update(boardVo);
	}
	
	public BoardVo delete(int no) {
		boardDao.delete(no);
		
		return boardDao.getBoard(no);
	}

}
