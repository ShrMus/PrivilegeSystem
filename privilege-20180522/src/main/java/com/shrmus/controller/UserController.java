package com.shrmus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import com.shrmus.pojo.User;
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
@Scope("prototype")
public class UserController {
	@Autowired
	private UserService userService;
	
	/**
	 * 删除用户
	 * @param userId
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping("/user/delete/{userId}")
	public ModelAndView deleteUser(@PathVariable("userId") Integer userId,ModelAndView modelAndView) {
		if(null == userId) {
			modelAndView.addObject("message", "调用deleteUser出现错误，userId is null");
			modelAndView.setViewName("redirect:/message.jsp");
			return modelAndView;
		}
		userService.deleteUser(userId);
		modelAndView.setViewName("redirect:/user/list");
		return modelAndView;
	}
	
	/**
	 * 修改用户
	 * @param user
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping("/user/update")
	public ModelAndView updateUser(User user,ModelAndView modelAndView) {
		userService.updateUser(user);
		modelAndView.setViewName("user/list");
		return modelAndView;
	}
	
	/**
	 * 跳转到修改用户页面
	 * @param userId
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping("/user/updateui/{userId}")
	public ModelAndView updateUserUI(@PathVariable("userId") Integer userId,ModelAndView modelAndView) {
		if(null == userId) {
			modelAndView.addObject("message", "调用updateUserUI出现错误，userId is null");
			modelAndView.setViewName("redirect:/message.jsp");
			return modelAndView;
		}
		User user = userService.getUserById(userId);
		modelAndView.addObject("user", user);
		modelAndView.setViewName("user/updateui");
		return modelAndView;
	}
	
	/**
	 * 添加用户
	 * @param user
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping("/user/add")
	public ModelAndView addUser(User user,ModelAndView modelAndView) {
		userService.addUser(user);
		modelAndView.setViewName("user/list");
		return modelAndView;
	}
	
	/**
	 * 跳转到添加用户页面
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping("/user/addui")
	public ModelAndView addUserUI(ModelAndView modelAndView) {
		modelAndView.setViewName("user/addui");
		return modelAndView;
	}
	
	/**
	 * 查找所有用户
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping("/user/list")
	public ModelAndView getUserList(ModelAndView modelAndView) {
		List<User> userList = userService.getUserList();
		modelAndView.addObject("userList", userList);
		modelAndView.setViewName("user/list");
		return modelAndView;
	}
	
	
	/**
	 * 跳转到主页
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping("/main")
	public ModelAndView toMain(View view,ModelAndView modelAndView) {
		modelAndView.setViewName("redirect:/index.jsp");
		return modelAndView;
	}
}
