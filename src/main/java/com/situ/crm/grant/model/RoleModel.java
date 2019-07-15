package com.situ.crm.grant.model;

import com.situ.until.Pager;

public class RoleModel  extends Pager{
    public  RoleModel() {}
    public  RoleModel(String  roleCode) {
    	this.roleCode=roleCode;
    }
	
	
	@Override
	public String toString() {
		return "RoleModel [id=" + id + ", roleCode=" + roleCode + ", roleName=" + roleName + ", descr=" + descr + "]";
	}
    private  Integer id;
    private  String  roleCode;
    private  String  roleName;
    private  String   descr;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getRoleCode() {
		return roleCode;
	}
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}
    
    
	
	
}
