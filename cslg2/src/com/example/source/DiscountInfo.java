package com.example.source;

import java.util.Date;

public class DiscountInfo {
	private int id;// id 标识
	private int discount;//优惠额度
	private String proid;
	private String proname;
	private Date discountCreateTime;// 优惠信息创建时间
	private String discountRemark;// 优惠信息备注
	private double proListPrice;
	public double getProListPrice() {
		return proListPrice;
	}
	public void setProListPrice(double proListPrice) {
		this.proListPrice = proListPrice;
	}
	public String getProid() {
		return proid;
	}
	public void setProid(String proid) {
		this.proid = proid;
	}
	public String getProname() {
		return proname;
	}
	public void setProname(String proname) {
		this.proname = proname;
	}

	public DiscountInfo(){}
	public DiscountInfo(int discount,
			String discountRemark) {
		this.discount = discount;
		this.discountCreateTime = new Date();
		this.discountRemark = discountRemark;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getDiscount() {
		return discount;
	}
	public void setDiscount(int discount) {
		this.discount = discount;
	}
	public Date getDiscountCreateTime() {
		return discountCreateTime;
	}
	public void setDiscountCreateTime(Date discountCreateTime) {
		this.discountCreateTime = discountCreateTime;
	}
	public String getDiscountRemark() {
		return discountRemark;
	}
	public void setDiscountRemark(String discountRemark) {
		this.discountRemark = discountRemark;
	}
	
}
