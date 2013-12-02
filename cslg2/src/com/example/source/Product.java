package com.example.source;

import java.util.Date;

public class Product {
	private int id;//id 标识 
	private String proId;//药品编号
	private String proName;//药品名称
	private Date proCreateTime;//药品创建时间
	private double proPrice;//药品售价
	private double proListPrice;//药品进价
	private Date usefulllife;//有效期
	private String proRemark;//商品备注
	private String englishName;// 英文名称
	private String proUnit;// 单位（板、盒、支等)
	private String chemName;// 化学名称
	private String spec;// 规格
	public Product()
	{
		
	}
	public Product(String proId, MedKind medKind, String proName,
			Date proCreateTime, double proPrice, double proListPrice,
			Date usefulllife, String proRemark) {
		super();
		this.proId = proId;
		this.proName = proName;
		this.proCreateTime = proCreateTime;
		this.proPrice = proPrice;
		this.proListPrice = proListPrice;
		this.usefulllife = usefulllife;
		this.proRemark = proRemark;
	}
	public String getEnglishName() {
		return englishName;
	}
	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}
	public String getproUnit() {
		return proUnit;
	}
	public void setproUnit(String unit) {
		this.proUnit = unit;
	}
	public String getChemName() {
		return chemName;
	}
	public void setChemName(String chemName) {
		this.chemName = chemName;
	}
	public String getSpec() {
		return spec;
	}
	public void setSpec(String spec) {
		this.spec = spec;
	}
	public Product(int id, String proId, MedKind medKind, String proName,
			Date proCreateTime, double proPrice, double proListPrice,
			Date usefulllife, String proRemark) {
		super();
		this.id = id;
		this.proId = proId;
		this.proName = proName;
		this.proCreateTime = proCreateTime;
		this.proPrice = proPrice;
		this.proListPrice = proListPrice;
		this.usefulllife = usefulllife;
		this.proRemark = proRemark;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getProId() {
		return proId;
	}
	public void setProId(String proId) {
		this.proId = proId;
	}

	
	public String getProName() {
		return proName;
	}
	public void setProName(String proName) {
		this.proName = proName;
	}
	public Date getProCreateTime() {
		return proCreateTime;
	}
	public void setProCreateTime(Date proCreateTime) {
		this.proCreateTime = proCreateTime;
	}
	public double getProPrice() {
		return proPrice;
	}
	public void setProPrice(double proPrice) {
		this.proPrice = proPrice;
	}
	public double getProListPrice() {
		return proListPrice;
	}
	public void setProListPrice(double proListPrice) {
		this.proListPrice = proListPrice;
	}
	public Date getUsefulllife() {
		return usefulllife;
	}
	public void setUsefulllife(Date usefulllife) {
		this.usefulllife = usefulllife;
	}
	public String getProRemark() {
		return proRemark;
	}
	public void setProRemark(String proRemark) {
		this.proRemark = proRemark;
	}
}
