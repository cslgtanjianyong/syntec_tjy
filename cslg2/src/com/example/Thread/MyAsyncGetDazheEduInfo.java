package com.example.Thread;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.R.integer;
import android.os.AsyncTask;
import android.view.Gravity;
import android.widget.GridView;
import android.widget.Toast;

import com.example.cslg.DrugInfo;
import com.example.cslg.Mainmenu;
import com.example.cslg.Myyugouinfo;
import com.example.cslg.VisitInfo;
import com.example.cslg.tools.JsonTolist;
import com.example.cslg.tools.tools;
import com.example.source.MedKind;

public class MyAsyncGetDazheEduInfo extends  AsyncTask<String,Integer,String> {
	//private static String pronum;
	private static String proid;
	private JSONObject j;
    public MyAsyncGetDazheEduInfo(String proidString) {  
    //	pronum=pronumString;
    	proid=proidString;
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
			try{List<NameValuePair> parmsList=new ArrayList<NameValuePair>();	
		parmsList.add(new BasicNameValuePair("proid", proid
				));
		parmsList.add(new BasicNameValuePair("proCount", "200"
				.toString()));
		HttpResponse httpResponse=tools.sendLoginInfo(parmsList,tools.GetYouhuiUrl);
	
		j =tools.ReceiveJSONObject(httpResponse);
			
			//获取json中的flag值并且进行判断
		return "t";
		} 
		catch(Exception ex){
			return "f";
		}
		
	}
	protected void onPostExecute(String result) {// 后台任务执行完之后被调用，在ui线程执行   
	if (result=="t") {
				Myyugouinfo.discount=JsonTolist.jsonTogetDiscount(j);
	}
	else {
		new Mainmenu().torst();
	}

		
	
	
	}  
      
       
    protected void onPreExecute() {// 在doInBackground(Params...)之前被调用，在ui线程执行   
        super.onPreExecute();  
    }  

    protected void onCancelled() {// 在ui线程执行   
        super.onCancelled();  
    }  
	
  
}
