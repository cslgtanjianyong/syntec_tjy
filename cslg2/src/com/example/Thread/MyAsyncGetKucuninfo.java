package com.example.Thread;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.view.Gravity;
import android.widget.Toast;

import com.example.cslg.DrugInfo;
import com.example.cslg.LoadingActivity;
import com.example.cslg.YouhuiInfo;
import com.example.cslg.Zhedangform;
import com.example.cslg.tools.AppManager;
import com.example.cslg.tools.JsonTolist;
import com.example.cslg.tools.tools;
import com.example.source.KuCunInfo;
import com.example.source.Page;

public class MyAsyncGetKucuninfo extends AsyncTask<String,Integer,String> {
	private static String proid;
	private DrugInfo drugInfo;
    public MyAsyncGetKucuninfo(String M,DrugInfo d) { 
    	proid=M;
    	drugInfo=d;
    }  
    // �˺���������һ���߳������У����ܲ���UI�߳��еĿؼ�  
    // ��doInBackground��������ִ�н���֮��ſ�ʼ���У�����������UI�߳��У����Զ�UI�߳��еĿؼ����в���  
  
//    	protected void onPostExecute(String result) {     
//        //textView��ʾ���������� 
//        System.out.println("����onPostExecute()����--->�첽����ִ�����"); 
//    } 


@Override
protected String  doInBackground(String... params) {
	// TODO Auto-generated method stub

	try 
	{
		List<Object > _Medkindlist=new ArrayList<Object>();
		final List<NameValuePair> parmsList=new ArrayList<NameValuePair>();	
		parmsList.add(new BasicNameValuePair("proid", proid));
		HttpResponse httpResponse=tools.sendLoginInfo(parmsList,tools.KucunSearchUrl);
		JSONObject jsonObject =tools.ReceiveJSONObject(httpResponse);
		YouhuiInfo.page=JsonTolist.jsonToYouhuiInfo(jsonObject);
		DrugInfo.kuCunInfo =JsonTolist.jsonToKuncuninfo(jsonObject);
		
		//��ȡjson�е�flagֵ���ҽ����ж�
		return "t";
		}
	catch(Exception ex) {System.out.println(ex.getMessage());ex.printStackTrace();	return "f";
}

}
protected void onPostExecute(String result) 
{// ��̨����ִ����֮�󱻵��ã���ui�߳�ִ��   
	if (result=="t") {
		AppManager.getAppManager().finishActivity(Zhedangform.class);
		drugInfo.intentchange(drugInfo);
		
	}
	else {
		Toast toast = Toast.makeText(drugInfo.getApplicationContext(),
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
