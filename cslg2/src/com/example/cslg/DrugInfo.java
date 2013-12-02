package com.example.cslg;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.example.cslg.tools.News;
import com.example.Thread.MyAsyncGetKucuninfo;
import com.example.Thread.MyAsyncGetMedType;
import com.example.Thread.MyAsyncGetPage;
import com.example.Thread.MyAsyncGetProduct;
import com.example.cslg.tools.AppManager;
import com.example.cslg.tools.JsonTolist;
import com.example.cslg.tools.YouhuiNews;
import com.example.cslg.tools.tools;
import com.example.source.DiscountInfo;
import com.example.source.KuCunInfo;
import com.example.source.MedKind;
import com.example.source.Page;
import com.example.source.Product;
import com.stay.pul.lib.PullToRefreshBase.OnRefreshListener;
import com.stay.pul.lib.PullToRefreshGridView;

public class DrugInfo extends Activity implements
OnScrollListener{
	private List<News> news = new ArrayList<News>();
	private ListView listView;
	private int visibleLastIndex = 0; // ���Ŀ���������
	private int visibleItemCount; // ��ǰ���ڿɼ�������
	private int datasize = 38; // ģ�����ݼ�������
	private PaginationAdapter adapter2;
	private View loadMoreView;
	private Button loadMoreButton;
	private Handler handler = new Handler();
//////////////////
	public static KuCunInfo kuCunInfo ;
	public static String proidString;
	public LinkedList<String> mListItems;
	public PullToRefreshGridView mPullRefreshGridView;
	private GridView mGridView;
	private DrugInfo drugInfo;
	public ArrayAdapter<String> mAdapter;
private Button btndrugsearch;
private Button btnbacvkmain;
public static List<Object> _productslist;
private String[] binddataStrings;
	private EditText editdrugname;
	public static List<Object> _Medkindlist=new ArrayList<Object>();
	private  String[] n;
	private TextView view ;
   public static String[] mStrings={" "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "};
	private Spinner spinnertype;
	private ArrayAdapter<String> adapter;
	private String drug;
	public static String drugnum;
	private String drugname;
	private String drugtype;
	private String drugcreatetime;
	private String drugintprice;
	private String drugoutprice;
	private String drugyouxiaoqi;
	private String drugbrizhu;
	private String drugdrugnameenglish;
	private String drugdrugnamehuaxue;
	private String  drugguige;
	private String drugDanweiString;
	public String ID;
	public String price;
	private Spinner spinnerdrugtype;
	public static int kindid;
	public static String proName;
	public static List<Page> pages=new ArrayList<Page>();
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.druginfoform);
		AppManager.getAppManager().addActivity(this);
	
		
		 Intent intentw = new Intent(DrugInfo.this,LoadingActivity.class); 
         startActivity(intentw); 
		//if ( new LoadingActivity().isConnectInternet()) {
			      
		MyAsyncGetMedType mAsyncTask = new MyAsyncGetMedType(mGridView,DrugInfo.this);  
		mAsyncTask.execute();
	//	}
//		else {
//			new AlertDialog.Builder(this)
//			.setTitle("������ʾ")
//			.setMessage("δ��⵽���磬�������磡")
//			.setPositiveButton("ȷ��",
//					new DialogInterface.OnClickListener() {
//						public void onClick(DialogInterface dialog,
//								int which) {
//						return;
//						}
//					})
//
//			.show();
//			
//		}
 

		
		drugInfo=this;

		
		init();
		mGridView = mPullRefreshGridView.getRefreshableView();
		// Set a listener to be invoked when the list should be refreshed.
		mPullRefreshGridView.setOnRefreshListener(new OnRefreshListener() {
			@Override
			public void onRefresh() {
				// Do work to refresh the list here.
			//	new GetDataTask().execute();
			
			}
		});
		
		
		//������ҳ��
		btnbacvkmain.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Intent intent = new Intent();
				intent.setClass(DrugInfo.this,Mainmenu.class);
				startActivity(intent);
				DrugInfo.this.finish();
				   overridePendingTransition(R.anim.slide_up_in, R.anim.slide_down_out);
			}
		});
		
		//����ҩƷ
		btndrugsearch.setOnClickListener(new OnClickListener(){
				public void onClick(View v) {
				// TODO Auto-generated method stub
					//�����ȴ�����
					 Intent intentw = new Intent(DrugInfo.this,LoadingActivity.class); 
			         startActivity(intentw); 
	//���listview
					cleanlist();
					listView.removeFooterView(loadMoreView);

				for(int i=0;i<18;i++) 
				{   
				mStrings[i]=" ";
			}
				//�Ƿ��ǵ�һҳ
				if(pages!=null)
				{pages.clear();
				proName=null;
				kindid=0;
				}
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			for (Object medObject : _Medkindlist) {
			
				if(((MedKind)medObject).getKindName().equals(spinnerdrugtype.getSelectedItem().toString())) 
				{
					
					kindid=((MedKind)medObject).getId();	
					
				}
			}
			try{
			proName=editdrugname.getText().toString().trim();
			
			MyAsyncGetProduct myAsyncGetProduct=new MyAsyncGetProduct(mGridView,editdrugname.getText().toString().trim(), kindid,mAdapter,mListItems,drugInfo);
			myAsyncGetProduct.execute();

			
			}
			catch(Exception ex){System.out.println("������Ϣ"+ex.getMessage());
			}
			}
				
			
				
				
				
				
			}	
	
		
			
		);
	
		///ҩƷ��Ϣ�б�
	listView.setOnItemClickListener(new OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,long arg3) {
			 Intent intentw = new Intent(DrugInfo.this,Zhedangform.class); 
	         startActivity(intentw); 
			String[] a=news.get(arg2).getContent().split("��");
			if(a[1]==null||a[1]=="") {return;}
			for (Object p : _productslist) {
				if(((Product)p).getProId().toString().equals(a[1]))
				{
					String dateStr = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(((Product)p).getProCreateTime());
					drugcreatetime=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(((Product)p).getProCreateTime());
					drugintprice=String.valueOf(((Product)p).getProListPrice());
					drugoutprice=String.valueOf(((Product)p).getProPrice());
					drugyouxiaoqi=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(((Product)p).getUsefulllife());
					drugbrizhu=((Product)p).getProRemark();
					drugname=((Product)p).getProName();
					ID=String.valueOf(((Product)p).getId());
				
					drugdrugnameenglish=((Product)p).getEnglishName();
					drugdrugnamehuaxue=((Product)p).getChemName();
					drugguige=((Product)p).getSpec();
					drugDanweiString=((Product)p).getproUnit();
				}
				}
			
			drug=a[1];
			MyAsyncGetKucuninfo myAsyncGetKucuninfo=new MyAsyncGetKucuninfo(ID,DrugInfo.this);
			myAsyncGetKucuninfo.execute();

			
			
			
		}});
		
///////////////////////////////////////����ӵ�listview
			loadMoreView = getLayoutInflater().inflate(R.layout.loadmore, null);

			loadMoreButton = (Button) loadMoreView
					.findViewById(R.id.loadMoreButton);

			loadMoreButton.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					//new GetDataTask().execute();
					loadMoreButton.setText("���ڼ�����..."); // ���ð�ť����
							try{
							MyAsyncGetPage myAsyncGetPage =new MyAsyncGetPage(DrugInfo.this);
							myAsyncGetPage.execute();
							}
							catch(Exception ex){System.out.println(ex.getMessage());}


				}

			});

		

			//listView.addFooterView(loadMoreView); // �����б�ײ���ͼ

			initializeAdapter();
			
			listView.setOnScrollListener(this);
	}
	private void init() {
		listView = (ListView) findViewById(R.id.listnews);
		editdrugname=(EditText)findViewById(R.id.editdrugname);
		btndrugsearch=(Button)findViewById(R.id.btndrugsearch);
		spinnerdrugtype=(Spinner)findViewById(R.id.spinnertype);
		btnbacvkmain=(Button)findViewById(R.id.btnbackmain);
//�������ظ���;
		mPullRefreshGridView = (PullToRefreshGridView) findViewById(R.id.pull_refresh_grid);
	}
	
	private void GetPage() 
	{
		Page page=new Page();
		try 
		{
			if(pages!=null){
				System.out.println(pages.get(pages.size()-1).getPageCount());
				System.out.println("��ǰҳ"+pages.get(pages.size()-1).getPageNow());
				if(pages.get(pages.size()-1).getPageCount()>pages.get(pages.size()-1).getPageNow())
				{
					List<NameValuePair> parmsList=new ArrayList<NameValuePair>();
					parmsList.add(new BasicNameValuePair("pageNo", String.valueOf(pages.get(pages.size()-1).getPageNow()+1)));
					parmsList.add(new BasicNameValuePair("proName",proName));
					parmsList.add(new BasicNameValuePair("kindId",String.valueOf(kindid)));
					HttpResponse httpResponse=tools.sendLoginInfo(parmsList,tools.ProUrl);
					JSONObject jsonObject =tools.ReceiveJSONObject(httpResponse);
					Page pageR=JsonTolist.jsonToProList(jsonObject);
					page=pageR;
					pages.add(page);
					System.out.println("��ǰҳΪ��"+pages.get(1).getPageNow());
				}
				else
				{
					System.out.println("��Ϣ�Ѿ�ȡ��");
				}
					}}
		catch(Exception ex) {System.out.println(ex.getMessage());}
		
	}
	public static void binddataDrug(String[] mStrings,DrugInfo d) {
		d.mListItems = new LinkedList<String>();
		d.mListItems.addAll(Arrays.asList(mStrings));
        d.mAdapter = new ArrayAdapter<String>(d, android.R.layout.simple_list_item_1, d.mListItems);
//		mGridView.setAdapter(mAdapter);
		}

	private void userdialog() {
		//View userview=this.getLayoutInflater().inflate(R.layout.exit_dialog, null);
		View userview=new View(this);
		AlertDialog.Builder builder=new Builder(this);
		builder.setTitle(drug);
	
		builder.setView(userview);
		//��Ӱ�ť
		builder.setPositiveButton("�ر�", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				dialog.dismiss();
			}
		});
		builder.create().show();
		
		
	}

	public  void spinnerDroplisttype(String[] dl) {
		//////spinner���ҩƷ����
	//	view = (TextView) findViewById(R.id.spinnerText);
		spinnertype = (Spinner) findViewById(R.id.spinnertype);

		//����ѡ������ArrayAdapter��������
		adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,dl);
		
		//���������б�ķ��
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		//��adapter ��ӵ�spinner��
		spinnertype.setAdapter(adapter);
		
		//����¼�Spinner�¼�����  
		spinnertype.setOnItemSelectedListener(new SpinnerSelectedListener());
		
		//����Ĭ��ֵ
		spinnertype.setVisibility(View.VISIBLE);
		spinnertype.setSelection(1);
		AppManager.getAppManager().finishActivity(LoadingActivity.class);
	}
	//ʹ��������ʽ����
public class SpinnerSelectedListener implements OnItemSelectedListener{
public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
						long arg3) {
			
				}
				public void onNothingSelected(AdapterView<?> arg0) {
				}
			}




/////////������ʾ����

	private class GetDataTask extends AsyncTask<Void, Void, String[]> {

		@Override
		protected String[] doInBackground(Void... params) {
			// Simulates a background job.
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
			}
			return mStrings;
		}

		@Override
		protected void onPostExecute(String[] result) {
			//GetPage();
			MyAsyncGetPage myAsyncGetPage =new MyAsyncGetPage(DrugInfo.this);
			myAsyncGetPage.execute();
		
		}
	
	}
	public void  getDrugtype()  {
		//��ȡҩƷ����
		
		final List<NameValuePair> parmsList=new ArrayList<NameValuePair>();			
		
		new Thread(new Runnable(){
			public void run(){
			HttpResponse httpResponse=tools.sendLoginInfo(parmsList,tools.MedTypeSearchUrl);
		
			JSONObject jsonObject =tools.ReceiveJSONObject(httpResponse);
			//��ȡjson�е�flagֵ���ҽ����ж�
		List<Object> medKind=JsonTolist.jsonToAllKindMedInfo(jsonObject);
		_Medkindlist=medKind;
		System.out.println(_Medkindlist.size());
			}}).start();
		bangdingdrug(_Medkindlist,DrugInfo.this);

		
	}
	public static void bangdingdrug(List<Object> medKind,DrugInfo d){
		
	    String dtype[]=new String[medKind.size()];
	   
		for(int i=0;i<medKind.size();i++)
		{
		dtype[i]=((MedKind)medKind.get(i)).getKindName();
	   
		}
		//���ҩƷ���ൽ�����б���
		d.spinnerDroplisttype(dtype);
		} 

///////////		//����ӵ¶�listview��ʾ//////////////////////////////////////
		
		@Override
		public void onScrollStateChanged(AbsListView view, int scrollState) {

			int itemsLastIndex = adapter2.getCount() - 1; // ���ݼ����һ�������

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
			try {
					if (pages.get(pages.size()-1).getPageCount()==pages.get(pages.size()-1).getPageNow()) {

				listView.removeFooterView(loadMoreView);

				//Toast.makeText(this, "����ȫ��������!", Toast.LENGTH_SHORT).show();

			}

			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}
		
		}

		/**
		 * 
		 * ��ʼ��ListView��������
		 */
		private void initializeAdapter() {

			adapter2 = new PaginationAdapter(news);

		}

		/**
		 * 
		 * ���ظ�������
		 */

		public static void loadMoreData(DrugInfo d) {
			
			for (int i=0; i<pages.get(pages.size()-1).getMyProList().size(); i++) {

				News item = new News();

				item.setTitle("ҩƷ���ƣ�"+((Product)pages.get(pages.size()-1).getMyProList().get(i)).getProName());

				item.setContent("ҩƷ��ţ�"+((Product)pages.get(pages.size()-1).getMyProList().get(i)).getProId());

				d.adapter2.addNewsItem(item);
				d.adapter2.notifyDataSetChanged();

				d.loadMoreButton.setText("�鿴����..."); // �ָ���ť����
			
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

				// ���ű���

				TextView tvTitle = (TextView) view.findViewById(R.id.newstitle);
				
				tvTitle.setText(newsItems.get(position).getTitle());

				// ��������

				TextView tvContent = (TextView) view.findViewById(R.id.newscontent);
				tvContent.setTextColor(0x7f05000f);
				tvContent.setText(newsItems.get(position).getContent());

				return view;

			}

			/**
			 * 
			 * ��������б���
			 * 
			 * @param newsitem
			 */

			public void addNewsItem(News newsitem) {

				newsItems.add(newsitem);

			}

		}
		//���β�ѯ����
		public static void loadfirstData(DrugInfo d) {
			if (d.mListItems==null) {
				return;
			}
for (int i = 0; i <d.mListItems.size(); i++) {
	String dString=d.mListItems.get(i);
	if (d.mListItems.get(i)==" "||d.mListItems.get(i)=="  ") {
		d.mListItems.remove(i);
	}
	
}
		
				
				for (int i =0; i <d.mListItems.size()/3; i++) {
					if (d.mListItems.get(3*i)==" "||d.mListItems.get(3*i)=="  ") {
						continue;
					}
					if (d.mListItems.get(3*i+1)==" "||d.mListItems.get(3*i+1)=="  ") {
						continue;
					}
					News item = new News();
					item.setContent("ҩƷ��ţ�"+d.mListItems.get(3*i));
					item.setTitle("ҩƷ���ƣ�"+d.mListItems.get(3*i+1));
					d.adapter2.addNewsItem(item);
				}
//				System.out.println(pages.get(pages.size()-1).getPageCount());
				if (pages.get(pages.size()-1).getPageCount()>1) 
				{

				d.listView.addFooterView(d.loadMoreView);
					}
				else {
					
			
					try
					{
					d.listView.removeFooterView(d.loadMoreView);
					}
					catch(Exception ex){}
				
				}
				
				System.out.println("�Ѿ����");
				d.listView.setAdapter(d.adapter2);
				d.adapter2.notifyDataSetChanged();
				AppManager.getAppManager().finishActivity(LoadingActivity.class);
	
		}
		public void cleanlist(){
			int size=news.size();
			if(size>0){
				System.out.println(size);
				news.removeAll(news);
				adapter2.notifyDataSetChanged();
				listView.setAdapter(adapter2);
			}
		}
		@Override
		public boolean onKeyDown(int keyCode, KeyEvent event)//��Ҫ�Ƕ���������ĸ�д
		{
		 // TODO Auto-generated method stub

		 if((keyCode == KeyEvent.KEYCODE_BACK)&&(event.getAction() == KeyEvent.ACTION_DOWN))
		 {
			 Intent intent = new Intent(DrugInfo.this,Mainmenu.class); 
		     startActivity(intent); 
		     DrugInfo.this.finish();
		     overridePendingTransition(R.anim.slide_up_in, R.anim.slide_down_out);
		  return true;
		 }
		 return super.onKeyDown(keyCode, event);
		}
public static void intentchange(DrugInfo d)
{
	Intent intent = new Intent();
	intent.setClass(d,Exit.class);
	intent.putExtra("drugid", d.drug); 
	intent.putExtra("drugnum", kuCunInfo.getKucunAcount()); 
	intent.putExtra("drugcreatetime", d.drugcreatetime); 
	intent.putExtra("drugintprice", d.drugintprice); 
	intent.putExtra("drugoutprice", d.drugoutprice); 
	intent.putExtra("drugyouxiaoqi", d.drugyouxiaoqi); 
	intent.putExtra("drugbrizhu", d.drugbrizhu); 
	intent.putExtra("drugname", d.drugname); 
	intent.putExtra("ID", d.ID);
	intent.putExtra("drugdrugnameenglish", d.drugdrugnameenglish); 
	intent.putExtra("drugdrugnamehuaxue", d.drugdrugnamehuaxue); 
	intent.putExtra("drugguige", d.drugguige);
	intent.putExtra("drugDanweiString", d.drugDanweiString);	
	
	d.startActivity(intent);
}

		}
