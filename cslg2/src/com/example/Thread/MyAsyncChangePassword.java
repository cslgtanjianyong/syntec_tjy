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
	    // �˺���������һ���߳������У����ܲ���UI�߳��еĿؼ�  
	    // ��doInBackground��������ִ�н���֮��ſ�ʼ���У�����������UI�߳��У����Զ�UI�߳��еĿؼ����в���  
	  
//	    	protected void onPostExecute(String result) {     
//	        //textView��ʾ���������� 
//	        System.out.println("����onPostExecute()����--->�첽����ִ�����"); 
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
			//�û���ţ�����д��Ϊ1 Ҫ��
			parmsList.add(new BasicNameValuePair("id","1"));
			HttpResponse httpResponse=tools.sendLoginInfo(parmsList,tools.UpdatePasswordUrl);
			JSONObject jsonObject =tools.ReceiveJSONObject(httpResponse);
			PersonalInfo.httpResponse=httpResponse;
			//��ȡjson�е�flagֵ���ҽ����ж�
			if(httpResponse.getStatusLine().getStatusCode() == 200)
			{
//				Toast toast = Toast.makeText(getApplicationContext(),
//					     "�޸ĳɹ�", Toast.LENGTH_LONG);
//					   toast.setGravity(Gravity.CENTER, 0, 0);
//					   toast.show();
				return "true";
			}
			else
			{
//				Toast toast = Toast.makeText(getApplicationContext(),
//					     "�޸�ʧ�ܣ��������޸�", Toast.LENGTH_LONG);
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
	protected void onPostExecute(String result) {// ��̨����ִ����֮�󱻵��ã���ui�߳�ִ��   
	    	if(result=="true")
	    	{
	    		AppManager.getAppManager().finishActivity(LoadingActivity.class);
	    		
	    			Toast toast = Toast.makeText(personalInfo.getApplicationContext(),
	    			"�޸ĳɹ�", Toast.LENGTH_LONG);
	    			toast.setGravity(Gravity.CENTER, 0, 0);
	    			toast.show();	
	    	}
	    	else
	    	{
	    		Toast toast = Toast.makeText(personalInfo.getApplicationContext(),
		    			"�����쳣��������", Toast.LENGTH_LONG);
		    			toast.setGravity(Gravity.CENTER, 0, 0);
		    			toast.show();
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
	

//MyAsyncChangePassword
