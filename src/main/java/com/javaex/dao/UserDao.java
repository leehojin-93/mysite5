package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UserVo;

@Repository
public class UserDao {

	@Autowired
	private SqlSession sqlSession;
	
	public UserVo userInfo(UserVo userVo) {
		UserVo authUser = sqlSession.selectOne("user.userInfo", userVo);

		return authUser;
	}
	
	public int insert(UserVo userVo) {
		int count = sqlSession.insert("user.insert", userVo);
		
		return count;
	}
	
	public UserVo authUserInfo(int no) {
		UserVo authUserInfo = sqlSession.selectOne("user.authUserInfo", no);
		
		return authUserInfo;
	}
	
	public int update(UserVo userVo) {
		int count = sqlSession.update("user.update", userVo);
		
		return count;
	}

}
