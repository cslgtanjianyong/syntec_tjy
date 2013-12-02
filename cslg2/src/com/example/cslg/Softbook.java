package com.example.cslg;



import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import com.example.cslg.tools.AppManager;
import com.example.cslg.tools.Newssoftbook;
import com.example.cslg.R;
import com.example.source.Page;

import android.os.Bundle;
import android.os.Handler;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;
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
@SuppressLint("ResourceAsColor")
public class Softbook extends Activity implements
OnScrollListener {
	private ListView listView;
	private int visibleLastIndex = 0; // ���Ŀ���������
	private int visibleItemCount; // ��ǰ���ڿɼ�������
	
	private PaginationAdapter adapter;

	
	private Handler handler = new Handler();


public void init() 
{//��ʼ���ؼ�
	listView = (ListView) findViewById(R.id.listViewsoft);

	initializeAdapter();

	listView.setAdapter(adapter);

	listView.setOnScrollListener(this);
}	

	
protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.softbook);
		AppManager.getAppManager().addActivity(this);
		//��ʼ���ؼ�
		init();
	
		
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

	

	Newssoftbook items = new Newssoftbook();

		items.setTitle("���˵��" );

		items.setContent("��������ݺ���ҩ���޹�˾�ƶ�Ӧ��ϵͳ��ʹ����Android2.3�����ϵĹ̼���������������ֻ�ϵͳ��ʹ�ñ���������ڳ��ֵ��κ����⣬����˾���е����Ρ�����������غͰ�װ��ȫ��ѣ���ʹ�õĹ����в����������������ã�����Ӫ����ȡ��");
		news.add(items);

		Newssoftbook items2 = new Newssoftbook();

		items2.setTitle("Ȩ������ " );

		items2.setContent("�������һ��֪ʶ��Ȩ���Լ��������ص�������Ϣ���ݣ������������ڣ����ֱ���������ϡ�ͼ�ꡢͼ�Ρ�ͼ��ͼ��ɫ�ʡ�������ơ������ܡ��й����ݡ����ӳ���ӡˢ���ϻ�����ĵ��Ⱦ�Ϊ����˾���У�������Ȩ���͹�������Ȩ��Լ�Լ�����֪ʶ��Ȩ���ɷ���ı�����");
		
		news.add(items2);
		
		Newssoftbook items3 = new Newssoftbook();

		items3.setTitle("Ȩ������ " );

		items3.setContent("��ֹ���򹤳̡�������룺���øĶ������ڳ����ļ��ڲ����κ���Դ�������ɡ��������Ĺ涨����������⣬�û��������ش�Э�����ơ�\n����ָ�:�������Ʒ����Ϊһ����һ��Ʒ�����������ʹ��, �û����ý��������ַֿ������κ�Ŀ�ġ�\n������Ȩ: ���������ҵ�Ե����ۡ����ơ��ַ���������������������ۡ�Ԥװ������ȣ������ñ���˾��������Ȩ����ɡ�\n����Ȩ������Э��δ��ʾ��Ȩ������Ȩ���Թ鱾��˾���У� �û�ʹ������Ȩ��ʱ�����ñ���˾������ͬ�⡣\n�û�ʹ����֪\n������ṩ�����ϴ������ؿ���ͨ����ҳ��������������Ŀͻ���Ӧ�ó���ȹ��ܡ�\n������ɱ���˾��ȫ�����ṩ��Ʒ֧�֡�\n������޸ĺ�����������˾����Ϊ�û��ṩ��������޸ġ������汾��Ȩ����\n������������κ�ּ���ƻ��û���������ݺͻ�ȡ�û���˽��Ϣ�Ķ�����룬�������κθ��١������û�������Ĺ��ܴ��룬�������û����ϡ����µ���Ϊ�������ռ��û�ʹ������������ĵ��ȸ�����Ϣ������й©�û���˽��\n�û�Ӧ�����ط��ɼ���Э���ǰ����ʹ�ñ�������û���Ȩʵʩ������������������Ϊ��\n����ɾ�����߸ı䱾����ϵ�����Ȩ�����������Ϣ��\n���ù���ܿ������ƻ�����Ȩ��Ϊ�������������Ȩ����ȡ�ļ�����ʩ��\n�û��������ñ�����󵼡���ƭ����;\n�ƻ������ϵͳ����վ���������У����⴫��������������ƻ��Գ���\n������ƽ̨��Ҫ�����û������Լ����棬��������������Ϣ���������û��ϴ����ݻ����������⣬��ֽ�����ָ�룬����ͼ��ȸ����ز��漰����Ȩ���������ϴ�������Ϊ�Ѿ��ڿͻ���Լ��Э�����վԼ��Э������ص��������粻����Э������ܽ���ע�ᣬ�ڴˡ�ħ�����桱���걾����ƽֻ̨�ṩ�Ϸ��û��������������ԷǷ��ύ�����������Ρ�\n���ڴӷǱ���˾ָ��վ�����صı������Ʒ�Լ��ӷǱ���˾���еĽ����ϻ�õı������Ʒ������˾�޷���֤������Ƿ��Ⱦ������������Ƿ�������αװ��������ľ�������ߺڿ������ʹ�ô�������������ܵ��²���Ԥ��ķ��գ������û���Ҫ�������ء���װ��ʹ�ã�����˾���е��κ��ɴ˲�����һ�з������Ρ�\n��˽Ȩ������Ϊ�˸��õظĽ�����ͷ������û���װ��ж�ر�����Լ�������ʱ����������򱾹�˾��ȫ���ķ���������������Ϊ�ķ��������屨�淽��Ϊ���ʷ�������һ��ҳ�棬���������ݸ�ҳ��ı����ʴ���ͳ��������Ϊ�ķ������������û������д����޷�ʶ���������������Լ��޷������Ӧ�����ʱ����������򱾹�˾��ȫ���ķ������ش�������Լ����������ݡ�����˾���Ὣ���������û��ĸ��������Ϣ�������");
		
		news.add(items3);
	


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

	public void addNewsItem(Newssoftbook newsitem) {

		newsItems.add(newsitem);

	}

}

}