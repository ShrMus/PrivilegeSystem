package com.shrmus.service;

import java.util.List;

import com.shrmus.pojo.Privilege;

public interface PrivilegeService {

	List<Privilege> getPrivilegeList();

	void addPrivilege(Privilege privilege);

	Privilege getPrivilegeByName(String privilegeName);

	void allocationPrivileges(String type, Integer typeId, List<Integer> privilegeArray);

	List<Privilege> getPrivilegeListByRoleId(Integer roleId);
	
	List<Privilege> getPrivilegeListByUserId(Integer userId);
	
	void updatePrivilege(Privilege privilege);
	
	void deletePrivilege(Integer privilegeId);

	List<Privilege> getParentPrivilegeList();

	Privilege getPrivilegeById(Integer privilegeId);

	Object getObjectById(String type, Integer typeId);

}
