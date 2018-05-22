package com.shrmus.pojo;

import java.util.List;

public class User {
    private Integer userId;

    private String userUsername;

    private String userPassword;
    
    /**
   	 * 用户所有角色
   	 */
   	private List<Role> userRoleList;

   	/**
   	 * 用户拥有的权限列表
   	 */
   	private List<Privilege> privilegeList;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserUsername() {
        return userUsername;
    }

    public void setUserUsername(String userUsername) {
        this.userUsername = userUsername == null ? null : userUsername.trim();
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword == null ? null : userPassword.trim();
    }

	public List<Role> getUserRoleList() {
		return userRoleList;
	}

	public void setUserRoleList(List<Role> userRoleList) {
		this.userRoleList = userRoleList;
	}

	public List<Privilege> getPrivilegeList() {
		return privilegeList;
	}

	public void setPrivilegeList(List<Privilege> privilegeList) {
		this.privilegeList = privilegeList;
	}
}