package com.shrmus.pojo;

import java.util.List;

public class Privilege {
    private Integer privilegeId;

    private String privilegeName;

    private String privilegeUrl;

    private Integer privilegeParentId;
    
    private Privilege parentPrivilege;
    
    private List<Privilege> childPrivilegeList;
    
    @Override
	public String toString() {
		return "Privilege [privilegeId=" + privilegeId + ", privilegeName=" + privilegeName + ", privilegeUrl="
				+ privilegeUrl + ", privilegeParentId=" + privilegeParentId + ", parentPrivilege=" + parentPrivilege
				+ ", childPrivilegeList=" + childPrivilegeList + "]";
	}

	public Privilege() {
    	
    }
    
    public Privilege(String privilegeName, String privilegeUrl) {
		this.privilegeName = privilegeName;
		this.privilegeUrl = privilegeUrl;
	}

	public Integer getPrivilegeId() {
        return privilegeId;
    }

    public void setPrivilegeId(Integer privilegeId) {
        this.privilegeId = privilegeId;
    }

    public String getPrivilegeName() {
        return privilegeName;
    }

    public void setPrivilegeName(String privilegeName) {
        this.privilegeName = privilegeName == null ? null : privilegeName.trim();
    }

    public String getPrivilegeUrl() {
        return privilegeUrl;
    }

    public void setPrivilegeUrl(String privilegeUrl) {
        this.privilegeUrl = privilegeUrl == null ? null : privilegeUrl.trim();
    }

    public Integer getPrivilegeParentId() {
        return privilegeParentId;
    }

    public void setPrivilegeParentId(Integer privilegeParentId) {
        this.privilegeParentId = privilegeParentId;
    }

	public Privilege getParentPrivilege() {
		return parentPrivilege;
	}

	public void setParentPrivilege(Privilege parentPrivilege) {
		this.parentPrivilege = parentPrivilege;
	}

	public List<Privilege> getChildPrivilegeList() {
		return childPrivilegeList;
	}

	public void setChildPrivilegeList(List<Privilege> childPrivilegeList) {
		this.childPrivilegeList = childPrivilegeList;
	}
}