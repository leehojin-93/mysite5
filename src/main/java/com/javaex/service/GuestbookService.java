package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.GuestbookDao;
import com.javaex.vo.GuestbookVo;

@Service
public class GuestbookService {
	
	@Autowired
	private GuestbookDao guestbookDao;
	
	public List<GuestbookVo> getList() {
		List<GuestbookVo> gList = guestbookDao.getList();
		
		return gList;
	}
	
	public int addGuestbook(GuestbookVo guestbookVo){
		
		return guestbookDao.addGuestbook(guestbookVo);
	}
	
	//  - Ajax
	public GuestbookVo addResultVo(GuestbookVo guestbookVo) {
		System.out.println("Service: addResultVo - 1 - " + guestbookVo);
		guestbookDao.addGuestbookKey(guestbookVo);
		System.out.println("Service: addResultVo - 2 - " + guestbookVo);
		
		int no = guestbookVo.getNo();
		return guestbookDao.selectGuestInfo(no);
	}
	
	public int deleteGuestbook(GuestbookVo guestbookVo) {
		
		return guestbookDao.deleteGuestbook(guestbookVo);
	}

}
