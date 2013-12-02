package com.example.cslg;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.Thread.MyAsyncGetMoreYouhuiinfo;
import com.example.Thread.MyAsyncGetYouhuiInfo;
import com.example.cslg.tools.AppManager;
import com.example.cslg.tools.Applicationtool;
import com.example.cslg.tools.YouhuiNews;
import com.example.source.DiscountInfo;
import com.example.source.Page;
public class YouhuiInfo extends Activity implements
OnScrollListener{ 
public static List<Page> pages=new ArrayList<Page>();
public static Page page=null;
public static HttpResponse httpResponse;
private Button btnseachyouhuiButton;
private EditText edityouhuiKey;
private ListView listView; 
private List<YouhuiNews> news = new ArrayList<YouhuiNews>();
private int visibleLastIndex = 0; // 最后的可视项索引
private int visibleItemCount; // 当前窗口可见项总数
public static int datasize=0; // 模拟数据集的条数
public PaginationAdapter adapter;
private View loadMoreView;
public Button loadMoreButton;
public Button btn_backyouhiuxinxi;
private  boolean isConnect=false;
private Handler handler = new Handler();

/** Called when the activity is first created. */

//初始化控件
private void init() {
	btnseachyouhuiButton=(Button)findViewById(R.id.btnsearchyouhui);
	edityouhuiKey=(EditText)findViewById(R.id.edityouhuiKey);
	listView=(ListView)findViewById(R.id.listView1x);
	loadMoreView = getLayoutInflater().inflate(R.layout.loadmore, null);

	loadMoreButton = (Button) loadMoreView.findViewById(R.id.loadMoreButton);
	btn_backyouhiuxinxi=(Button)findViewById(R.id.btn_backyouhiuxinxi);
}
@Override
protected void onCreate(Bundle savedInstanceState) { 
super.onCreate(savedInstanceState); 
this.requestWindowFeature(Window.FEATURE_NO_TITLE);
setContentView(R.layout.youhuiinfo); 
AppManager.getAppManager().addActivity(this);
init();
initializeAdapter();
btn_backyouhiuxinxi.setOnClickListener(new View.OnClickListener() {
	
	@Override
	public void onClick(View v) {
	
		 Intent intent = new Intent(YouhuiInfo.this,Mainmenu.class); 
	     startActivity(intent); 
	          YouhuiInfo.this.finish();
	          overridePendingTransition(R.anim.slide_up_in, R.anim.slide_down_out);
	}
});
btnseachyouhuiButton.setOnClickListener(new View.OnClickListener() {
	
	@Override
	public void onClick(View v) {
	
		//启动等待界面
		 Intent intentw = new Intent(YouhuiInfo.this,LoadingActivity.class); 
         startActivity(intentw); 
		//清除listview列表
		cleanlist();
		listView.removeFooterView(loadMoreView);
        pages.clear();
		// TODO Auto-generated method stub
		MyAsyncGetYouhuiInfo myAsyncGetYouhuiInfo=new MyAsyncGetYouhuiInfo(edityouhuiKey.getText().toString(),YouhuiInfo.this);
		myAsyncGetYouhuiInfo.execute();
		savehistory("优惠信息查询");
	}
});
//加载更多数据的事件
loadMoreButton.setOnClickListener(new View.OnClickListener() {

	@Override
	public void onClick(View v) {
	
		loadMoreButton.setText("正在加载中..."); // 设置按钮文字

		handler.postDelayed(new Runnable() {

			@Override
			public void run() {

				loadMoreData();


			}

		}, 3000);

	}
});

listView.setAdapter(adapter);
listView.setOnScrollListener(this);

}
@Override
public void onScrollStateChanged(AbsListView view, int scrollState) {

	int itemsLastIndex = adapter.getCount() - 1; // 数据集最后一项的索引

	int lastIndex = itemsLastIndex + 1;

	if (scrollState == OnScrollListener.SCROLL_STATE_IDLE

	&& visibleLastIndex == lastIndex) {

	

	}

}

@Override
public void onScroll(AbsListView view, int firstVisibleItem,

int visibleItemCount, int totalItemCount) {
	this.visibleItemCount = visibleItemCount;
	visibleLastIndex = firstVisibleItem + visibleItemCount - 1;
	// 如果所有的记录选项等于数据集的条数，则移除列表底部视图
	if(page==null)
		return;
	if (page.getPageCount()==page.getPageNow()+1) {
		listView.removeFooterView(loadMoreView);
		Toast.makeText(this, "数据全部加载完!", Toast.LENGTH_SHORT).show();
	}

}

/**
 * 
 * 初始化ListView的适配器
 */
private void initializeAdapter() {

	

	
	adapter = new PaginationAdapter(news);

}
//初次查询数据
public static void loadfirstData(YouhuiInfo y) {
	
	
if (pages.get(pages.size()-1).getPageCount()>1) {
	y.listView.addFooterView(y.loadMoreView); // 设置列表底部视图
}
	if (page.getRowCount()<6) {
		
		for (int i =0; i <page.getRowCount(); i++) {
			YouhuiNews item = new YouhuiNews();
			item.setTitle("药品名：" + ((DiscountInfo)page.getMyProList().get(i)).getProname());
			if(((DiscountInfo)page.getMyProList().get(i)).getDiscountRemark()==null)
			{
				item.setContent("优惠方案：" + ((DiscountInfo)page.getMyProList().get(i)).getDiscount()+"\n优惠起始时间："+new SimpleDateFormat("yyyy-MM-dd ").format(((DiscountInfo)page.getMyProList().get(i)).getDiscountCreateTime())+"\n优惠备注："+"");
				
				
			}
			else {
				item.setContent("优惠方案：" + ((DiscountInfo)page.getMyProList().get(i)).getDiscount()+"\n优惠起始时间："+new SimpleDateFormat("yyyy-MM-dd ").format(((DiscountInfo)page.getMyProList().get(i)).getDiscountCreateTime())+"\n优惠备注："+((DiscountInfo)page.getMyProList().get(i)).getDiscountRemark());
				
			}	y.adapter.addNewsItem(item);
			
			Toast.makeText(y, "数据全部加载完!", Toast.LENGTH_SHORT).show();
		}
	} 
	else {
		
		for (int i =0; i <6; i++) {
			YouhuiNews item = new YouhuiNews();
			item.setTitle("药品名：" + ((DiscountInfo)page.getMyProList().get(i)).getProname());
			if(((DiscountInfo)page.getMyProList().get(i)).getDiscountRemark()==null)
			{
				item.setContent("优惠方案：" + ((DiscountInfo)page.getMyProList().get(i)).getDiscount()+"\n优惠起始时间："+new SimpleDateFormat("yyyy-MM-dd ").format(((DiscountInfo)page.getMyProList().get(i)).getDiscountCreateTime())+"\n优惠备注："+"");
				
				
			}
			else {
				item.setContent("优惠方案：" + ((DiscountInfo)page.getMyProList().get(i)).getDiscount()+"\n优惠起始时间："+new SimpleDateFormat("yyyy-MM-dd ").format(((DiscountInfo)page.getMyProList().get(i)).getDiscountCreateTime())+"\n优惠备注："+((DiscountInfo)page.getMyProList().get(i)).getDiscountRemark());
				
			}	y.adapter.addNewsItem(item);
			
		}
		
		
	}
	y.listView.setAdapter(y.adapter);
	//
	AppManager.getAppManager().finishActivity(LoadingActivity.class);
}
/**
 * 
 * 加载更多数据
 */

private void loadMoreData() {
	
	int count = adapter.getCount();
	GetPage();
	
	
		int co=page.getMyProList().size();
		for (int i =0; i <co; i++) {
			YouhuiNews item = new YouhuiNews();
			item.setTitle("药品名：" + ((DiscountInfo)page.getMyProList().get(i)).getProname());
			if(((DiscountInfo)page.getMyProList().get(i)).getDiscountRemark()==null)
			{
				item.setContent("优惠方案：" + ((DiscountInfo)page.getMyProList().get(i)).getDiscount()+"\n优惠起始时间："+new SimpleDateFormat("yyyy-MM-dd ").format(((DiscountInfo)page.getMyProList().get(i)).getDiscountCreateTime())+"\n优惠备注："+"");
				
				
			}
			else {
				item.setContent("优惠方案：" + ((DiscountInfo)page.getMyProList().get(i)).getDiscount()+"\n优惠起始时间："+new SimpleDateFormat("yyyy-MM-dd ").format(((DiscountInfo)page.getMyProList().get(i)).getDiscountCreateTime())+"\n优惠备注："+((DiscountInfo)page.getMyProList().get(i)).getDiscountRemark());
				
			}
			
			
			adapter.addNewsItem(item);
		
	} 

if (pages.get(pages.size()-1).getPageCount()==pages.get(pages.size()-1).getPageNow()) {
	listView.removeFooterView(loadMoreView); // 设置列表底部视图
}
		
		
}

public class PaginationAdapter extends BaseAdapter {

	List<YouhuiNews> newsItems;

	public PaginationAdapter(List<YouhuiNews> newsitems) {

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
		tvContent.setTextColor(0x7f05000f);
		tvContent.setText(newsItems.get(position).getContent());

		return view;

	}

	/**
	 * 
	 * 添加数据列表项
	 * 
	 * @param newsitem
	 */

	public void addNewsItem(YouhuiNews newsitem) {

		newsItems.add(newsitem);

	}

}
private void GetPage() 
{
	
	try 
	{
		if(pages!=null){
			System.out.println(pages.get(pages.size()-1).getPageCount());
			System.out.println("当前页"+pages.get(pages.size()-1).getPageNow());
			if(pages.get(pages.size()-1).getPageCount()>pages.get(pages.size()-1).getPageNow())
			{
				System.out.println(edityouhuiKey.getText().toString()+"------"+String.valueOf(pages.get(pages.size()-1).getPageNow()+1));
				MyAsyncGetMoreYouhuiinfo myAsyncGetMoreYouhuiinfo=new MyAsyncGetMoreYouhuiinfo(edityouhuiKey.getText().toString(), String.valueOf(pages.get(pages.size()-1).getPageNow()+1),YouhuiInfo.this);
				myAsyncGetMoreYouhuiinfo.execute();
			}
			else
			{
				System.out.println("消息已经取完");
			}
				}}
	catch(Exception ex) {System.out.println(ex.getMessage());}
	
}
public void cleanlist(){
	int size=news.size();
	if(size>0){
		System.out.println(size);
		news.removeAll(news);
		adapter.notifyDataSetChanged();
		listView.setAdapter(adapter);
	}
}

@Override
public boolean onKeyDown(int keyCode, KeyEvent event)//主要是对这个函数的复写
{
 // TODO Auto-generated method stub

 if((keyCode == KeyEvent.KEYCODE_BACK)&&(event.getAction() == KeyEvent.ACTION_DOWN))
 {
	 Intent intent = new Intent(YouhuiInfo.this,Mainmenu.class); 
     startActivity(intent); 
          YouhuiInfo.this.finish();
          overridePendingTransition(R.anim.slide_up_in, R.anim.slide_down_out);
  return true;
 }
 return super.onKeyDown(keyCode, event);
}
public  void savehistory(String operationname) {
	 SharedPreferences sharedPreferencesget = getSharedPreferences("historys", Context.MODE_PRIVATE);
		//getString()第二个参数为缺省值，如果preference中不存在该key，将返回缺省值
		String lastname=sharedPreferencesget.getString("operationname", "");
		String lasttime=sharedPreferencesget.getString("operationtime", "");
	 if (lastname=="") {
		SharedPreferences sharedPreferences =getSharedPreferences("historys", Context.MODE_PRIVATE);
	Editor editor = sharedPreferences.edit();//获取编辑器
	editor.putString("operationname", operationname+"@");
	editor.putString("operationtime", Applicationtool.sysytemtime().toString()+"@");
	editor.commit();//提交修改
	}
	 else {
		 SharedPreferences sharedPreferences =getSharedPreferences("historys", Context.MODE_PRIVATE);
			Editor editor = sharedPreferences.edit();//获取编辑器
			editor.putString("operationname",lastname+operationname+"@");
			editor.putString("operationtime", lasttime+Applicationtool.sysytemtime().toString()+"@");
			editor.commit();//提交修改
	}
	 
	
}

} 