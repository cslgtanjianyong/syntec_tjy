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
  
    // �˺���������һ���߳������У����ܲ���UI�߳��еĿؼ�  
  
  
    // ��doInBackground��������ִ�н���֮��ſ�ʼ���У�����������UI�߳��У����Զ�UI�߳��еĿؼ����в���  
    protected String[] onPostExecute(List<Object> medKind) {  
    	 String dtype[]=new String[medKind.size()];
  	   
 		for(int i=0;i<medKind.size();i++)
 		{
 		dtype[i]=((MedKind)medKind.get(i)).getKindName();
 	   
 		}
 		//���ҩƷ���ൽ�����б���
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
			//��ȡjson�е�flagֵ���ҽ����ж�
	          
		
		} 
		catch(Exception ex){ex.printStackTrace();}	
		return null;
	}
	protected void onPostExecute(String result) {// ��̨����ִ����֮�󱻵��ã���ui�߳�ִ��   
    	if(Returnmsg.b=true)
    	{
    		
    		Toast.makeText(returnmsg.getApplicationContext(),"�����ɹ�",1).show();
			   Intent intent = new Intent(returnmsg,CompanyMsg.class); 
			   returnmsg.startActivity(intent); 
			   returnmsg.finish();	
    	}
    	else
    	{
    		Toast.makeText(returnmsg.getApplicationContext(),"����ʧ�ܣ�������",1).show();
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

