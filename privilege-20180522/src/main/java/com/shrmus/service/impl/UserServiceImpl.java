package com.shrmus.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shrmus.mapper.RolePrivilegeMapper;
import com.shrmus.mapper.UserMapper;
import com.shrmus.mapper.UserPrivilegeMapper;
import com.shrmus.mapper.UserRoleMapper;
import com.shrmus.pojo.Role;
import com.shrmus.pojo.RolePrivilege;
import com.shrmus.pojo.RolePrivilegeExample;
import com.shrmus.pojo.User;
import com.shrmus.pojo.UserExample;
import com.shrmus.pojo.UserPrivilege;
import com.shrmus.pojo.UserPrivilegeExample;
import com.shrmus.pojo.UserPrivilegeExample.Criteria;
import com.shrmus.pojo.UserRole;
import com.shrmus.pojo.UserRoleExample;
import com.shrmus.service.UserService;

/**
 * 
 * <p>Title:UserServiceImpl</p>
 * <p>Description:</p>
 * @author Shr
 * @date 2018年5月22日上午4:47:49
 * @version
 */
@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private UserRoleMapper userRoleMapper;
	@Autowired
	private UserPrivilegeMapper userPrivilegeMapper;
	@Autowired
	private RolePrivilegeMapper rolePrivilegeMapper;

	/**
	 * 登录
	 */
	public User loginUser(User user) {
		UserExample userExample = new UserExample();
		com.shrmus.pojo.UserExample.Criteria criteria = userExample.createCriteria();
		criteria.andUserUsernameEqualTo(user.getUserUsername());
		criteria.andUserPasswordEqualTo(user.getUserPassword());
		List<User> userList = userMapper.selectByExample(userExample);
		if(0 == userList.size()) {
			return null;
		}else {
			user = userList.get(0);
			return user;
		}
	}
	
	/**
	 * 添加用户
	 */
	@Transactional
	public void addUser(User user) {
		// 密码加密
		// 添加用户
		userMapper.insert(user);
		// 添加用户的权限
		int userId = userMapper.getMaxPrimaryKey();
		List<Role> roleList = user.getUserRoleList();
		List<Integer> privilegeIdList = new ArrayList<>();
		// 查找角色的权限
		for(Role role : roleList) {
			// 添加用户的角色
			UserRole userRole = new UserRole(userId, role.getRoleId());
			userRoleMapper.insert(userRole);
			
			RolePrivilegeExample rolePrivilegeExample = new RolePrivilegeExample();
			com.shrmus.pojo.RolePrivilegeExample.Criteria criteria = rolePrivilegeExample.createCriteria();
			criteria.andRoleIdEqualTo(role.getRoleId());
			List<RolePrivilege> rolePrivilegeList = rolePrivilegeMapper.selectByExample(rolePrivilegeExample);
			// 将其中一个角色的权限添加到集合中，如果有多个角色，将多个角色共同拥有的权限去重
			for(RolePrivilege rolePrivilege : rolePrivilegeList) {
				if(!privilegeIdList.contains(rolePrivilege.getPrivilegeId())) {
					privilegeIdList.add(rolePrivilege.getPrivilegeId());
				}
			}
		}
		for(Integer privilegeId : privilegeIdList) {
			UserPrivilege userPrivilege = new UserPrivilege(userId,privilegeId);
			userPrivilegeMapper.insert(userPrivilege);
		}
	}

	/**
	 * 根据用户名获取用户信息
	 */
	@Override
	public User getUserByUsername(String username) {
		UserExample userExample = new UserExample();
		com.shrmus.pojo.UserExample.Criteria criteria = userExample.createCriteria();
		criteria.andUserUsernameEqualTo(username);
		List<User> userList = userMapper.selectByExample(userExample);
		User user = userList.get(0);
		return user;
	}
	
	/**
	 * 根据用户id获取用户信息
	 */
	@Override
	public User getUserById(Integer userId) {
		UserExample userExample = new UserExample();
		com.shrmus.pojo.UserExample.Criteria criteria = userExample.createCriteria();
		criteria.andUserIdEqualTo(userId);
		List<User> userList = userMapper.selectByExample(userExample);
		User user = userList.get(0);
		return user;
	}

	/**
	 * 修改用户的信息
	 */
	@Transactional
	public void updateUser(User user) {
		// 修改用户的角色
		// 取出用户需要修改的角色
		List<Role> roleList = user.getUserRoleList();
		// 获取需要修改的角色id
		List<Integer> roleIdList = new ArrayList<>();
		for(Role role : roleList) {
			roleIdList.add(role.getRoleId());
		}
		// 查找用户原有的角色
		int userId = user.getUserId();
		UserRoleExample userRoleExample = new UserRoleExample();
		com.shrmus.pojo.UserRoleExample.Criteria userRoleExampleCriteria = userRoleExample.createCriteria();
		userRoleExampleCriteria.andUserIdEqualTo(userId);
		List<UserRole> userRoleList = userRoleMapper.selectByExample(userRoleExample);
		// 获取原有的角色id
		List<Integer> userRoleIdList = new ArrayList<>();
		for(UserRole userRole : userRoleList) {
			userRoleIdList.add(userRole.getRoleId());
		}
		for(Integer userRoleId : userRoleIdList) {
			// 如果用户需要修改的角色id包含用户原有的角色id，说明用户还属于这个角色,反之,用户不属于这个角色，需要将用户的这个角色删除
			if(!roleIdList.contains(userRoleId)) {
				UserRoleExample userRoleExample2 = new UserRoleExample();
				com.shrmus.pojo.UserRoleExample.Criteria userRoleExample2Criteria = userRoleExample2.createCriteria();
				userRoleExample2Criteria.andUserIdEqualTo(userId);
				userRoleExample2Criteria.andRoleIdEqualTo(userRoleId);
				userRoleMapper.deleteByExample(userRoleExample2);
			}
		}
		for(Integer roleId : roleIdList) {
			// 如果用户原来的角色不包含这个角色，添加用户的角色
			if(!userRoleIdList.contains(roleId)) {
				UserRole userRole = new UserRole(userId, roleId);
				userRoleMapper.insert(userRole);
			}
		}
		
		// 修改用户的权限
		// 查找用户需要修改的角色的权限
		List<Integer> privilegeIdList = new ArrayList<>();
		for(Role role : roleList) {
			RolePrivilegeExample rolePrivilegeExample = new RolePrivilegeExample();
			com.shrmus.pojo.RolePrivilegeExample.Criteria criteria = rolePrivilegeExample.createCriteria();
			criteria.andRoleIdEqualTo(role.getRoleId());
			List<RolePrivilege> rolePrivilegeList = rolePrivilegeMapper.selectByExample(rolePrivilegeExample);
			// 将其中一个角色的权限添加到集合中，如果有多个角色，将多个角色共同拥有的权限去重
			for(RolePrivilege rolePrivilege : rolePrivilegeList) {
				if(!privilegeIdList.contains(rolePrivilege.getPrivilegeId())) {
					privilegeIdList.add(rolePrivilege.getPrivilegeId());
				}
			}
		}
		// 查找用户已有的权限
		UserPrivilegeExample userPrivilegeExample = new UserPrivilegeExample();
		Criteria criteria = userPrivilegeExample.createCriteria();
		criteria.andUserIdEqualTo(userId);
		List<UserPrivilege> userPrivilegeList = userPrivilegeMapper.selectByExample(userPrivilegeExample);
		List<Integer> userPrivilegeIdList = new ArrayList<>();
		for(UserPrivilege userPrivilege : userPrivilegeList) {
			userPrivilegeIdList.add(userPrivilege.getPrivilegeId());
		}
		for(Integer userPrivilegeId : userPrivilegeIdList) {
			if(!privilegeIdList.contains(userPrivilegeId)) {
				UserPrivilegeExample userPrivilegeExample2 = new UserPrivilegeExample();
				Criteria criteria2 = userPrivilegeExample2.createCriteria();
				criteria2.andUserIdEqualTo(userId);
				criteria2.andPrivilegeIdEqualTo(userPrivilegeId);
				userPrivilegeMapper.deleteByExample(userPrivilegeExample2);
			}
		}
		for(Integer privilegeId : privilegeIdList) {
			// 如果用户原来的权限不包含这个权限，添加用户的权限
			if(!userPrivilegeIdList.contains(privilegeId)) {
				UserPrivilege userPrivilege = new UserPrivilege(userId, privilegeId);
				userPrivilegeMapper.insert(userPrivilege);
			}
		}
		// 修改用户的信息
		userMapper.updateByPrimaryKeySelective(user);
	}

	/**
	 * 删除用户
	 */
	@Transactional
	public void deleteUser(Integer userId) {
		// 删除用户拥有的权限
		UserPrivilegeExample userPrivilegeExample = new UserPrivilegeExample();
		Criteria criteria = userPrivilegeExample.createCriteria();
		criteria.andUserIdEqualTo(userId);
		userPrivilegeMapper.deleteByExample(userPrivilegeExample);
		// 删除用户的角色
		UserRoleExample userRoleExample = new UserRoleExample();
		com.shrmus.pojo.UserRoleExample.Criteria criteria2 = userRoleExample.createCriteria();
		criteria2.andUserIdEqualTo(userId);
		userRoleMapper.deleteByExample(userRoleExample);
		// 删除用户
		userMapper.deleteByPrimaryKey(userId);
	}

	/**
	 * 查找所有用户
	 */
	@Override
	public List<User> getUserList() {
		UserExample userExample = new UserExample();
		List<User> userList = userMapper.selectByExample(userExample);
		return userList;
	}
	

}
