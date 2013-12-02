package com.example.source;

import java.sql.Date;

public class SellInfo {
	private Page page;
	private int id;//id 标识
	private String sellId;//销售编号
	private String customerId;//客户ID
	private String userId;//业务员编号
	private Date sellDateTime;//销售时间
	private String proId;//商品编号
	private String sellAcount;//销售数量
	private String proSellPrice;//总金额
	private String proSellRemark;//销售备注
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
