package com.example.Thread;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.cslg.DrugInfo;
import com.example.cslg.Extendpage;
import com.example.cslg.LoadingActivity;
import com.example.cslg.DrugInfo.SpinnerSelectedListener;
import com.example.cslg.tools.AppManager;
import com.example.cslg.tools.JsonTolist;
import com.example.cslg.tools.News;
import com.example.cslg.tools.tools;
import com.example.cslg.R;
import com.example.source.MedKind;

public class MyAsyncGetMedType extends AsyncTask<String,Integer,String> {
	private GridView MedType = null;  
	public static Boolean flag=false;
	private DrugInfo drugInfo;
	private static List<Object> _Medkindlist1=new ArrayList<Object>();
    public MyAsyncGetMedType(GridView M,DrugInfo d) {  
    	MedType=M;
    	drugInfo=d;
    }  
  
    // 此函数在另外一个线程中运行，不能操作UI线程中的控件  
   
  
	@Override
	protected String doInBackground(String... params) {
		// TODO Auto-generated method stub
	try {
		
		
		List<Object > _Medkindlist=new ArrayList<Object>();
		final List<NameValuePair> parmsList=new ArrayList<NameValuePair>();	
		HttpResponse httpResponse=tools.sendLoginInfo(parmsList,tools.MedTypeSearchUrl);
		JSONObject jsonObject =tools.ReceiveJSONObject(httpResponse);
		System.out.println(jsonObject);
		//获取json中的flag值并且进行判断
		List<Object> medKind=JsonTolist.jsonToAllKindMedInfo(jsonObject);
		DrugInfo._Medkindlist=medKind;
		_Medkindlist1=medKind;
		flag=true;
		return "t";
	} catch (Exception e) {e.printStackTrace();
		// TODO: handle exception
	flag=true;
	return "f";
	}
		
	
	}

	protected void onPostExecute(String result) {// 后台任务执行完之后被调用，在ui线程执行   
	if (result=="t") {
		try {
				drugInfo.bangdingdrug(_Medkindlist1,drugInfo);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	
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
 

