package com.example.Thread;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.content.Context;
import android.os.AsyncTask;
import android.view.Gravity;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;

import com.example.cslg.DrugInfo;
import com.example.cslg.LoadingActivity;
import com.example.cslg.PersonalInfo;
import com.example.cslg.YouhuiInfo;
import com.example.cslg.tools.AppManager;
import com.example.cslg.tools.JsonTolist;
import com.example.cslg.tools.tools;
import com.example.source.Page;
import com.example.source.Product;

public class MyAsyncGetYouhuiInfo extends AsyncTask<String,Integer,String> {
		private static String proname;
		private YouhuiInfo youhuiInfo;
	    public MyAsyncGetYouhuiInfo(String M,YouhuiInfo y) { 
	    	proname=M;
	    	youhuiInfo=y;
	    }  
	 

	@Override
	protected String  doInBackground(String... params) {
		// TODO Auto-generated method stub
	
		try 
		{
			List<Object > _Medkindlist=new ArrayList<Object>();
			final List<NameValuePair> parmsList=new ArrayList<NameValuePair>();	
			parmsList.add(new BasicNameValuePair("proname", proname));
			parmsList.add(new BasicNameValuePair("pageNo", "1"));
			HttpResponse httpResponse=tools.sendLoginInfo(parmsList,tools.YouhuiSearchUrl);
			JSONObject jsonObject =tools.ReceiveJSONObject(httpResponse);
			YouhuiInfo.page=JsonTolist.jsonToYouhuiInfo(jsonObject);
			
			//获取json中的flag值并且进行判断
			return "t";
			}
		catch(Exception ex) {System.out.println(ex.getMessage());return "f";}
		
	}
	protected void onPostExecute(String result) {// 后台任务执行完之后被调用，在ui线程执行   
		if (result=="t") {
			try {
					youhuiInfo.pages.add(youhuiInfo.page);
		youhuiInfo.datasize=youhuiInfo.page.getRowCount();
		youhuiInfo.loadfirstData(youhuiInfo);
		
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		
		}
		else {
			Toast toast = Toast.makeText(youhuiInfo.getApplicationContext(),
	    			"网络异常，请重试", Toast.LENGTH_LONG);
	    			toast.setGravity(Gravity.CENTER, 0, 0);
	    			toast.show();AppManager.getAppManager().finishActivity(LoadingActivity.class);

		}
		
       
	}  
      
       
    protected void onPreExecute() {// 在doInBackground(Params...)之前被调用，在ui线程执行   
        super.onPreExecute();  
    }  

    protected void onCancelled() {// 在ui线程执行   
        super.onCancelled();  
    }  
	}
	

//MyAsyncChangePassword
