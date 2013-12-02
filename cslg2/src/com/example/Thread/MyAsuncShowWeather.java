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

public class MyAsuncShowWeather extends AsyncTask<String,Integer,String> {//�̳�AsyncTask   
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
      
    protected String doInBackground(String... params) {//�����ִ̨�е������ں�̨�߳�ִ��   
        // �����ں�̨�̲߳���ui   
        //��ʼ�� Ʒ���б�   
    try{
    	List<NameValuePair> parmsList=new ArrayList<NameValuePair>();	
		parmsList.add(new BasicNameValuePair("userRegName",userRegName));
		parmsList
				.add(new BasicNameValuePair("userPassword", userPassword));
		parmsList
				.add(new BasicNameValuePair("level",level));
			HttpResponse httpResponse=tools.sendLoginInfo(parmsList,tools.LoginUrl);
			
				JSONObject jspnObject =tools.ReceiveJSONObject(httpResponse);
				//��ȡjson�е�flagֵ���ҽ����ж�
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
			//��ȡjson�е�flagֵ���ҽ����ж�
	          
		
		} 
		catch(Exception ex){}	
		return "";
    }  

    protected void onPostExecute(String result) {// ��̨����ִ����֮�󱻵��ã���ui�߳�ִ��   
      if(result=="true")
      {
      mainSreen.f=true;      
  	  Toast.makeText(m,"��¼�ɹ�", 1).show();
  	  Intent intent = new Intent(m,Mainmenu.class); 
  	  m.startActivity(intent);
  	  m.RememberPassword(m);
      }
      else
      {
       mainSreen.f=false; 
       Toast.makeText(m,"��¼ʧ�ܣ�������", 1).show();
       AppManager.getAppManager().finishActivity(LoadingActivity.class);

      }
    }  
      
       
    protected void onPreExecute() {// ��doInBackground(Params...)֮ǰ�����ã���ui�߳�ִ��   
        super.onPreExecute();  
    }  

    protected void onCancelled() {// ��ui�߳�ִ��   
        super.onCancelled();  
    }  
      
}  

