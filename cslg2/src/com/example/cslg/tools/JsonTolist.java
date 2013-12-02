package com.example.cslg.tools;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import android.R.integer;

import com.example.cslg.DrugInfo;
import com.example.cslg.Mytask;
import com.example.cslg.VisitInfo;
import com.example.cslg.YouhuiInfo;
import com.example.cslg.mainSreen;
import com.example.source.Customer;
import com.example.source.DiscountInfo;
import com.example.source.KuCunInfo;
import com.example.source.MedKind;
import com.example.source.Page;
import com.example.source.Product;
import com.example.source.Staff;
import com.example.source.VisitInfoGet;


public class JsonTolist {
public static Page jsonToProList(JSONObject jsonObject) 
	{
	
	 	
	 	Date date;
	 	List<Object> products=new ArrayList<Object>();
	 	Page page=new Page();
	 	try
	 {
	    SimpleDateFormat fmt =new SimpleDateFormat("yyyy-MM-dd");   
	 	int rowcount = jsonObject
        .getInt("rowCount"); 
	 	int pageCount = jsonObject
        .getInt("pageCount"); 
	 	int pageNow = jsonObject
        .getInt("pageNow"); 
	 	page.setRowCount(rowcount);
	 	page.setPageCount(pageCount);
	 	page.setPageNow(pageNow);
	    JSONArray jsonArray = jsonObject.getJSONArray("products"); 
	    for(int i=0;i<jsonArray.length();i++){ 
	    	Product product=new Product();
            JSONObject jsonObject2 = (JSONObject)jsonArray.opt(i);
            product.setId(jsonObject2.getInt("id"));     
            date = fmt.parse(jsonObject2.getString("proCreateTime"));
            String s=fmt.format(date);
            product.setProCreateTime(date);
            product.setProId(jsonObject2.getString("proId"));
            product.setProListPrice(jsonObject2.getDouble("proListPrice"));
            product.setProName(jsonObject2.getString("proName"));
            product.setProPrice(jsonObject2.getDouble("proPrice"));
            product.setProRemark(jsonObject2.getString("proRemark"));
            date=fmt.parse(jsonObject2.getString("usefulllife"));
            product.setUsefulllife(date);
            //新加字段
            product.setEnglishName(jsonObject2.getString("englishName"));
            product.setproUnit(jsonObject2.getString("proUnit"));
            product.setChemName(jsonObject2.getString("chemName"));
            product.setSpec(jsonObject2.getString("spec"));
            //
            System.out.println(product.getProName());
            products.add(product);
        }
	    page.setMyProList(products);
	    for (Object p : products) {
			System.out.println(((Product)p).getProName());
		
		}
	   return page;	
	 }
	 	catch(Exception ex){System.out.println(ex.getMessage());}
		return page;
	}

public static List<Object> jsonToAllKindMedInfo(JSONObject jsonObject)
{
	List<Object> medKindList=new ArrayList<Object>();
	try
	{
	
	JSONArray jsonArray = jsonObject.getJSONArray("medKinds"); 
	  for(int i=0;i<jsonArray.length();i++){ 
	      MedKind medKind=new MedKind();
          JSONObject jsonObject2 = (JSONObject)jsonArray.opt(i);
          medKind.setId(jsonObject2.getInt("id")); 
          medKind.setKindId(jsonObject2.getString("kindId")); 
          medKind.setKindName(jsonObject2.getString("kindName")); 
          medKind.setKindRemark(jsonObject2.getString("kindRemark")); 
          medKindList.add(medKind);      
      }
	  for (Object p :medKindList ) {
			System.out.println("id"+((MedKind)p).getId());
	}
	 
	
	}
	catch(Exception ex){
	 
	 
	}
	DrugInfo._Medkindlist=medKindList;
	 return medKindList;
}
public static List<VisitInfoGet> jsonTogetvisitinfo(JSONObject jsonObject)
{
	Date date;
	List<VisitInfoGet> visitInfos=new ArrayList<VisitInfoGet>();
	try
	{
	    SimpleDateFormat fmt =new SimpleDateFormat("yyyy-MM-dd");   
		 JSONArray jsonArray = jsonObject.getJSONArray("products"); 
		 for(int i=0;i<jsonArray.length();i++)
		 {
			 VisitInfoGet visitInfo=new VisitInfoGet();
			 JSONObject jsonObject2 = (JSONObject)jsonArray.opt(i);
			 date = fmt.parse(jsonObject2.getString("visitDay"));
			 visitInfo.setVisitDay(date);
			 visitInfo.setContent(jsonObject2.getString("content"));
			 visitInfo.setGoal(jsonObject2.getString("goal"));
			 JSONObject jsonObject3 = new JSONObject(jsonObject2.getString("customer")) ;
			 visitInfo.setCustomerName(jsonObject3.getString("customerName"));
			 visitInfos.add(visitInfo);
		 }
		 
	}
	catch(Exception ex){}
	return visitInfos;
	}
public static KuCunInfo jsonToKuncuninfo(JSONObject jsonObject)
{
KuCunInfo kuCunInfo =new KuCunInfo();
	try
	{
		JSONObject jsonObject2 = new JSONObject(jsonObject.toString()) 
        .getJSONObject("kuCun"); 
		kuCunInfo.setId(jsonObject2.getInt("id"));
		kuCunInfo.setKucunAcount(jsonObject2.getInt("kucunAcount"));
		kuCunInfo.setKucunRemark(jsonObject2.getString("kucunRemark"));
		return kuCunInfo;
	}
	catch(Exception ex)
	{
		System.out.println(ex.getMessage());
	}
	return kuCunInfo;
}
public static Staff jsonLogin(JSONObject jsonObject2)
{
	Staff me=new Staff();
	try
	{
//		JSONObject jsonObject2 = new JSONObject(jsonObject.toString()) 
//        .getJSONObject("staff"); 
		System.out.println(jsonObject2.getString("position"));
		me.setPosition(jsonObject2.getString("position"));
		me.setUserEail(jsonObject2.getString("userEail"));
		me.setUserId(jsonObject2.getString("userId"));
		me.setUserName(jsonObject2.getString("userName"));
		me.setUserPassword(jsonObject2.getString("userPassword"));
		me.setUserRegName(jsonObject2.getString("userRegName"));
		me.setUserRemark(jsonObject2.getString("userRemark"));
		me.setId(jsonObject2.getInt("id"));
		me.setLevel(jsonObject2.getInt("Level"));
		mainSreen.f=true;	
		return me;
	}
	catch(Exception ex)
	{
		System.out.println(ex.getMessage());
	}
	return me;
}
public static List<Customer> jsonToCustomers(JSONObject jsonObject)
{
	List<Customer> customers=new ArrayList<Customer>();
	try
	{
 	JSONArray jsonArray = jsonObject.getJSONArray("customer"); 
 	 for(int i=0;i<jsonArray.length();i++){ 
 		JSONObject jsonObject2 = (JSONObject)jsonArray.opt(i);
 		Customer customer=new Customer();
 		customer.setId(jsonObject2.getInt("id"));
 		customer.setCustomerId(jsonObject2.getString("customerId"));
 		customer.setcLinkname(jsonObject2.getString("linkname"));
 		customer.setCustomerName(jsonObject2.getString("customerName"));
 		customer.setClinkTell(jsonObject2.getString("clinkTell"));
 		customer.setCustomerAddress(jsonObject2.getString("customerAddress"));
 		customer.setCustomerRemark(jsonObject2.getString("customerRemark"));
 		//新加字段
 		customer.setProvince(jsonObject2.getString("province"));
 		customer.setCity(jsonObject2.getString("city"));
 		customer.setBizType(jsonObject2.getString("bizType"));
 		customer.setCredit(jsonObject2.getInt("credit"));	
 		System.out.println(customer.getProvince());
 		customers.add(customer);
     }
 	tools.customers.clear();
 	System.out.println("-----------");
 	tools.customers=customers;
 	 return customers;
	}
	catch(Exception ex)
	{
		System.out.println(ex.getMessage());
	}
	return customers;
}
public static Page jsonToYouhuiInfo(JSONObject jsonObject)
{
	Date date;
	Page page=new Page();
	List<Object> discountInfos=new ArrayList<Object>();
	try{
		SimpleDateFormat fmt =new SimpleDateFormat("yyyy-MM-dd");  
			int rowcount = jsonObject
	        .getInt("rowCount"); 
		 	int pageCount = jsonObject
	        .getInt("pageCount"); 
		 	int pageNow = jsonObject
	        .getInt("pageNow"); 
		 	page.setRowCount(rowcount);
		 	page.setPageCount(pageCount);
		 	page.setPageNow(pageNow);
		 	JSONArray jsonArray = jsonObject.getJSONArray("products"); 
		    for(int i=0;i<jsonArray.length();i++){ 
		    	JSONObject jsonObject2 = (JSONObject)jsonArray.opt(i);
		    	DiscountInfo discountInfo=new DiscountInfo();
		    	discountInfo.setProid(jsonObject2.getString("proId"));
		    	discountInfo.setProname(jsonObject2.getString("proName"));
		    	discountInfo.setProListPrice(jsonObject2.getDouble("proListPrice"));
		    	JSONObject jsonObject3 = new JSONObject(jsonObject2.getString("discountInfo")) ;
		    	discountInfo.setDiscount(jsonObject3.getInt("discount"));
		    	date = fmt.parse(jsonObject3.getString("discountCreateTime"));
		    	discountInfo.setDiscountCreateTime(date);
		    	discountInfo.setDiscountRemark(jsonObject3.getString("discountRemark"));
		    	discountInfo.setId(jsonObject3.getInt("id"));
		    	discountInfos.add(discountInfo);
	        }
		    page.setMyProList(discountInfos);
		    YouhuiInfo.page=page;
		    System.out.println(YouhuiInfo.page.getPageCount());
}
	
	catch(Exception ex){System.out.println(ex.getMessage());}
	page.setMyProList(discountInfos);
	 YouhuiInfo.page=page;
	return page;
}
public static int jsonTogetDiscount(JSONObject jsonObject)
{
	int discount=0;
	try{
		if(jsonObject.getString("discountInfo")!=null)
		{
		JSONObject jsonObject2 = new JSONObject(jsonObject.toString()) 
        .getJSONObject("discountInfo"); 
		discount=jsonObject2.getInt("discount");
		}
		else
		{
			discount=0;
		}
	   }
	catch(Exception ex){System.out.println(ex.getMessage());}
	return discount;
}
}
