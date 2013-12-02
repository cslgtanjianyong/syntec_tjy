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
			try{List<NameValuePair> parmsList=new ArrayList<NameValuePair>();	
		parmsList.add(new BasicNameValuePair("proid", proid
				));
		parmsList.add(new BasicNameValuePair("proCount", "200"
				.toString()));
		HttpResponse httpResponse=tools.sendLoginInfo(parmsList,tools.GetYouhuiUrl);
	
		j =tools.ReceiveJSONObject(httpResponse);
			
			//��ȡjson�е�flagֵ���ҽ����ж�
		return "t";
		} 
		catch(Exception ex){
			return "f";
		}
		
	}
	protected void onPostExecute(String result) {// ��̨����ִ����֮�󱻵��ã���ui�߳�ִ��   
	if (result=="t") {
				Myyugouinfo.discount=JsonTolist.jsonTogetDiscount(j);
	}
	else {
		new Mainmenu().torst();
	}

		
	
	
	}  
      
       
    protected void onPreExecute() {// ��doInBackground(Params...)֮ǰ�����ã���ui�߳�ִ��   
        super.onPreExecute();  
    }  

    protected void onCancelled() {// ��ui�߳�ִ��   
        super.onCancelled();  
    }  
	
  
}
