package com.shrmus.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shrmus.pojo.Role;
import com.shrmus.pojo.User;
import com.shrmus.service.RoleService;
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
	@Autowired
	private RoleService roleService;
	
	/**
	 * 退出系统
	 * @param session
	 * @return
	 */
	@RequestMapping("/user/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("user");
		session.invalidate();
		return "redirect:/main";
	}
	
	/**
	 * 登录
	 * @param user
	 * @param modelAndView
	 * @param request
	 * @return
	 */
	@RequestMapping("/user/login")
	public ModelAndView loginUser(User user,ModelAndView modelAndView,HttpServletRequest request,RedirectAttributes redirectAttributes) {
		user = userService.loginUser(user);
		if(null == user) {
			// redirectAttributes.addFlashAttribute("message1", "用户名或密码不正确");
			redirectAttributes.addAttribute("message", "用户名或密码不正确！");
			modelAndView.setViewName("redirect:/login.jsp");
			return modelAndView;
		}
		request.getSession().setAttribute("user", user);
		modelAndView.setViewName("redirect:/index.jsp");
		return modelAndView;
	}
	
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
	public ModelAndView updateUser(@RequestParam(value="roleId",required=false) Integer[] roleIdArray, // 
			User user,ModelAndView modelAndView) {
		List<Role> roleList = new ArrayList<>();
		for(Integer roleId : roleIdArray) {
			Role role = new Role();
			role.setRoleId(roleId);
			roleList.add(role);
		}
		user.setUserRoleList(roleList);
		userService.updateUser(user);
		modelAndView.setViewName("redirect:/user/list");
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
		modelAndView.addObject("userinfo", user);
		List<Role> userRoleList = user.getUserRoleList();
		List<Integer> userRoleIdList = new ArrayList<>();
		for(Role role : userRoleList) {
			userRoleIdList.add(role.getRoleId());
		}
		modelAndView.addObject("userRoleIdList", userRoleIdList);
		List<Role> roleList = roleService.getRoleList();
		modelAndView.addObject("roleList", roleList);
		modelAndView.setViewName("user/updateui");
		return modelAndView;
	}
	
	/**
	 * 添加用户
	 * @param roleIdArray
	 * @param user
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping("/user/add")
	public ModelAndView addUser(@RequestParam(value="roleId",required=false) Integer[] roleIdArray, // 
											User user,ModelAndView modelAndView) {
		List<Role> roleList = new ArrayList<>();
		for(Integer roleId : roleIdArray) {
			Role role = new Role();
			role.setRoleId(roleId);
			roleList.add(role);
		}
		user.setUserRoleList(roleList);
		userService.addUser(user);
		modelAndView.setViewName("redirect:/user/list");
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
		List<Role> roleList = roleService.getRoleList();
		modelAndView.addObject("roleList", roleList);
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
		List<Role> roleList = roleService.getRoleList();
		modelAndView.addObject("roleList", roleList);
		return modelAndView;
	}
	
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
