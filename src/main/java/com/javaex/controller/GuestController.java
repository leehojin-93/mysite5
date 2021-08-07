package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.service.GuestbookService;
import com.javaex.vo.GuestbookVo;

@Controller
@RequestMapping(value = "/guestbook", method = { RequestMethod.GET, RequestMethod.POST })
public class GuestController {
	
	@Autowired
	private GuestbookService guestbookService;
	
	// addList
	@RequestMapping("/addList")
	public String addList(Model model) {
//		List<GuestbookVo> gList = guestbookDao.getList();
//		model.addAttribute("gList", gList);
		model.addAttribute("gList", guestbookService.getList());
		
		return "/guestbook/addList";
	}

	// add
	@RequestMapping("/add")
	public String add(@ModelAttribute GuestbookVo guestbookVo) {
		guestbookService.addGuestbook(guestbookVo);
		
		return "redirect:/guestbook/addList";
	}
	
	// deleteForm
	@RequestMapping("/deleteForm")
	public String deleteForm() {

		return "/guestbook/deleteForm";
	}
	
	// delete
	@RequestMapping("/delete")
	public String delete(@ModelAttribute GuestbookVo guestbookVo) {
		guestbookService.deleteGuestbook(guestbookVo);
		
		return "redirect:/guestbook/addList";
	}
	
	// Ajax방명록 .jsp
	@RequestMapping("/ajaxMain")
	public String ajaxMain() {
		
		return "/guestbook/ajaxList";
	}

}
