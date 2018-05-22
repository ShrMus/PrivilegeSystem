package com.shrmus.service;

import java.util.List;

import com.shrmus.pojo.Role;

public interface RoleService {

	void addRole(Role role);

	Role getRoleByName(String roleName);

	List<Role> getRoleList();
	
	void updateRole(Role role);
	
	void deleteRole(Integer roleId);
}
