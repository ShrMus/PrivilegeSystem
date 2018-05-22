package com.shrmus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import com.shrmus.service.UserService;

/**
 * 
 * <p>Title:UserController</p>
 * <p>Description:</p>
 * @author Shr
 * @date 2018年5月22日下午11:33:17
 * @version
 */
@Controller
@Scope("ptototype")
public class UserController {
	@Autowired
	private UserService userService;
	
	/**
	 * 跳转到主页
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping("/main")
	public ModelAndView toMain(ModelAndView modelAndView) {

		modelAndView.setViewName("redirect:/index.jsp");
		return modelAndView;
	}
	
	
	
}
