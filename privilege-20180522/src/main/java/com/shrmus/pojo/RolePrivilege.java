package com.shrmus.pojo;

public class RolePrivilege {
    private Integer rolePrivilegeId;

    private Integer roleId;

    private Integer privilegeId;

    public RolePrivilege() {
    	
    }
    
    public RolePrivilege(Integer roleId, Integer privilegeId) {
		this.roleId = roleId;
		this.privilegeId = privilegeId;
	}

	public Integer getRolePrivilegeId() {
        return rolePrivilegeId;
    }

    public void setRolePrivilegeId(Integer rolePrivilegeId) {
        this.rolePrivilegeId = rolePrivilegeId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getPrivilegeId() {
        return privilegeId;
    }

    public void setPrivilegeId(Integer privilegeId) {
        this.privilegeId = privilegeId;
    }
}