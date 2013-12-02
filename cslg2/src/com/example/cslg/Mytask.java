package com.example.cslg;

import java.util.ArrayList;
import java.util.List;

import javax.security.auth.PrivateCredentialPermission;

import com.example.Thread.MyAsyncGetCustomers;
import com.example.cslg.tools.AppManager;
import com.example.cslg.tools.News;
import com.example.cslg.tools.tools;
import com.example.source.Customer;

import android.R.integer;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.os.Handler;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
public class Mytask extends Activity  {
	protected static final String n = null;
	private ListView listView;
	private static int Num;

	private String customerid="0";

	private  List<News> news = new ArrayList<News>();

	private TextView tvTitle;
	private PaginationAdapter adapter;

	private static View listitemView;

private Button	btn_backmytask;
	private Handler handler = new Handler();
	public static void SaveCustomers(Mytask m)
	{
		SharedPreferences settings = m.getSharedPreferences("Customers", 0);
		SharedPreferences.Editor editor = settings.edit();
		editor.putString("customername10","abc");
		if(tools.customers.size()!=0)
		{
		if(tools.customers.size()!=settings.getInt("num", 0) || tools.customers.get(0).getId()!=settings.getInt("id"+0, 0))
		{
		editor.putInt("num",tools.customers.size());
		for(int i=0;i<tools.customers.size();i++)
		{
			editor.putString("customername"+i,tools.customers.get(i).getCustomerName());
			editor.putString("customerRemark"+i,tools.customers.get(i).getCustomerRemark());
			editor.putString("customerid"+i,tools.customers.get(i).getCustomerId());
			editor.putString("customerAddress"+i,tools.customers.get(i).getCustomerAddress());
			editor.putString("ClinkTell"+i,tools.customers.get(i).getClinkTell());
			editor.putString("cLinkname"+i,tools.customers.get(i).getcLinkname());
			editor.putInt("id"+i,tools.customers.get(i).getId());
			
			editor.putString("customerSF"+i,tools.customers.get(i).getProvince());
			editor.putString("customerCity"+i,tools.customers.get(i).getCity());
			editor.putString("customerBusinessType"+i,tools.customers.get(i).getBizType());
			editor.putInt("customercredit"+i,tools.customers.get(i).getCredit());
			
		}
		System.out.println("更新成功");
		}
		}
		editor.commit();
		Num=settings.getInt("num", 0);
		m.getCustomers();
		System.out.println("------"+Num);
		System.out.println(settings.getString("customerid0", null));
	}
	public   void getCustomers()
	{
		SharedPreferences settings = getSharedPreferences("Customers", 0);
		tools.customers.clear();
		for(int i=0;i<Num;i++)
		{
			Customer customer=new Customer();
			customer.setCustomerName(settings.getString("customername"+i, null));
			customer.setCustomerRemark(settings.getString("customerRemark"+i, null));
			customer.setCustomerId(settings.getString("customerid"+i, null));
			customer.setCustomerAddress(settings.getString("customerAddress"+i, null));
			customer.setClinkTell(settings.getString("ClinkTell"+i, null));
			customer.setcLinkname(settings.getString("cLinkname"+i, null));
			customer.setId(settings.getInt("id"+i, 0));	
			customer.setProvince(settings.getString("customerSF"+i, null));
			customer.setCity(settings.getString("customerCity"+i, null));
			customer.setBizType(settings.getString("customerBusinessType"+i, null));
			customer.setCredit(settings.getInt("customercredit"+i, 0));
			
			tools.customers.add(customer);
			
		}
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.mytask);
		AppManager.getAppManager().addActivity(this);
	
		
		 Intent intentw = new Intent(Mytask.this,LoadingActivity.class); 
         startActivity(intentw); 
         try {MyAsyncGetCustomers myAsyncGetCustomers = new MyAsyncGetCustomers(Mytask.this);
		myAsyncGetCustomers.execute();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			Toast toast = Toast.makeText(Mytask.this,
	    			"网络连接中断，请检查网络！", Toast.LENGTH_LONG);
	    			toast.setGravity(Gravity.CENTER, 0, 0);
	    			toast.show();	
		}

         
		init();
		btn_backmytask.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				 Intent intent = new Intent(Mytask.this,Mainmenu.class); 
			     startActivity(intent); 
			     Mytask.this.finish();
			     overridePendingTransition(R.anim.slide_up_in, R.anim.slide_down_out);
			}
		});
		listView.setOnItemClickListener(new OnItemClickListener() {
		
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				listitemView=arg1;
				
				String[] a=news.get(arg2).getTitle().split("：");
				if(a[1]==null||a[1]=="") {return;}
				// TODO Auto-generated method stub
				for (int i = 0; i < tools.customers.size(); i++) {
					
					
					if (a[1].equals(tools.customers.get(i)
							.getCustomerName()) ) {

						customerid = String.valueOf(tools.customers.get(i)
								.getId());
						break;
					}
				}

				Intent intent = new Intent(Mytask.this, Taskdetials.class);
				intent.putExtra("customerid", customerid);
				intent.putExtra("customername", news.get(arg2).getTitle());
				intent.putExtra("linkname", tools.customers.get(arg2).getcLinkname());
				intent.putExtra("linkadress",tools.customers.get(arg2).getCustomerAddress());
				intent.putExtra("linktel", tools.customers.get(arg2).getClinkTell());
				intent.putExtra("linkbeizhu", tools.customers.get(arg2).getCustomerRemark());
				
				intent.putExtra("customerSF", tools.customers.get(arg2).getProvince());
				intent.putExtra("customerCity",tools.customers.get(arg2).getCity());
				intent.putExtra("customerBusinessType", tools.customers.get(arg2).getBizType());
				intent.putExtra("customercredit", tools.customers.get(arg2).getCredit());
				int ii=tools.customers.get(arg2).getCredit();
				startActivity(intent);

			}
		});


	}
	private void init() {
		listView=(ListView) findViewById(R.id.listView1);
		btn_backmytask=(Button) findViewById(R.id.btn_backmytask);
	}
	
	

		/**
		 * 
		 * 初始化ListView的适配器
		 */
		public static void initializeAdapter(Mytask m) {

		
		if(tools.customers.size()!=0) {
		for (int i = 0; i <tools.customers.size(); i++) {

			News items = new News();
			
			items.setTitle("拜访客户："+tools.customers.get(i).getCustomerName());

			items.setContent("联系人："+tools.customers.get(i).getcLinkname()+"\n查看任务详情");

			m.news.add(items);

		}}
		else {
			News items = new News();
	
			items.setTitle("无任务");

			items.setContent("任务没有绑定" );

			m.news.add(items);
	
			}

			m.adapter = m.new PaginationAdapter(m.news);
			m.listView.setAdapter(m.adapter);
			AppManager.getAppManager().finishActivity(LoadingActivity.class);

	}



	public class PaginationAdapter extends BaseAdapter {

		List<News> newsItems;

		public PaginationAdapter(List<News> newsitems) {

			this.newsItems = newsitems;

		}

		@Override
		public int getCount() {

			return newsItems.size();
		}

		@Override
		public Object getItem(int position) {

			return newsItems.get(position);

		}

		@Override
		public long getItemId(int position) {

			return position;

		}

		@Override
		public View getView(int position, View view, ViewGroup parent) {

			if (view == null) {

				view = getLayoutInflater().inflate(R.layout.list_item, null);

			}

			// 新闻标题

			 tvTitle = (TextView) view.findViewById(R.id.newstitle);

			tvTitle.setText(newsItems.get(position).getTitle());

			// 新闻内容

			TextView tvContent = (TextView) view.findViewById(R.id.newscontent);

			tvContent.setText(newsItems.get(position).getContent());

			return view;

		}

		/**
		 * 
		 * 添加数据列表项
		 * 
		 * @param newsitem
		 */

		public void addNewsItem(News newsitem) {

			newsItems.add(newsitem);

		}
		
	}
	public static void setitemcolor() {
		listitemView.setBackgroundColor(R.drawable.app_list_corneras);

	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)//主要是对这个函数的复写
	{
	 // TODO Auto-generated method stub

	 if((keyCode == KeyEvent.KEYCODE_BACK)&&(event.getAction() == KeyEvent.ACTION_DOWN))
	 {
		 Intent intent = new Intent(Mytask.this,Mainmenu.class); 
	     startActivity(intent); 
	     Mytask.this.finish();
	     overridePendingTransition(R.anim.slide_up_in, R.anim.slide_down_out);
	  return true;
	 }
	 return super.onKeyDown(keyCode, event);
	}


	}