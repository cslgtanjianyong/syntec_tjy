package com.example.source;

import java.sql.Date;

public class RuKuinfo {
	private int id;//id ��ʶ
	private String rukuId;//�����
	private Date rukuDateTime;//���ʱ��
	private int rukuAcount;//�������
	private String  proId;//ҩƷ���
	private String  rukuRemark;//��ⱸע
	public RuKuinfo(String rukuId, Date rukuDateTime, int rukuAcount,
			String proId, String rukuRemark) {
		super();
		this.rukuId = rukuId;
		this.rukuDateTime = rukuDateTime;
		this.rukuAcount = rukuAcount;
		this.proId = proId;
		this.rukuRemark = rukuRemark;
	}
	public RuKuinfo(int id, String rukuId, Date rukuDateTime, int rukuAcount,
			String proId, String rukuRemark) {
		super();
		this.id = id;
		this.rukuId = rukuId;
		this.rukuDateTime = rukuDateTime;
		this.rukuAcount = rukuAcount;
		this.proId = proId;
		this.rukuRemark = rukuRemark;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRukuId() {
		return rukuId;
	}
	public void setRukuId(String rukuId) {
		this.rukuId = rukuId;
	}
	public Date getRukuDateTime() {
		return rukuDateTime;
	}
	public void setRukuDateTime(Date rukuDateTime) {
		this.rukuDateTime = rukuDateTime;
	}
	public int getRukuAcount() {
		return rukuAcount;
	}
	public void setRukuAcount(int rukuAcount) {
		this.rukuAcount = rukuAcount;
	}
	public String getProId() {
		return proId;
	}
	public void setProId(String proId) {
		this.proId = proId;
	}
	public String getRukuRemark() {
		return rukuRemark;
	}
	public void setRukuRemark(String rukuRemark) {
		this.rukuRemark = rukuRemark;
	}
	
}
