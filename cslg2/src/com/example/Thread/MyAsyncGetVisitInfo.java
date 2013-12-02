package com.example.Thread;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.view.Gravity;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;

import com.example.cslg.DrugInfo;
import com.example.cslg.LoadingActivity;
import com.example.cslg.tools.AppManager;
import com.example.cslg.tools.JsonTolist;
import com.example.cslg.tools.tools;
import com.example.source.Page;
import com.example.source.Product;

public class MyAsyncGetVisitInfo extends AsyncTask<String,Integer,String> {
	public static boolean flag=false;
	private GridView MedType = null;  
	private LinkedList<String> mListItems;
	private ArrayAdapter<String> mAdapter;
	private int mkindid=0;
	private GridView mGridView;
	private DrugInfo drugInfo;
	private String meditdrugnameString=null;
    public MyAsyncGetVisitInfo(GridView M,String editdrugnameString,int kindid,ArrayAdapter<String> Adapter,LinkedList<String> ListItems,DrugInfo drug) { 
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

	parmsList.add(new BasicNameValuePair("id",String.valueOf(tools._me.getId())));
	
	
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
	catch(Exception ex){System.out.println("ִ�������");flag=true;
	return "f";}
	
}
protected void onPostExecute(String result) {// ��̨����ִ����֮�󱻵��ã���ui�߳�ִ��   
	if (result=="t") {
			System.out.println("123");
	drugInfo.binddataDrug(drugInfo.mStrings,drugInfo);
	drugInfo.loadfirstData(drugInfo);	
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
