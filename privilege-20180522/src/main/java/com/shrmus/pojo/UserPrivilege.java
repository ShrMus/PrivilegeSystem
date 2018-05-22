package com.shrmus.pojo;

public class UserPrivilege {
    private Integer userPrivilegeId;

    private Integer userId;

    private Integer privilegeId;
    
    public UserPrivilege() {
    	
    }

    public UserPrivilege(Integer userId, Integer privilegeId) {
		this.userId = userId;
		this.privilegeId = privilegeId;
	}

	public Integer getUserPrivilegeId() {
        return userPrivilegeId;
    }

    public void setUserPrivilegeId(Integer userPrivilegeId) {
        this.userPrivilegeId = userPrivilegeId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getPrivilegeId() {
        return privilegeId;
    }

    public void setPrivilegeId(Integer privilegeId) {
        this.privilegeId = privilegeId;
    }
}