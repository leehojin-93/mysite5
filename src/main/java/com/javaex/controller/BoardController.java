package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.BoardService;
import com.javaex.vo.BoardVo;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping(value="/board", method = { RequestMethod.GET, RequestMethod.POST })
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@RequestMapping(value="/list", method = { RequestMethod.GET, RequestMethod.POST })
	public String list(Model model, @RequestParam(value = "keyword", required = false, defaultValue = "") String keyword) {
		model.addAttribute("boardList", boardService.boardList(keyword));
		
		return "/board/list";
	}
	
	@RequestMapping(value="/writeForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String writeForm() {
		
		return "/board/writeForm";
	}
	
	@RequestMapping(value="/write", method = { RequestMethod.GET, RequestMethod.POST })
	public String write(@ModelAttribute BoardVo boardVo, HttpSession session) {
		boardVo.setUserNo(((UserVo)session.getAttribute("authUser")).getNo());
		
		boardService.write(boardVo);
		
		return "redirect:/board/list";
	}
	
	@RequestMapping(value="/read", method = { RequestMethod.GET, RequestMethod.POST })
	public String read(Model model, @RequestParam("no") int no) {
		model.addAttribute("getBoard", boardService.getBoard(no));

		return "/board/read";
	}
	
	@RequestMapping(value="/modifyForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String modifyForm(Model model, @RequestParam("no") int no) {
		model.addAttribute("getBoard", boardService.getBoard(no));

		return "/board/modifyForm";
	}
	
	@RequestMapping(value="/modify", method = { RequestMethod.GET, RequestMethod.POST })
	public String modify(@ModelAttribute BoardVo boardVo) {
		boardService.update(boardVo);
		
		return "redirect:/board/list";
	}
	
	@RequestMapping(value="/delete", method = { RequestMethod.GET, RequestMethod.POST })
	public String delete(@RequestParam("no") int no) {
		boardService.delete(no);
		
		return "redirect:/board/list";
	}

}
