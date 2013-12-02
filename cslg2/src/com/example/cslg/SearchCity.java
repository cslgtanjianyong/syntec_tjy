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
	private int visibleLastIndex = 0; // 最后的可视项索引
	private int visibleItemCount; // 当前窗口可见项总数
	private	List<NewsCitys> news = new ArrayList<NewsCitys>();
	private	List<Newscitylistview> news2 = new ArrayList<Newscitylistview>();
	private PaginationAdapter adapter;
private String cityitemname="cityname";
	
	private Handler handler = new Handler();
public void init() 
{//初始化控件
listView = (ListView) findViewById(R.id.listViewscity);

btnscity=(Button)findViewById(R.id.btnscity);
editcityname=(EditText)findViewById(R.id.editcityname);
inputcity();//添加城市数据	
//initializeAdapter();//初始化listview数据

	
	
}
	
protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.searchcity);
		AppManager.getAppManager().addActivity(this);
		//初始化控件
		init();

		
		//搜索城市
		btnscity.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				 cancerSerachcity();
				
			//	initializeAdapter();//加载搜索数据
				
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
				Editor editor = sharedPreferences2.edit();//获取编辑器
				editor.putString("islogin", "isinstall");
				editor.commit();//提交修改
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
				   System.out.println("不包含");
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
public boolean onKeyDown(int keyCode, KeyEvent event)//主要是对这个函数的复写
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
		//getString()第二个参数为缺省值，如果preference中不存在该key，将返回缺省值
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
private void initializeAdapter(List<Newscitylistview> newsc) {

	
////////////////////


//////////////////////
	adapter = new PaginationAdapter(newsc);
	listView.setAdapter(adapter);

	listView.setOnScrollListener(this);

}

private void inputcity() {
	
	NewsCitys item1 = new NewsCitys();item1.setTitle("哈尔滨" );item1.setContent(" ");news.add(item1);
	NewsCitys item2 = new NewsCitys();item2.setTitle("齐齐哈尔" );item2.setContent(" ");news.add(item2);
	NewsCitys item3 = new NewsCitys();item3.setTitle("牡丹江" );item3.setContent(" ");news.add(item3);
	NewsCitys item4 = new NewsCitys();item4.setTitle("大庆" );item4.setContent(" ");news.add(item4);
	NewsCitys item5 = new NewsCitys();item5.setTitle("伊春" );item5.setContent(" ");news.add(item5);
	NewsCitys item6 = new NewsCitys();item6.setTitle("双鸭山" );item6.setContent(" ");news.add(item6);
	NewsCitys item7 = new NewsCitys();item7.setTitle("鹤岗" );item7.setContent(" ");news.add(item7);
	NewsCitys item8 = new NewsCitys();item8.setTitle("鸡西" );item8.setContent(" ");news.add(item8);
	NewsCitys item9 = new NewsCitys();item9.setTitle("佳木斯" );item9.setContent(" ");news.add(item9);
	NewsCitys item10 = new NewsCitys();item10.setTitle("七台河" );item10.setContent(" ");news.add(item10);
	NewsCitys item11 = new NewsCitys();item11.setTitle("黑河" );item11.setContent(" ");news.add(item11);
	NewsCitys item12 = new NewsCitys();item12.setTitle("绥化" );item12.setContent(" ");news.add(item12);
	NewsCitys item13 = new NewsCitys();item13.setTitle("大兴安岭" );item13.setContent(" ");news.add(item13);
	NewsCitys item14 = new NewsCitys();item14.setTitle("长春" );item14.setContent(" ");news.add(item14);
	NewsCitys item15 = new NewsCitys();item15.setTitle("吉林" );item15.setContent(" ");news.add(item15);
	NewsCitys item16 = new NewsCitys();item16.setTitle("白山" );item16.setContent(" ");news.add(item16);
	NewsCitys item17 = new NewsCitys();item17.setTitle("白城" );item17.setContent(" ");news.add(item17);
	NewsCitys item18 = new NewsCitys();item18.setTitle("四平" );item18.setContent(" ");news.add(item18);
	NewsCitys item19 = new NewsCitys();item19.setTitle("松原" );item19.setContent(" ");news.add(item19);
	NewsCitys item20 = new NewsCitys();item20.setTitle("辽源" );item20.setContent(" ");news.add(item20);
	NewsCitys item21 = new NewsCitys();item21.setTitle("大安" );item21.setContent(" ");news.add(item21);
	NewsCitys item22 = new NewsCitys();item22.setTitle("通化" );item22.setContent(" ");news.add(item22);
	NewsCitys item23 = new NewsCitys();item23.setTitle("沈阳" );item23.setContent(" ");news.add(item23);
	NewsCitys item24 = new NewsCitys();item24.setTitle("大连" );item24.setContent(" ");news.add(item24);
	NewsCitys item25 = new NewsCitys();item25.setTitle("葫芦岛" );item25.setContent(" ");news.add(item25);
	NewsCitys item26 = new NewsCitys();item26.setTitle("旅顺" );item26.setContent(" ");news.add(item26);
	NewsCitys item27 = new NewsCitys();item27.setTitle("本溪" );item27.setContent(" ");news.add(item27);
	NewsCitys item28 = new NewsCitys();item28.setTitle("抚顺" );item28.setContent(" ");news.add(item28);
	NewsCitys item29 = new NewsCitys();item29.setTitle("铁岭" );item29.setContent(" ");news.add(item29);
	NewsCitys item30 = new NewsCitys();item30.setTitle("辽阳" );item30.setContent(" ");news.add(item30);
	NewsCitys item31 = new NewsCitys();item31.setTitle("营口" );item31.setContent(" ");news.add(item31);
	NewsCitys item32 = new NewsCitys();item32.setTitle("阜新" );item32.setContent(" ");news.add(item32);
	NewsCitys item33 = new NewsCitys();item33.setTitle("朝阳" );item33.setContent(" ");news.add(item33);
	NewsCitys item34 = new NewsCitys();item34.setTitle("锦州" );item34.setContent(" ");news.add(item34);
	NewsCitys item35 = new NewsCitys();item35.setTitle("丹东" );item35.setContent(" ");news.add(item35);
	NewsCitys item36 = new NewsCitys();item36.setTitle("鞍山" );item36.setContent(" ");news.add(item36);
	NewsCitys item37 = new NewsCitys();item37.setTitle("呼和浩特" );item37.setContent(" ");news.add(item37);
	NewsCitys item38 = new NewsCitys();item38.setTitle("锡林浩特" );item38.setContent(" ");news.add(item38);
	NewsCitys item39 = new NewsCitys();item39.setTitle("包头" );item39.setContent(" ");news.add(item39);
	NewsCitys item40 = new NewsCitys();item40.setTitle("赤峰" );item40.setContent(" ");news.add(item40);
	NewsCitys item41 = new NewsCitys();item41.setTitle("海拉尔" );item41.setContent(" ");news.add(item41);
	NewsCitys item42 = new NewsCitys();item42.setTitle("乌海" );item42.setContent(" ");news.add(item42);
	NewsCitys item43 = new NewsCitys();item43.setTitle("鄂尔多斯" );item43.setContent(" ");news.add(item43);
	NewsCitys item44 = new NewsCitys();item44.setTitle("锡林浩特" );item44.setContent(" ");news.add(item44);
	NewsCitys item45 = new NewsCitys();item45.setTitle("通辽" );item45.setContent(" ");news.add(item45);
	NewsCitys item46 = new NewsCitys();item46.setTitle("石家庄" );item46.setContent(" ");news.add(item46);
	NewsCitys item47 = new NewsCitys();item47.setTitle("唐山" );item47.setContent(" ");news.add(item47);
	NewsCitys items48 = new NewsCitys();items48.setTitle("张家口" );items48.setContent(" ");news.add(items48);
	NewsCitys items49 = new NewsCitys();items49.setTitle("廊坊" );items49.setContent(" ");news.add(items49);
	NewsCitys items50 = new NewsCitys();items50.setTitle("邢台" );items50.setContent(" ");news.add(items50);
	NewsCitys items51 = new NewsCitys();items51.setTitle("邯郸" );items51.setContent(" ");news.add(items51);
	NewsCitys items52 = new NewsCitys();items52.setTitle("沧州" );items52.setContent(" ");news.add(items52);
	NewsCitys items53 = new NewsCitys();items53.setTitle("衡水" );items53.setContent(" ");news.add(items53);
	NewsCitys items54 = new NewsCitys();items54.setTitle("承德" );items54.setContent(" ");news.add(items54);
	NewsCitys items55 = new NewsCitys();items55.setTitle("保定" );items55.setContent(" ");news.add(items55);
	NewsCitys items56 = new NewsCitys();items56.setTitle("秦皇岛" );items56.setContent(" ");news.add(items56);
	NewsCitys items57 = new NewsCitys();items57.setTitle("郑州" );items57.setContent(" ");news.add(items57);
	NewsCitys items58 = new NewsCitys();items58.setTitle("开封" );items58.setContent(" ");news.add(items58);
	NewsCitys items59 = new NewsCitys();items59.setTitle("洛阳" );items59.setContent(" ");news.add(items59);
	NewsCitys items60 = new NewsCitys();items60.setTitle("平顶山" );items60.setContent(" ");news.add(items60);
	NewsCitys items61 = new NewsCitys();items61.setTitle("焦作" );items61.setContent(" ");news.add(items61);
	NewsCitys items62 = new NewsCitys();items62.setTitle("鹤壁" );items62.setContent(" ");news.add(items62);
	NewsCitys items63 = new NewsCitys();items63.setTitle("新乡" );items63.setContent(" ");news.add(items63);
	NewsCitys items64 = new NewsCitys();items64.setTitle("安阳" );items64.setContent(" ");news.add(items64);
	NewsCitys items65 = new NewsCitys();items65.setTitle("濮阳" );items65.setContent(" ");news.add(items65);
	NewsCitys items66 = new NewsCitys();items66.setTitle("许昌" );items66.setContent(" ");news.add(items66);
	NewsCitys items67 = new NewsCitys();items67.setTitle("漯河" );items67.setContent(" ");news.add(items67);
	NewsCitys items68 = new NewsCitys();items68.setTitle("三门峡" );items68.setContent(" ");news.add(items68);
	NewsCitys items69 = new NewsCitys();items69.setTitle("南阳" );items69.setContent(" ");news.add(items69);
	NewsCitys items70 = new NewsCitys();items70.setTitle("商丘" );items70.setContent(" ");news.add(items70);
	NewsCitys items71 = new NewsCitys();items71.setTitle("信阳" );items71.setContent(" ");news.add(items71);
	NewsCitys items72 = new NewsCitys();items72.setTitle("周口" );items72.setContent(" ");news.add(items72);
	NewsCitys items73 = new NewsCitys();items73.setTitle("驻马店" );items73.setContent(" ");news.add(items73);
	NewsCitys items74 = new NewsCitys();items74.setTitle("济南" );items74.setContent(" ");news.add(items74);
	NewsCitys items75 = new NewsCitys();items75.setTitle("青岛" );items75.setContent(" ");news.add(items75);
	NewsCitys items76 = new NewsCitys();items76.setTitle("淄博" );items76.setContent(" ");news.add(items76);
	NewsCitys items77 = new NewsCitys();items77.setTitle("威海" );items77.setContent(" ");news.add(items77);
	NewsCitys items78 = new NewsCitys();items78.setTitle("曲阜" );items78.setContent(" ");news.add(items78);
	NewsCitys items79 = new NewsCitys();items79.setTitle("临沂" );items79.setContent(" ");news.add(items79);
	NewsCitys items80 = new NewsCitys();items80.setTitle("烟台" );items80.setContent(" ");news.add(items80);
	NewsCitys items81 = new NewsCitys();items81.setTitle("枣庄" );items81.setContent(" ");news.add(items81);
	NewsCitys items82 = new NewsCitys();items82.setTitle("聊城" );items82.setContent(" ");news.add(items82);
	NewsCitys items83 = new NewsCitys();items83.setTitle("济宁" );items83.setContent(" ");news.add(items83);
	NewsCitys items84 = new NewsCitys();items84.setTitle("菏泽" );items84.setContent(" ");news.add(items84);
	NewsCitys items85 = new NewsCitys();items85.setTitle("泰安" );items85.setContent(" ");news.add(items85);
	NewsCitys items86 = new NewsCitys();items86.setTitle("日照" );items86.setContent(" ");news.add(items86);
	NewsCitys items87 = new NewsCitys();items87.setTitle("东营" );items87.setContent(" ");news.add(items87);
	NewsCitys items88 = new NewsCitys();items88.setTitle("德州" );items88.setContent(" ");news.add(items88);
	NewsCitys items89 = new NewsCitys();items89.setTitle("滨州" );items89.setContent(" ");news.add(items89);
	NewsCitys items90 = new NewsCitys();items90.setTitle("莱芜" );items90.setContent(" ");news.add(items90);
	NewsCitys items91 = new NewsCitys();items91.setTitle("潍坊" );items91.setContent(" ");news.add(items91);
	NewsCitys items92 = new NewsCitys();items92.setTitle("太原" );items92.setContent(" ");news.add(items92);
	NewsCitys items93 = new NewsCitys();items93.setTitle("阳泉" );items93.setContent(" ");news.add(items93);
	NewsCitys items94 = new NewsCitys();items94.setTitle("晋城" );items94.setContent(" ");news.add(items94);
	NewsCitys items95 = new NewsCitys();items95.setTitle("晋中" );items95.setContent(" ");news.add(items95);
	/////////////////////////////////////////////
    NewsCitys ite1 = new NewsCitys();ite1.setTitle("临汾" );ite1.setContent(" ");news.add(ite1);
NewsCitys ite2 = new NewsCitys();ite2.setTitle("运城" );ite2.setContent(" ");news.add(ite2);
NewsCitys ite3 = new NewsCitys();ite3.setTitle("长治" );ite3.setContent(" ");news.add(ite3);
NewsCitys ite4 = new NewsCitys();ite4.setTitle("朔州" );ite4.setContent(" ");news.add(ite4);
NewsCitys ite5 = new NewsCitys();ite5.setTitle("忻州" );ite5.setContent(" ");news.add(ite5);
NewsCitys ite6 = new NewsCitys();ite6.setTitle("大同" );ite6.setContent(" ");news.add(ite6);
NewsCitys ite7 = new NewsCitys();ite7.setTitle("吕梁" );ite7.setContent(" ");news.add(ite7);
NewsCitys ite8 = new NewsCitys();ite8.setTitle("南京" );ite8.setContent(" ");news.add(ite8);
NewsCitys ite9 = new NewsCitys();ite9.setTitle("苏州" );ite9.setContent(" ");news.add(ite9);
NewsCitys ite10 = new NewsCitys();ite10.setTitle("昆山" );ite10.setContent(" ");news.add(ite10);
NewsCitys ite11 = new NewsCitys();ite11.setTitle("南通" );ite11.setContent(" ");news.add(ite11);
NewsCitys ite12 = new NewsCitys();ite12.setTitle("太仓" );ite12.setContent(" ");news.add(ite12);
NewsCitys ite13 = new NewsCitys();ite13.setTitle("吴县" );ite13.setContent(" ");news.add(ite13);
NewsCitys ite14 = new NewsCitys();ite14.setTitle("徐州" );ite14.setContent(" ");news.add(ite14);
NewsCitys ite15 = new NewsCitys();ite15.setTitle("宜兴" );ite15.setContent(" ");news.add(ite15);
NewsCitys ite16 = new NewsCitys();ite16.setTitle("镇江" );ite16.setContent(" ");news.add(ite16);
NewsCitys ite17 = new NewsCitys();ite17.setTitle("淮安" );ite17.setContent(" ");news.add(ite17);
NewsCitys ite18 = new NewsCitys();ite18.setTitle("常熟" );ite18.setContent(" ");news.add(ite18);
NewsCitys ite19 = new NewsCitys();ite19.setTitle("盐城" );ite19.setContent(" ");news.add(ite19);
NewsCitys ite20 = new NewsCitys();ite20.setTitle("泰州" );ite20.setContent(" ");news.add(ite20);
NewsCitys ite21 = new NewsCitys();ite21.setTitle("无锡" );ite21.setContent(" ");news.add(ite21);
NewsCitys ite22 = new NewsCitys();ite22.setTitle("连云港" );ite22.setContent(" ");news.add(ite22);
NewsCitys ite23 = new NewsCitys();ite23.setTitle("扬州" );ite23.setContent(" ");news.add(ite23);
NewsCitys ite24 = new NewsCitys();ite24.setTitle("常州" );ite24.setContent(" ");news.add(ite24);
NewsCitys ite25 = new NewsCitys();ite25.setTitle("宿迁" );ite25.setContent(" ");news.add(ite25);
NewsCitys ite26 = new NewsCitys();ite26.setTitle("合肥" );ite26.setContent(" ");news.add(ite26);
NewsCitys ite27 = new NewsCitys();ite27.setTitle("巢湖" );ite27.setContent(" ");news.add(ite27);
NewsCitys ite28 = new NewsCitys();ite28.setTitle("蚌埠" );ite28.setContent(" ");news.add(ite28);
NewsCitys ite29 = new NewsCitys();ite29.setTitle("安庆" );ite29.setContent(" ");news.add(ite29);
NewsCitys ite30 = new NewsCitys();ite30.setTitle("六安" );ite30.setContent(" ");news.add(ite30);
NewsCitys ite31 = new NewsCitys();ite31.setTitle("滁州" );ite31.setContent(" ");news.add(ite31);
NewsCitys ite32 = new NewsCitys();ite32.setTitle("马鞍山" );ite32.setContent(" ");news.add(ite32);
NewsCitys ite33 = new NewsCitys();ite33.setTitle("阜阳" );ite33.setContent(" ");news.add(ite33);
NewsCitys ite34 = new NewsCitys();ite34.setTitle("宣城" );ite34.setContent(" ");news.add(ite34);
NewsCitys ite35 = new NewsCitys();ite35.setTitle("铜陵" );ite35.setContent(" ");news.add(ite35);
NewsCitys ite36 = new NewsCitys();ite36.setTitle("淮北" );ite36.setContent(" ");news.add(ite36);
NewsCitys ite37 = new NewsCitys();ite37.setTitle("芜湖" );ite37.setContent(" ");news.add(ite37);
NewsCitys ite38 = new NewsCitys();ite38.setTitle("宿州" );ite38.setContent(" ");news.add(ite38);
NewsCitys ite39 = new NewsCitys();ite39.setTitle("淮南" );ite39.setContent(" ");news.add(ite39);
NewsCitys ite40 = new NewsCitys();ite40.setTitle("池州" );ite40.setContent(" ");news.add(ite40);
NewsCitys ite41 = new NewsCitys();ite41.setTitle("西安" );ite41.setContent(" ");news.add(ite41);
NewsCitys ite42 = new NewsCitys();ite42.setTitle("韩城" );ite42.setContent(" ");news.add(ite42);
NewsCitys ite43 = new NewsCitys();ite43.setTitle("安康" );ite43.setContent(" ");news.add(ite43);
NewsCitys ite44 = new NewsCitys();ite44.setTitle("汉中" );ite44.setContent(" ");news.add(ite44);
NewsCitys ite45 = new NewsCitys();ite45.setTitle("宝鸡" );ite45.setContent(" ");news.add(ite45);
NewsCitys ite46 = new NewsCitys();ite46.setTitle("咸阳" );ite46.setContent(" ");news.add(ite46);
NewsCitys ite47 = new NewsCitys();ite47.setTitle("榆林" );ite47.setContent(" ");news.add(ite47);
NewsCitys ites48 = new NewsCitys();ites48.setTitle("渭南" );ites48.setContent(" ");news.add(ites48);
NewsCitys ites49 = new NewsCitys();ites49.setTitle("商洛" );ites49.setContent(" ");news.add(ites49);
NewsCitys ites50 = new NewsCitys();ites50.setTitle("铜川" );ites50.setContent(" ");news.add(ites50);
NewsCitys ites51 = new NewsCitys();ites51.setTitle("延安" );ites51.setContent(" ");news.add(ites51);
NewsCitys ites52 = new NewsCitys();ites52.setTitle("银川" );ites52.setContent(" ");news.add(ites52);
NewsCitys ites53 = new NewsCitys();ites53.setTitle("固原" );ites53.setContent(" ");news.add(ites53);
NewsCitys ites54 = new NewsCitys();ites54.setTitle("中卫" );ites54.setContent(" ");news.add(ites54);
NewsCitys ites55 = new NewsCitys();ites55.setTitle("石嘴山" );ites55.setContent(" ");news.add(ites55);
NewsCitys ites56 = new NewsCitys();ites56.setTitle("吴忠" );ites56.setContent(" ");news.add(ites56);
NewsCitys ites57 = new NewsCitys();ites57.setTitle("兰州" );ites57.setContent(" ");news.add(ites57);
NewsCitys ites58 = new NewsCitys();ites58.setTitle("白银" );ites58.setContent(" ");news.add(ites58);
NewsCitys ites59 = new NewsCitys();ites59.setTitle("庆阳" );ites59.setContent(" ");news.add(ites59);
NewsCitys ites60 = new NewsCitys();ites60.setTitle("酒泉" );ites60.setContent(" ");news.add(ites60);
NewsCitys ites61 = new NewsCitys();ites61.setTitle("天水" );ites61.setContent(" ");news.add(ites61);
NewsCitys ites62 = new NewsCitys();ites62.setTitle("武威" );ites62.setContent(" ");news.add(ites62);
NewsCitys ites63 = new NewsCitys();ites63.setTitle("张掖" );ites63.setContent(" ");news.add(ites63);
NewsCitys ites64 = new NewsCitys();ites64.setTitle("甘南" );ites64.setContent(" ");news.add(ites64);
NewsCitys ites65 = new NewsCitys();ites65.setTitle("临夏" );ites65.setContent(" ");news.add(ites65);
NewsCitys ites66 = new NewsCitys();ites66.setTitle("平凉" );ites66.setContent(" ");news.add(ites66);
NewsCitys ites67 = new NewsCitys();ites67.setTitle("定西" );ites67.setContent(" ");news.add(ites67);
NewsCitys ites68 = new NewsCitys();ites68.setTitle("金昌" );ites68.setContent(" ");news.add(ites68);
NewsCitys ites69 = new NewsCitys();ites69.setTitle("西宁" );ites69.setContent(" ");news.add(ites69);
NewsCitys ites70 = new NewsCitys();ites70.setTitle("海北" );ites70.setContent(" ");news.add(ites70);
NewsCitys ites71 = new NewsCitys();ites71.setTitle("海西" );ites71.setContent(" ");news.add(ites71);
NewsCitys ites72 = new NewsCitys();ites72.setTitle("黄南" );ites72.setContent(" ");news.add(ites72);
NewsCitys ites73 = new NewsCitys();ites73.setTitle("果洛" );ites73.setContent(" ");news.add(ites73);
NewsCitys ites74 = new NewsCitys();ites74.setTitle("玉树" );ites74.setContent(" ");news.add(ites74);
NewsCitys ites75 = new NewsCitys();ites75.setTitle("海东" );ites75.setContent(" ");news.add(ites75);
NewsCitys ites76 = new NewsCitys();ites76.setTitle("海南" );ites76.setContent(" ");news.add(ites76);
NewsCitys ites77 = new NewsCitys();ites77.setTitle("武汉" );ites77.setContent(" ");news.add(ites77);
NewsCitys ites78 = new NewsCitys();ites78.setTitle("宜昌" );ites78.setContent(" ");news.add(ites78);
NewsCitys ites79 = new NewsCitys();ites79.setTitle("黄冈" );ites79.setContent(" ");news.add(ites79);
NewsCitys ites80 = new NewsCitys();ites80.setTitle("恩施" );ites80.setContent(" ");news.add(ites80);
NewsCitys ites81 = new NewsCitys();ites81.setTitle("荆州" );ites81.setContent(" ");news.add(ites81);
NewsCitys ites82 = new NewsCitys();ites82.setTitle("神农架" );ites82.setContent(" ");news.add(ites82);
NewsCitys ites83 = new NewsCitys();ites83.setTitle("十堰" );ites83.setContent(" ");news.add(ites83);
NewsCitys ites84 = new NewsCitys();ites84.setTitle("咸宁" );ites84.setContent(" ");news.add(ites84);
NewsCitys ites85 = new NewsCitys();ites85.setTitle("襄樊" );ites85.setContent(" ");news.add(ites85);
NewsCitys ites86 = new NewsCitys();ites86.setTitle("孝感" );ites86.setContent(" ");news.add(ites86);
NewsCitys ites87 = new NewsCitys();ites87.setTitle("随州" );ites87.setContent(" ");news.add(ites87);
NewsCitys ites88 = new NewsCitys();ites88.setTitle("黄石" );ites88.setContent(" ");news.add(ites88);
NewsCitys ites89 = new NewsCitys();ites89.setTitle("荆门" );ites89.setContent(" ");news.add(ites89);
NewsCitys ites90 = new NewsCitys();ites90.setTitle("鄂州" );ites90.setContent(" ");news.add(ites90);
NewsCitys ites91 = new NewsCitys();ites91.setTitle("长沙" );ites91.setContent(" ");news.add(ites91);
NewsCitys ites92 = new NewsCitys();ites92.setTitle("邵阳" );ites92.setContent(" ");news.add(ites92);
NewsCitys ites93 = new NewsCitys();ites93.setTitle("常德" );ites93.setContent(" ");news.add(ites93);
NewsCitys ites94 = new NewsCitys();ites94.setTitle("郴州" );ites94.setContent(" ");news.add(ites94);
NewsCitys ites95 = new NewsCitys();ites95.setTitle("吉首" );ites95.setContent(" ");news.add(ites95);
//////////////////////////////////////////////////
NewsCitys items1 = new NewsCitys();items1.setTitle("株洲" );items1.setContent(" ");news.add(items1);
NewsCitys items2 = new NewsCitys();items2.setTitle("娄底" );items2.setContent(" ");news.add(items2);
NewsCitys items3 = new NewsCitys();items3.setTitle("湘潭" );items3.setContent(" ");news.add(items3);
NewsCitys items4 = new NewsCitys();items4.setTitle("益阳" );items4.setContent(" ");news.add(items4);
NewsCitys items5 = new NewsCitys();items5.setTitle("永州" );items5.setContent(" ");news.add(items5);
NewsCitys items6 = new NewsCitys();items6.setTitle("岳阳" );items6.setContent(" ");news.add(items6);
NewsCitys items7 = new NewsCitys();items7.setTitle("衡阳" );items7.setContent(" ");news.add(items7);
NewsCitys items8 = new NewsCitys();items8.setTitle("怀化" );items8.setContent(" ");news.add(items8);
NewsCitys items9 = new NewsCitys();items9.setTitle("韶山" );items9.setContent(" ");news.add(items9);
NewsCitys items10 = new NewsCitys();items10.setTitle("张家界" );items10.setContent(" ");news.add(items10);
NewsCitys items11 = new NewsCitys();items11.setTitle("杭州" );items11.setContent(" ");news.add(items11);
NewsCitys items12 = new NewsCitys();items12.setTitle("湖州" );items12.setContent(" ");news.add(items12);
NewsCitys items13 = new NewsCitys();items13.setTitle("金华" );items13.setContent(" ");news.add(items13);
NewsCitys items14 = new NewsCitys();items14.setTitle("宁波" );items14.setContent(" ");news.add(items14);
NewsCitys items15 = new NewsCitys();items15.setTitle("丽水" );items15.setContent(" ");news.add(items15);
NewsCitys items16 = new NewsCitys();items16.setTitle("绍兴" );items16.setContent(" ");news.add(items16);
NewsCitys items17 = new NewsCitys();items17.setTitle("衢州" );items17.setContent(" ");news.add(items17);
NewsCitys items18 = new NewsCitys();items18.setTitle("嘉兴" );items18.setContent(" ");news.add(items18);
NewsCitys items19 = new NewsCitys();items19.setTitle("台州" );items19.setContent(" ");news.add(items19);
NewsCitys items20 = new NewsCitys();items20.setTitle("舟山" );items20.setContent(" ");news.add(items20);
NewsCitys items21 = new NewsCitys();items21.setTitle("温州" );items21.setContent(" ");news.add(items21);
NewsCitys items22 = new NewsCitys();items22.setTitle("南昌" );items22.setContent(" ");news.add(items22);
NewsCitys items23 = new NewsCitys();items23.setTitle("萍乡" );items23.setContent(" ");news.add(items23);
NewsCitys items24 = new NewsCitys();items24.setTitle("九江" );items24.setContent(" ");news.add(items24);
NewsCitys items25 = new NewsCitys();items25.setTitle("上饶" );items25.setContent(" ");news.add(items25);
NewsCitys items26 = new NewsCitys();items26.setTitle("抚州" );items26.setContent(" ");news.add(items26);
NewsCitys items27 = new NewsCitys();items27.setTitle("吉安" );items27.setContent(" ");news.add(items27);
NewsCitys items28 = new NewsCitys();items28.setTitle("鹰潭" );items28.setContent(" ");news.add(items28);
NewsCitys items29 = new NewsCitys();items29.setTitle("宜春" );items29.setContent(" ");news.add(items29);
NewsCitys items30 = new NewsCitys();items30.setTitle("新余" );items30.setContent(" ");news.add(items30);
NewsCitys items31 = new NewsCitys();items31.setTitle("景德镇" );items31.setContent(" ");news.add(items31);
NewsCitys items32 = new NewsCitys();items32.setTitle("赣州" );items32.setContent(" ");news.add(items32);
NewsCitys items33 = new NewsCitys();items33.setTitle("福州" );items33.setContent(" ");news.add(items33);
NewsCitys items34 = new NewsCitys();items34.setTitle("厦门" );items34.setContent(" ");news.add(items34);
NewsCitys items35 = new NewsCitys();items35.setTitle("龙岩" );items35.setContent(" ");news.add(items35);
NewsCitys items36 = new NewsCitys();items36.setTitle("南平" );items36.setContent(" ");news.add(items36);
NewsCitys items37 = new NewsCitys();items37.setTitle("宁德" );items37.setContent(" ");news.add(items37);
NewsCitys items38 = new NewsCitys();items38.setTitle("莆田" );items38.setContent(" ");news.add(items38);
NewsCitys items39 = new NewsCitys();items39.setTitle("泉州" );items39.setContent(" ");news.add(items39);
NewsCitys items40 = new NewsCitys();items40.setTitle("三明" );items40.setContent(" ");news.add(items40);
NewsCitys items41 = new NewsCitys();items41.setTitle("漳州" );items41.setContent(" ");news.add(items41);
NewsCitys items42 = new NewsCitys();items42.setTitle("福州" );items42.setContent(" ");news.add(items42);
NewsCitys items43 = new NewsCitys();items43.setTitle("厦门" );items43.setContent(" ");news.add(items43);
NewsCitys items44 = new NewsCitys();items44.setTitle("龙岩" );items44.setContent(" ");news.add(items44);
NewsCitys items45 = new NewsCitys();items45.setTitle("南平" );items45.setContent(" ");news.add(items45);
NewsCitys items46 = new NewsCitys();items46.setTitle("宁德" );items46.setContent(" ");news.add(items46);
NewsCitys items47 = new NewsCitys();items47.setTitle("莆田" );items47.setContent(" ");news.add(items47);
NewsCitys itemss48 = new NewsCitys();itemss48.setTitle("泉州" );itemss48.setContent(" ");news.add(itemss48);
NewsCitys itemss49 = new NewsCitys();itemss49.setTitle("三明" );itemss49.setContent(" ");news.add(itemss49);
NewsCitys itemss50 = new NewsCitys();itemss50.setTitle("漳州" );itemss50.setContent(" ");news.add(itemss50);
NewsCitys itemss51 = new NewsCitys();itemss51.setTitle("贵阳" );itemss51.setContent(" ");news.add(itemss51);
NewsCitys itemss52 = new NewsCitys();itemss52.setTitle("安顺" );itemss52.setContent(" ");news.add(itemss52);
NewsCitys itemss53 = new NewsCitys();itemss53.setTitle("赤水" );itemss53.setContent(" ");news.add(itemss53);
NewsCitys itemss54 = new NewsCitys();itemss54.setTitle("遵义" );itemss54.setContent(" ");news.add(itemss54);
NewsCitys itemss55 = new NewsCitys();itemss55.setTitle("铜仁" );itemss55.setContent(" ");news.add(itemss55);
NewsCitys itemss56 = new NewsCitys();itemss56.setTitle("六盘水" );itemss56.setContent(" ");news.add(itemss56);
NewsCitys itemss57 = new NewsCitys();itemss57.setTitle("毕节" );itemss57.setContent(" ");news.add(itemss57);
NewsCitys itemss58 = new NewsCitys();itemss58.setTitle("凯里" );itemss58.setContent(" ");news.add(itemss58);
NewsCitys itemss59 = new NewsCitys();itemss59.setTitle("都匀" );itemss59.setContent(" ");news.add(itemss59);
NewsCitys itemss60 = new NewsCitys();itemss60.setTitle("成都" );itemss60.setContent(" ");news.add(itemss60);
NewsCitys itemss61 = new NewsCitys();itemss61.setTitle("泸州" );itemss61.setContent(" ");news.add(itemss61);
NewsCitys itemss62 = new NewsCitys();itemss62.setTitle("内江" );itemss62.setContent(" ");news.add(itemss62);
NewsCitys itemss63 = new NewsCitys();itemss63.setTitle("凉山" );itemss63.setContent(" ");news.add(itemss63);
NewsCitys itemss64 = new NewsCitys();itemss64.setTitle("阿坝" );itemss64.setContent(" ");news.add(itemss64);
NewsCitys itemss65 = new NewsCitys();itemss65.setTitle("巴中" );itemss65.setContent(" ");news.add(itemss65);
NewsCitys itemss66 = new NewsCitys();itemss66.setTitle("广元" );itemss66.setContent(" ");news.add(itemss66);
NewsCitys itemss67 = new NewsCitys();itemss67.setTitle("乐山" );itemss67.setContent(" ");news.add(itemss67);
NewsCitys itemss68 = new NewsCitys();itemss68.setTitle("绵阳" );itemss68.setContent(" ");news.add(itemss68);
NewsCitys itemss69 = new NewsCitys();itemss69.setTitle("德阳" );itemss69.setContent(" ");news.add(itemss69);
NewsCitys itemss70 = new NewsCitys();itemss70.setTitle("攀枝花" );itemss70.setContent(" ");news.add(itemss70);
NewsCitys itemss71 = new NewsCitys();itemss71.setTitle("雅安" );itemss71.setContent(" ");news.add(itemss71);
NewsCitys itemss72 = new NewsCitys();itemss72.setTitle("宜宾" );itemss72.setContent(" ");news.add(itemss72);
NewsCitys itemss73 = new NewsCitys();itemss73.setTitle("自贡" );itemss73.setContent(" ");news.add(itemss73);
NewsCitys itemss74 = new NewsCitys();itemss74.setTitle("甘孜州" );itemss74.setContent(" ");news.add(itemss74);
NewsCitys itemss75 = new NewsCitys();itemss75.setTitle("达州" );itemss75.setContent(" ");news.add(itemss75);
NewsCitys itemss76 = new NewsCitys();itemss76.setTitle("资阳" );itemss76.setContent(" ");news.add(itemss76);
NewsCitys itemss77 = new NewsCitys();itemss77.setTitle("广安" );itemss77.setContent(" ");news.add(itemss77);
NewsCitys itemss78 = new NewsCitys();itemss78.setTitle("遂宁" );itemss78.setContent(" ");news.add(itemss78);
NewsCitys itemss79 = new NewsCitys();itemss79.setTitle("眉山" );itemss79.setContent(" ");news.add(itemss79);
NewsCitys itemss80 = new NewsCitys();itemss80.setTitle("南充" );itemss80.setContent(" ");news.add(itemss80);
NewsCitys itemss81 = new NewsCitys();itemss81.setTitle("广州" );itemss81.setContent(" ");news.add(itemss81);
NewsCitys itemss82 = new NewsCitys();itemss82.setTitle("深圳" );itemss82.setContent(" ");news.add(itemss82);
NewsCitys itemss83 = new NewsCitys();itemss83.setTitle("潮州" );itemss83.setContent(" ");news.add(itemss83);
NewsCitys itemss84 = new NewsCitys();itemss84.setTitle("韶关" );itemss84.setContent(" ");news.add(itemss84);
NewsCitys itemss85 = new NewsCitys();itemss85.setTitle("湛江" );itemss85.setContent(" ");news.add(itemss85);
NewsCitys itemss86 = new NewsCitys();itemss86.setTitle("惠州" );itemss86.setContent(" ");news.add(itemss86);
NewsCitys itemss87 = new NewsCitys();itemss87.setTitle("清远" );itemss87.setContent(" ");news.add(itemss87);
NewsCitys itemss88 = new NewsCitys();itemss88.setTitle("东莞" );itemss88.setContent(" ");news.add(itemss88);
NewsCitys itemss89 = new NewsCitys();itemss89.setTitle("江门" );itemss89.setContent(" ");news.add(itemss89);
NewsCitys itemss90 = new NewsCitys();itemss90.setTitle("茂名" );itemss90.setContent(" ");news.add(itemss90);
NewsCitys itemss91 = new NewsCitys();itemss91.setTitle("肇庆" );itemss91.setContent(" ");news.add(itemss91);
NewsCitys itemss92 = new NewsCitys();itemss92.setTitle("汕尾" );itemss92.setContent(" ");news.add(itemss92);
NewsCitys itemss93 = new NewsCitys();itemss93.setTitle("河源" );itemss93.setContent(" ");news.add(itemss93);
NewsCitys itemss94 = new NewsCitys();itemss94.setTitle("揭阳" );itemss94.setContent(" ");news.add(itemss94);
NewsCitys itemss95 = new NewsCitys();itemss95.setTitle("梅州" );itemss95.setContent(" ");news.add(itemss95);
//////////////////////////////////
NewsCitys itemsx1 = new NewsCitys();itemsx1.setTitle("中山" );itemsx1.setContent(" ");news.add(itemsx1);
NewsCitys itemsx2 = new NewsCitys();itemsx2.setTitle("德庆" );itemsx2.setContent(" ");news.add(itemsx2);
NewsCitys itemsx3 = new NewsCitys();itemsx3.setTitle("阳江" );itemsx3.setContent(" ");news.add(itemsx3);
NewsCitys itemsx4 = new NewsCitys();itemsx4.setTitle("云浮" );itemsx4.setContent(" ");news.add(itemsx4);
NewsCitys itemsx5 = new NewsCitys();itemsx5.setTitle("珠海" );itemsx5.setContent(" ");news.add(itemsx5);
NewsCitys itemsx6 = new NewsCitys();itemsx6.setTitle("汕头" );itemsx6.setContent(" ");news.add(itemsx6);
NewsCitys itemsx7 = new NewsCitys();itemsx7.setTitle("佛山" );itemsx7.setContent(" ");news.add(itemsx7);
NewsCitys itemsx8 = new NewsCitys();itemsx8.setTitle("南宁" );itemsx8.setContent(" ");news.add(itemsx8);
NewsCitys itemsx9 = new NewsCitys();itemsx9.setTitle("桂林" );itemsx9.setContent(" ");news.add(itemsx9);
NewsCitys itemsx10 = new NewsCitys();itemsx10.setTitle("阳朔" );itemsx10.setContent(" ");news.add(itemsx10);
NewsCitys itemsx11 = new NewsCitys();itemsx11.setTitle("柳州" );itemsx11.setContent(" ");news.add(itemsx11);
NewsCitys itemsx12 = new NewsCitys();itemsx12.setTitle("梧州" );itemsx12.setContent(" ");news.add(itemsx12);
NewsCitys itemsx13 = new NewsCitys();itemsx13.setTitle("玉林" );itemsx13.setContent(" ");news.add(itemsx13);
NewsCitys itemsx14 = new NewsCitys();itemsx14.setTitle("桂平" );itemsx14.setContent(" ");news.add(itemsx14);
NewsCitys itemsx15 = new NewsCitys();itemsx15.setTitle("贺州" );itemsx15.setContent(" ");news.add(itemsx15);
NewsCitys itemsx16 = new NewsCitys();itemsx16.setTitle("钦州" );itemsx16.setContent(" ");news.add(itemsx16);
NewsCitys itemsx17 = new NewsCitys();itemsx17.setTitle("贵港" );itemsx17.setContent(" ");news.add(itemsx17);
NewsCitys itemsx18 = new NewsCitys();itemsx18.setTitle("防城港" );itemsx18.setContent(" ");news.add(itemsx18);
NewsCitys itemsx19 = new NewsCitys();itemsx19.setTitle("百色" );itemsx19.setContent(" ");news.add(itemsx19);
NewsCitys itemsx20 = new NewsCitys();itemsx20.setTitle("北海" );itemsx20.setContent(" ");news.add(itemsx20);
NewsCitys itemsx21 = new NewsCitys();itemsx21.setTitle("河池" );itemsx21.setContent(" ");news.add(itemsx21);
NewsCitys itemsx22 = new NewsCitys();itemsx22.setTitle("来宾" );itemsx22.setContent(" ");news.add(itemsx22);
NewsCitys itemsx23 = new NewsCitys();itemsx23.setTitle("崇左" );itemsx23.setContent(" ");news.add(itemsx23);
NewsCitys itemsx24 = new NewsCitys();itemsx24.setTitle("昆明" );itemsx24.setContent(" ");news.add(itemsx24);
NewsCitys itemsx25 = new NewsCitys();itemsx25.setTitle("保山" );itemsx25.setContent(" ");news.add(itemsx25);
NewsCitys itemsx26 = new NewsCitys();itemsx26.setTitle("楚雄" );itemsx26.setContent(" ");news.add(itemsx26);
NewsCitys itemsx27 = new NewsCitys();itemsx27.setTitle("德宏" );itemsx27.setContent(" ");news.add(itemsx27);
NewsCitys itemsx28 = new NewsCitys();itemsx28.setTitle("红河" );itemsx28.setContent(" ");news.add(itemsx28);
NewsCitys itemsx29 = new NewsCitys();itemsx29.setTitle("临沧" );itemsx29.setContent(" ");news.add(itemsx29);
NewsCitys itemsx30 = new NewsCitys();itemsx30.setTitle("怒江" );itemsx30.setContent(" ");news.add(itemsx30);
NewsCitys itemsx31 = new NewsCitys();itemsx31.setTitle("曲靖" );itemsx31.setContent(" ");news.add(itemsx31);
NewsCitys itemsx32 = new NewsCitys();itemsx32.setTitle("思茅" );itemsx32.setContent(" ");news.add(itemsx32);
NewsCitys itemsx33 = new NewsCitys();itemsx33.setTitle("文山" );itemsx33.setContent(" ");news.add(itemsx33);
NewsCitys itemsx34 = new NewsCitys();itemsx34.setTitle("玉溪" );itemsx34.setContent(" ");news.add(itemsx34);
NewsCitys itemsx35 = new NewsCitys();itemsx35.setTitle("昭通" );itemsx35.setContent(" ");news.add(itemsx35);
NewsCitys itemsx36 = new NewsCitys();itemsx36.setTitle("丽江" );itemsx36.setContent(" ");news.add(itemsx36);
NewsCitys itemsx37 = new NewsCitys();itemsx37.setTitle("大理" );itemsx37.setContent(" ");news.add(itemsx37);
NewsCitys itemsx38 = new NewsCitys();itemsx38.setTitle("海口" );itemsx38.setContent(" ");news.add(itemsx38);
NewsCitys itemsx39 = new NewsCitys();itemsx39.setTitle("三亚" );itemsx39.setContent(" ");news.add(itemsx39);
NewsCitys itemsx40 = new NewsCitys();itemsx40.setTitle("儋州" );itemsx40.setContent(" ");news.add(itemsx40);
NewsCitys itemsx41 = new NewsCitys();itemsx41.setTitle("琼山" );itemsx41.setContent(" ");news.add(itemsx41);
NewsCitys itemsx42 = new NewsCitys();itemsx42.setTitle("通什" );itemsx42.setContent(" ");news.add(itemsx42);
NewsCitys itemsx43 = new NewsCitys();itemsx43.setTitle("文昌" );itemsx43.setContent(" ");news.add(itemsx43);
NewsCitys itemsx44 = new NewsCitys();itemsx44.setTitle("乌鲁木齐" );itemsx44.setContent(" ");news.add(itemsx44);
NewsCitys itemsx45 = new NewsCitys();itemsx45.setTitle("阿勒泰" );itemsx45.setContent(" ");news.add(itemsx45);
NewsCitys itemsx46 = new NewsCitys();itemsx46.setTitle("阿克苏" );itemsx46.setContent(" ");news.add(itemsx46);
NewsCitys itemsx47 = new NewsCitys();itemsx47.setTitle("昌吉" );itemsx47.setContent(" ");news.add(itemsx47);
NewsCitys itemsxs48 = new NewsCitys();itemsxs48.setTitle("哈密" );itemsxs48.setContent(" ");news.add(itemsxs48);
NewsCitys itemsxs49 = new NewsCitys();itemsxs49.setTitle("和田" );itemsxs49.setContent(" ");news.add(itemsxs49);
NewsCitys itemsxs50 = new NewsCitys();itemsxs50.setTitle("喀什" );itemsxs50.setContent(" ");news.add(itemsxs50);
NewsCitys itemsxs51 = new NewsCitys();itemsxs51.setTitle("克拉玛依" );itemsxs51.setContent(" ");news.add(itemsxs51);
NewsCitys itemsxs52 = new NewsCitys();itemsxs52.setTitle("石河子" );itemsxs52.setContent(" ");news.add(itemsxs52);
NewsCitys itemsxs53 = new NewsCitys();itemsxs53.setTitle("塔城" );itemsxs53.setContent(" ");news.add(itemsxs53);
NewsCitys itemsxs54 = new NewsCitys();itemsxs54.setTitle("库尔勒" );itemsxs54.setContent(" ");news.add(itemsxs54);
NewsCitys itemsxs55 = new NewsCitys();itemsxs55.setTitle("吐鲁番" );itemsxs55.setContent(" ");news.add(itemsxs55);
NewsCitys itemsxs56 = new NewsCitys();itemsxs56.setTitle("伊宁" );itemsxs56.setContent(" ");news.add(itemsxs56);
NewsCitys itemsxs57 = new NewsCitys();itemsxs57.setTitle("拉萨" );itemsxs57.setContent(" ");news.add(itemsxs57);
NewsCitys itemsxs58 = new NewsCitys();itemsxs58.setTitle("阿里" );itemsxs58.setContent(" ");news.add(itemsxs58);
NewsCitys itemsxs59 = new NewsCitys();itemsxs59.setTitle("昌都" );itemsxs59.setContent(" ");news.add(itemsxs59);
NewsCitys itemsxs60 = new NewsCitys();itemsxs60.setTitle("那曲" );itemsxs60.setContent(" ");news.add(itemsxs60);
NewsCitys itemsxs61 = new NewsCitys();itemsxs61.setTitle("日喀则" );itemsxs61.setContent(" ");news.add(itemsxs61);
NewsCitys itemsxs62 = new NewsCitys();itemsxs62.setTitle("山南" );itemsxs62.setContent(" ");news.add(itemsxs62);
NewsCitys itemsxs63 = new NewsCitys();itemsxs63.setTitle("林芝" );itemsxs63.setContent(" ");news.add(itemsxs63);
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