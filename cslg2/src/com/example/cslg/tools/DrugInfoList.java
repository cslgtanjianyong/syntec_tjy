package com.example.cslg.tools;

import android.provider.ContactsContract.Contacts.Data;

public class DrugInfoList {
private int drugID;
private String drugName;
private String drugType;
private Data drugTime;
private float intPrice;
public float getIntPrice() {
	return intPrice;
}

public void setIntPrice(float intPrice) {
	this.intPrice = intPrice;
}

public float getSallPrice() {
	return sallPrice;
}

public void setSallPrice(float sallPrice) {
	this.sallPrice = sallPrice;
}

public Data getEffctiveTime() {
	return effctiveTime;
}

public void setEffctiveTime(Data effctiveTime) {
	this.effctiveTime = effctiveTime;
}

public String getBeizhu() {
	return Beizhu;
}

public void setBeizhu(String beizhu) {
	Beizhu = beizhu;
}

private float sallPrice;
private Data effctiveTime;
private String Beizhu;


public Data getDrugTime() {
	return drugTime;
}

public void setDrugTime(Data drugTime) {
	this.drugTime = drugTime;
}

public String getDrugType() {
	return drugType;
}

public void setDrugType(String drugType) {
	this.drugType = drugType;
}

public String getDrugName() {
	return drugName;
}

public void setDrugName(String drugName) {
	this.drugName = drugName;
}

public int getDrugID() {
	return drugID;
}

public void setDrugID(int drugID) {
	this.drugID = drugID;
}

}
