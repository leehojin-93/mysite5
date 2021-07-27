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
	public void insert(GuestbookVo guestbookVo) {
		sqlSession.insert("guestbook.insert", guestbookVo);
		
	}
	
	// delete
	public void delete(GuestbookVo guestbookVo) {
		sqlSession.delete("guestbook.delete", guestbookVo);
		
	}
	
	
	
}
