package com.example.source;

import java.util.List;

import android.R.integer;

public class Customer {
	private Page page;
	private int id; // id��ʶ
	private String customerId;// �ͻ�Id
	private String customerName;// �ͻ���
	private String province;//����ʡ��
	private String city;//���ڳ���
	private String bizType;//��ҵ����
	private int credit;//����
	private String cLinkname;// ��ϵ����
	private String clinkTell;// ��ϵ�˵绰
	private String customerAddress;// �ͻ���ַ
	private String customerRemark;// �ͻ���ע
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
