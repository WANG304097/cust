package com.situ.crm.grant.model;

import java.util.ArrayList;
import java.util.List;

import com.situ.until.Pager;

public class UserModel  extends Pager {

	
	private Integer id;
	private String  userName;
	private String  userCode;
	private String  userPass;
	private String  roleCode;
	private String  roleName;
	private String  parentCode;
	
	
	public UserModel(){}
	public UserModel(String  roleCode,String  userCode){
		this.roleCode=roleCode;
		this.userCode=userCode;
	}
	public UserModel(String  roleCode){
		this.roleCode=roleCode;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getUserPass() {
		return userPass;
	}
	public void setUserPass(String userPass) {
		this.userPass = userPass;
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
	public String getParentCode() {
		return parentCode;
	}
	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}
	

	@Override
	public String toString() {
		return "UserModel [id=" + id + ", userName=" + userName + ", userCode=" + userCode + ", userPass=" + userPass
				+ ", roleCode=" + roleCode + ", roleName=" + roleName + ", parentCode=" + parentCode + "]";
	}
	
}
