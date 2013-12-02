package com.example.Thread;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.view.Gravity;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.cslg.DrugInfo;
import com.example.cslg.LoadingActivity;
import com.example.cslg.LoginActivity;
import com.example.cslg.Mainmenu;
import com.example.cslg.MyJobList;
import com.example.cslg.PersonalInfo;
import com.example.cslg.R;
import com.example.cslg.ScrollUIActivity;
import com.example.cslg.mainSreen;
import com.example.cslg.tools.AppManager;
import com.example.cslg.tools.JsonTolist;
import com.example.cslg.tools.tools;
import com.example.source.Page;
import com.example.source.Product;

public class MyAsyncGetVisitInfoMonth extends AsyncTask<String,Integer,String> {
		private MyJobList myJobList;
	    public MyAsyncGetVisitInfoMonth(MyJobList m) { 
	    	myJobList=m;
	    	
	    }  
	

	@Override
	protected String  doInBackground(String... params) {
		// TODO Auto-generated method stub
	
		try 
		{
			List<Object > _Medkindlist=new ArrayList<Object>();
			final List<NameValuePair> parmsList=new ArrayList<NameValuePair>();			
	
			parmsList.add(new BasicNameValuePair("id",String.valueOf(tools._me.getId())));
			
			HttpResponse httpResponse=tools.sendLoginInfo(parmsList,tools.GetVisitInfo);

			JSONObject jsonObject =tools.ReceiveJSONObject(httpResponse);

			myJobList.visitInfos= JsonTolist.jsonTogetvisitinfo(jsonObject);
			int i=0;
		             return "t";
			//获取json中的flag值并且进行判断
			}
		catch(Exception ex) {ex.printStackTrace();
		System.out.println("20秒超时");
		return "f";
						}
		
		
		
	}
	protected void onPostExecute(String result) {// 后台任务执行完之后被调用，在ui线程执行   
		  if(result=="t")
	      {
			  try {
					myJobList.listView = (ListView) myJobList.findViewById(R.id.listViewjoblist);
		myJobList.initializeAdapter();
		myJobList.listView.setAdapter(myJobList.adapter);
		myJobList.listView.setOnScrollListener(myJobList);	
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}

	      }
		  else {
			  Toast toast = Toast.makeText(myJobList.getApplicationContext(),
		    			"网络超时！请重试", Toast.LENGTH_SHORT);
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
	

//MyAsyncChangePassword

