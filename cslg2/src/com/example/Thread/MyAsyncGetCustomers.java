package com.example.Thread;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.content.Intent;
import android.os.AsyncTask;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.cslg.DrugInfo;
import com.example.cslg.LoadingActivity;
import com.example.cslg.Mainmenu;
import com.example.cslg.mainSreen;
import com.example.cslg.DrugInfo.SpinnerSelectedListener;
import com.example.cslg.Mytask;
import com.example.cslg.tools.AppManager;
import com.example.cslg.tools.JsonTolist;
import com.example.cslg.tools.tools;
import com.example.cslg.R;
import com.example.source.Customer;
import com.example.source.MedKind;
import com.example.source.Page;
import com.example.source.Product;

public class MyAsyncGetCustomers extends AsyncTask<String,Integer,String> {

	private Mytask mytask;
    public MyAsyncGetCustomers(Mytask m) {  
    	mytask=m;
  
    }  
  
    // �˺���������һ���߳������У����ܲ���UI�߳��еĿؼ�  
   
  
	@Override
	protected String doInBackground(String... params) {
		// TODO Auto-generated method stub
	try{
		List<NameValuePair> parmsList=new ArrayList<NameValuePair>();
		parmsList.add(new BasicNameValuePair("id",String.valueOf(tools._me.getId())));
		HttpResponse httpResponse=tools.sendLoginInfo(parmsList,tools.CustomerUrl);
		JSONObject jsonObject =tools.ReceiveJSONObject(httpResponse);
		System.out.println(jsonObject.toString());
		
		List<Customer> customers=JsonTolist.jsonToCustomers(jsonObject);
		tools.customers=customers;
		return "t";
		}
		catch(Exception ex){
			ex.printStackTrace();
			return "f";}
	
		
	}
	  protected void onPostExecute(String result) 
	  {// ��̨����ִ����֮�󱻵��ã���ui�߳�ִ��   
		  if (result=="t") {
			  try {
				 mytask.SaveCustomers(mytask);
		  mytask.initializeAdapter(mytask);
	
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			 
		}
		  else {
			  Toast toast = Toast.makeText(mytask.getApplicationContext(),
		    			"�����쳣��������!", Toast.LENGTH_LONG);
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
