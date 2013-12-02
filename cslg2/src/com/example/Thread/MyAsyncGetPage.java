package com.example.Thread;

import java.util.ArrayList;
import java.util.Arrays;
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

public class MyAsyncGetPage extends AsyncTask<String,Integer,String> {
		public static boolean flag=false;
		private GridView MedType = null;  
		private LinkedList<String> mListItems;
		private ArrayAdapter<String> mAdapter;
		private int mkindid=0;
		private GridView mGridView;
		private DrugInfo drugInfo;
		private String meditdrugnameString=null;
	    public MyAsyncGetPage(DrugInfo d) { 
	    	drugInfo=d;
	    }  
	    // �˺���������һ���߳������У����ܲ���UI�߳��еĿؼ�  
	

	@Override
	protected String  doInBackground(String... params) {
		// TODO Auto-generated method stub
		Page page=new Page();
		try 
		{
			if(DrugInfo.pages!=null){
				System.out.println(DrugInfo.pages.get(DrugInfo.pages.size()-1).getPageCount());
				System.out.println("��ǰҳ"+DrugInfo.pages.get(DrugInfo.pages.size()-1).getPageNow());
				if(DrugInfo.pages.get(DrugInfo.pages.size()-1).getPageCount()>DrugInfo.pages.get(DrugInfo.pages.size()-1).getPageNow())
				{
					List<NameValuePair> parmsList=new ArrayList<NameValuePair>();
					parmsList.add(new BasicNameValuePair("pageNo", String.valueOf(DrugInfo.pages.get(DrugInfo.pages.size()-1).getPageNow()+1)));
					parmsList.add(new BasicNameValuePair("proName",DrugInfo.proName));
					parmsList.add(new BasicNameValuePair("kindId",String.valueOf(DrugInfo.kindid)));
					HttpResponse httpResponse=tools.sendLoginInfo(parmsList,tools.ProUrl);
					JSONObject jsonObject =tools.ReceiveJSONObject(httpResponse);
					Page pageR=JsonTolist.jsonToProList(jsonObject);
					page=pageR;
					DrugInfo.pages.add(page);
					List<Object> productlist=page.getMyProList();
					for (Object p : productlist) {
						DrugInfo._productslist.add((Product)p);
					}
					
					System.out.println("��ǰҳΪ��"+DrugInfo.pages.get(1).getPageNow());
				}
				else
				{
					System.out.println("��Ϣ�Ѿ�ȡ��");
				}
					}
			return "t";
		}
		catch(Exception ex) {System.out.println(ex.getMessage());return "f";}
		
	}
	protected void onPostExecute(String result) {// ��̨����ִ����֮�󱻵��ã���ui�߳�ִ��   
		if (result=="t") {
			System.out.println(drugInfo.pages.get(drugInfo.pages.size()-1).getMyProList().size());
		for(int i=0; i<drugInfo.pages.get(drugInfo.pages.size()-1).getMyProList().size(); i++) 
		{
			drugInfo.mListItems.addFirst(((Product)drugInfo.pages.get(drugInfo.pages.size()-1).getMyProList().get(i)).getProRemark());
			drugInfo.mListItems.addFirst(((Product)drugInfo.pages.get(drugInfo.pages.size()-1).getMyProList().get(i)).getProName());
			drugInfo.mListItems.addFirst(((Product)drugInfo.pages.get(drugInfo.pages.size()-1).getMyProList().get(i)).getProId());
		}
		drugInfo.mAdapter.notifyDataSetChanged();

		drugInfo.mPullRefreshGridView.onRefreshComplete();
		
		drugInfo.loadMoreData(drugInfo);
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
	

//MyAsyncGetPage
