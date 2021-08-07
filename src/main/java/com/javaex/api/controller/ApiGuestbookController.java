package com.javaex.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.GuestbookService;
import com.javaex.vo.GuestbookVo;

@Controller
@RequestMapping(value = "/api/guestbook", method = { RequestMethod.GET, RequestMethod.POST })
public class ApiGuestbookController {
	
	@Autowired
	private GuestbookService guestbookService;
	
	@ResponseBody // viewResolver로 가지않고 반환 데이터를 JSON 형식으로 response body로 반환
	@RequestMapping("/list")
	public List<GuestbookVo> List() {
		
		// DB에서 list 데이터 반환(JSON 형식)
		return guestbookService.getList();
	}
	
	@ResponseBody
	@RequestMapping("/add")
	public GuestbookVo add(@ModelAttribute GuestbookVo guestVo) {
		
		return guestbookService.addResultVo(guestVo);
	}
	
	@ResponseBody
	@RequestMapping("/delete")
	public int delete(@ModelAttribute GuestbookVo guestVo) {
		
		return guestbookService.deleteGuestbook(guestVo);
	}
	
}
