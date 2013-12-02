package com.example.cslg;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import com.example.cslg.tools.*;
import com.example.cslg.R;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AbsListView.OnScrollListener;
public class Yugouhistory extends Activity implements
OnScrollListener{
	private ListView listView;
private int visibleLastIndex = 0; // 最后的可视项索引
private int visibleItemCount; // 当前窗口可见项总数
private int datasize = 38; // 模拟数据集的条数
private PaginationAdapter adapter;
private View loadMoreView;
private Button loadMoreButton;
private Handler handler = new Handler();
public void init() 
{//初始化控件

}	

	
protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.yugouhistory);
		AppManager.getAppManager().addActivity(this);
		//初始化控件
		init();
		//搜索事件
		loadMoreView = getLayoutInflater().inflate(R.layout.loadmore, null);

		loadMoreButton = (Button) loadMoreView
				.findViewById(R.id.loadMoreButton);

		loadMoreButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				loadMoreButton.setText("正在加载中..."); // 设置按钮文字

				handler.postDelayed(new Runnable() {

					@Override
					public void run() {

						loadMoreData();

						adapter.notifyDataSetChanged();

						loadMoreButton.setText("查看更多..."); // 恢复按钮文字

					}

				}, 3000);

			}

		});

		listView = (ListView) findViewById(R.id.listViewyugoulist);

	//	listView.addFooterView(loadMoreView); // 设置列表底部视图

		initializeAdapter();

		listView.setAdapter(adapter);

		listView.setOnScrollListener(this);

		
		
		
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

@Override
public void onScroll(AbsListView view, int firstVisibleItem,

int visibleItemCount, int totalItemCount) {

	this.visibleItemCount = visibleItemCount;

	visibleLastIndex = firstVisibleItem + visibleItemCount - 1;


	if (totalItemCount == datasize + 1) {

		listView.removeFooterView(loadMoreView);

		Toast.makeText(this, "数据全部加载完!", Toast.LENGTH_LONG).show();

	}

}

/**
 * 
 * 初始化ListView的适配器
 */
private void initializeAdapter() {
	List<Newsyugoulist> news = new ArrayList<Newsyugoulist>();
	 SharedPreferences sharedPreferencesget = getSharedPreferences("yugouhistorys", Context.MODE_PRIVATE);
		//getString()第二个参数为缺省值，如果preference中不存在该key，将返回缺省值
		String lastname=sharedPreferencesget.getString("yugouname", "");
		String lasttime=sharedPreferencesget.getString("yugoutime", "");
		String lastdrugname=sharedPreferencesget.getString("yugoudrugname", "");
		String lastdrugnum=sharedPreferencesget.getString("yugoudrugnum", "");
		String lastdrugprice=sharedPreferencesget.getString("yugoudrugprice", "");
	
	if (lastname=="") {
		Toast toast = Toast.makeText(Yugouhistory.this.getApplicationContext(),
    			"暂无信息显示！", Toast.LENGTH_LONG);
    			toast.setGravity(Gravity.CENTER, 0, 0);
    			toast.show();	
		return;
	}
	String[] kname=lastname.split("@");
	String[] ktime=lasttime.split("@");
	String[] dname=lastdrugname.split("@");
	String[] dnum=lastdrugnum.split("@");
	String[] dprice=lastdrugprice.split("@");
	SimpleDateFormat    formatter    =   new    SimpleDateFormat    ("MM");       
	Date    curDate    =   new    Date(System.currentTimeMillis());//获取当前时间       
	String    nowtime    =    formatter.format(curDate);
	for (int i = 0; i <kname.length; i++) {
		String[] resS=ktime[i].split("月");
		if (resS[0]==null) {
			return;
				}
		if (resS[0].equals(nowtime)) {
			Newsyugoulist items = new Newsyugoulist();
		items.setTitle("预购时间："+ktime[i]+ "\n预购客户："+kname[i]);
		items.setContent("预购药品："+dname[i]+"\n预购数量："+dnum[i]+"\t预付总价："+dprice[i]+"元");
		news.add(items);
		}
		
	}

	adapter = new PaginationAdapter(news);

}

/**
 * 
 * 加载更多数据
 */

private void loadMoreData() {

	int count = adapter.getCount();

	if (count + 10 <= datasize) {

		for (int i = count + 1; i <= count + 10; i++) {

			Newsyugoulist item = new Newsyugoulist();

			item.setTitle("Title" + i);

			item.setContent("This is News Content" + i);

			adapter.addNewsItem(item);

		}

	} else {

		for (int i = count + 1; i <= datasize; i++) {

			Newsyugoulist item = new Newsyugoulist();

			item.setTitle("Title" + i);

			item.setContent("This is News Content" + i);

			adapter.addNewsItem(item);

		}

	}

}

class PaginationAdapter extends BaseAdapter {

	List<Newsyugoulist> newsItems;

	public PaginationAdapter(List<Newsyugoulist> newsitems) {

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

	public void addNewsItem(Newsyugoulist newsitem) {

		newsItems.add(newsitem);

	}

}


@Override
public boolean onKeyDown(int keyCode, KeyEvent event)//主要是对这个函数的复写
{
 // TODO Auto-generated method stub

 if((keyCode == KeyEvent.KEYCODE_BACK)&&(event.getAction() == KeyEvent.ACTION_DOWN))
 {
	 Intent intent = new Intent(Yugouhistory.this,Mainmenu.class); 
     startActivity(intent); 
     Yugouhistory.this.finish();
     overridePendingTransition(R.anim.slide_up_in, R.anim.slide_down_out);
  return true;
 }
 return super.onKeyDown(keyCode, event);
}
}
