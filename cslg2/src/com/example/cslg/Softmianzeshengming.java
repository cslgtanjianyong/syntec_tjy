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
	private int visibleLastIndex = 0; // ���Ŀ���������
	private int visibleItemCount; // ��ǰ���ڿɼ�������
	
	private PaginationAdapter adapter;
private Button	btn_backsoftmianzeshengming;
	
	private Handler handler = new Handler();


public void init() 
{//��ʼ���ؼ�
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
		//��ʼ���ؼ�
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

	


}

/**
 * 
 * ��ʼ��ListView��������
 */
private void initializeAdapter() {

	List<Newssoftbook> news = new ArrayList<Newssoftbook>();
	
Newssoftbook items4 = new Newssoftbook();

items4.setTitle("�������������� " );

items4.setContent("�����������ϸ�Ĳ��ԣ������ܱ�֤�����е���Ӳ��ϵͳ��ȫ���ݣ����ܱ�֤�������ȫû�д���������ֲ����ݼ���������������û��ɲ�����֧�ֵ绰��������汾��˾����ü���֧�֡�����޷�������������⣬�û�����ɾ���������\nʹ�ñ������Ʒ�������û����ге��������÷�����������Χ�ڣ�����ʹ�û���ʹ�ñ�������������𺦼����գ�������������ֱ�ӻ��ӵĸ����𺦡���ҵӮ����ɥʧ��ó���жϡ���ҵ��Ϣ�Ķ�ʧ���κ�����������ʧ������˾���е��κ����Ρ�\n���������ϵͳ������������ϡ���������ϻ򲡶�����Ϣ�𻵻�ʧ�������ϵͳ����������κβ��ɿ���ԭ���������ʧ������˾���е��κ����Ρ�\n�û�Υ����Э��涨���Ա���˾��˾����𺦵ġ�����˾��Ȩ��ȡ�������������ж�ʹ����ɡ�ֹͣ�ṩ��������ʹ�á�����׷���ȴ�ʩ��\n���ɼ�������\n��Э�������л����񹲺͹����ɡ� \n��Э������Ļ��뱾Э���йص��κ����飬����Ӧ�Ѻ�Э�̽����Э�̲��ɵģ��κ�һ�����ɽ��й������ύ�������ٲ�ίԱ�Ტ�������ʱ��Ч���ٲù����ٲã��ٲòþ����վֵģ��Ը�������Լ������");

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

		// ���ű���

		TextView tvTitle = (TextView) view.findViewById(R.id.newstitle);
		tvTitle.setTextSize(20);
		tvTitle.setText("\t\t\t\t\t\t"+newsItems.get(position).getTitle());

		// ��������

		TextView tvContent = (TextView) view.findViewById(R.id.newscontent);
		tvContent.setTextSize(15);
		tvContent.setText(newsItems.get(position).getContent());

		return view;

	}

	/**
	 * 
	 * ��������б���
	 * 
	 * @param newsitem
	 */

	public void addNewsItem(Newssoftbook newsitem) {

		newsItems.add(newsitem);

	}

}




@Override
public boolean onKeyDown(int keyCode, KeyEvent event)//��Ҫ�Ƕ���������ĸ�д
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