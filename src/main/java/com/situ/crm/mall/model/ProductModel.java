package com.situ.crm.mall.model;

import com.situ.until.Pager;

public class ProductModel  extends Pager{

	private Integer id;
	private String code;
	private String  name;
	private String  sum;
	private String  cost;
	private String  url;
	public  ProductModel() {}
	public  ProductModel(String code) { 
		this.code=code;
		}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSum() {
		return sum;
	}
	public void setSum(String sum) {
		this.sum = sum;
	}
	public String getCost() {
		return cost;
	}
	public void setCost(String cost) {
		this.cost = cost;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Override
	public String toString() {
		return "ProductModel [id=" + id + ", code=" + code + ", name=" + name + ", sum=" + sum + ", cost=" + cost
				+ ", url=" + url + "]";
	}
	
}
