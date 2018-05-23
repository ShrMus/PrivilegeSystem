package com.shrmus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.shrmus.pojo.Privilege;
import com.shrmus.service.PrivilegeService;

/**
 * 
 * <p>Title:PrivilegeController</p>
 * <p>Description:</p>
 * @author Shr
 * @date 2018年5月22日下午11:38:28
 * @version
 */
@Controller
@Scope("prototype")
public class PrivilegeController {
	@Autowired
	private PrivilegeService privilegeService;
	
	
	/**
	 * 删除权限
	 * @param privilegeId
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping("/privilege/delete/{privilegeId}")
	public ModelAndView deletePrivilege(@PathVariable("privilegeId") Integer privilegeId,ModelAndView modelAndView) {
		if(null == privilegeId) {
			modelAndView.addObject("message", "调用deletePrivilege出现错误，privilegeId is null");
			modelAndView.setViewName("redirect:/message.jsp");
			return modelAndView;
		}
		privilegeService.deletePrivilege(privilegeId);
		modelAndView.setViewName("redirect:/privilege/list");
		return modelAndView;
	}
	
	/**
	 * 修改权限
	 * @param privilege
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping("/privilege/update")
	public ModelAndView updatePrivilege(Privilege privilege,ModelAndView modelAndView) {
		privilegeService.updatePrivilege(privilege);
		modelAndView.setViewName("privilege/list");
		return modelAndView;
	}
	
	/**
	 * 跳转到修改权限页面
	 * @param privilegeId
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping("/privilege/updateui/{privilegeId}")
	public ModelAndView updatePrivilegeUI(@PathVariable("privilegeId") Integer privilegeId,ModelAndView modelAndView) {
		if(null == privilegeId) {
			modelAndView.addObject("message", "调用updatePrivilegeUI出现错误，privilegeId is null");
			modelAndView.setViewName("redirect:/message.jsp");
			return modelAndView;
		}
		Privilege privilege = privilegeService.getPrivilegeById(privilegeId);
		modelAndView.addObject("privilege", privilege);
		List<Privilege> parentPrivilegeList = privilegeService.getParentPrivilegeList();
		modelAndView.addObject("parentPrivilegeList", parentPrivilegeList);
		modelAndView.setViewName("privilege/updateui");
		return modelAndView;
	}
	
	/**
	 * 添加权限
	 * @param privilege
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping("/privilege/add")
	public ModelAndView addPrivilege(Privilege privilege,ModelAndView modelAndView) {
		privilegeService.addPrivilege(privilege);
		modelAndView.setViewName("privilege/list");
		return modelAndView;
	}
	
	/**
	 * 跳转到添加权限页面
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping("/privilege/addui")
	public ModelAndView addPrivilegeUI(ModelAndView modelAndView) {
		List<Privilege> parentPrivilegeList = privilegeService.getParentPrivilegeList();
		modelAndView.addObject("parentPrivilegeList", parentPrivilegeList);
		modelAndView.setViewName("privilege/addui");
		return modelAndView;
	}
	
	/**
	 * 查找所有父权限跳转到权限列表页面
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping("/privilege/list")
	public ModelAndView getParentPrivilegeList(ModelAndView modelAndView) {
		List<Privilege> parentPrivilegeList = privilegeService.getParentPrivilegeList();
		modelAndView.addObject("parentPrivilegeList", parentPrivilegeList);
		modelAndView.setViewName("privilege/list");
		return modelAndView;
	}
	
}
