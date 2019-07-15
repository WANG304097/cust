package com.situ.crm.mall.model;

import com.situ.until.Pager;

public class CustUserModel  extends Pager{
   private Integer id;
   
   private String  custCode;
   private String  custName;
   private String  status;
   private String   email;
   
   public  CustUserModel() {}
   public  CustUserModel(String custCode) {
	   
	   this.custCode=custCode;
   }
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public String getCustCode() {
	return custCode;
}
public void setCustCode(String custCode) {
	this.custCode = custCode;
}
public String getCustName() {
	return custName;
}
public void setCustName(String custName) {
	this.custName = custName;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
   
   
   
   
}
