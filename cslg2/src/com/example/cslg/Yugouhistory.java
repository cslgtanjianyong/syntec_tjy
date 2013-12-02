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
private int visibleLastIndex = 0; // ���Ŀ���������
private int visibleItemCount; // ��ǰ���ڿɼ�������
private int datasize = 38; // ģ�����ݼ�������
private PaginationAdapter adapter;
private View loadMoreView;
private Button loadMoreButton;
private Handler handler = new Handler();
public void init() 
{//��ʼ���ؼ�

}	

	
protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.yugouhistory);
		AppManager.getAppManager().addActivity(this);
		//��ʼ���ؼ�
		init();
		//�����¼�
		loadMoreView = getLayoutInflater().inflate(R.layout.loadmore, null);

		loadMoreButton = (Button) loadMoreView
				.findViewById(R.id.loadMoreButton);

		loadMoreButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				loadMoreButton.setText("���ڼ�����..."); // ���ð�ť����

				handler.postDelayed(new Runnable() {

					@Override
					public void run() {

						loadMoreData();

						adapter.notifyDataSetChanged();

						loadMoreButton.setText("�鿴����..."); // �ָ���ť����

					}

				}, 3000);

			}

		});

		listView = (ListView) findViewById(R.id.listViewyugoulist);

	//	listView.addFooterView(loadMoreView); // �����б�ײ���ͼ

		initializeAdapter();

		listView.setAdapter(adapter);

		listView.setOnScrollListener(this);

		
		
		
}
@Override
public void onScrollStateChanged(AbsListView view, int scrollState) {

	int itemsLastIndex = adapter.getCount() - 1; // ���ݼ����һ�������

	int lastIndex = itemsLastIndex + 1;

	if (scrollState == OnScrollListener.SCROLL_STATE_IDLE

	&& visibleLastIndex == lastIndex) {

		// ������Զ�����,��������������첽�������ݵĴ���

	}

}

@Override
public void onScroll(AbsListView view, int firstVisibleItem,

int visibleItemCount, int totalItemCount) {

	this.visibleItemCount = visibleItemCount;

	visibleLastIndex = firstVisibleItem + visibleItemCount - 1;


	if (totalItemCount == datasize + 1) {

		listView.removeFooterView(loadMoreView);

		Toast.makeText(this, "����ȫ��������!", Toast.LENGTH_LONG).show();

	}

}

/**
 * 
 * ��ʼ��ListView��������
 */
private void initializeAdapter() {
	List<Newsyugoulist> news = new ArrayList<Newsyugoulist>();
	 SharedPreferences sharedPreferencesget = getSharedPreferences("yugouhistorys", Context.MODE_PRIVATE);
		//getString()�ڶ�������Ϊȱʡֵ�����preference�в����ڸ�key��������ȱʡֵ
		String lastname=sharedPreferencesget.getString("yugouname", "");
		String lasttime=sharedPreferencesget.getString("yugoutime", "");
		String lastdrugname=sharedPreferencesget.getString("yugoudrugname", "");
		String lastdrugnum=sharedPreferencesget.getString("yugoudrugnum", "");
		String lastdrugprice=sharedPreferencesget.getString("yugoudrugprice", "");
	
	if (lastname=="") {
		Toast toast = Toast.makeText(Yugouhistory.this.getApplicationContext(),
    			"������Ϣ��ʾ��", Toast.LENGTH_LONG);
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
	Date    curDate    =   new    Date(System.currentTimeMillis());//��ȡ��ǰʱ��       
	String    nowtime    =    formatter.format(curDate);
	for (int i = 0; i <kname.length; i++) {
		String[] resS=ktime[i].split("��");
		if (resS[0]==null) {
			return;
				}
		if (resS[0].equals(nowtime)) {
			Newsyugoulist items = new Newsyugoulist();
		items.setTitle("Ԥ��ʱ�䣺"+ktime[i]+ "\nԤ���ͻ���"+kname[i]);
		items.setContent("Ԥ��ҩƷ��"+dname[i]+"\nԤ��������"+dnum[i]+"\tԤ���ܼۣ�"+dprice[i]+"Ԫ");
		news.add(items);
		}
		
	}

	adapter = new PaginationAdapter(news);

}

/**
 * 
 * ���ظ�������
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

		// ���ű���

		TextView tvTitle = (TextView) view.findViewById(R.id.newstitle);

		tvTitle.setText(newsItems.get(position).getTitle());

		// ��������

		TextView tvContent = (TextView) view.findViewById(R.id.newscontent);

		tvContent.setText(newsItems.get(position).getContent());

		return view;

	}

	/**
	 * 
	 * ��������б���
	 * 
	 * @param newsitem
	 */

	public void addNewsItem(Newsyugoulist newsitem) {

		newsItems.add(newsitem);

	}

}


@Override
public boolean onKeyDown(int keyCode, KeyEvent event)//��Ҫ�Ƕ���������ĸ�д
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
