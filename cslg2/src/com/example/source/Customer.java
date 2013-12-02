package com.example.source;

import java.util.List;

import android.R.integer;

public class Customer {
	private Page page;
	private int id; // id标识
	private String customerId;// 客户Id
	private String customerName;// 客户名
	private String province;//所在省份
	private String city;//所在城市
	private String bizType;//商业类型
	private int credit;//积分
	private String cLinkname;// 联系人名
	private String clinkTell;// 联系人电话
	private String customerAddress;// 客户地址
	private String customerRemark;// 客户备注
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getBizType() {
		return bizType;
	}
	public void setBizType(String bizType) {
		this.bizType = bizType;
	}
	public int getCredit() {
		return credit;
	}
	public void setCredit(int credit) {
		this.credit = credit;
	}
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getcLinkname() {
		return cLinkname;
	}
	public void setcLinkname(String cLinkname) {
		this.cLinkname = cLinkname;
	}
	public String getClinkTell() {
		return clinkTell;
	}
	public void setClinkTell(String clinkTell) {
		this.clinkTell = clinkTell;
	}
	public String getCustomerAddress() {
		return customerAddress;
	}
	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}
	public String getCustomerRemark() {
		return customerRemark;
	}
	public void setCustomerRemark(String customerRemark) {
		this.customerRemark = customerRemark;
	}
}
