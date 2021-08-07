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

	// paging practice
	public Map<String, Object> getList2(int crtPage, String keyword) {
		System.out.println("boardService: getList2;");

		//////////////////////////////////////
		/* 리스트 가져오기 */
		//////////////////////////////////////
		// 1page당 출력 개수
		int listCnt = 10;

		// crtPage 음수일때 crtPage=1 처리
		if (crtPage < 1) {
			crtPage = 1;
		}

		// 3항 연산자 (e ? a : b)
		// crtPage = (crtPage > 0) ? crtPage : 1;

		// pageNum에 따라 출력되는 게시글 시작번호
		int startRnum = (crtPage - 1) * listCnt + 1;

		// pageNum에 따라 출력되는 게시글 끝번호
		int endRnum = crtPage * listCnt;
//		int endRnum = startRnum + listCnt - 1;

		Map<String, Object> pageInfo = new HashMap<String, Object>();
		pageInfo.put("startRnum", startRnum);
		pageInfo.put("endRnum", endRnum);
		pageInfo.put("keyword", keyword);
		
		List<BoardVo> boardList = boardDao.boardList2(pageInfo);

		//////////////////////////////////////
		/* paging */
		//////////////////////////////////////

		// 전체 게시글 개수
		int totalCount = boardDao.selectTotalCnt(keyword);
		System.out.println(totalCount);

		// 페이지당 버튼 출력 개수
		int pageBtnCount = 8;

		// 페이지의 마지막 버튼
		int endPageBtnNo = (int) Math.ceil(crtPage / (double) pageBtnCount) * pageBtnCount;
		System.out.println(endPageBtnNo);

		// 페이지의 시작 버튼
		int startPageBtnNo = endPageBtnNo - (pageBtnCount - 1);

		// 페이지 넘김 화살표 버튼
		boolean next = false;
		if ((endPageBtnNo * listCnt) < totalCount) {
			next = true;
		} else {
			endPageBtnNo = (int)Math.ceil(totalCount / (double)listCnt);
		}
		
		// 페이지 이전 화살표 버튼
		boolean prev = false;
		if (startPageBtnNo != 1) {
			prev = true;
		}
		
		// 리턴
		Map<String, Object> pagingMap = new HashMap<String, Object>();
		pagingMap.put("prev", prev);
		pagingMap.put("startPageBtnNo", startPageBtnNo);
		pagingMap.put("endPageBtnNo", endPageBtnNo);
		pagingMap.put("next", next);
		pagingMap.put("boardList", boardList);

		return pagingMap;
	}

	public Map<String, Object> boardMap(String keyword) {
//		int countList = boardDao.countList(keyword);
//		List<BoardVo> boardList = boardDao.boardList(keyword);

		Map<String, Object> boardMap = new HashMap<String, Object>();
		boardMap.put("listCount", boardDao.listCount(keyword));
		boardMap.put("boardList", boardDao.boardList(keyword));

		return boardMap;
	}

	public int write(BoardVo boardVo) {

		/*
		 * 페이징 연습 - 게시글 199개 for (int i = 0; i < 199; i++) { boardVo.setTitle("페이징 연습: "
		 * + i + "번째 제목 입니다."); boardVo.setContent("페이징 연습: " + i + "번째 글 입니다");
		 * boardDao.write(boardVo); }
		 * 
		 * return 1;
		 */
		return boardDao.write(boardVo);
	}

	public BoardVo getBoardRead(int no) {
		boardDao.countHit(no);

		return boardDao.getBoard(no);
	}

	public BoardVo getBoardModifyForm(int no) {

		return boardDao.getBoard(no);
	}

	public int modify(BoardVo boardVo) {
//		Map<String, Object> boardModifyMap = new HashMap<String, Object>();
//		boardModifyMap.put("no", boardVo.getNo());
//		boardModifyMap.put("title", boardVo.getTitle());
//		boardModifyMap.put("content", boardVo.getContent());

		return boardDao.modify(boardVo);
	}

	/*
	 * public Map<String, Object> delete(int no) {
	 * 
	 * Map<String, Object> deleteMap = new HashMap<String, Object>();
	 * deleteMap.put("getBoard", boardDao.getBoard(no)); deleteMap.put("delete",
	 * boardDao.delete(no));
	 * 
	 * return deleteMap; }
	 */

	public int delete(BoardVo boardVo) {

		return boardDao.delete(boardVo);
	}

}
