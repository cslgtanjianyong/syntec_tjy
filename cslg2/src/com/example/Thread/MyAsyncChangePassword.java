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
import android.view.Gravity;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;

import com.example.cslg.DrugInfo;
import com.example.cslg.LoadingActivity;
import com.example.cslg.Mainmenu;
import com.example.cslg.PersonalInfo;
import com.example.cslg.mainSreen;
import com.example.cslg.tools.AppManager;
import com.example.cslg.tools.JsonTolist;
import com.example.cslg.tools.tools;
import com.example.source.Page;
import com.example.source.Product;

public class MyAsyncChangePassword extends AsyncTask<String,Integer,String> {
		private String passwordString;
		private String beizhu;
		private String emailString;
		private PersonalInfo personalInfo;
	    public MyAsyncChangePassword(String password,String bz,String email,PersonalInfo p) { 
	    	passwordString=password;
	    	beizhu=bz;
	    	emailString=email;
	    	personalInfo=p;
	    	
	    }  
	    // 此函数在另外一个线程中运行，不能操作UI线程中的控件  
	    // 在doInBackground（）方法执行结束之后才开始运行，并且运行在UI线程中，可以对UI线程中的控件进行操作  
	  
//	    	protected void onPostExecute(String result) {     
//	        //textView显示网络请求结果 
//	        System.out.println("调用onPostExecute()方法--->异步任务执行完毕"); 
//	    } 


	@Override
	protected String  doInBackground(String... params) {
		// TODO Auto-generated method stub
	
		try 
		{
			List<Object > _Medkindlist=new ArrayList<Object>();
			final List<NameValuePair> parmsList=new ArrayList<NameValuePair>();	
			parmsList.add(new BasicNameValuePair("userPassword", passwordString));
			parmsList.add(new BasicNameValuePair("userEail",emailString));
			//用户编号，这里写死为1 要改
			parmsList.add(new BasicNameValuePair("id","1"));
			HttpResponse httpResponse=tools.sendLoginInfo(parmsList,tools.UpdatePasswordUrl);
			JSONObject jsonObject =tools.ReceiveJSONObject(httpResponse);
			PersonalInfo.httpResponse=httpResponse;
			//获取json中的flag值并且进行判断
			if(httpResponse.getStatusLine().getStatusCode() == 200)
			{
//				Toast toast = Toast.makeText(getApplicationContext(),
//					     "修改成功", Toast.LENGTH_LONG);
//					   toast.setGravity(Gravity.CENTER, 0, 0);
//					   toast.show();
				return "true";
			}
			else
			{
//				Toast toast = Toast.makeText(getApplicationContext(),
//					     "修改失败，请重新修改", Toast.LENGTH_LONG);
//					   toast.setGravity(Gravity.CENTER, 0, 0);
//					   toast.show();
				return "false";
			}
			}
		catch(Exception ex) {
			ex.printStackTrace();
		
			System.out.println(ex.getMessage());}
		return null;
	}
	protected void onPostExecute(String result) {// 后台任务执行完之后被调用，在ui线程执行   
	    	if(result=="true")
	    	{
	    		AppManager.getAppManager().finishActivity(LoadingActivity.class);
	    		
	    			Toast toast = Toast.makeText(personalInfo.getApplicationContext(),
	    			"修改成功", Toast.LENGTH_LONG);
	    			toast.setGravity(Gravity.CENTER, 0, 0);
	    			toast.show();	
	    	}
	    	else
	    	{
	    		Toast toast = Toast.makeText(personalInfo.getApplicationContext(),
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
	

//MyAsyncChangePassword
