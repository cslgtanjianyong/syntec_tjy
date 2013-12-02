package com.example.Thread;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.view.Gravity;
import android.widget.GridView;
import android.widget.Toast;

import com.example.cslg.DrugInfo;
import com.example.cslg.Exit;
import com.example.cslg.LoadingActivity;
import com.example.cslg.Myyugouinfo;
import com.example.cslg.VisitInfo;
import com.example.cslg.tools.AppManager;
import com.example.cslg.tools.JsonTolist;
import com.example.cslg.tools.tools;
import com.example.source.MedKind;

public class MyAsyncPutYugouInfo extends AsyncTask<String,Integer,String>  {
	private String myid;
	private String medid;
	private String medshuliang;
	private String customerid;
	private String sellPrice;
	private String SellRemark;
	private Myyugouinfo myyugouinfo;
	private	String yugouname, yugoudrugname, yugoudrugnum, yugoudrugprice;
    public MyAsyncPutYugouInfo(String myidsString,String medidString,String medshuliangsString,String customeridstring,String price,String remark,Myyugouinfo m,String _yugouname,String _yugoudrugname,String _yugoudrugnum,String _yugoudrugprice) {  
    	myid=myidsString;
    	medid=medidString;
    	medshuliang=medshuliangsString;
    	customerid=customeridstring;
    	sellPrice=price;
    	SellRemark=remark;
    	myyugouinfo=m;
    	yugouname=_yugouname; yugoudrugname=_yugoudrugname; yugoudrugnum=_yugoudrugnum; yugoudrugprice=_yugoudrugprice;
    	
    	
    }  
  
    // 此函数在另外一个线程中运行，不能操作UI线程中的控件  
   
  
    // 在doInBackground（）方法执行结束之后才开始运行，并且运行在UI线程中，可以对UI线程中的控件进行操作  
    protected String[] onPostExecute(List<Object> medKind) {  
    	 String dtype[]=new String[medKind.size()];
  	   
 		for(int i=0;i<medKind.size();i++)
 		{
 		dtype[i]=((MedKind)medKind.get(i)).getKindName();
 	   
 		}
 		//添加药品种类到下拉列表中
		return dtype;
 		
  
    }

	@Override
	protected String doInBackground(String... params) {
		// TODO Auto-generated method stub
		List<NameValuePair> parmsList=new ArrayList<NameValuePair>();	
		parmsList.add(new BasicNameValuePair("userId", myid.toString().trim()
				));
		parmsList.add(new BasicNameValuePair("proid", medid.toString().trim()
				.toString()));
		parmsList.add(new BasicNameValuePair("sellAcount", medshuliang
				.toString()));
		parmsList.add(new BasicNameValuePair("customerId", customerid.toString()));
		parmsList.add(new BasicNameValuePair("proSellPrice", sellPrice));
		parmsList.add(new BasicNameValuePair("proSellRemark", SellRemark));

		HttpResponse httpResponse=tools.sendLoginInfo(parmsList,tools.YugouUrl);
		try{
			JSONObject jsonObject =tools.ReceiveJSONObject(httpResponse);
			if(httpResponse.getStatusLine().getStatusCode()==200)
			{
				Myyugouinfo.b=true;
			
			}
			else
			{
				Myyugouinfo.b=false;
				
			}
			//获取json中的flag值并且进行判断
	          
		
		} 
		catch(Exception ex){}
		return null;
	}
	protected void onPostExecute(String result) {// 后台任务执行完之后被调用，在ui线程执行   
		if(Myyugouinfo.b==true)
		{
			Toast toast = Toast.makeText(myyugouinfo.getApplicationContext(),
	    			"上传成功", Toast.LENGTH_LONG);
	    			toast.setGravity(Gravity.CENTER, 0, 0);
	    			toast.show();	
	    			myyugouinfo.savehistory(yugouname,yugoudrugname,yugoudrugnum,yugoudrugprice);
	    			AppManager.getAppManager().finishActivity(LoadingActivity.class);
	    			AppManager.getAppManager().finishActivity(Myyugouinfo.class);
	    			AppManager.getAppManager().finishActivity(Exit.class);
					
		}
		else
		{
			Toast toast = Toast.makeText(myyugouinfo.getApplicationContext(),
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
