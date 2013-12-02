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
import android.widget.Toast;

import com.example.cslg.CompanyMsg;
import com.example.cslg.LoadingActivity;
import com.example.cslg.Myyugouinfo;
import com.example.cslg.Returnmsg;
import com.example.cslg.tools.AppManager;
import com.example.cslg.tools.tools;
import com.example.source.MedKind;

public class MyAsyncReturnMsg extends AsyncTask<String,Integer,String> {
	private String flag;
	private String contentString;
	private Returnmsg returnmsg;
  
    public MyAsyncReturnMsg(String f,String c,Returnmsg r) {  
    	flag=f;
    	contentString=c;
    	returnmsg=r;
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
	try{	List<NameValuePair> parmsList=new ArrayList<NameValuePair>();	
		parmsList.add(new BasicNameValuePair("notificationID", flag
				.toString()));
		parmsList.add(new BasicNameValuePair("content", contentString));
		parmsList.add(new BasicNameValuePair("staffid", String.valueOf(tools._me.getId())
				.toString()));
		HttpResponse httpResponse=tools.sendLoginInfo(parmsList,tools.RemsgUrl);
		
			JSONObject jsonObject =tools.ReceiveJSONObject(httpResponse);
			if(httpResponse.getStatusLine().getStatusCode()==200)
			{					
				Returnmsg.b=true;
				AppManager.getAppManager().finishActivity(LoadingActivity.class);
			}
			else
			{AppManager.getAppManager().finishActivity(LoadingActivity.class);
				Returnmsg.b=false;
			}
			//获取json中的flag值并且进行判断
	          
		
		} 
		catch(Exception ex){ex.printStackTrace();}	
		return null;
	}
	protected void onPostExecute(String result) {// 后台任务执行完之后被调用，在ui线程执行   
    	if(Returnmsg.b=true)
    	{
    		
    		Toast.makeText(returnmsg.getApplicationContext(),"反馈成功",1).show();
			   Intent intent = new Intent(returnmsg,CompanyMsg.class); 
			   returnmsg.startActivity(intent); 
			   returnmsg.finish();	
    	}
    	else
    	{
    		Toast.makeText(returnmsg.getApplicationContext(),"反馈失败，请重试",1).show();
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

