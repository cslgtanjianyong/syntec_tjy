package com.example.cslg;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import com.example.Thread.MyAsyncGetVisitInfoMonth;
import com.example.cslg.tools.AppManager;
import com.example.cslg.tools.Applicationtool;
import com.example.cslg.tools.JobListNews;

import com.example.cslg.tools.*;
import com.example.cslg.R;
import com.example.source.Page;
import com.example.source.Product;
import com.example.source.VisitInfoGet;

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

public class MyJobList extends Activity implements
OnScrollListener{
public ListView listView;
private int visibleLastIndex = 0; // ���Ŀ���������
private int visibleItemCount; // ��ǰ���ڿɼ�������
private int datasize = 38; // ģ�����ݼ�������
public PaginationAdapter adapter;
public View loadMoreView;
private Button loadMoreButton;
private boolean isConnect=false;
public static List<VisitInfoGet> visitInfos=new ArrayList<VisitInfoGet>();
private Handler handler = new Handler();

public void init() 
{//��ʼ���ؼ�
	loadMoreView = getLayoutInflater().inflate(R.layout.loadmore, null);

	loadMoreButton = (Button) loadMoreView
			.findViewById(R.id.loadMoreButton);
	listView = (ListView) findViewById(R.id.listViewjoblist);
}	

	
protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.myjoblist);
		AppManager.getAppManager().addActivity(this);
		//��ʼ���ؼ�
		init();

		 Intent intentw = new Intent(MyJobList.this,LoadingActivity.class); 
         startActivity(intentw); 
		
		 MyAsyncGetVisitInfoMonth myAsyncGetVisitInfoMonth=new MyAsyncGetVisitInfoMonth(MyJobList.this);
		 myAsyncGetVisitInfoMonth.execute();
		 savehistory("�鿴������¼");
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

	// ������еļ�¼ѡ��������ݼ������������Ƴ��б�ײ���ͼ

	if (totalItemCount == datasize + 1) {

		listView.removeFooterView(loadMoreView);

		Toast.makeText(this, "����ȫ��������!", Toast.LENGTH_LONG).show();

	}

}

/**
 * 
 * ��ʼ��ListView��������
 */
public void initializeAdapter() {

	List<JobListNews> news = new ArrayList<JobListNews>();

	for (int i = 0; i <visitInfos.size(); i++) {

		JobListNews items = new JobListNews();
		
	String aString=	new SimpleDateFormat("yyyy-MM-dd ").format(visitInfos.get(i).getVisitDay());
		items.setTitle("�ݷ����ڣ�" + aString+"\n�ݷÿͻ���"+ visitInfos.get(i).getCustomerName());

		items.setContent("�ݷ�Ŀ�ģ�" + visitInfos.get(i).getGoal()+"\n�ݷ����ݣ�"+ visitInfos.get(i).getContent());

		news.add(items);

	}

	adapter = new PaginationAdapter(news);
	AppManager.getAppManager().finishActivity(LoadingActivity.class);
}

/**
 * ��ҳ����
 * ���ظ�������
 */

private void loadMoreData() {

	int count = adapter.getCount();

	if (count + 10 <= datasize) {

		for (int i = count + 1; i <= count + 10; i++) {

			JobListNews item = new JobListNews();

			item.setTitle("������¼" + i);

			item.setContent("��������" + i);

			adapter.addNewsItem(item);

		}

	} else {

		for (int i = count + 1; i <= datasize; i++) {

			JobListNews item = new JobListNews();

			item.setTitle("Title" + i);

			item.setContent("This is News Content" + i);

			adapter.addNewsItem(item);

		}

	}

}

class PaginationAdapter extends BaseAdapter {

	List<JobListNews> newsItems;

	public PaginationAdapter(List<JobListNews> newsitems) {

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

	public void addNewsItem(JobListNews newsitem) {

		newsItems.add(newsitem);

	}

}
@Override
public boolean onKeyDown(int keyCode, KeyEvent event)//��Ҫ�Ƕ���������ĸ�д
{
 // TODO Auto-generated method stub

 if((keyCode == KeyEvent.KEYCODE_BACK)&&(event.getAction() == KeyEvent.ACTION_DOWN))
 {
	 Intent intent = new Intent(MyJobList.this,Mainmenu.class); 
     startActivity(intent);  overridePendingTransition(R.anim.slide_up_in, R.anim.slide_down_out);
    
  return true;
 }
 return super.onKeyDown(keyCode, event);
}

public  void savehistory(String operationname) {
  	 SharedPreferences sharedPreferencesget = getSharedPreferences("historys", Context.MODE_PRIVATE);
  		//getString()�ڶ�������Ϊȱʡֵ�����preference�в����ڸ�key��������ȱʡֵ
  		String lastname=sharedPreferencesget.getString("operationname", "");
  		String lasttime=sharedPreferencesget.getString("operationtime", "");
  	 if (lastname=="") {
  		SharedPreferences sharedPreferences =getSharedPreferences("historys", Context.MODE_PRIVATE);
  	Editor editor = sharedPreferences.edit();//��ȡ�༭��
  	editor.putString("operationname", operationname+"@");
  	editor.putString("operationtime", Applicationtool.sysytemtime().toString()+"@");
  	editor.commit();//�ύ�޸�
  	}
  	 else {
  		 SharedPreferences sharedPreferences =getSharedPreferences("historys", Context.MODE_PRIVATE);
  			Editor editor = sharedPreferences.edit();//��ȡ�༭��
  			editor.putString("operationname",lastname+operationname+"@");
  			editor.putString("operationtime", lasttime+Applicationtool.sysytemtime().toString()+"@");
  			editor.commit();//�ύ�޸�
  	}
  	 
  	
  }
public void isConnectInternet() {
	ConnectivityManager con = (ConnectivityManager) getSystemService(Activity.CONNECTIVITY_SERVICE);
	boolean wifi = con.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
			.isConnectedOrConnecting();
	boolean internet = con.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
			.isConnectedOrConnecting();
	if (wifi || internet) {
	isConnect=true;
		
	} else {isConnect=false;
	// �½���ʾ����ʾ��Ϣ
		new AlertDialog.Builder(this)
				.setTitle("������ʾ")
				.setMessage("δ��⵽���磬�������磡")
				.setPositiveButton("ȷ��",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
							return;
							}
						})

				.show();

	}
}
}