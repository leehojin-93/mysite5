package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.UserService;
import com.javaex.vo.UserVo;

@Controller()
@RequestMapping(value="/user", method = { RequestMethod.GET, RequestMethod.POST })
public class UserController {
	
	@Autowired
	private UserService userService;
	
	// loginForm
	@RequestMapping(value="/loginForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String loginForm() {
		
		return "/user/loginForm";
	}
	
	// login
	@RequestMapping(value="/login", method = { RequestMethod.GET, RequestMethod.POST })
	public String login(@ModelAttribute UserVo userVo, HttpSession session) {
		UserVo authUser = userService.userInfo(userVo);
		if (authUser != null) {
			session.setAttribute("authUser", authUser);
			// session.setAttribute("authUser", userDao.userInfo(userVo));
			
			return "redirect:/main";
		} else {
			return "redirect:/user/loginForm?result=fail";
		}

	}
	
	// logout
	@RequestMapping(value="/logout", method = { RequestMethod.GET, RequestMethod.POST })
	public String logout(HttpSession session) {
		session.removeAttribute("authUser");
		session.invalidate();
		
		return "redirect:/main";
	}
	
	// joinForm
	@RequestMapping(value="/joinForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String joinForm() {
		
		return "/user/joinForm";
	}
	
	// join
	@RequestMapping(value="/join", method = { RequestMethod.GET, RequestMethod.POST })
	public String join(@ModelAttribute UserVo userVo) {
		userService.userJoin(userVo);
		
		return "/user/joinOk";
	}
	
	// modifyForm
	@RequestMapping(value="/modifyForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String modifyForm(Model model, HttpSession session) {
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		
		if (authUser != null) {
			model.addAttribute("authUserInfo", userService.authUserInfo(authUser.getNo()));
			 
			return "/user/modifyForm";
		
		} else {
			
			return "/user/loginForm";
		}
	}
	
	// modify
	@RequestMapping(value="/modify", method = { RequestMethod.GET, RequestMethod.POST })
	public String modify(@ModelAttribute UserVo userVo, HttpSession session) {
//		userVo.setNo(((UserVo)session.getAttribute("authUser")).getNo());
		// int no = ((UserVo)session.getAttribute("authUser")).getNo()
		// userVo.setNo(no);
		
		UserVo authUser = userService.update(userVo);
		
//		((UserVo)session.getAttribute("authUser")).setName(authUser.getName());
		session.setAttribute("authUser", authUser);
		
		return "redirect:/main";
	}

}
