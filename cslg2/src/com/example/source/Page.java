package com.example.source;

import java.util.ArrayList;
import java.util.List;

public class Page {
	private  List<Object> myProList=new ArrayList<Object>();//����û��б���
	private int rowCount; //��¼���� 
	private int pageCount; //��ҳ�� 
	private int pageNow; //��ǰҳ��
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
