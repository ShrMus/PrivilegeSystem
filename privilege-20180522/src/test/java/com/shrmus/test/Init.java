package com.shrmus.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.shrmus.pojo.Privilege;
import com.shrmus.pojo.Role;
import com.shrmus.pojo.User;
import com.shrmus.service.PrivilegeService;
import com.shrmus.service.RoleService;
import com.shrmus.service.UserService;

public class Init {
	private ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:spring/applicationContext.xml");
	
	
	/**
	 * Step1 : 初始化权限
	 */
	@Test
	public void initPrivilege() {
		// 先初始化权限
		PrivilegeService privilegeService = ac.getBean(PrivilegeService.class);
		// 权限对象名根据权限url命名，管理类的就用manager作后缀
		
		// -----------------------------权限管理start-----------------------------
		Privilege privilegemanager = new Privilege("权限管理","/privilege/list");
		// 管理类没有父权限
		privilegemanager.setPrivilegeParentId(0);
		privilegemanager.setParentPrivilege(null);
		privilegeService.addPrivilege(privilegemanager);
		
		// 添加权限
		// 因为要用到权限管理的id,直接运行这个Test方法会导致获取不到这个id，所以再根据权限名称查找
		privilegemanager = privilegeService.getPrivilegeByName("权限管理");
		Privilege privilegeadd = new Privilege("添加权限","/privilege/add");
		// 设置父权限
		privilegeadd.setParentPrivilege(privilegemanager);
		privilegeadd.setPrivilegeParentId(privilegemanager.getPrivilegeId());
		privilegeService.addPrivilege(privilegeadd);
		
		// 修改权限
		Privilege privilegeupdate = new Privilege("修改权限", "/privilege/update");
		privilegeupdate.setParentPrivilege(privilegemanager);
		privilegeupdate.setPrivilegeParentId(privilegemanager.getPrivilegeId());
		privilegeService.addPrivilege(privilegeupdate);
		
		// 删除权限
		Privilege privilegedelete = new Privilege("修改权限", "/privilege/delete");
		privilegedelete.setParentPrivilege(privilegemanager);
		privilegedelete.setPrivilegeParentId(privilegemanager.getPrivilegeId());
		privilegeService.addPrivilege(privilegedelete);
		
		// 分配权限
		Privilege privilegeallocation = new Privilege("分配权限","/privilege/allocation");
		privilegeallocation.setParentPrivilege(privilegemanager);
		privilegeallocation.setPrivilegeParentId(privilegemanager.getPrivilegeId());
		privilegeService.addPrivilege(privilegeallocation);
		
		// 权限列表
		Privilege privilegelist = new Privilege("分配权限","/privilege/list");
		privilegelist.setParentPrivilege(privilegemanager);
		privilegelist.setPrivilegeParentId(privilegemanager.getPrivilegeId());
		privilegeService.addPrivilege(privilegelist);
		// -----------------------------权限管理end-----------------------------
		
		
		// -----------------------------角色管理start-----------------------------
		Privilege rolemanager = new Privilege("角色管理","/role/list");
		rolemanager.setParentPrivilege(null);
		rolemanager.setPrivilegeParentId(0);
		privilegeService.addPrivilege(rolemanager);
		
		// 添加角色
		rolemanager = privilegeService.getPrivilegeByName("角色管理");
		Privilege roleadd = new Privilege("添加角色","/role/add");
		roleadd.setParentPrivilege(rolemanager);
		roleadd.setPrivilegeParentId(rolemanager.getPrivilegeId());
		privilegeService.addPrivilege(roleadd);
		
		// 修改角色
		Privilege roleupdate = new Privilege("修改角色","/role/update");
		roleupdate.setParentPrivilege(rolemanager);
		roleupdate.setPrivilegeParentId(rolemanager.getPrivilegeId());
		privilegeService.addPrivilege(roleupdate);
		
		// 删除角色
		Privilege roledelete = new Privilege("删除角色","/role/delete");
		roledelete.setParentPrivilege(rolemanager);
		roledelete.setPrivilegeParentId(rolemanager.getPrivilegeId());
		privilegeService.addPrivilege(roledelete);
		
		// 角色列表
		Privilege rolelist = new Privilege("角色列表","/role/list");
		rolelist.setParentPrivilege(rolemanager);
		rolelist.setPrivilegeParentId(rolemanager.getPrivilegeId());
		privilegeService.addPrivilege(rolelist);
		// -----------------------------权限管理end-----------------------------
		
		
		// -----------------------------用户管理start-----------------------------
		Privilege usermanager = new Privilege("用户管理","/user/list");
		usermanager.setParentPrivilege(null);
		usermanager.setPrivilegeParentId(0);
		privilegeService.addPrivilege(usermanager);
		
		// 添加用户
		usermanager = privilegeService.getPrivilegeByName("用户管理");
		Privilege useradd = new Privilege("添加用户","/user/add");
		useradd.setParentPrivilege(usermanager);
		useradd.setPrivilegeParentId(usermanager.getPrivilegeId());
		privilegeService.addPrivilege(useradd);
		
		// 修改用户
		Privilege userupdate = new Privilege("修改用户","/user/update");
		userupdate.setParentPrivilege(usermanager);
		userupdate.setPrivilegeParentId(usermanager.getPrivilegeId());
		privilegeService.addPrivilege(userupdate);
		
		// 删除用户
		Privilege userdelete = new Privilege("删除用户","/user/delete");
		userdelete.setParentPrivilege(usermanager);
		userdelete.setPrivilegeParentId(usermanager.getPrivilegeId());
		privilegeService.addPrivilege(userdelete);
		
		// 用户列表
		Privilege userlist = new Privilege("用户列表","/user/list");
		userlist.setParentPrivilege(usermanager);
		userlist.setPrivilegeParentId(usermanager.getPrivilegeId());
		privilegeService.addPrivilege(userlist);
		// -----------------------------用户管理end-----------------------------
	}
	
	/**
	 * Step2 : 初始化角色
	 */
	@Test
	public void initRole() {
		// 再初始化角色
		RoleService roleService = ac.getBean(RoleService.class);
		Role role1 = new Role();
		role1.setRoleName("超级管理员");
		roleService.addRole(role1);
		
		Role role2 = new Role();
		role2.setRoleName("普通管理员");
		roleService.addRole(role2);
		
		Role role3 = new Role();
		role3.setRoleName("普通用户");
		roleService.addRole(role3);
		
		// 给超级管理员分配权限
		role1 = roleService.getRoleByName("超级管理员");
		PrivilegeService privilegeService = ac.getBean(PrivilegeService.class);
		List<Privilege> privilegeList = privilegeService.getPrivilegeList();
		List<Integer> allocationPrivilegeList = new ArrayList<>();
		for(Privilege privilege : privilegeList) {
			allocationPrivilegeList.add(privilege.getPrivilegeId());
		}
		privilegeService.allocationPrivileges("role", role1.getRoleId(), allocationPrivilegeList);
	}
	
	/**
	 * Step3 : 初始化用户
	 */
	@Test
	public void initUser() {
		// 最后初始化用户，分配所有权限
		UserService userService = ac.getBean(UserService.class);
		User user1 = new User();
		user1.setUserUsername("admin");
		user1.setUserPassword("admin");
		
		// 给这个用户分配超级管理员角色 TODO 暂时只做了一个用户一个角色的情况
		RoleService roleService = ac.getBean(RoleService.class);
		Role role = roleService.getRoleByName("超级管理员");
		
		List<Role> userRoleList = new ArrayList<>();
		userRoleList.add(role);
		// 设置用户的角色
		user1.setUserRoleList(userRoleList);
		userService.addUser(user1);
		
		// 给用户分配权限
		user1 = userService.getUserByUsername(user1.getUserUsername());
		PrivilegeService privilegeService = ac.getBean(PrivilegeService.class);
		// 获取用户的角色
		userRoleList = user1.getUserRoleList();
		List<Integer> allocationPrivilegeList = new ArrayList<>();
		// 获取用户拥有的所有角色的权限
		for(Role userRole : userRoleList) {
			List<Privilege> privilegeList = privilegeService.getPrivilegeListByRoleId(userRole.getRoleId());
			for(Privilege privilege : privilegeList) {
				// 如果用户的第二个角色和第一个角色拥有同样的权限，只取一个
				if(false == allocationPrivilegeList.contains(privilege.getPrivilegeId())) {
					allocationPrivilegeList.add(privilege.getPrivilegeId());
				}
			}
		}
		privilegeService.allocationPrivileges("user", user1.getUserId(), allocationPrivilegeList);
	}
}
