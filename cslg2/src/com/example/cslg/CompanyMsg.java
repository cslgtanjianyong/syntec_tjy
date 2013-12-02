package com.example.cslg;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.cslg.tools.*;
import com.example.cslg.R;
import com.example.source.CompanyMessage;
import com.example.source.KuCunInfo;

import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.R.integer;
import android.app.Activity;
import android.app.AlertDialog;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;

import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AbsListView.OnScrollListener;
import com.example.cslg.tools.CompanymsgList;
import com.joboevan.push.tool.PushManager;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
public class CompanyMsg extends Activity implements
OnScrollListener{
	private static String flag;
	private static List<CompanyMessage> companyMsgs=new ArrayList<CompanyMessage>();
	
	private List<News> news = new ArrayList<News>();
	private ListView listView;

	private int visibleLastIndex = 0; // 最后的可视项索引

private Button	btn_backmainCompangmsg;
	private PaginationAdapter adapter;

	private View loadMoreView;

	private boolean isConnect=false;

	private Handler handler = new Handler();

	
//控件初始化
public void init() 
{btn_backmainCompangmsg=(Button)findViewById(R.id.btn_backmainCompangmsg);
}
	
protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.companymsg);
		init();
	
	   AppManager.getAppManager().addActivity(this);
	
	   
	   SharedPreferences settings = getSharedPreferences("Mynotification", 0);
	   int num=settings.getInt("num", 0);
	   companyMsgs.clear();
	   for(int i=0;i<num;i++) 
	   {
		   CompanyMessage companyMsg=new CompanyMessage();
		   companyMsg.setFlag(settings.getString("flag"+(i+1), null));
		   companyMsg.setContentString(settings.getString("msgContextString"+(i+1), null));
		   companyMsg.setDatetimeString(settings.getString("datetime"+(i+1), null));
		   companyMsgs.add(companyMsg);
	   }
	  		listView = (ListView) findViewById(R.id.listViewlist);
				initializeAdapter();
		listView.setAdapter(adapter);
		listView.setOnScrollListener(this);
				listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				
				for(CompanyMessage c:companyMsgs) 
				{
					if(c.getDatetimeString()==news.get(arg2).getTitle()&&c.getContentString()==news.get(arg2).getContent())
					{
						flag=c.getFlag();
					}
				}
							Intent intent = new Intent(CompanyMsg.this, Returnmsg.class);
			Returnmsg.contentString=news.get(arg2).getContent();
			Returnmsg.flag=flag;
							startActivity(intent);
			}
		});
				btn_backmainCompangmsg.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						 Intent intent = new Intent(CompanyMsg.this,Mainmenu.class); 
					     startActivity(intent); 
					 	
					 	CompanyMsg.this.finish();
					 	overridePendingTransition(R.anim.slide_up_in, R.anim.slide_down_out);
					}
				});
}

@Override
public void onScrollStateChanged(AbsListView view, int scrollState) {

	int itemsLastIndex = adapter.getCount() - 1; // 数据集最后一项的索引
	int lastIndex = itemsLastIndex + 1;
	if (scrollState == OnScrollListener.SCROLL_STATE_IDLE
	&& visibleLastIndex == lastIndex) {

		// 如果是自动加载,可以在这里放置异步加载数据的代码

	}
}
/**
 * 
 * 初始化ListView的适配器
 */
private void initializeAdapter() {
	//清除listview列表
			cleanlist();
	if(companyMsgs.size()>=10) {
	for (int i =companyMsgs.size(); i >companyMsgs.size()-10; i--) {
		News items = new News();
		items.setTitle("通知时间"+companyMsgs.get(i-1).getDatetimeString());
		items.setContent("通知内容"+companyMsgs.get(i-1).getContentString());
		news.add(items);
	}
	adapter = new PaginationAdapter(news);
	
}
	else {
					for (int i =0; i <companyMsgs.size(); i++) {
			News items = new News();
			items.setTitle(companyMsgs.get(i).getDatetimeString());
			items.setContent(companyMsgs.get(i).getContentString());
			news.add(items);
		}
		adapter = new PaginationAdapter(news);
	}	
	}
class PaginationAdapter extends BaseAdapter {
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
		TextView tvTitle = (TextView) view.findViewById(R.id.newstitle);
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

@Override
public void onScroll(AbsListView arg0, int arg1, int arg2, int arg3) {
	// TODO Auto-generated method stub
	}
public void cleanlist(){
	int size=news.size();
	if(size>0){
		System.out.println(size);
		news.removeAll(news);
		adapter.notifyDataSetChanged();
		listView.setAdapter(adapter);
	}}
@Override
public boolean onKeyDown(int keyCode, KeyEvent event)//主要是对这个函数的复写
{
 // TODO Auto-generated method stub

 if((keyCode == KeyEvent.KEYCODE_BACK)&&(event.getAction() == KeyEvent.ACTION_DOWN))
 {
	 Intent intent = new Intent(CompanyMsg.this,Mainmenu.class); 
     startActivity(intent); 
 	overridePendingTransition(R.anim.slide_up_in, R.anim.slide_down_out);
 	CompanyMsg.this.finish();
  return true;
 }
 return super.onKeyDown(keyCode, event);
}


}


