package com.example.cslg;
import java.util.ArrayList;
import java.util.List;
import com.example.cslg.tools.AppManager;
import com.example.cslg.tools.Newssoftbook;
import com.example.cslg.R;
import android.os.Bundle;
import android.os.Handler;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AbsListView.OnScrollListener;
@SuppressLint("ResourceAsColor")
public class Softmianzeshengming extends Activity implements
OnScrollListener {
	private ListView listView;
	private int visibleLastIndex = 0; // 最后的可视项索引
	private int visibleItemCount; // 当前窗口可见项总数
	
	private PaginationAdapter adapter;
private Button	btn_backsoftmianzeshengming;
	
	private Handler handler = new Handler();


public void init() 
{//初始化控件
	listView = (ListView) findViewById(R.id.listViewsoftmianze);
	btn_backsoftmianzeshengming=(Button)findViewById(R.id.btn_backsoftmianzeshengming);
	initializeAdapter();

	listView.setAdapter(adapter);

	listView.setOnScrollListener(this);
}	

	
protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.softmianzeshengming);
		AppManager.getAppManager().addActivity(this);
		//初始化控件
		init();
		btn_backsoftmianzeshengming.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Softmianzeshengming.this.finish();
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
@Override
public void onScroll(AbsListView view, int firstVisibleItem,

int visibleItemCount, int totalItemCount) {

	


}

/**
 * 
 * 初始化ListView的适配器
 */
private void initializeAdapter() {

	List<Newssoftbook> news = new ArrayList<Newssoftbook>();
	
Newssoftbook items4 = new Newssoftbook();

items4.setTitle("免责与责任限制 " );

items4.setContent("本软件经过详细的测试，但不能保证与所有的软硬件系统完全兼容，不能保证本软件完全没有错误。如果出现不兼容及软件错误的情况，用户可拨打技术支持电话将情况报告本公司，获得技术支持。如果无法解决兼容性问题，用户可以删除本软件。\n使用本软件产品风险由用户自行承担，在适用法律允许的最大范围内，对因使用或不能使用本软件所产生的损害及风险，包括但不限于直接或间接的个人损害、商业赢利的丧失、贸易中断、商业信息的丢失或任何其它经济损失，本公司不承担任何责任。\n对于因电信系统或互联网网络故障、计算机故障或病毒、信息损坏或丢失、计算机系统问题或其它任何不可抗力原因而产生损失，本公司不承担任何责任。\n用户违反本协议规定，对本公司公司造成损害的。本公司有权采取包括但不限于中断使用许可、停止提供服务、限制使用、法律追究等措施。\n法律及争议解决\n本协议适用中华人民共和国法律。 \n因本协议引起的或与本协议有关的任何争议，各方应友好协商解决；协商不成的，任何一方均可将有关争议提交至北京仲裁委员会并按照其届时有效的仲裁规则仲裁；仲裁裁决是终局的，对各方均有约束力。");

news.add(items4);

	adapter = new PaginationAdapter(news);

}
class PaginationAdapter extends BaseAdapter {

	List<Newssoftbook> newsItems;

	public PaginationAdapter(List<Newssoftbook> newsitems) {

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

			view = getLayoutInflater().inflate(R.layout.listsoftbook, null);

		}

		// 新闻标题

		TextView tvTitle = (TextView) view.findViewById(R.id.newstitle);
		tvTitle.setTextSize(20);
		tvTitle.setText("\t\t\t\t\t\t"+newsItems.get(position).getTitle());

		// 新闻内容

		TextView tvContent = (TextView) view.findViewById(R.id.newscontent);
		tvContent.setTextSize(15);
		tvContent.setText(newsItems.get(position).getContent());

		return view;

	}

	/**
	 * 
	 * 添加数据列表项
	 * 
	 * @param newsitem
	 */

	public void addNewsItem(Newssoftbook newsitem) {

		newsItems.add(newsitem);

	}

}




@Override
public boolean onKeyDown(int keyCode, KeyEvent event)//主要是对这个函数的复写
{
 // TODO Auto-generated method stub

 if((keyCode == KeyEvent.KEYCODE_BACK)&&(event.getAction() == KeyEvent.ACTION_DOWN))
 {
	 Intent intent = new Intent(Softmianzeshengming.this,Systemset.class); 
     startActivity(intent); 
     Softmianzeshengming.this.finish();
    
  return true;
 }
 return super.onKeyDown(keyCode, event);
}


}