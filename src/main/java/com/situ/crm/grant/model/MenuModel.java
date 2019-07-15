package com.situ.crm.grant.model;

import java.util.ArrayList;
import java.util.List;

import com.situ.until.Pager;

public class MenuModel extends Pager{

  private	Integer id;
  private   String  menuCode;
  private   String  menuName;
  private   String  menuUrl;
  private   String  parentCode;//上级菜单
  private   String  level;
  
  private  List<MenuModel>  child= new ArrayList<>();
  public MenuModel() {}
  public MenuModel(String menuCode) {
	  
	  this.menuCode=menuCode;
  }
  public MenuModel(String menuCode,String parentCode) {
	  this.menuCode=menuCode;
	  this.parentCode=parentCode;
  }
  
  
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public List<MenuModel> getChild() {
	return child;
}
public void setChild(List<MenuModel> child) {
	this.child = child;
}
public String getMenuCode() {
	return menuCode;
}
public void setMenuCode(String menuCode) {
	this.menuCode = menuCode;
}
public String getMenuName() {
	return menuName;
}
public void setMenuName(String menuName) {
	this.menuName = menuName;
}
public String getmenuUrl() {
	return menuUrl;
}
public void setmenuUrl(String menuUrl) {
	this.menuUrl = menuUrl;
}
public String getParentCode() {
	return parentCode;
}
public void setParentCode(String parentCode) {
	this.parentCode = parentCode;
}
public String getLevel() {
	return level;
}
public void setLevel(String level) {
	this.level = level;
}
@Override
public String toString() {
	return "MenuModel [id=" + id + ", menuCode=" + menuCode + ", menuName=" + menuName + ", menuUrl=" + menuUrl
			+ ", parentCode=" + parentCode + ", level=" + level + "]";
}

  
}
