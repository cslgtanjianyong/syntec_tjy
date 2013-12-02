package com.example.Thread;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import com.example.cslg.DrugInfo;
import com.example.cslg.tools.JsonTolist;
import com.example.cslg.tools.tools;
import com.example.source.KuCunInfo;
import com.example.source.Page;

import android.os.AsyncTask;

public class MyAsyncGetProductList extends AsyncTask<Object, Object, Object>{
	private String proidString;
	public MyAsyncGetProductList(String a)
	{
		proidString=a;
	}
	protected String  doInBackground(Object... arg0) {
		// TODO Auto-generated method stub
	try{	List<NameValuePair> parmsList=new ArrayList<NameValuePair>();	
		
		parmsList.add(new BasicNameValuePair("proid", proidString));
		HttpResponse httpResponse=tools.sendLoginInfo(parmsList,tools.KucunSearchUrl);
		
			JSONObject jsonObject =tools.ReceiveJSONObject(httpResponse);
			//获取json中的flag值并且进行判断
			KuCunInfo kuCunInfo=JsonTolist.jsonToKuncuninfo(jsonObject);
			DrugInfo.drugnum=String.valueOf(kuCunInfo.getKucunAcount());
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	
	
	}

