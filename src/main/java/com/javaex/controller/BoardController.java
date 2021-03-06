package com.javaex.controller;

import java.util.Map;

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
@RequestMapping(value = "/board", method = { RequestMethod.GET, RequestMethod.POST })
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	// paging practice
	@RequestMapping("/list2")
	public String list2(
			Model model,
			@RequestParam(value = "crtPage", required = false, defaultValue = "1") int crtPage,
			@RequestParam(value = "keyword", required = false, defaultValue = "") String keyword
		) {
		System.out.println("boardCtrl: list2;");
		/* Service에서 처리
		 * if ( crtPage < 1) { crtPage = 1; }
		 */
		Map<String, Object> boardListMap = boardService.getList2(crtPage, keyword);
		
		model.addAttribute("boardListMap", boardListMap);
		
		return "/board/list2";
	}
	
	
	
	@RequestMapping("/list")
	public String list(Model model, @RequestParam(value = "keyword", required = false, defaultValue = "") String keyword) {
		model.addAttribute("boardMap", boardService.boardMap(keyword)); // --> boardMap = { boardDao.listCount(keyword), boardDao.boardList(keyword) }
		
		return "/board/list";
	}
	
	@RequestMapping("/writeForm")
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
	
	@RequestMapping("/write")
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
	
	@RequestMapping("/read")
	public String read(Model model, @RequestParam(value = "no", required = false, defaultValue = "0") int no) {
		// @PathVariable에 defaultValue 사용 불가능: @RequestMapping의 value 값 추가: /read/ 뒤의 값이 오지 않을 경우 ERROR
		if (no == 0) { // read에 no파라미터가 입력되지 않았을때 0으로 처리하고 redirect:/board/list
			return "redirect:/board/list";
			
		} else {
			model.addAttribute("getBoard", boardService.getBoardRead(no));
			
			return "/board/read";
		}
	}
	
	@RequestMapping("/modifyForm")
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
	
	@RequestMapping("/modify")
	public String modify(@ModelAttribute BoardVo boardVo) {
		String boardVoTitle = boardVo.getTitle();
		int boardVoNo = boardVo.getNo();

		if (boardVoTitle == null) { // modify를 url로 접속 했을 경우
			return "redirect:/board/read?no=" + boardVoNo;
		}

		
		boardService.modify(boardVo);
		
		return "redirect:/board/read?no=" + boardVoNo;
	}
	
	@RequestMapping("/delete")
	public String delete(@RequestParam(value = "no", required = false, defaultValue = "0") int no, HttpSession session) {
		BoardVo boardVo = new BoardVo();
		
		if (no == 0) {
			return "redirect:/board/list";
		} else {
			UserVo authUser = (UserVo)session.getAttribute("authUser");
			
			if (authUser == null) {
				return "redirect:/board/list";
			}
			
			int authUserNo = authUser.getNo();
			
			// Map 오류(로그인만하면 파라미터값으로 삭제가능) or service 2개 사용
			/*
			Map<String, Object> deleteMap = boardService.delete(no); // 여기서 delete가 실행 되는건가
			BoardVo getBoard = (BoardVo)deleteMap.get("getBoard");
			int boardUserNo = getBoard.getUserNo();
			*/
			/*
			BoardVo getBoard = boardService.getBoardModifyForm(no);
			int boardUserNo = getBoard.getUserNo();
			
			System.out.println(authUserNo);
			System.out.println(boardUserNo);
			
			if (authUserNo == boardUserNo ) { // ((BoardVo)boardService.delete(no).get("getBoard")).getUserNo()
//				deleteMap.get("delete");
				boardService.delete(no);
				
				return "redirect:/board/list";
			}
			*/
			
			boardVo.setNo(no);
			boardVo.setUserNo(authUserNo);
			
			boardService.delete(boardVo);
			
			return "redirect:/board/list";
		}
		
	}

}
