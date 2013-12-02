package com.example.source;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MedKind {
	private int id;//id 标识
	private String kindId;//产品类别编号
	private String kindName;//类别名
	private String kindRemark;//类别备注
	private Set<Product> products= new HashSet<Product>();
	public MedKind()
	{
		
	}
	public MedKind(int id, String kindId, String kindName, String kindRemark,
			Set<Product> products) {
		super();
		this.id = id;
		this.kindId = kindId;
		this.kindName = kindName;
		this.kindRemark = kindRemark;
		this.products = products;
	}
	public MedKind(String kindId, String kindName, String kindRemark,
			Set<Product> products) {
		super();
		this.kindId = kindId;
		this.kindName = kindName;
		this.kindRemark = kindRemark;
		this.products = products;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getKindId() {
		return kindId;
	}
	public void setKindId(String kindId) {
		this.kindId = kindId;
	}
	public String getKindName() {
		return kindName;
	}
	public void setKindName(String kindName) {
		this.kindName = kindName;
	}
	public String getKindRemark() {
		return kindRemark;
	}
	public void setKindRemark(String kindRemark) {
		this.kindRemark = kindRemark;
	}
	public Set<Product> getProducts() {
		return products;
	}
	public void setProducts(Set<Product> products) {
		this.products = products;
	}
}
