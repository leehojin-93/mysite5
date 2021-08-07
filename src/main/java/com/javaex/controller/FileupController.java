package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.FileupService;

@Controller
@RequestMapping(value = "/fileup", method = { RequestMethod.GET, RequestMethod.POST })
public class FileupController {
	
	@Autowired
	private FileupService fileupService;
	
	@RequestMapping("/form")
	public String form() {
		System.out.println("FileupController: form;");
		return "/fileup/form";
	}
	
	@RequestMapping("/upload")
	public String upload(Model model, @RequestParam("file") MultipartFile file) { // 첨부 파일이 없어도 기본 주소값이 있다
		System.out.println("FileupController: upload;");

		String saveName = fileupService.restore(file);
		model.addAttribute("saveName", saveName);
		
		return "/fileup/result";
	}
	
}
