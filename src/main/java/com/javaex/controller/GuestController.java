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
@RequestMapping(value = "/guest", method = { RequestMethod.GET, RequestMethod.POST })
public class GuestController {
	
	@Autowired
	private GuestbookService guestbookService;
	
	// addList
	@RequestMapping(value = "/addList", method = { RequestMethod.GET, RequestMethod.POST })
	public String addList(Model model) {
//		List<GuestbookVo> gList = guestbookDao.getList();
//		model.addAttribute("gList", gList);
		model.addAttribute("gList", guestbookService.getList());
		
		return "/guestbook/addList";
	}

	// add
	@RequestMapping(value = "/add", method = { RequestMethod.GET, RequestMethod.POST })
	public String add(@ModelAttribute GuestbookVo guestbookVo) {
		guestbookService.addGuestbook(guestbookVo);
		
		return "redirect:/guest/addList";
	}
	
	// deleteForm
	@RequestMapping(value = "/deleteForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String deleteForm() {

		return "/guestbook/deleteForm";
	}
	
	// delete
	@RequestMapping(value = "/delete", method = { RequestMethod.GET, RequestMethod.POST })
	public String delete(@ModelAttribute GuestbookVo guestbookVo) {
		guestbookService.deleteGuestbook(guestbookVo);
		
		return "redirect:/guest/addList";
	}

}
