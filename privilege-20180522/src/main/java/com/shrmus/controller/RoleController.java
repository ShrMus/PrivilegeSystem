package com.shrmus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.shrmus.pojo.Role;
import com.shrmus.service.RoleService;

/**
 * 
 * <p>Title:RoleService</p>
 * <p>Description:</p>
 * @author Shr
 * @date 2018年5月22日下午11:37:54
 * @version
 */
@Controller
@Scope("prototype")
public class RoleController {
	@Autowired
	private RoleService roleService;
	
	/**
	 * 删除角色
	 * @param roleId
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping("/role/delete/{roleId}")
	public ModelAndView deleteRole(@PathVariable("roleId") Integer roleId,ModelAndView modelAndView) {
		if(null == roleId) {
			modelAndView.addObject("message", "调用deleteRole出现错误，roleId is null");
			modelAndView.setViewName("redirect:/message.jsp");
			return modelAndView;
		}
		roleService.deleteRole(roleId);
		modelAndView.setViewName("redirect:/role/list");
		return modelAndView;
	}
	
	/**
	 * 修改角色
	 * @param role
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping("/role/update")
	public ModelAndView updateRole(Role role,ModelAndView modelAndView) {
		roleService.updateRole(role);
		modelAndView.setViewName("role/list");
		return modelAndView;
	}
	
	/**
	 * 跳转到修改角色页面
	 * @param roleId
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping("/role/updateui/{roleId}")
	public ModelAndView updateRoleUI(@PathVariable("roleId") Integer roleId,ModelAndView modelAndView) {
		if(null == roleId) {
			modelAndView.addObject("message", "调用updateRoleUI出现错误，roleId is null");
			modelAndView.setViewName("redirect:/message.jsp");
			return modelAndView;
		}
		Role role = roleService.getRoleById(roleId);
		modelAndView.addObject("role", role);
		modelAndView.setViewName("role/updateui");
		return modelAndView;
	}
	
	/**
	 * 添加角色
	 * @param role
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping("/role/add")
	public ModelAndView addRole(Role role,ModelAndView modelAndView) {
		roleService.addRole(role);
		modelAndView.setViewName("role/list");
		return modelAndView;
	}
	
	/**
	 * 跳转到添加角色页面
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping("/role/addui")
	public ModelAndView addRoleUI(ModelAndView modelAndView) {
		modelAndView.setViewName("role/addui");
		return modelAndView;
	}
	
	/**
	 * 查找所有的角色
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping("/role/list")
	public ModelAndView getRoleList(ModelAndView modelAndView) {
		List<Role> roleList = roleService.getRoleList();
		modelAndView.addObject("roleList", roleList);
		modelAndView.setViewName("role/list");
		return modelAndView;
	}
	
}
