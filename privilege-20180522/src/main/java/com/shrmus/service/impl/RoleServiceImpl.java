package com.shrmus.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shrmus.mapper.RoleMapper;
import com.shrmus.mapper.RolePrivilegeMapper;
import com.shrmus.mapper.UserPrivilegeMapper;
import com.shrmus.mapper.UserRoleMapper;
import com.shrmus.pojo.Role;
import com.shrmus.pojo.RoleExample;
import com.shrmus.pojo.RolePrivilege;
import com.shrmus.pojo.RolePrivilegeExample;
import com.shrmus.pojo.RolePrivilegeExample.Criteria;
import com.shrmus.pojo.UserPrivilegeExample;
import com.shrmus.pojo.UserRoleExample;
import com.shrmus.service.RoleService;

/**
 * 
 * <p>Title:RoleServiceImpl</p>
 * <p>Description:</p>
 * @author Shr
 * @date 2018年5月22日上午3:35:34
 * @version
 */
@Service
public class RoleServiceImpl implements RoleService{
	@Autowired
	private RoleMapper roleMapper;
	@Autowired
	private RolePrivilegeMapper rolePrivilegeMapper;
	@Autowired
	private UserRoleMapper userRoleMapper;
	@Autowired
	private UserPrivilegeMapper userPrivilegeMapper;
	
	/**
	 * 添加角色
	 * @param role
	 */
	@Transactional
	public void addRole(Role role) {
		roleMapper.insert(role);
	}

	/**
	 * 根据角色名称获取角色信息
	 */
	@Override
	public Role getRoleByName(String roleName) {
		RoleExample roleExample = new RoleExample();
		com.shrmus.pojo.RoleExample.Criteria criteria = roleExample.createCriteria();
		criteria.andRoleNameEqualTo(roleName);
		List<Role> roleList = roleMapper.selectByExample(roleExample);
		Role role = roleList.get(0);
		return role;
	}

	/**
	 * 查找所有角色
	 */
	@Override
	public List<Role> getRoleList() {
		RoleExample roleExample = new RoleExample();
		List<Role> roleList = roleMapper.selectByExample(roleExample);
		return roleList;
	}

	/**
	 * 修改角色信息
	 */
	@Transactional
	public void updateRole(Role role) {
		roleMapper.updateByPrimaryKeySelective(role);
	}

	/**
	 * 删除角色的信息
	 */
	@Transactional
	public void deleteRole(Integer roleId) {
		// 查找出角色的权限
		RolePrivilegeExample rolePrivilegeExample = new RolePrivilegeExample();
		Criteria criteria = rolePrivilegeExample.createCriteria();
		criteria.andRoleIdEqualTo(roleId);
		List<RolePrivilege> rolePrivilegeList = rolePrivilegeMapper.selectByExample(rolePrivilegeExample);
		
		// 删除拥有这个角色的用户的权限 TODO 没有考虑用户其他角色也拥有这个权限的情况
		UserPrivilegeExample userPrivilegeExample = new UserPrivilegeExample();
		com.shrmus.pojo.UserPrivilegeExample.Criteria userPrivilegeExampleCriteria = userPrivilegeExample.createCriteria();
		for(RolePrivilege rolePrivilege : rolePrivilegeList) {
			userPrivilegeExampleCriteria.andPrivilegeIdEqualTo(rolePrivilege.getPrivilegeId());
		}
		userPrivilegeMapper.deleteByExample(userPrivilegeExample);
		
		// 删除用户的角色
		UserRoleExample userRoleExample = new UserRoleExample();
		com.shrmus.pojo.UserRoleExample.Criteria userRoleExampleCriteria = userRoleExample.createCriteria();
		userRoleExampleCriteria.andRoleIdEqualTo(roleId);
		userRoleMapper.deleteByExample(userRoleExample);
		
		// 删除角色的权限
		rolePrivilegeMapper.deleteByExample(rolePrivilegeExample);
		// 删除角色
		roleMapper.deleteByPrimaryKey(roleId);
	}

}
