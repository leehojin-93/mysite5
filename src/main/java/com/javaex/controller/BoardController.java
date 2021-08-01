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
		model.addAttribute("boardMap", boardService.boardMap(keyword)); // --> boardDao.listCount(keyword), boardDao.boardList(keyword)
		
		return "/board/list";
	}
	
	@RequestMapping(value="/writeForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String writeForm(HttpSession session) {
		// 로그인 정보가 없을때
		
		// Controller에서 처리
		/*
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		
		if (authUser != null) {
			return "/board/writeForm";
		} else {
			return "redirect:/user/loginForm";
		}
		*/
		
		// wirteForm에서 처리
		return "/board/writeForm";
		
	}
	
	@RequestMapping(value="/write", method = { RequestMethod.GET, RequestMethod.POST })
	public String write(@ModelAttribute BoardVo boardVo, HttpSession session) {
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		
		if (authUser == null) { // 로그인 정보가 없을때
			return "redirect:/user/loginForm";
		}
		
		String boardVoTitle = boardVo.getTitle();

		if (boardVoTitle == null) { // write를 url로 접속 했을 경우
			return "redirect:/board/list";
		}
		
		int authUserNo = authUser.getNo();
		boardVo.setUserNo(authUserNo);
		
		boardService.write(boardVo); // boardVo = userNo, title, content
		
		return "redirect:/board/list";
	}
	
	@RequestMapping(value="/read", method = { RequestMethod.GET, RequestMethod.POST })
	public String read(Model model, @RequestParam(value = "no", required = false, defaultValue = "0") int no) {
		if (no == 0) { // read에 no파라미터가 입력되지 않았을때 0으로 처리하고 redirect:/board/list
			return "redirect:/board/list";
			
		} else {
			model.addAttribute("getBoard", boardService.getBoardRead(no));
			
			return "/board/read";
		}
	}
	
	@RequestMapping(value="/modifyForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String modifyForm(Model model, @RequestParam(value = "no", required = false, defaultValue = "0") int no, HttpSession session) {
		
		if (no == 0) { // modifyForm에 no파라미터가 입력되지 않았을때 0으로 처리하고 redirect:/board/list
			return "redirect:/board/list";
			
		} else {
			BoardVo boardVo = boardService.getBoardModifyForm(no);
			int userNo = boardVo.getUserNo();
			
			UserVo authUser = (UserVo)session.getAttribute("authUser");		
			
			if (authUser == null) { // 로그인 정보가 없을때
				return "redirect:/user/loginForm";
			}
			
			int loginUserNo = authUser.getNo();
			
			if (userNo == loginUserNo) { // 수정하려는 글 작성자의 번호와 로그인 유저의 번호가 같을때
				model.addAttribute("getBoard", boardVo);
				
				return "/board/modifyForm";
				
			} else { // 다른 유저의 글을 수정하려고 시도 할 때
				return "redirect:/board/read?no=" + no;
			}
		}
		
	}
	
	@RequestMapping(value="/modify", method = { RequestMethod.GET, RequestMethod.POST })
	public String modify(@ModelAttribute BoardVo boardVo) {
		String boardVoTitle = boardVo.getTitle();
		int boardVoNo = boardVo.getNo();

		if (boardVoTitle == null) { // modify를 url로 접속 했을 경우
			return "redirect:/board/read?no=" + boardVoNo;
		}

		
		boardService.modify(boardVo);
		
		return "redirect:/board/read?no=" + boardVoNo;
	}
	
	@RequestMapping(value="/delete", method = { RequestMethod.GET, RequestMethod.POST })
	public String delete(@RequestParam(value = "no", required = false, defaultValue = "0") int no, HttpSession session) {
		
		if (no == 0) {
			return "redirect:/board/list";
		} else {
			UserVo authUser = (UserVo)session.getAttribute("authUser");
			
			if (authUser == null) {
				return "redirect:/board/list";
			}
			
			int authUserNo = authUser.getNo();
			
			BoardVo getBoard = boardService.getBoardModifyForm(no);
			int boardUserNo = getBoard.getUserNo();
			
			if (authUserNo == boardUserNo) {
				boardService.delete(no);
				
				return "redirect:/board/list";
			}
			
		}
		
		return "redirect:/board/list";
	}

}
