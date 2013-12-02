package com.example.source;

import java.sql.Date;

public class SellInfo {
	private Page page;
	private int id;//id ��ʶ
	private String sellId;//���۱��
	private String customerId;//�ͻ�ID
	private String userId;//ҵ��Ա���
	private Date sellDateTime;//����ʱ��
	private String proId;//��Ʒ���
	private String sellAcount;//��������
	private String proSellPrice;//�ܽ��
	private String proSellRemark;//���۱�ע
	public SellInfo(int id, String sellId, String customerId, String userId,
			Date sellDateTime, String proId, String sellAcount,
			String proSellPrice, String proSellRemark) {
		super();
		this.id = id;
		this.sellId = sellId;
		this.customerId = customerId;
		this.userId = userId;
		this.sellDateTime = sellDateTime;
		this.proId = proId;
		this.sellAcount = sellAcount;
		this.proSellPrice = proSellPrice;
		this.proSellRemark = proSellRemark;
	}
	public SellInfo(String sellId, String customerId, String userId,
			Date sellDateTime, String proId, String sellAcount,
			String proSellPrice, String proSellRemark) {
		super();
		this.sellId = sellId;
		this.customerId = customerId;
		this.userId = userId;
		this.sellDateTime = sellDateTime;
		this.proId = proId;
		this.sellAcount = sellAcount;
		this.proSellPrice = proSellPrice;
		this.proSellRemark = proSellRemark;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSellId() {
		return sellId;
	}
	public void setSellId(String sellId) {
		this.sellId = sellId;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Date getSellDateTime() {
		return sellDateTime;
	}
	public void setSellDateTime(Date sellDateTime) {
		this.sellDateTime = sellDateTime;
	}
	public String getProId() {
		return proId;
	}
	public void setProId(String proId) {
		this.proId = proId;
	}
	public String getSellAcount() {
		return sellAcount;
	}
	public void setSellAcount(String sellAcount) {
		this.sellAcount = sellAcount;
	}
	public String getProSellPrice() {
		return proSellPrice;
	}
	public void setProSellPrice(String proSellPrice) {
		this.proSellPrice = proSellPrice;
	}
	public String getProSellRemark() {
		return proSellRemark;
	}
	public void setProSellRemark(String proSellRemark) {
		this.proSellRemark = proSellRemark;
	}
}
