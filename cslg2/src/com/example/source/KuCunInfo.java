package com.example.source;

public class KuCunInfo {	
	private int id;//id 标识
	private Page page;
    private String proId;//药品编号
	private int kucunAcount;//库存数量
	private String kucunRemark;//库存备注
	public KuCunInfo(int id, String proId, int kucunAcount, String kucunRemark) {
		super();
		this.id = id;
		this.proId = proId;
		this.kucunAcount = kucunAcount;
		this.kucunRemark = kucunRemark;
	}
public KuCunInfo() {}
	public KuCunInfo(String proId, int kucunAcount, String kucunRemark) {
		super();
		this.proId = proId;
		this.kucunAcount = kucunAcount;
		this.kucunRemark = kucunRemark;
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
	public int getKucunAcount() {
		return kucunAcount;
	}
	public void setKucunAcount(int kucunAcount) {
		this.kucunAcount = kucunAcount;
	}
	public String getKucunRemark() {
		return kucunRemark;
	}
	public void setKucunRemark(String kucunRemark) {
		this.kucunRemark = kucunRemark;
	}
}
