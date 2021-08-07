package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.GalleryService;
import com.javaex.vo.GalleryVo;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping(value = "/gallery", method = { RequestMethod.GET, RequestMethod.POST })
public class GalleryController {
	
	@Autowired
	private GalleryService galleryService;
	
	@RequestMapping("/list")
	public String list(Model model) {
		System.out.println("GalleryController: list;");
		model.addAttribute("galleryList", galleryService.getList());
		
		return "/gallery/list";
	}
	
	@RequestMapping("/add")
	public String add(@ModelAttribute GalleryVo galleryVo, MultipartFile file, HttpSession session) {
		System.out.println("galleryController: add");
		System.out.println(galleryVo);
		
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		String name = authUser.getName();
		int userNo = authUser.getNo();
		
		galleryVo.setName(name);
		galleryVo.setUserNo(userNo);
		
		System.out.println(galleryVo);
		
		galleryService.addGalleryFile(galleryVo, file);
		
		return "redirect:/gallery/list";
	}
	
	@ResponseBody
	@RequestMapping("/view")
	public GalleryVo view(@RequestParam("no") int no) {
		System.out.println("galleryController: view");
		System.out.println(no);
		System.out.println(galleryService.viewInfo(no));
		
		return galleryService.viewInfo(no);
	}
	
	@ResponseBody
	@RequestMapping("/delete")
	public GalleryVo delete(@ModelAttribute GalleryVo galVo) {
		
		return null;
	}

}
