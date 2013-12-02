package com.example.Thread;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.view.Gravity;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;

import com.example.cslg.DrugInfo;
import com.example.cslg.LoadingActivity;
import com.example.cslg.YouhuiInfo;
import com.example.cslg.tools.AppManager;
import com.example.cslg.tools.JsonTolist;
import com.example.cslg.tools.tools;
import com.example.source.Page;
import com.example.source.Product;

public class MyAsyncGetMoreYouhuiinfo extends AsyncTask<String,Integer,String> {
		private static String proname,pageNo;
		private YouhuiInfo youhuiInfo;
		private JSONObject jsonObject;
	    public MyAsyncGetMoreYouhuiinfo(String proname1,String  pageNo1,YouhuiInfo y) { 
	    	proname=proname1;
	    	pageNo=pageNo1;
	    	youhuiInfo=y;
	    }  
	   
	  


	@Override
	protected String  doInBackground(String... params) {
	try {
		
	
		List<NameValuePair> parmsList=new ArrayList<NameValuePair>();
		parmsList.add(new BasicNameValuePair("proname",proname));
		parmsList.add(new BasicNameValuePair("pageNo", pageNo));
		HttpResponse httpResponse=tools.sendLoginInfo(parmsList,tools.YouhuiSearchUrl);
		jsonObject =tools.ReceiveJSONObject(httpResponse);
		YouhuiInfo.httpResponse=httpResponse;
		return "t";
	} catch (Exception e) {e.printStackTrace();
		// TODO: handle exception
	return "f";
	}
		
		// TODO Auto-generated method stub
		
	}
	  protected void onPostExecute(String result) 
	  {// 后台任务执行完之后被调用，在ui线程执行   
		
		  if (result=="t") {
			Page pageR=JsonTolist.jsonToYouhuiInfo(jsonObject);
			youhuiInfo.page=pageR;
			youhuiInfo.pages.add(youhuiInfo.page);
			System.out.println("当前页为："+youhuiInfo.pages.get(1).getPageNow());
			youhuiInfo.adapter.notifyDataSetChanged();
			youhuiInfo.loadMoreButton.setText("查看更多..."); // 恢复按钮文字
		}
		  else {
			
				Toast toast = Toast.makeText(youhuiInfo.getApplicationContext(),
		    			"网络异常，请重试", Toast.LENGTH_LONG);
		    			toast.setGravity(Gravity.CENTER, 0, 0);
		    			toast.show();
		    			AppManager.getAppManager().finishActivity(LoadingActivity.class);

			  
		}
		  
	        }  
	      
	       
	    protected void onPreExecute() {// 在doInBackground(Params...)之前被调用，在ui线程执行   
	        super.onPreExecute();  
	    }  

	    protected void onCancelled() {// 在ui线程执行   
	        super.onCancelled();  
	    }  
	
	}
	

//MyAsyncGetPage


