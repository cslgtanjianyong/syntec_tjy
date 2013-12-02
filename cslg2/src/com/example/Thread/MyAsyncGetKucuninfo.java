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
    // 此函数在另外一个线程中运行，不能操作UI线程中的控件  
    // 在doInBackground（）方法执行结束之后才开始运行，并且运行在UI线程中，可以对UI线程中的控件进行操作  
  
//    	protected void onPostExecute(String result) {     
//        //textView显示网络请求结果 
//        System.out.println("调用onPostExecute()方法--->异步任务执行完毕"); 
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
		
		//获取json中的flag值并且进行判断
		return "t";
		}
	catch(Exception ex) {System.out.println(ex.getMessage());ex.printStackTrace();	return "f";
}

}
protected void onPostExecute(String result) 
{// 后台任务执行完之后被调用，在ui线程执行   
	if (result=="t") {
		AppManager.getAppManager().finishActivity(Zhedangform.class);
		drugInfo.intentchange(drugInfo);
		
	}
	else {
		Toast toast = Toast.makeText(drugInfo.getApplicationContext(),
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
