package com.situ.crm.mall.model;

import com.situ.until.Pager;
//沟通记录表

public class OrderModel  extends Pager{
   private Integer id;
   
   private String  custCode;
   private String  custName;
   private String  userName;
   private String   userCode;
   private String   prodCode;
   private String   prodName;
   private String   time; 
   private String   count;
   private String status;
   public  OrderModel() {}
   public  OrderModel(String custCode) {
	   
	   this.custCode=custCode;
   }
 public  OrderModel(String custCode,String userCode) {
	   
	   this.custCode=custCode;
	   this.userCode=userCode;
   }
 public  OrderModel(String custCode,String userCode,String prodCode) {
	   
	   this.custCode=custCode;
	   this.userCode=userCode;
	   this.prodCode=prodCode;
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
public String getProdCode() {
	return prodCode;
}
public void setProdCode(String prodCode) {
	this.prodCode = prodCode;
}
public String getProdName() {
	return prodName;
}
public void setProdName(String prodName) {
	this.prodName = prodName;
}
public String getTime() {
	return time;
}
public void setTime(String time) {
	this.time = time;
}
public String getCount() {
	return count;
}
public void setCount(String count) {
	this.count = count;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
@Override
public String toString() {
	return "OrderModel [id=" + id + ", custCode=" + custCode + ", custName=" + custName + ", userName=" + userName
			+ ", userCode=" + userCode + ", prodCode=" + prodCode + ", prodName=" + prodName + ", time=" + time
			+ ", count=" + count + ", status=" + status + "]";
}
   
 
   
}
