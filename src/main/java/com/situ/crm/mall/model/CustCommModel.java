package com.situ.crm.mall.model;

import com.situ.until.Pager;
//沟通记录表

public class CustCommModel  extends Pager{
   private Integer id;
   
   private String  custCode;
   private String  custName;
   private String  userName;
   private String   userCode;
   private String   time; 
   private String   type;
   private String content;
   public  CustCommModel() {}
   public  CustCommModel(String custCode) {
	   
	   this.custCode=custCode;
   }
 public  CustCommModel(String custCode, String userCode,String   time,String   type,String content) {
	   
	   this.custCode=custCode;
	   this.userCode=userCode;
	   this.time=time;
	   this.type=type;
	   this.content=content;
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
public String getTime() {
	return time;
}
public void setTime(String time) {
	this.time = time;
}
public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}
public String getContent() {
	return content;
}
public void setContent(String content) {
	this.content = content;
}
@Override
public String toString() {
	return "CustCommModel [id=" + id + ", custCode=" + custCode + ", custName=" + custName + ", userName=" + userName
			+ ", userCode=" + userCode + ", time=" + time + ", type=" + type + ", content=" + content + "]";
}
   
   
   
   
}
