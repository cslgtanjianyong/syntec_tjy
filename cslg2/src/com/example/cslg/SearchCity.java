package com.example.cslg;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cslg.Softbook.PaginationAdapter;
import com.example.cslg.tools.AppManager;
import com.example.cslg.tools.CodequerenList;
import com.example.cslg.tools.NewsCitys;
import com.example.cslg.tools.Newscitylistview;
import com.example.cslg.tools.Weatherlist;
import com.example.source.CompanyMessage;
public class SearchCity extends Activity implements
OnScrollListener{
	private EditText editcityname;
	private Button btnscity;
	private ListView listView;
	private int visibleLastIndex = 0; // ���Ŀ���������
	private int visibleItemCount; // ��ǰ���ڿɼ�������
	private	List<NewsCitys> news = new ArrayList<NewsCitys>();
	private	List<Newscitylistview> news2 = new ArrayList<Newscitylistview>();
	private PaginationAdapter adapter;
private String cityitemname="cityname";
	
	private Handler handler = new Handler();
public void init() 
{//��ʼ���ؼ�
listView = (ListView) findViewById(R.id.listViewscity);

btnscity=(Button)findViewById(R.id.btnscity);
editcityname=(EditText)findViewById(R.id.editcityname);
inputcity();//��ӳ�������	
//initializeAdapter();//��ʼ��listview����

	
	
}
	
protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.searchcity);
		AppManager.getAppManager().addActivity(this);
		//��ʼ���ؼ�
		init();

		
		//��������
		btnscity.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				 cancerSerachcity();
				
			//	initializeAdapter();//������������
				
			}
		});
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				
			 cityitemname=news2.get(arg2).getTitle();
				
			 Intent intent = new Intent(SearchCity.this,Mainmenu.class); 
			 intent.putExtra("cityitemname", cityitemname); 
			 startActivity(intent); 
			 SharedPreferences sharedPreferences2 = getSharedPreferences("isonelogin", Context.MODE_PRIVATE);
				Editor editor = sharedPreferences2.edit();//��ȡ�༭��
				editor.putString("islogin", "isinstall");
				editor.commit();//�ύ�޸�
		          SearchCity.this.finish();
				
				
			}
		});
		editcityname.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				cleanlist();
				news2.clear();
				for (NewsCitys it : news) {
					String strings=editcityname.getText().toString();
					if ((editcityname.getText().toString()).equals("")||(editcityname.getText().toString()).equals(null)) {
						cleanlist();
						news2.clear();
						   break;
					}
					Newscitylistview nlist=new Newscitylistview();
					String str=it.getTitle().toString();
					if(str.indexOf(editcityname.getText().toString())!=-1){
				
				   nlist.setTitle(it.getTitle());
				   nlist.setContent(" ");
				   news2.add(nlist);
				  }
					else{
				   System.out.println("������");
				  }
				
				
				
				
				} 
				initializeAdapter(news2);
			}
			
		

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				
			}
		});

}






@Override
public boolean onKeyDown(int keyCode, KeyEvent event)//��Ҫ�Ƕ���������ĸ�д
{
 // TODO Auto-generated method stub

 if((keyCode == KeyEvent.KEYCODE_BACK)&&(event.getAction() == KeyEvent.ACTION_DOWN))
 {
	 cancerSerachcity();
	 
	
  return true;
 }
 return super.onKeyDown(keyCode, event);
}

private void cancerSerachcity() {
	SharedPreferences sharedPreferences = getSharedPreferences("isonelogin", Context.MODE_PRIVATE);
		//getString()�ڶ�������Ϊȱʡֵ�����preference�в����ڸ�key��������ȱʡֵ
		String iSonelogin=sharedPreferences.getString("islogin", "");
		if (iSonelogin=="") {}
		else {
			 Intent intent = new Intent(SearchCity.this,Mainmenu.class); 
	 intent.putExtra("cityitemname", cityitemname); 
	 startActivity(intent); 
          SearchCity.this.finish();
		}
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
private void initializeAdapter(List<Newscitylistview> newsc) {

	
////////////////////


//////////////////////
	adapter = new PaginationAdapter(newsc);
	listView.setAdapter(adapter);

	listView.setOnScrollListener(this);

}

private void inputcity() {
	
	NewsCitys item1 = new NewsCitys();item1.setTitle("������" );item1.setContent(" ");news.add(item1);
	NewsCitys item2 = new NewsCitys();item2.setTitle("�������" );item2.setContent(" ");news.add(item2);
	NewsCitys item3 = new NewsCitys();item3.setTitle("ĵ����" );item3.setContent(" ");news.add(item3);
	NewsCitys item4 = new NewsCitys();item4.setTitle("����" );item4.setContent(" ");news.add(item4);
	NewsCitys item5 = new NewsCitys();item5.setTitle("����" );item5.setContent(" ");news.add(item5);
	NewsCitys item6 = new NewsCitys();item6.setTitle("˫Ѽɽ" );item6.setContent(" ");news.add(item6);
	NewsCitys item7 = new NewsCitys();item7.setTitle("�׸�" );item7.setContent(" ");news.add(item7);
	NewsCitys item8 = new NewsCitys();item8.setTitle("����" );item8.setContent(" ");news.add(item8);
	NewsCitys item9 = new NewsCitys();item9.setTitle("��ľ˹" );item9.setContent(" ");news.add(item9);
	NewsCitys item10 = new NewsCitys();item10.setTitle("��̨��" );item10.setContent(" ");news.add(item10);
	NewsCitys item11 = new NewsCitys();item11.setTitle("�ں�" );item11.setContent(" ");news.add(item11);
	NewsCitys item12 = new NewsCitys();item12.setTitle("�绯" );item12.setContent(" ");news.add(item12);
	NewsCitys item13 = new NewsCitys();item13.setTitle("���˰���" );item13.setContent(" ");news.add(item13);
	NewsCitys item14 = new NewsCitys();item14.setTitle("����" );item14.setContent(" ");news.add(item14);
	NewsCitys item15 = new NewsCitys();item15.setTitle("����" );item15.setContent(" ");news.add(item15);
	NewsCitys item16 = new NewsCitys();item16.setTitle("��ɽ" );item16.setContent(" ");news.add(item16);
	NewsCitys item17 = new NewsCitys();item17.setTitle("�׳�" );item17.setContent(" ");news.add(item17);
	NewsCitys item18 = new NewsCitys();item18.setTitle("��ƽ" );item18.setContent(" ");news.add(item18);
	NewsCitys item19 = new NewsCitys();item19.setTitle("��ԭ" );item19.setContent(" ");news.add(item19);
	NewsCitys item20 = new NewsCitys();item20.setTitle("��Դ" );item20.setContent(" ");news.add(item20);
	NewsCitys item21 = new NewsCitys();item21.setTitle("��" );item21.setContent(" ");news.add(item21);
	NewsCitys item22 = new NewsCitys();item22.setTitle("ͨ��" );item22.setContent(" ");news.add(item22);
	NewsCitys item23 = new NewsCitys();item23.setTitle("����" );item23.setContent(" ");news.add(item23);
	NewsCitys item24 = new NewsCitys();item24.setTitle("����" );item24.setContent(" ");news.add(item24);
	NewsCitys item25 = new NewsCitys();item25.setTitle("��«��" );item25.setContent(" ");news.add(item25);
	NewsCitys item26 = new NewsCitys();item26.setTitle("��˳" );item26.setContent(" ");news.add(item26);
	NewsCitys item27 = new NewsCitys();item27.setTitle("��Ϫ" );item27.setContent(" ");news.add(item27);
	NewsCitys item28 = new NewsCitys();item28.setTitle("��˳" );item28.setContent(" ");news.add(item28);
	NewsCitys item29 = new NewsCitys();item29.setTitle("����" );item29.setContent(" ");news.add(item29);
	NewsCitys item30 = new NewsCitys();item30.setTitle("����" );item30.setContent(" ");news.add(item30);
	NewsCitys item31 = new NewsCitys();item31.setTitle("Ӫ��" );item31.setContent(" ");news.add(item31);
	NewsCitys item32 = new NewsCitys();item32.setTitle("����" );item32.setContent(" ");news.add(item32);
	NewsCitys item33 = new NewsCitys();item33.setTitle("����" );item33.setContent(" ");news.add(item33);
	NewsCitys item34 = new NewsCitys();item34.setTitle("����" );item34.setContent(" ");news.add(item34);
	NewsCitys item35 = new NewsCitys();item35.setTitle("����" );item35.setContent(" ");news.add(item35);
	NewsCitys item36 = new NewsCitys();item36.setTitle("��ɽ" );item36.setContent(" ");news.add(item36);
	NewsCitys item37 = new NewsCitys();item37.setTitle("���ͺ���" );item37.setContent(" ");news.add(item37);
	NewsCitys item38 = new NewsCitys();item38.setTitle("���ֺ���" );item38.setContent(" ");news.add(item38);
	NewsCitys item39 = new NewsCitys();item39.setTitle("��ͷ" );item39.setContent(" ");news.add(item39);
	NewsCitys item40 = new NewsCitys();item40.setTitle("���" );item40.setContent(" ");news.add(item40);
	NewsCitys item41 = new NewsCitys();item41.setTitle("������" );item41.setContent(" ");news.add(item41);
	NewsCitys item42 = new NewsCitys();item42.setTitle("�ں�" );item42.setContent(" ");news.add(item42);
	NewsCitys item43 = new NewsCitys();item43.setTitle("������˹" );item43.setContent(" ");news.add(item43);
	NewsCitys item44 = new NewsCitys();item44.setTitle("���ֺ���" );item44.setContent(" ");news.add(item44);
	NewsCitys item45 = new NewsCitys();item45.setTitle("ͨ��" );item45.setContent(" ");news.add(item45);
	NewsCitys item46 = new NewsCitys();item46.setTitle("ʯ��ׯ" );item46.setContent(" ");news.add(item46);
	NewsCitys item47 = new NewsCitys();item47.setTitle("��ɽ" );item47.setContent(" ");news.add(item47);
	NewsCitys items48 = new NewsCitys();items48.setTitle("�żҿ�" );items48.setContent(" ");news.add(items48);
	NewsCitys items49 = new NewsCitys();items49.setTitle("�ȷ�" );items49.setContent(" ");news.add(items49);
	NewsCitys items50 = new NewsCitys();items50.setTitle("��̨" );items50.setContent(" ");news.add(items50);
	NewsCitys items51 = new NewsCitys();items51.setTitle("����" );items51.setContent(" ");news.add(items51);
	NewsCitys items52 = new NewsCitys();items52.setTitle("����" );items52.setContent(" ");news.add(items52);
	NewsCitys items53 = new NewsCitys();items53.setTitle("��ˮ" );items53.setContent(" ");news.add(items53);
	NewsCitys items54 = new NewsCitys();items54.setTitle("�е�" );items54.setContent(" ");news.add(items54);
	NewsCitys items55 = new NewsCitys();items55.setTitle("����" );items55.setContent(" ");news.add(items55);
	NewsCitys items56 = new NewsCitys();items56.setTitle("�ػʵ�" );items56.setContent(" ");news.add(items56);
	NewsCitys items57 = new NewsCitys();items57.setTitle("֣��" );items57.setContent(" ");news.add(items57);
	NewsCitys items58 = new NewsCitys();items58.setTitle("����" );items58.setContent(" ");news.add(items58);
	NewsCitys items59 = new NewsCitys();items59.setTitle("����" );items59.setContent(" ");news.add(items59);
	NewsCitys items60 = new NewsCitys();items60.setTitle("ƽ��ɽ" );items60.setContent(" ");news.add(items60);
	NewsCitys items61 = new NewsCitys();items61.setTitle("����" );items61.setContent(" ");news.add(items61);
	NewsCitys items62 = new NewsCitys();items62.setTitle("�ױ�" );items62.setContent(" ");news.add(items62);
	NewsCitys items63 = new NewsCitys();items63.setTitle("����" );items63.setContent(" ");news.add(items63);
	NewsCitys items64 = new NewsCitys();items64.setTitle("����" );items64.setContent(" ");news.add(items64);
	NewsCitys items65 = new NewsCitys();items65.setTitle("���" );items65.setContent(" ");news.add(items65);
	NewsCitys items66 = new NewsCitys();items66.setTitle("���" );items66.setContent(" ");news.add(items66);
	NewsCitys items67 = new NewsCitys();items67.setTitle("���" );items67.setContent(" ");news.add(items67);
	NewsCitys items68 = new NewsCitys();items68.setTitle("����Ͽ" );items68.setContent(" ");news.add(items68);
	NewsCitys items69 = new NewsCitys();items69.setTitle("����" );items69.setContent(" ");news.add(items69);
	NewsCitys items70 = new NewsCitys();items70.setTitle("����" );items70.setContent(" ");news.add(items70);
	NewsCitys items71 = new NewsCitys();items71.setTitle("����" );items71.setContent(" ");news.add(items71);
	NewsCitys items72 = new NewsCitys();items72.setTitle("�ܿ�" );items72.setContent(" ");news.add(items72);
	NewsCitys items73 = new NewsCitys();items73.setTitle("פ���" );items73.setContent(" ");news.add(items73);
	NewsCitys items74 = new NewsCitys();items74.setTitle("����" );items74.setContent(" ");news.add(items74);
	NewsCitys items75 = new NewsCitys();items75.setTitle("�ൺ" );items75.setContent(" ");news.add(items75);
	NewsCitys items76 = new NewsCitys();items76.setTitle("�Ͳ�" );items76.setContent(" ");news.add(items76);
	NewsCitys items77 = new NewsCitys();items77.setTitle("����" );items77.setContent(" ");news.add(items77);
	NewsCitys items78 = new NewsCitys();items78.setTitle("����" );items78.setContent(" ");news.add(items78);
	NewsCitys items79 = new NewsCitys();items79.setTitle("����" );items79.setContent(" ");news.add(items79);
	NewsCitys items80 = new NewsCitys();items80.setTitle("��̨" );items80.setContent(" ");news.add(items80);
	NewsCitys items81 = new NewsCitys();items81.setTitle("��ׯ" );items81.setContent(" ");news.add(items81);
	NewsCitys items82 = new NewsCitys();items82.setTitle("�ĳ�" );items82.setContent(" ");news.add(items82);
	NewsCitys items83 = new NewsCitys();items83.setTitle("����" );items83.setContent(" ");news.add(items83);
	NewsCitys items84 = new NewsCitys();items84.setTitle("����" );items84.setContent(" ");news.add(items84);
	NewsCitys items85 = new NewsCitys();items85.setTitle("̩��" );items85.setContent(" ");news.add(items85);
	NewsCitys items86 = new NewsCitys();items86.setTitle("����" );items86.setContent(" ");news.add(items86);
	NewsCitys items87 = new NewsCitys();items87.setTitle("��Ӫ" );items87.setContent(" ");news.add(items87);
	NewsCitys items88 = new NewsCitys();items88.setTitle("����" );items88.setContent(" ");news.add(items88);
	NewsCitys items89 = new NewsCitys();items89.setTitle("����" );items89.setContent(" ");news.add(items89);
	NewsCitys items90 = new NewsCitys();items90.setTitle("����" );items90.setContent(" ");news.add(items90);
	NewsCitys items91 = new NewsCitys();items91.setTitle("Ϋ��" );items91.setContent(" ");news.add(items91);
	NewsCitys items92 = new NewsCitys();items92.setTitle("̫ԭ" );items92.setContent(" ");news.add(items92);
	NewsCitys items93 = new NewsCitys();items93.setTitle("��Ȫ" );items93.setContent(" ");news.add(items93);
	NewsCitys items94 = new NewsCitys();items94.setTitle("����" );items94.setContent(" ");news.add(items94);
	NewsCitys items95 = new NewsCitys();items95.setTitle("����" );items95.setContent(" ");news.add(items95);
	/////////////////////////////////////////////
    NewsCitys ite1 = new NewsCitys();ite1.setTitle("�ٷ�" );ite1.setContent(" ");news.add(ite1);
NewsCitys ite2 = new NewsCitys();ite2.setTitle("�˳�" );ite2.setContent(" ");news.add(ite2);
NewsCitys ite3 = new NewsCitys();ite3.setTitle("����" );ite3.setContent(" ");news.add(ite3);
NewsCitys ite4 = new NewsCitys();ite4.setTitle("˷��" );ite4.setContent(" ");news.add(ite4);
NewsCitys ite5 = new NewsCitys();ite5.setTitle("����" );ite5.setContent(" ");news.add(ite5);
NewsCitys ite6 = new NewsCitys();ite6.setTitle("��ͬ" );ite6.setContent(" ");news.add(ite6);
NewsCitys ite7 = new NewsCitys();ite7.setTitle("����" );ite7.setContent(" ");news.add(ite7);
NewsCitys ite8 = new NewsCitys();ite8.setTitle("�Ͼ�" );ite8.setContent(" ");news.add(ite8);
NewsCitys ite9 = new NewsCitys();ite9.setTitle("����" );ite9.setContent(" ");news.add(ite9);
NewsCitys ite10 = new NewsCitys();ite10.setTitle("��ɽ" );ite10.setContent(" ");news.add(ite10);
NewsCitys ite11 = new NewsCitys();ite11.setTitle("��ͨ" );ite11.setContent(" ");news.add(ite11);
NewsCitys ite12 = new NewsCitys();ite12.setTitle("̫��" );ite12.setContent(" ");news.add(ite12);
NewsCitys ite13 = new NewsCitys();ite13.setTitle("����" );ite13.setContent(" ");news.add(ite13);
NewsCitys ite14 = new NewsCitys();ite14.setTitle("����" );ite14.setContent(" ");news.add(ite14);
NewsCitys ite15 = new NewsCitys();ite15.setTitle("����" );ite15.setContent(" ");news.add(ite15);
NewsCitys ite16 = new NewsCitys();ite16.setTitle("��" );ite16.setContent(" ");news.add(ite16);
NewsCitys ite17 = new NewsCitys();ite17.setTitle("����" );ite17.setContent(" ");news.add(ite17);
NewsCitys ite18 = new NewsCitys();ite18.setTitle("����" );ite18.setContent(" ");news.add(ite18);
NewsCitys ite19 = new NewsCitys();ite19.setTitle("�γ�" );ite19.setContent(" ");news.add(ite19);
NewsCitys ite20 = new NewsCitys();ite20.setTitle("̩��" );ite20.setContent(" ");news.add(ite20);
NewsCitys ite21 = new NewsCitys();ite21.setTitle("����" );ite21.setContent(" ");news.add(ite21);
NewsCitys ite22 = new NewsCitys();ite22.setTitle("���Ƹ�" );ite22.setContent(" ");news.add(ite22);
NewsCitys ite23 = new NewsCitys();ite23.setTitle("����" );ite23.setContent(" ");news.add(ite23);
NewsCitys ite24 = new NewsCitys();ite24.setTitle("����" );ite24.setContent(" ");news.add(ite24);
NewsCitys ite25 = new NewsCitys();ite25.setTitle("��Ǩ" );ite25.setContent(" ");news.add(ite25);
NewsCitys ite26 = new NewsCitys();ite26.setTitle("�Ϸ�" );ite26.setContent(" ");news.add(ite26);
NewsCitys ite27 = new NewsCitys();ite27.setTitle("����" );ite27.setContent(" ");news.add(ite27);
NewsCitys ite28 = new NewsCitys();ite28.setTitle("����" );ite28.setContent(" ");news.add(ite28);
NewsCitys ite29 = new NewsCitys();ite29.setTitle("����" );ite29.setContent(" ");news.add(ite29);
NewsCitys ite30 = new NewsCitys();ite30.setTitle("����" );ite30.setContent(" ");news.add(ite30);
NewsCitys ite31 = new NewsCitys();ite31.setTitle("����" );ite31.setContent(" ");news.add(ite31);
NewsCitys ite32 = new NewsCitys();ite32.setTitle("��ɽ" );ite32.setContent(" ");news.add(ite32);
NewsCitys ite33 = new NewsCitys();ite33.setTitle("����" );ite33.setContent(" ");news.add(ite33);
NewsCitys ite34 = new NewsCitys();ite34.setTitle("����" );ite34.setContent(" ");news.add(ite34);
NewsCitys ite35 = new NewsCitys();ite35.setTitle("ͭ��" );ite35.setContent(" ");news.add(ite35);
NewsCitys ite36 = new NewsCitys();ite36.setTitle("����" );ite36.setContent(" ");news.add(ite36);
NewsCitys ite37 = new NewsCitys();ite37.setTitle("�ߺ�" );ite37.setContent(" ");news.add(ite37);
NewsCitys ite38 = new NewsCitys();ite38.setTitle("����" );ite38.setContent(" ");news.add(ite38);
NewsCitys ite39 = new NewsCitys();ite39.setTitle("����" );ite39.setContent(" ");news.add(ite39);
NewsCitys ite40 = new NewsCitys();ite40.setTitle("����" );ite40.setContent(" ");news.add(ite40);
NewsCitys ite41 = new NewsCitys();ite41.setTitle("����" );ite41.setContent(" ");news.add(ite41);
NewsCitys ite42 = new NewsCitys();ite42.setTitle("����" );ite42.setContent(" ");news.add(ite42);
NewsCitys ite43 = new NewsCitys();ite43.setTitle("����" );ite43.setContent(" ");news.add(ite43);
NewsCitys ite44 = new NewsCitys();ite44.setTitle("����" );ite44.setContent(" ");news.add(ite44);
NewsCitys ite45 = new NewsCitys();ite45.setTitle("����" );ite45.setContent(" ");news.add(ite45);
NewsCitys ite46 = new NewsCitys();ite46.setTitle("����" );ite46.setContent(" ");news.add(ite46);
NewsCitys ite47 = new NewsCitys();ite47.setTitle("����" );ite47.setContent(" ");news.add(ite47);
NewsCitys ites48 = new NewsCitys();ites48.setTitle("μ��" );ites48.setContent(" ");news.add(ites48);
NewsCitys ites49 = new NewsCitys();ites49.setTitle("����" );ites49.setContent(" ");news.add(ites49);
NewsCitys ites50 = new NewsCitys();ites50.setTitle("ͭ��" );ites50.setContent(" ");news.add(ites50);
NewsCitys ites51 = new NewsCitys();ites51.setTitle("�Ӱ�" );ites51.setContent(" ");news.add(ites51);
NewsCitys ites52 = new NewsCitys();ites52.setTitle("����" );ites52.setContent(" ");news.add(ites52);
NewsCitys ites53 = new NewsCitys();ites53.setTitle("��ԭ" );ites53.setContent(" ");news.add(ites53);
NewsCitys ites54 = new NewsCitys();ites54.setTitle("����" );ites54.setContent(" ");news.add(ites54);
NewsCitys ites55 = new NewsCitys();ites55.setTitle("ʯ��ɽ" );ites55.setContent(" ");news.add(ites55);
NewsCitys ites56 = new NewsCitys();ites56.setTitle("����" );ites56.setContent(" ");news.add(ites56);
NewsCitys ites57 = new NewsCitys();ites57.setTitle("����" );ites57.setContent(" ");news.add(ites57);
NewsCitys ites58 = new NewsCitys();ites58.setTitle("����" );ites58.setContent(" ");news.add(ites58);
NewsCitys ites59 = new NewsCitys();ites59.setTitle("����" );ites59.setContent(" ");news.add(ites59);
NewsCitys ites60 = new NewsCitys();ites60.setTitle("��Ȫ" );ites60.setContent(" ");news.add(ites60);
NewsCitys ites61 = new NewsCitys();ites61.setTitle("��ˮ" );ites61.setContent(" ");news.add(ites61);
NewsCitys ites62 = new NewsCitys();ites62.setTitle("����" );ites62.setContent(" ");news.add(ites62);
NewsCitys ites63 = new NewsCitys();ites63.setTitle("��Ҵ" );ites63.setContent(" ");news.add(ites63);
NewsCitys ites64 = new NewsCitys();ites64.setTitle("����" );ites64.setContent(" ");news.add(ites64);
NewsCitys ites65 = new NewsCitys();ites65.setTitle("����" );ites65.setContent(" ");news.add(ites65);
NewsCitys ites66 = new NewsCitys();ites66.setTitle("ƽ��" );ites66.setContent(" ");news.add(ites66);
NewsCitys ites67 = new NewsCitys();ites67.setTitle("����" );ites67.setContent(" ");news.add(ites67);
NewsCitys ites68 = new NewsCitys();ites68.setTitle("���" );ites68.setContent(" ");news.add(ites68);
NewsCitys ites69 = new NewsCitys();ites69.setTitle("����" );ites69.setContent(" ");news.add(ites69);
NewsCitys ites70 = new NewsCitys();ites70.setTitle("����" );ites70.setContent(" ");news.add(ites70);
NewsCitys ites71 = new NewsCitys();ites71.setTitle("����" );ites71.setContent(" ");news.add(ites71);
NewsCitys ites72 = new NewsCitys();ites72.setTitle("����" );ites72.setContent(" ");news.add(ites72);
NewsCitys ites73 = new NewsCitys();ites73.setTitle("����" );ites73.setContent(" ");news.add(ites73);
NewsCitys ites74 = new NewsCitys();ites74.setTitle("����" );ites74.setContent(" ");news.add(ites74);
NewsCitys ites75 = new NewsCitys();ites75.setTitle("����" );ites75.setContent(" ");news.add(ites75);
NewsCitys ites76 = new NewsCitys();ites76.setTitle("����" );ites76.setContent(" ");news.add(ites76);
NewsCitys ites77 = new NewsCitys();ites77.setTitle("�人" );ites77.setContent(" ");news.add(ites77);
NewsCitys ites78 = new NewsCitys();ites78.setTitle("�˲�" );ites78.setContent(" ");news.add(ites78);
NewsCitys ites79 = new NewsCitys();ites79.setTitle("�Ƹ�" );ites79.setContent(" ");news.add(ites79);
NewsCitys ites80 = new NewsCitys();ites80.setTitle("��ʩ" );ites80.setContent(" ");news.add(ites80);
NewsCitys ites81 = new NewsCitys();ites81.setTitle("����" );ites81.setContent(" ");news.add(ites81);
NewsCitys ites82 = new NewsCitys();ites82.setTitle("��ũ��" );ites82.setContent(" ");news.add(ites82);
NewsCitys ites83 = new NewsCitys();ites83.setTitle("ʮ��" );ites83.setContent(" ");news.add(ites83);
NewsCitys ites84 = new NewsCitys();ites84.setTitle("����" );ites84.setContent(" ");news.add(ites84);
NewsCitys ites85 = new NewsCitys();ites85.setTitle("�差" );ites85.setContent(" ");news.add(ites85);
NewsCitys ites86 = new NewsCitys();ites86.setTitle("Т��" );ites86.setContent(" ");news.add(ites86);
NewsCitys ites87 = new NewsCitys();ites87.setTitle("����" );ites87.setContent(" ");news.add(ites87);
NewsCitys ites88 = new NewsCitys();ites88.setTitle("��ʯ" );ites88.setContent(" ");news.add(ites88);
NewsCitys ites89 = new NewsCitys();ites89.setTitle("����" );ites89.setContent(" ");news.add(ites89);
NewsCitys ites90 = new NewsCitys();ites90.setTitle("����" );ites90.setContent(" ");news.add(ites90);
NewsCitys ites91 = new NewsCitys();ites91.setTitle("��ɳ" );ites91.setContent(" ");news.add(ites91);
NewsCitys ites92 = new NewsCitys();ites92.setTitle("����" );ites92.setContent(" ");news.add(ites92);
NewsCitys ites93 = new NewsCitys();ites93.setTitle("����" );ites93.setContent(" ");news.add(ites93);
NewsCitys ites94 = new NewsCitys();ites94.setTitle("����" );ites94.setContent(" ");news.add(ites94);
NewsCitys ites95 = new NewsCitys();ites95.setTitle("����" );ites95.setContent(" ");news.add(ites95);
//////////////////////////////////////////////////
NewsCitys items1 = new NewsCitys();items1.setTitle("����" );items1.setContent(" ");news.add(items1);
NewsCitys items2 = new NewsCitys();items2.setTitle("¦��" );items2.setContent(" ");news.add(items2);
NewsCitys items3 = new NewsCitys();items3.setTitle("��̶" );items3.setContent(" ");news.add(items3);
NewsCitys items4 = new NewsCitys();items4.setTitle("����" );items4.setContent(" ");news.add(items4);
NewsCitys items5 = new NewsCitys();items5.setTitle("����" );items5.setContent(" ");news.add(items5);
NewsCitys items6 = new NewsCitys();items6.setTitle("����" );items6.setContent(" ");news.add(items6);
NewsCitys items7 = new NewsCitys();items7.setTitle("����" );items7.setContent(" ");news.add(items7);
NewsCitys items8 = new NewsCitys();items8.setTitle("����" );items8.setContent(" ");news.add(items8);
NewsCitys items9 = new NewsCitys();items9.setTitle("��ɽ" );items9.setContent(" ");news.add(items9);
NewsCitys items10 = new NewsCitys();items10.setTitle("�żҽ�" );items10.setContent(" ");news.add(items10);
NewsCitys items11 = new NewsCitys();items11.setTitle("����" );items11.setContent(" ");news.add(items11);
NewsCitys items12 = new NewsCitys();items12.setTitle("����" );items12.setContent(" ");news.add(items12);
NewsCitys items13 = new NewsCitys();items13.setTitle("��" );items13.setContent(" ");news.add(items13);
NewsCitys items14 = new NewsCitys();items14.setTitle("����" );items14.setContent(" ");news.add(items14);
NewsCitys items15 = new NewsCitys();items15.setTitle("��ˮ" );items15.setContent(" ");news.add(items15);
NewsCitys items16 = new NewsCitys();items16.setTitle("����" );items16.setContent(" ");news.add(items16);
NewsCitys items17 = new NewsCitys();items17.setTitle("����" );items17.setContent(" ");news.add(items17);
NewsCitys items18 = new NewsCitys();items18.setTitle("����" );items18.setContent(" ");news.add(items18);
NewsCitys items19 = new NewsCitys();items19.setTitle("̨��" );items19.setContent(" ");news.add(items19);
NewsCitys items20 = new NewsCitys();items20.setTitle("��ɽ" );items20.setContent(" ");news.add(items20);
NewsCitys items21 = new NewsCitys();items21.setTitle("����" );items21.setContent(" ");news.add(items21);
NewsCitys items22 = new NewsCitys();items22.setTitle("�ϲ�" );items22.setContent(" ");news.add(items22);
NewsCitys items23 = new NewsCitys();items23.setTitle("Ƽ��" );items23.setContent(" ");news.add(items23);
NewsCitys items24 = new NewsCitys();items24.setTitle("�Ž�" );items24.setContent(" ");news.add(items24);
NewsCitys items25 = new NewsCitys();items25.setTitle("����" );items25.setContent(" ");news.add(items25);
NewsCitys items26 = new NewsCitys();items26.setTitle("����" );items26.setContent(" ");news.add(items26);
NewsCitys items27 = new NewsCitys();items27.setTitle("����" );items27.setContent(" ");news.add(items27);
NewsCitys items28 = new NewsCitys();items28.setTitle("ӥ̶" );items28.setContent(" ");news.add(items28);
NewsCitys items29 = new NewsCitys();items29.setTitle("�˴�" );items29.setContent(" ");news.add(items29);
NewsCitys items30 = new NewsCitys();items30.setTitle("����" );items30.setContent(" ");news.add(items30);
NewsCitys items31 = new NewsCitys();items31.setTitle("������" );items31.setContent(" ");news.add(items31);
NewsCitys items32 = new NewsCitys();items32.setTitle("����" );items32.setContent(" ");news.add(items32);
NewsCitys items33 = new NewsCitys();items33.setTitle("����" );items33.setContent(" ");news.add(items33);
NewsCitys items34 = new NewsCitys();items34.setTitle("����" );items34.setContent(" ");news.add(items34);
NewsCitys items35 = new NewsCitys();items35.setTitle("����" );items35.setContent(" ");news.add(items35);
NewsCitys items36 = new NewsCitys();items36.setTitle("��ƽ" );items36.setContent(" ");news.add(items36);
NewsCitys items37 = new NewsCitys();items37.setTitle("����" );items37.setContent(" ");news.add(items37);
NewsCitys items38 = new NewsCitys();items38.setTitle("����" );items38.setContent(" ");news.add(items38);
NewsCitys items39 = new NewsCitys();items39.setTitle("Ȫ��" );items39.setContent(" ");news.add(items39);
NewsCitys items40 = new NewsCitys();items40.setTitle("����" );items40.setContent(" ");news.add(items40);
NewsCitys items41 = new NewsCitys();items41.setTitle("����" );items41.setContent(" ");news.add(items41);
NewsCitys items42 = new NewsCitys();items42.setTitle("����" );items42.setContent(" ");news.add(items42);
NewsCitys items43 = new NewsCitys();items43.setTitle("����" );items43.setContent(" ");news.add(items43);
NewsCitys items44 = new NewsCitys();items44.setTitle("����" );items44.setContent(" ");news.add(items44);
NewsCitys items45 = new NewsCitys();items45.setTitle("��ƽ" );items45.setContent(" ");news.add(items45);
NewsCitys items46 = new NewsCitys();items46.setTitle("����" );items46.setContent(" ");news.add(items46);
NewsCitys items47 = new NewsCitys();items47.setTitle("����" );items47.setContent(" ");news.add(items47);
NewsCitys itemss48 = new NewsCitys();itemss48.setTitle("Ȫ��" );itemss48.setContent(" ");news.add(itemss48);
NewsCitys itemss49 = new NewsCitys();itemss49.setTitle("����" );itemss49.setContent(" ");news.add(itemss49);
NewsCitys itemss50 = new NewsCitys();itemss50.setTitle("����" );itemss50.setContent(" ");news.add(itemss50);
NewsCitys itemss51 = new NewsCitys();itemss51.setTitle("����" );itemss51.setContent(" ");news.add(itemss51);
NewsCitys itemss52 = new NewsCitys();itemss52.setTitle("��˳" );itemss52.setContent(" ");news.add(itemss52);
NewsCitys itemss53 = new NewsCitys();itemss53.setTitle("��ˮ" );itemss53.setContent(" ");news.add(itemss53);
NewsCitys itemss54 = new NewsCitys();itemss54.setTitle("����" );itemss54.setContent(" ");news.add(itemss54);
NewsCitys itemss55 = new NewsCitys();itemss55.setTitle("ͭ��" );itemss55.setContent(" ");news.add(itemss55);
NewsCitys itemss56 = new NewsCitys();itemss56.setTitle("����ˮ" );itemss56.setContent(" ");news.add(itemss56);
NewsCitys itemss57 = new NewsCitys();itemss57.setTitle("�Ͻ�" );itemss57.setContent(" ");news.add(itemss57);
NewsCitys itemss58 = new NewsCitys();itemss58.setTitle("����" );itemss58.setContent(" ");news.add(itemss58);
NewsCitys itemss59 = new NewsCitys();itemss59.setTitle("����" );itemss59.setContent(" ");news.add(itemss59);
NewsCitys itemss60 = new NewsCitys();itemss60.setTitle("�ɶ�" );itemss60.setContent(" ");news.add(itemss60);
NewsCitys itemss61 = new NewsCitys();itemss61.setTitle("����" );itemss61.setContent(" ");news.add(itemss61);
NewsCitys itemss62 = new NewsCitys();itemss62.setTitle("�ڽ�" );itemss62.setContent(" ");news.add(itemss62);
NewsCitys itemss63 = new NewsCitys();itemss63.setTitle("��ɽ" );itemss63.setContent(" ");news.add(itemss63);
NewsCitys itemss64 = new NewsCitys();itemss64.setTitle("����" );itemss64.setContent(" ");news.add(itemss64);
NewsCitys itemss65 = new NewsCitys();itemss65.setTitle("����" );itemss65.setContent(" ");news.add(itemss65);
NewsCitys itemss66 = new NewsCitys();itemss66.setTitle("��Ԫ" );itemss66.setContent(" ");news.add(itemss66);
NewsCitys itemss67 = new NewsCitys();itemss67.setTitle("��ɽ" );itemss67.setContent(" ");news.add(itemss67);
NewsCitys itemss68 = new NewsCitys();itemss68.setTitle("����" );itemss68.setContent(" ");news.add(itemss68);
NewsCitys itemss69 = new NewsCitys();itemss69.setTitle("����" );itemss69.setContent(" ");news.add(itemss69);
NewsCitys itemss70 = new NewsCitys();itemss70.setTitle("��֦��" );itemss70.setContent(" ");news.add(itemss70);
NewsCitys itemss71 = new NewsCitys();itemss71.setTitle("�Ű�" );itemss71.setContent(" ");news.add(itemss71);
NewsCitys itemss72 = new NewsCitys();itemss72.setTitle("�˱�" );itemss72.setContent(" ");news.add(itemss72);
NewsCitys itemss73 = new NewsCitys();itemss73.setTitle("�Թ�" );itemss73.setContent(" ");news.add(itemss73);
NewsCitys itemss74 = new NewsCitys();itemss74.setTitle("������" );itemss74.setContent(" ");news.add(itemss74);
NewsCitys itemss75 = new NewsCitys();itemss75.setTitle("����" );itemss75.setContent(" ");news.add(itemss75);
NewsCitys itemss76 = new NewsCitys();itemss76.setTitle("����" );itemss76.setContent(" ");news.add(itemss76);
NewsCitys itemss77 = new NewsCitys();itemss77.setTitle("�㰲" );itemss77.setContent(" ");news.add(itemss77);
NewsCitys itemss78 = new NewsCitys();itemss78.setTitle("����" );itemss78.setContent(" ");news.add(itemss78);
NewsCitys itemss79 = new NewsCitys();itemss79.setTitle("üɽ" );itemss79.setContent(" ");news.add(itemss79);
NewsCitys itemss80 = new NewsCitys();itemss80.setTitle("�ϳ�" );itemss80.setContent(" ");news.add(itemss80);
NewsCitys itemss81 = new NewsCitys();itemss81.setTitle("����" );itemss81.setContent(" ");news.add(itemss81);
NewsCitys itemss82 = new NewsCitys();itemss82.setTitle("����" );itemss82.setContent(" ");news.add(itemss82);
NewsCitys itemss83 = new NewsCitys();itemss83.setTitle("����" );itemss83.setContent(" ");news.add(itemss83);
NewsCitys itemss84 = new NewsCitys();itemss84.setTitle("�ع�" );itemss84.setContent(" ");news.add(itemss84);
NewsCitys itemss85 = new NewsCitys();itemss85.setTitle("տ��" );itemss85.setContent(" ");news.add(itemss85);
NewsCitys itemss86 = new NewsCitys();itemss86.setTitle("����" );itemss86.setContent(" ");news.add(itemss86);
NewsCitys itemss87 = new NewsCitys();itemss87.setTitle("��Զ" );itemss87.setContent(" ");news.add(itemss87);
NewsCitys itemss88 = new NewsCitys();itemss88.setTitle("��ݸ" );itemss88.setContent(" ");news.add(itemss88);
NewsCitys itemss89 = new NewsCitys();itemss89.setTitle("����" );itemss89.setContent(" ");news.add(itemss89);
NewsCitys itemss90 = new NewsCitys();itemss90.setTitle("ï��" );itemss90.setContent(" ");news.add(itemss90);
NewsCitys itemss91 = new NewsCitys();itemss91.setTitle("����" );itemss91.setContent(" ");news.add(itemss91);
NewsCitys itemss92 = new NewsCitys();itemss92.setTitle("��β" );itemss92.setContent(" ");news.add(itemss92);
NewsCitys itemss93 = new NewsCitys();itemss93.setTitle("��Դ" );itemss93.setContent(" ");news.add(itemss93);
NewsCitys itemss94 = new NewsCitys();itemss94.setTitle("����" );itemss94.setContent(" ");news.add(itemss94);
NewsCitys itemss95 = new NewsCitys();itemss95.setTitle("÷��" );itemss95.setContent(" ");news.add(itemss95);
//////////////////////////////////
NewsCitys itemsx1 = new NewsCitys();itemsx1.setTitle("��ɽ" );itemsx1.setContent(" ");news.add(itemsx1);
NewsCitys itemsx2 = new NewsCitys();itemsx2.setTitle("����" );itemsx2.setContent(" ");news.add(itemsx2);
NewsCitys itemsx3 = new NewsCitys();itemsx3.setTitle("����" );itemsx3.setContent(" ");news.add(itemsx3);
NewsCitys itemsx4 = new NewsCitys();itemsx4.setTitle("�Ƹ�" );itemsx4.setContent(" ");news.add(itemsx4);
NewsCitys itemsx5 = new NewsCitys();itemsx5.setTitle("�麣" );itemsx5.setContent(" ");news.add(itemsx5);
NewsCitys itemsx6 = new NewsCitys();itemsx6.setTitle("��ͷ" );itemsx6.setContent(" ");news.add(itemsx6);
NewsCitys itemsx7 = new NewsCitys();itemsx7.setTitle("��ɽ" );itemsx7.setContent(" ");news.add(itemsx7);
NewsCitys itemsx8 = new NewsCitys();itemsx8.setTitle("����" );itemsx8.setContent(" ");news.add(itemsx8);
NewsCitys itemsx9 = new NewsCitys();itemsx9.setTitle("����" );itemsx9.setContent(" ");news.add(itemsx9);
NewsCitys itemsx10 = new NewsCitys();itemsx10.setTitle("��˷" );itemsx10.setContent(" ");news.add(itemsx10);
NewsCitys itemsx11 = new NewsCitys();itemsx11.setTitle("����" );itemsx11.setContent(" ");news.add(itemsx11);
NewsCitys itemsx12 = new NewsCitys();itemsx12.setTitle("����" );itemsx12.setContent(" ");news.add(itemsx12);
NewsCitys itemsx13 = new NewsCitys();itemsx13.setTitle("����" );itemsx13.setContent(" ");news.add(itemsx13);
NewsCitys itemsx14 = new NewsCitys();itemsx14.setTitle("��ƽ" );itemsx14.setContent(" ");news.add(itemsx14);
NewsCitys itemsx15 = new NewsCitys();itemsx15.setTitle("����" );itemsx15.setContent(" ");news.add(itemsx15);
NewsCitys itemsx16 = new NewsCitys();itemsx16.setTitle("����" );itemsx16.setContent(" ");news.add(itemsx16);
NewsCitys itemsx17 = new NewsCitys();itemsx17.setTitle("���" );itemsx17.setContent(" ");news.add(itemsx17);
NewsCitys itemsx18 = new NewsCitys();itemsx18.setTitle("���Ǹ�" );itemsx18.setContent(" ");news.add(itemsx18);
NewsCitys itemsx19 = new NewsCitys();itemsx19.setTitle("��ɫ" );itemsx19.setContent(" ");news.add(itemsx19);
NewsCitys itemsx20 = new NewsCitys();itemsx20.setTitle("����" );itemsx20.setContent(" ");news.add(itemsx20);
NewsCitys itemsx21 = new NewsCitys();itemsx21.setTitle("�ӳ�" );itemsx21.setContent(" ");news.add(itemsx21);
NewsCitys itemsx22 = new NewsCitys();itemsx22.setTitle("����" );itemsx22.setContent(" ");news.add(itemsx22);
NewsCitys itemsx23 = new NewsCitys();itemsx23.setTitle("����" );itemsx23.setContent(" ");news.add(itemsx23);
NewsCitys itemsx24 = new NewsCitys();itemsx24.setTitle("����" );itemsx24.setContent(" ");news.add(itemsx24);
NewsCitys itemsx25 = new NewsCitys();itemsx25.setTitle("��ɽ" );itemsx25.setContent(" ");news.add(itemsx25);
NewsCitys itemsx26 = new NewsCitys();itemsx26.setTitle("����" );itemsx26.setContent(" ");news.add(itemsx26);
NewsCitys itemsx27 = new NewsCitys();itemsx27.setTitle("�º�" );itemsx27.setContent(" ");news.add(itemsx27);
NewsCitys itemsx28 = new NewsCitys();itemsx28.setTitle("���" );itemsx28.setContent(" ");news.add(itemsx28);
NewsCitys itemsx29 = new NewsCitys();itemsx29.setTitle("�ٲ�" );itemsx29.setContent(" ");news.add(itemsx29);
NewsCitys itemsx30 = new NewsCitys();itemsx30.setTitle("ŭ��" );itemsx30.setContent(" ");news.add(itemsx30);
NewsCitys itemsx31 = new NewsCitys();itemsx31.setTitle("����" );itemsx31.setContent(" ");news.add(itemsx31);
NewsCitys itemsx32 = new NewsCitys();itemsx32.setTitle("˼é" );itemsx32.setContent(" ");news.add(itemsx32);
NewsCitys itemsx33 = new NewsCitys();itemsx33.setTitle("��ɽ" );itemsx33.setContent(" ");news.add(itemsx33);
NewsCitys itemsx34 = new NewsCitys();itemsx34.setTitle("��Ϫ" );itemsx34.setContent(" ");news.add(itemsx34);
NewsCitys itemsx35 = new NewsCitys();itemsx35.setTitle("��ͨ" );itemsx35.setContent(" ");news.add(itemsx35);
NewsCitys itemsx36 = new NewsCitys();itemsx36.setTitle("����" );itemsx36.setContent(" ");news.add(itemsx36);
NewsCitys itemsx37 = new NewsCitys();itemsx37.setTitle("����" );itemsx37.setContent(" ");news.add(itemsx37);
NewsCitys itemsx38 = new NewsCitys();itemsx38.setTitle("����" );itemsx38.setContent(" ");news.add(itemsx38);
NewsCitys itemsx39 = new NewsCitys();itemsx39.setTitle("����" );itemsx39.setContent(" ");news.add(itemsx39);
NewsCitys itemsx40 = new NewsCitys();itemsx40.setTitle("����" );itemsx40.setContent(" ");news.add(itemsx40);
NewsCitys itemsx41 = new NewsCitys();itemsx41.setTitle("��ɽ" );itemsx41.setContent(" ");news.add(itemsx41);
NewsCitys itemsx42 = new NewsCitys();itemsx42.setTitle("ͨʲ" );itemsx42.setContent(" ");news.add(itemsx42);
NewsCitys itemsx43 = new NewsCitys();itemsx43.setTitle("�Ĳ�" );itemsx43.setContent(" ");news.add(itemsx43);
NewsCitys itemsx44 = new NewsCitys();itemsx44.setTitle("��³ľ��" );itemsx44.setContent(" ");news.add(itemsx44);
NewsCitys itemsx45 = new NewsCitys();itemsx45.setTitle("����̩" );itemsx45.setContent(" ");news.add(itemsx45);
NewsCitys itemsx46 = new NewsCitys();itemsx46.setTitle("������" );itemsx46.setContent(" ");news.add(itemsx46);
NewsCitys itemsx47 = new NewsCitys();itemsx47.setTitle("����" );itemsx47.setContent(" ");news.add(itemsx47);
NewsCitys itemsxs48 = new NewsCitys();itemsxs48.setTitle("����" );itemsxs48.setContent(" ");news.add(itemsxs48);
NewsCitys itemsxs49 = new NewsCitys();itemsxs49.setTitle("����" );itemsxs49.setContent(" ");news.add(itemsxs49);
NewsCitys itemsxs50 = new NewsCitys();itemsxs50.setTitle("��ʲ" );itemsxs50.setContent(" ");news.add(itemsxs50);
NewsCitys itemsxs51 = new NewsCitys();itemsxs51.setTitle("��������" );itemsxs51.setContent(" ");news.add(itemsxs51);
NewsCitys itemsxs52 = new NewsCitys();itemsxs52.setTitle("ʯ����" );itemsxs52.setContent(" ");news.add(itemsxs52);
NewsCitys itemsxs53 = new NewsCitys();itemsxs53.setTitle("����" );itemsxs53.setContent(" ");news.add(itemsxs53);
NewsCitys itemsxs54 = new NewsCitys();itemsxs54.setTitle("�����" );itemsxs54.setContent(" ");news.add(itemsxs54);
NewsCitys itemsxs55 = new NewsCitys();itemsxs55.setTitle("��³��" );itemsxs55.setContent(" ");news.add(itemsxs55);
NewsCitys itemsxs56 = new NewsCitys();itemsxs56.setTitle("����" );itemsxs56.setContent(" ");news.add(itemsxs56);
NewsCitys itemsxs57 = new NewsCitys();itemsxs57.setTitle("����" );itemsxs57.setContent(" ");news.add(itemsxs57);
NewsCitys itemsxs58 = new NewsCitys();itemsxs58.setTitle("����" );itemsxs58.setContent(" ");news.add(itemsxs58);
NewsCitys itemsxs59 = new NewsCitys();itemsxs59.setTitle("����" );itemsxs59.setContent(" ");news.add(itemsxs59);
NewsCitys itemsxs60 = new NewsCitys();itemsxs60.setTitle("����" );itemsxs60.setContent(" ");news.add(itemsxs60);
NewsCitys itemsxs61 = new NewsCitys();itemsxs61.setTitle("�տ���" );itemsxs61.setContent(" ");news.add(itemsxs61);
NewsCitys itemsxs62 = new NewsCitys();itemsxs62.setTitle("ɽ��" );itemsxs62.setContent(" ");news.add(itemsxs62);
NewsCitys itemsxs63 = new NewsCitys();itemsxs63.setTitle("��֥" );itemsxs63.setContent(" ");news.add(itemsxs63);
}
class PaginationAdapter extends BaseAdapter {

	List<Newscitylistview> newsItems;

	public PaginationAdapter(List<Newscitylistview> newsitems) {

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

	public void addNewsItem(Newscitylistview newsitem) {

		newsItems.add(newsitem);

	}

}
public void cleanlist(){
	int size=news2.size();
	if(size>0){
		System.out.println(size);
		news2.removeAll(news2);
		adapter.notifyDataSetChanged();
		listView.setAdapter(adapter);
	}
}
}