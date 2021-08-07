package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GuestbookVo;

@Repository
public class GuestbookDao {
	
	@Autowired
	private SqlSession sqlSession; // SqlSessionFactory + OracleDataSource
	
	// addList
	public List<GuestbookVo> getList() {
		List<GuestbookVo> gList = sqlSession.selectList("guestbook.getList");
		
		return gList;
	}
	
	// insert
	public int addGuestbook(GuestbookVo guestbookVo) {
		
		return sqlSession.insert("guestbook.addGuestbook", guestbookVo);
	}
	
	// insertSelectKey - Ajax
	public int addGuestbookKey(GuestbookVo guestbookVo) {
		System.out.println("Dao: addGuestbookKey - 1 - " + guestbookVo);
		return sqlSession.insert("guestbook.addGuestbookKey", guestbookVo);
	}
	
	// getGuestInfo - Ajax
	public GuestbookVo selectGuestInfo(int no) {
		
		return sqlSession.selectOne("guestbook.selectGuestInfo", no);
	}
	
	// delete
	public int deleteGuestbook(GuestbookVo guestbookVo) {
		
		return sqlSession.delete("guestbook.deleteGuestbook", guestbookVo);
	}
	
}
