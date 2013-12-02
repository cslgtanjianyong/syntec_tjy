package com.example.source;

import java.util.ArrayList;
import java.util.List;

public class Page {
	private  List<Object> myProList=new ArrayList<Object>();//获得用户列表集合
	private int rowCount; //记录总数 
	private int pageCount; //总页数 
	private int pageNow; //当前页码
	public List<Object> getMyProList() {
		return myProList;
	}
	public void setMyProList(List<Object> myProList) {
		this.myProList = myProList;
	}
	public int getRowCount() {
		return rowCount;
	}
	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public int getPageNow() {
		return pageNow;
	}
	public void setPageNow(int page) {
		this.pageNow = page;
	}

	
}
