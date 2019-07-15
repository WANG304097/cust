package com.situ.crm.grant.model;

import com.situ.until.Pager;

public class RelModel extends Pager{


	private  Integer    id;
	private  String     roleCode;
	private  String     menuCode;
	
	private   RoleModel roleModel=new RoleModel();
	private   MenuModel menuModel=new  MenuModel();
	
	
	public  RelModel() {}
	public  RelModel(Integer    id) {
		this.id=id;
	}
	public  RelModel(String  roleCode,String     menuCode) {
		this.roleCode=roleCode;
		this.menuCode=menuCode;
	}
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
	public String getMenuCode() {
		return menuCode;
	}
	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}
	public RoleModel getRoleModel() {
		return roleModel;
	}
	public void setRoleModel(RoleModel roleModel) {
		this.roleModel = roleModel;
	}
	public MenuModel getMenuModel() {
		return menuModel;
	}
	public void setMenuModel(MenuModel menuModel) {
		this.menuModel = menuModel;
	}
	@Override
	public String toString() {
		return "RelModel [id=" + id + ", roleCode=" + roleCode + ", menuCode=" + menuCode + ", roleModel=" + roleModel
				+ ", menuModel=" + menuModel + "]";
	}
	
}
