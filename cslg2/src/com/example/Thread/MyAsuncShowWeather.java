package com.example.Thread;


import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.cslg.LoadingActivity;
import com.example.cslg.Mainmenu;
import com.example.cslg.mainSreen;
import com.example.cslg.tools.AppManager;
import com.example.cslg.tools.JsonTolist;
import com.example.cslg.tools.tools;

public class MyAsuncShowWeather extends AsyncTask<String,Integer,String> {//继承AsyncTask   
	private String userRegName;
	private String userPassword;
	private String level;
	private mainSreen m;
  
    public MyAsuncShowWeather(String name,String password,String l,mainSreen cm) {  
    	userRegName=name;
    	userPassword=password;
    	level=l;
    	m=cm;
    }  
      
    protected String doInBackground(String... params) {//处理后台执行的任务，在后台线程执行   
        // 不能在后台线程操作ui   
        //初始化 品牌列表   
    try{
    	List<NameValuePair> parmsList=new ArrayList<NameValuePair>();	
		parmsList.add(new BasicNameValuePair("userRegName",userRegName));
		parmsList
				.add(new BasicNameValuePair("userPassword", userPassword));
		parmsList
				.add(new BasicNameValuePair("level",level));
			HttpResponse httpResponse=tools.sendLoginInfo(parmsList,tools.LoginUrl);
			
				JSONObject jspnObject =tools.ReceiveJSONObject(httpResponse);
				//获取json中的flag值并且进行判断
				if(jspnObject.getBoolean("flag"))
				{
					
				JSONObject jsonObject2=new JSONObject(jspnObject.toString()).getJSONObject("staff");
				tools._me=JsonTolist.jsonLogin(jsonObject2);
//					mainSreen.f=true;
				return "true";
				}		
			else
			{
				return "false";
			}
			//获取json中的flag值并且进行判断
	          
		
		} 
		catch(Exception ex){}	
		return "";
    }  

    protected void onPostExecute(String result) {// 后台任务执行完之后被调用，在ui线程执行   
      if(result=="true")
      {
      mainSreen.f=true;      
  	  Toast.makeText(m,"登录成功", 1).show();
  	  Intent intent = new Intent(m,Mainmenu.class); 
  	  m.startActivity(intent);
  	  m.RememberPassword(m);
      }
      else
      {
       mainSreen.f=false; 
       Toast.makeText(m,"登录失败！请重试", 1).show();
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

