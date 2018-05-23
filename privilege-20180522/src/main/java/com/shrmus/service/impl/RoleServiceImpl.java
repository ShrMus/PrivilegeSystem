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
import com.shrmus.pojo.UserRole;
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
	 * 根据角色id获取角色信息
	 */
	@Override
	public Role getRoleById(Integer roleId) {
		RoleExample roleExample = new RoleExample();
		com.shrmus.pojo.RoleExample.Criteria criteria = roleExample.createCriteria();
		criteria.andRoleIdEqualTo(roleId);
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
		删除角色的信息,删除角色之前要先删除拥有这个角色的用户的权限
	 	查找拥有这个角色的用户
		查找用户的其他角色
		查找用户其他角色的权限
		其他角色没有这个权限就删除，有就什么都不做
	 */
	@Transactional
	public void deleteRole(Integer roleId) {
		// 查找出角色的权限
		RolePrivilegeExample rolePrivilegeExample = new RolePrivilegeExample();
		Criteria criteria = rolePrivilegeExample.createCriteria();
		criteria.andRoleIdEqualTo(roleId);
		List<RolePrivilege> rolePrivilegeList = rolePrivilegeMapper.selectByExample(rolePrivilegeExample);
		
		// 删除拥有这个角色的用户的权限 TODO 没有考虑用户其他角色也拥有这个权限的情况
		// 查找拥有这个角色的用户
		UserRoleExample userRoleExample = new UserRoleExample();
		com.shrmus.pojo.UserRoleExample.Criteria userRoleExampleCriteria = userRoleExample.createCriteria();
		userRoleExampleCriteria.andRoleIdEqualTo(roleId);
		List<UserRole> userRoleList = userRoleMapper.selectByExample(userRoleExample);
		
		for(RolePrivilege rolePrivilege : rolePrivilegeList) {
			// 查找用户的其他角色
			// 标志
			boolean tag1 = false;
			for(UserRole userRole : userRoleList) {
				UserRoleExample userRoleExample2 = new UserRoleExample();
				com.shrmus.pojo.UserRoleExample.Criteria userRoleExampleCriteria2 = userRoleExample2.createCriteria();
				userRoleExampleCriteria2.andUserIdEqualTo(userRole.getUserId());
				List<UserRole> userRoleList2 = userRoleMapper.selectByExample(userRoleExample2);
				
				// 标志2
				boolean tag2 = false;
				// 查找用户其他角色的权限
				for(UserRole userRole2 : userRoleList2) {
					// 当前角色不再判断
					if(roleId != userRole2.getRoleId()) {
						// 根据角色id和权限id查找，只有一条记录
						RolePrivilegeExample rolePrivilegeExample1 = new RolePrivilegeExample();
						com.shrmus.pojo.RolePrivilegeExample.Criteria criteria1 = rolePrivilegeExample1.createCriteria();
						criteria1.andRoleIdEqualTo(userRole2.getRoleId());
						criteria1.andPrivilegeIdEqualTo(rolePrivilege.getPrivilegeId());
						List<RolePrivilege> rolePrivilegeList1 = rolePrivilegeMapper.selectByExample(rolePrivilegeExample1);
						// 这个用户的其他角色也有这个权限
						if(0 != rolePrivilegeList1.size()) {
							tag2 = true;
							break;
						}
							
					}
					// 标志这个用户的其他角色有这个权限
					if(true == tag2) {
						tag1 = true;
						break;
					}
				}
				if(true == tag1) {
					break;
				}
			}
			// 用户的其他角色没有这个权限
			if(false == tag1) {
				// 在用户权限表中删除这个权限
				UserPrivilegeExample userPrivilegeExample = new UserPrivilegeExample();
				com.shrmus.pojo.UserPrivilegeExample.Criteria userPrivilegeExampleCriteria = userPrivilegeExample.createCriteria();
				for(UserRole userRole : userRoleList) {
					userPrivilegeExampleCriteria.andUserIdEqualTo(userRole.getUserId());
					userPrivilegeExampleCriteria.andPrivilegeIdEqualTo(rolePrivilege.getPrivilegeId());
				}
				userPrivilegeMapper.deleteByExample(userPrivilegeExample);
			}
		}
		
		// 删除用户的角色
		for(UserRole userRole : userRoleList) {
			userRoleExampleCriteria.andUserIdEqualTo(userRole.getUserId());
			userRoleMapper.deleteByExample(userRoleExample);
		}
		
		// 删除角色的权限
		rolePrivilegeMapper.deleteByExample(rolePrivilegeExample);
		// 删除角色
		roleMapper.deleteByPrimaryKey(roleId);
	}

}
