package com.example.Thread;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.Gravity;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;

import com.example.cslg.DrugInfo;
import com.example.cslg.Helpandreturn;
import com.example.cslg.LoadingActivity;
import com.example.cslg.Mainmenu;
import com.example.cslg.PersonalInfo;
import com.example.cslg.mainSreen;
import com.example.cslg.tools.AppManager;
import com.example.cslg.tools.JsonTolist;
import com.example.cslg.tools.MailSenderInfo;
import com.example.cslg.tools.SimpleMailSender;
import com.example.cslg.tools.tools;
import com.example.source.Page;
import com.example.source.Product;

public class MyAsyncSendEmail extends AsyncTask<String,Integer,String> {
		private String setMailServerHost;
		private String setMailServerPort;
		private String setUserName;
		private String setPassword;
		private String setFromAddress;
		private String setToAddress;
		private String setSubject;
		private String setContent;
		private Helpandreturn helpandreturn;
	    public MyAsyncSendEmail(String _setMailServerHost,String _setMailServerPort,String _setUserName,String _setPassword,String _setFromAddress,String _setToAddress,String _setSubject,String _setContent,Helpandreturn _helpandreturn) { 
	    	setMailServerHost=_setMailServerHost;
	    	setMailServerPort=_setMailServerPort;
	    	setUserName=_setUserName;
	    	setPassword=_setPassword;
	    	setFromAddress=_setFromAddress;
	    	setToAddress=_setToAddress;
	    	setSubject=_setSubject;
	    	setContent=_setContent;
	    	helpandreturn=_helpandreturn;
	    }  
	 

	@Override
	protected String  doInBackground(String... params) {
		// TODO Auto-generated method stub
	
		   try 
           { 
          	 MailSenderInfo mailInfo = new MailSenderInfo();    
            mailInfo.setMailServerHost("smtp.qq.com");    
            mailInfo.setMailServerPort("25");    
            mailInfo.setValidate(true);    
            mailInfo.setUserName("1442718753@qq.com");  //你的邮箱地址  
            mailInfo.setPassword("469850618718");//您的邮箱密码    
            mailInfo.setFromAddress("1442718753@qq.com");    
            mailInfo.setToAddress("cslgsoftteam@163.com");    
            mailInfo.setSubject(setSubject);    
            mailInfo.setContent(setContent);    
            //cslghhzy
               //这个类主要来发送邮件   
            SimpleMailSender sms = new SimpleMailSender();   
                sms.sendTextMail(mailInfo);//发送文体格式    
               return "t";
           } 
           catch (Exception e) { 
        	  e.printStackTrace();
        	   return "f";
           }
		
	}
	protected void onPostExecute(String result) {// 后台任务执行完之后被调用，在ui线程执行   
	    	if(result=="t")
	    	{
	    		AppManager.getAppManager().finishActivity(LoadingActivity.class);
	    			Toast toast = Toast.makeText(helpandreturn.getApplicationContext(),
	    			"反馈成功！", Toast.LENGTH_LONG);
	    			toast.setGravity(Gravity.CENTER, 0, 0);
	    			toast.show();	
	    			
	    	}
	    	else
	    	{	AppManager.getAppManager().finishActivity(LoadingActivity.class);
	    		Toast toast = Toast.makeText(helpandreturn.getApplicationContext(),
		    			"反馈失败，请重试", Toast.LENGTH_LONG);
		    			toast.setGravity(Gravity.CENTER, 0, 0);
		    			toast.show();
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
