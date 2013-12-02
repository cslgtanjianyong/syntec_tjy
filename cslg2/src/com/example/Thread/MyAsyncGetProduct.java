package com.example.Thread;

import java.text.SimpleDateFormat;
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
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.example.cslg.DrugInfo;
import com.example.cslg.Exit;
import com.example.cslg.LoadingActivity;
import com.example.cslg.tools.AppManager;
import com.example.cslg.tools.JsonTolist;
import com.example.cslg.tools.tools;
import com.example.source.Page;
import com.example.source.Product;

public class MyAsyncGetProduct extends AsyncTask<String,Integer,String> {
		public static boolean flag=false;
		private GridView MedType = null;  
		private LinkedList<String> mListItems;
		private ArrayAdapter<String> mAdapter;
		private int mkindid=0;
		private GridView mGridView;
		private DrugInfo drugInfo;
		private String meditdrugnameString=null;
	    public MyAsyncGetProduct(GridView M,String editdrugnameString,int kindid,ArrayAdapter<String> Adapter,LinkedList<String> ListItems,DrugInfo drug) { 
	    	drugInfo=drug;
	    	mGridView=M;
	    	MedType=M;
	    	mkindid=kindid;
	    	mAdapter=Adapter;
	    	mListItems=ListItems;
	    	meditdrugnameString=editdrugnameString;
	    }  
	   

	@Override
	protected String  doInBackground(String... params) {
		// TODO Auto-generated method stub
	try{	List<NameValuePair> parmsList=new ArrayList<NameValuePair>();
	
		parmsList.add(new BasicNameValuePair("proName",meditdrugnameString.toString().trim()));
		parmsList.add(new BasicNameValuePair("kindId",String.valueOf(mkindid)));
		parmsList.add(new BasicNameValuePair("pageNo","1".toString().trim()));
		
		
		HttpResponse httpResponse=tools.sendLoginInfo(parmsList,tools.ProUrl);
		JSONObject jsonObject =tools.ReceiveJSONObject(httpResponse);
		System.out.println(jsonObject.toString());
		
		Page page=JsonTolist.jsonToProList(jsonObject);
		DrugInfo.pages.add(page);
		List<Object> productlist=page.getMyProList();
		DrugInfo._productslist=productlist;
		int j=0;
			for(int i=0;i<DrugInfo._productslist.size();i++) 
				{   
				DrugInfo.mStrings[j]=((Product)DrugInfo._productslist.get(i)).getProId();
				DrugInfo.mStrings[j+1]=((Product)DrugInfo._productslist.get(i)).getProName();
				DrugInfo.mStrings[j+2]=((Product)DrugInfo._productslist.get(i)).getProRemark();
				j=j+3;
			}
			flag=true;
			return "t";
		}
		catch(Exception ex){System.out.println("执行完成了"); flag=true;
		return "f";}
		
	}
	protected void onPostExecute(String result) {// 后台任务执行完之后被调用，在ui线程执行   
	if (result=="t") {
		try{
		drugInfo.binddataDrug(drugInfo.mStrings,drugInfo);
		drugInfo.loadfirstData(drugInfo);		
		}
		catch(Exception ex)
		{	
			ex.printStackTrace();
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
	
