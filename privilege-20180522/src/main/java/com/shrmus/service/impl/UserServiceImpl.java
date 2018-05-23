package com.shrmus.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shrmus.mapper.UserMapper;
import com.shrmus.mapper.UserPrivilegeMapper;
import com.shrmus.mapper.UserRoleMapper;
import com.shrmus.pojo.Role;
import com.shrmus.pojo.User;
import com.shrmus.pojo.UserExample;
import com.shrmus.pojo.UserPrivilegeExample;
import com.shrmus.pojo.UserPrivilegeExample.Criteria;
import com.shrmus.pojo.UserRole;
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

	/**
	 * 添加用户
	 */
	@Transactional
	public void addUser(User user) {
		// 密码加密
		userMapper.insert(user);
		List<Role> userRoleList = user.getUserRoleList();
		user = getUserByUsername(user.getUserUsername());
		for(Role role : userRoleList) {
			UserRole userRole = new UserRole(user.getUserId(),role.getRoleId());
			userRoleMapper.insert(userRole);
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
