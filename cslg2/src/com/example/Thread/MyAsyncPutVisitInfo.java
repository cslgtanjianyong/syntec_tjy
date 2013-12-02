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
import android.widget.GridView;
import android.widget.Toast;

import com.example.cslg.CompanyMsg;
import com.example.cslg.DrugInfo;
import com.example.cslg.LoadingActivity;
import com.example.cslg.Mytask;
import com.example.cslg.Returnmsg;
import com.example.cslg.VisitInfo;
import com.example.cslg.tools.AppManager;
import com.example.cslg.tools.JsonTolist;
import com.example.cslg.tools.tools;
import com.example.source.MedKind;

public class MyAsyncPutVisitInfo extends AsyncTask<String,Integer,String> {
	private String editvisittimestring;
	private String editvisitdststring;
	private String customeridString;
	private String editvisitcontentString;
	private VisitInfo visitInfo;
    public MyAsyncPutVisitInfo(String editvisittime,String editvisitdst,String customerid,String editvisitcontent,VisitInfo v) {  
    	editvisittimestring=editvisittime;
    	editvisitdststring=editvisitdst;
    	customeridString=customerid;
    	editvisitcontentString=editvisitcontent;
    	visitInfo=v;
    }  
  
    // 此函数在另外一个线程中运行，不能操作UI线程中的控件  
   
  
   

	@Override
	protected String doInBackground(String... params) {
		// TODO Auto-generated method stub
	try
		{	List<NameValuePair> parmsList=new ArrayList<NameValuePair>();
		
		parmsList.add(new BasicNameValuePair("visitDay",editvisittimestring));
	    parmsList.add(new BasicNameValuePair("id", "1".toString().trim()));
	    parmsList.add(new BasicNameValuePair("goal", editvisitdststring));
	    //实际上是id
	    parmsList.add(new BasicNameValuePair("customerID",customeridString));
	    parmsList.add(new BasicNameValuePair("content",editvisitcontentString));
		HttpResponse httpResponse=tools.sendLoginInfo(parmsList,tools.VisitUrl);
		JSONObject jspnObject =tools.ReceiveJSONObject(httpResponse);
		if(httpResponse.getStatusLine().getStatusCode() == 200)
		{
			VisitInfo.b=true;
		}
		else
		{
			VisitInfo.b=false;
		}
		
		
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	protected void onPostExecute(String result) {// 后台任务执行完之后被调用，在ui线程执行   
    	if(VisitInfo.b=true)
    	{AppManager.getAppManager().finishActivity(LoadingActivity.class);
    		
    		Toast.makeText(visitInfo.getApplicationContext(),"反馈成功",1).show();
    		
			   Mytask.setitemcolor();
			   visitInfo.finish();
    	}
    	else
    	{AppManager.getAppManager().finishActivity(LoadingActivity.class);
    		Toast.makeText(visitInfo.getApplicationContext(),"反馈失败，请重试",1).show();
    		
			   
    	}
        }  
      
       
    protected void onPreExecute() {// 在doInBackground(Params...)之前被调用，在ui线程执行   
        super.onPreExecute();  
    }  

    protected void onCancelled() {// 在ui线程执行   
        super.onCancelled();  
    } 
	
  
}

