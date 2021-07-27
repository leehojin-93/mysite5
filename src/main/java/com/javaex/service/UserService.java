package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	public UserVo userInfo(UserVo userVo) {
		
		UserVo authUser = userDao.userInfo(userVo);
		
		return authUser;
	}
	
	public int userJoin(UserVo userVo) {
		int count = userDao.insert(userVo);
		
		return count;
	}
	
	public UserVo authUserInfo(int no) {
		UserVo authUserInfo = userDao.authUserInfo(no);
		
		return authUserInfo;
	}
	
	public UserVo update(UserVo userVo) {
		userDao.update(userVo);
		UserVo authUser = userDao.userInfo(userVo);
		
		return authUser;
	}

}
