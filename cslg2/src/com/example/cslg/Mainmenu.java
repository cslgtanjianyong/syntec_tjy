package com.example.cslg;

import java.io.BufferedReader;

import java.io.InputStream;
import java.io.InputStreamReader;

import java.io.Serializable;

import java.net.URLConnection;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;


import android.app.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;

import android.widget.Button;

import android.widget.ImageButton;
import android.widget.ImageView;

import android.widget.TextView;
import android.widget.Toast;

import com.example.Thread.MyAsyncgetWeather;
import com.example.cslg.tools.AppManager;
import com.example.cslg.tools.Applicationtool;

import com.example.cslg.tools.HistoryOPlist;
import com.example.cslg.tools.MenuitemList;
import com.example.cslg.tools.Weatherlist;
 






public class Mainmenu extends Activity {

	private ImageButton ImageButton02;
	private ImageButton 	ImageButton01;
	private Button btnzhuxiao;

	
	private String state;
	
	private TextView  history1, history2, history3;

    private TextView txtcity;
	private List<HashMap<String, Object>> meumList = new ArrayList<HashMap<String, Object>>();
	private static List<HistoryOPlist> hisOPlists=new ArrayList<HistoryOPlist>();
	private static List<MenuitemList> menuitemLists=new ArrayList<MenuitemList>();
	private List<Weatherlist> wList=new ArrayList<Weatherlist>();
	private Button btnweather;
	private String cityitemname;
	private ImageView imgcompangmsg;
	private ImageView imgproductyouhui;
	private ImageView imgdrugselect;
	private ImageView imgmytask;
	private ImageView imgjobdetials;
	private ImageView imgpersoninfo;
	private ImageView imgsystemset;

	StringBuffer sb;
	URLConnection conn = null;
	InputStream fis = null;
	InputStreamReader in = null;
	BufferedReader buffer = null;
	public void init() {// 初始化控件
	
		imgcompangmsg=(ImageView)findViewById(R.id.imgcompangmsg);
		imgproductyouhui=(ImageView)findViewById(R.id.imgproductyouhui);
		imgdrugselect=(ImageView)findViewById(R.id.imgdrugselect);
		imgmytask=(ImageView)findViewById(R.id.imgmytask);
		imgjobdetials=(ImageView)findViewById(R.id.imgjobdetials);
		imgpersoninfo=(ImageView)findViewById(R.id.imgpersoninfo);
		imgsystemset=(ImageView)findViewById(R.id.imgsystemset);
		ImageButton02=(ImageButton)findViewById(R.id.ImageButton02);
		ImageButton01=(ImageButton)findViewById(R.id.ImageButton01);
		
		history1 = (TextView) findViewById(R.id.history1);
		history2 = (TextView) findViewById(R.id.history2);
		history3 = (TextView) findViewById(R.id.history3);
		txtcity= (TextView) findViewById(R.id.txtcity);
		btnweather=(Button) findViewById(R.id.btnweather);
		btnzhuxiao=(Button) findViewById(R.id.btnzhuxiao);
	
	
	}

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.mainmenu);
		AppManager.getAppManager().addActivity(this);
// 初始化控件
		init();
		
		gethistory() ;
		try {
			Bundle extras = getIntent().getExtras(); 

			cityitemname=extras.getString("cityitemname"); 
			if (cityitemname.equals("cityname")) {
				//读取xml
				SharedPreferences sharedPreferences = getSharedPreferences("city", Context.MODE_PRIVATE);
				//getString()第二个参数为缺省值，如果preference中不存在该key，将返回缺省值
				if (sharedPreferences.getString("cityname", "").equals("")) {
					txtcity.setText("北京");
				}
				else {String jString=sharedPreferences.getString("cityname", "");
					txtcity.setText(sharedPreferences.getString("cityname", ""));	
				}
								
			}
			else {
				txtcity.setText(cityitemname);
				SharedPreferences sharedPreferences = getSharedPreferences("city", Context.MODE_PRIVATE);
				Editor editor = sharedPreferences.edit();//获取编辑器
				editor.putString("cityname", cityitemname);
				
				editor.commit();//提交修改
			}
		} catch (Exception e) {
			txtcity.setText("北京");
			SharedPreferences sharedPreferences = getSharedPreferences("city", Context.MODE_PRIVATE);
			//getString()第二个参数为缺省值，如果preference中不存在该key，将返回缺省值
			
			txtcity.setText(sharedPreferences.getString("cityname", ""));
			e.printStackTrace();
		}
		      
	       
//		SimpleDateFormat formatter=new SimpleDateFormat("yyyy年MM月dd日    HH:mm:ss");       
//		Date curDate=new Date(System.currentTimeMillis());//获取当前时间       
//		String str=formatter.format(curDate);    
		
		//获取天气信息
	
		 MyAsyncgetWeather task = new MyAsyncgetWeather(txtcity.getText().toString(),wList,this,"mainform");  
         task.execute(); 
         btnzhuxiao.setOnClickListener(new View.OnClickListener() {
 			@Override
 			//注销登录
 			public void onClick(View v) {
 				// TODO Auto-generated method stub
 				
 				Intent intent22 = new Intent(Mainmenu.this,
 						Applicationzhuxiao.class);
 				
 				startActivity(intent22);
 			
 			
 			}
 		});
         ImageButton01.setOnClickListener(new View.OnClickListener() {
 			@Override
 			public void onClick(View v) {
 				// TODO Auto-generated method stub
 			
 				Intent intent22 = new Intent(Mainmenu.this,
 						OperationHistory.class);
 				
 				startActivity(intent22);
 				
 				overridePendingTransition(R.anim.push_up_in,
						R.anim.push_up_out);
 			
 				
 				Mainmenu.this.finish();
 			
 			
 			}
 		});
		ImageButton02.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			
				Intent intent22 = new Intent(Mainmenu.this,
						Notebook.class);
				
				startActivity(intent22);
				overridePendingTransition(R.anim.push_up_in,
						R.anim.push_up_out);
				
				savehistory("最近备忘");
				
				Mainmenu.this.finish();
			
			
			}
		});
	
		//初始化按钮侦听事件
		imgcompangmsg.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				HistoryOPlist hisop = new HistoryOPlist();
				hisop.setOPlistname("公司通知");
				hisOPlists.add(hisop);
				Intent intent22 = new Intent(Mainmenu.this,
						CompanyMsg.class);
				
				startActivity(intent22);
				overridePendingTransition(R.anim.fade, R.anim.hold);
				savehistory("公司通知");
				Mainmenu.this.finish();
			}
		});
		imgproductyouhui.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				HistoryOPlist hisop = new HistoryOPlist();
				hisop.setOPlistname("产品优惠");
				hisOPlists.add(hisop);
				
				Intent intent1 = new Intent(Mainmenu.this, YouhuiInfo.class);
				startActivity(intent1);
				overridePendingTransition(R.anim.fade, R.anim.hold);
				savehistory("产品优惠");
				Mainmenu.this.finish();
			}
		});
		imgdrugselect.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				HistoryOPlist hisop = new HistoryOPlist();
				hisop.setOPlistname("药品查询");
				hisOPlists.add(hisop);
				
				Intent intent11 = new Intent(Mainmenu.this, DrugInfo.class);
			
				startActivity(intent11);
				overridePendingTransition(R.anim.fade, R.anim.hold);
				savehistory("药品查询");
				Mainmenu.this.finish();
			}
		});
		imgmytask.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				HistoryOPlist hisop = new HistoryOPlist();
				hisop.setOPlistname("我的任务");
				hisOPlists.add(hisop);
						Intent intent3 = new Intent(Mainmenu.this, Mytask.class);
				startActivity(intent3);
				overridePendingTransition(R.anim.fade, R.anim.hold);
				savehistory("我的任务");
				Mainmenu.this.finish();
			}
		});
		imgjobdetials.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				HistoryOPlist hisop = new HistoryOPlist();
				hisop.setOPlistname("工作记录");
				hisOPlists.add(hisop);
							Intent intent33 = new Intent(Mainmenu.this, OPhistory.class);
				startActivity(intent33);
				overridePendingTransition(R.anim.fade, R.anim.hold);
				savehistory("工作记录");
				Mainmenu.this.finish();
			}
		});
		imgpersoninfo.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				HistoryOPlist hisop = new HistoryOPlist();
				hisop.setOPlistname("个人信息");
				hisOPlists.add(hisop);
							Intent intent = new Intent(Mainmenu.this,
						PersonalInfo.class);
				state = "0";
				intent.putExtra("state", state);
				startActivity(intent);
				overridePendingTransition(R.anim.fade, R.anim.hold);
				savehistory("个人信息");
				Mainmenu.this.finish();
			}
		});

		imgsystemset.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				HistoryOPlist hisop = new HistoryOPlist();
				hisop.setOPlistname("系统设置");
				hisOPlists.add(hisop);
								Intent intent = new Intent(Mainmenu.this,
						Systemset.class);
				
				startActivity(intent);
				overridePendingTransition(R.anim.fade, R.anim.hold);
				savehistory("系统设置");
				Mainmenu.this.finish();
			}
		});
		
		btnweather.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				if (wList!=null) {
					
					
							Intent intent = new Intent(Mainmenu.this, Weather.class);
							
							intent.putExtra("txtcitymainmenu",txtcity.getText().toString());
							intent.putExtra("wList", (Serializable)wList);
							
							startActivity(intent);
							overridePendingTransition(R.anim.push_up_in,
									R.anim.push_up_out);
					
					
				}
				else {
					Weatherlist s=new Weatherlist();
					s.set_level("请更新天气信息");
					s.set_uptime("请更新天气信息");
					s.set_wendu("请更新天气信息");
					Intent intent = new Intent(Mainmenu.this, Weather.class);
					
					
					intent.putExtra("wList", (Serializable)wList);
					
					startActivity(intent);
					overridePendingTransition(R.anim.push_up_in,
							R.anim.push_up_out);
				
				}
				savehistory("查询天气");
				
			}
		});
		
		history1.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String valueString=history1.getText().toString();
				menuitemlistmethod(valueString);
				
			}
		});
		history2.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String valueString=history2.getText().toString();
				menuitemlistmethod(valueString);
				
			}
		});
		history3.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String valueString=history3.getText().toString();
				menuitemlistmethod(valueString);
				
			}
		});


		SharedPreferences sharedPreferences = getSharedPreferences("isonelogin", Context.MODE_PRIVATE);
		//getString()第二个参数为缺省值，如果preference中不存在该key，将返回缺省值
		String iSonelogin=sharedPreferences.getString("islogin", "");
		if (iSonelogin=="") {
			//第一次登陆
			Intent intent = new Intent(Mainmenu.this,SearchCity.class);
				startActivity(intent);
				Toast toast = Toast.makeText(getApplicationContext(),
					     "请输入城市", Toast.LENGTH_LONG);
					   toast.setGravity(Gravity.CENTER, 0, 0);
					   toast.show();
				Mainmenu.this.finish();
			
				
		}
		else {if (iSonelogin=="isinstall") {
			//cityname
		}}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)// 主要是对这个函数的复写
	{
		// TODO Auto-generated method stub

		if ((keyCode == KeyEvent.KEYCODE_BACK)
				&& (event.getAction() == KeyEvent.ACTION_DOWN)) {
			Intent intent = new Intent(Mainmenu.this, ExitApplication.class);
			startActivity(intent);

			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	private void menuitemlistmethod(String valueString) {
		int x=8;
		for (MenuitemList item : menuitemLists) {
			if (item.get_menuitem().equals(valueString)) {
				x=item.get_menuitemID();
				break;
			}
		}
		
		switch (x) {
		case 0:
		
			Intent intent22 = new Intent(Mainmenu.this,
					CompanyMsg.class);
			overridePendingTransition(R.layout.push_left_out,
					R.layout.push_left_in);
			startActivity(intent22);
			Mainmenu.this.finish();
			break;
		case 1:
			
			Intent intent1 = new Intent(Mainmenu.this, YouhuiInfo.class);
			startActivity(intent1);
			Mainmenu.this.finish();
			break;
		case 2:
			
			Intent intent11 = new Intent(Mainmenu.this, DrugInfo.class);
			overridePendingTransition(R.layout.push_left_out,
					R.layout.push_left_in);
			startActivity(intent11);
			Mainmenu.this.finish();
			break;
		case 3:

			Intent intent3 = new Intent(Mainmenu.this, Mytask.class);

			startActivity(intent3);
			Mainmenu.this.finish();
			break;
		case 4:
			
			Intent intent33 = new Intent(Mainmenu.this, OPhistory.class);
			startActivity(intent33);
			Mainmenu.this.finish();
			break;
		case 5:
		
			Intent intent = new Intent(Mainmenu.this,
					PersonalInfo.class);
			state = "0";
			intent.putExtra("state", state);
			startActivity(intent);
			Mainmenu.this.finish();
			break;
		default:
			break;

		}
	}
//////////////////////////////////
	public void gethistory() {
		
		 SharedPreferences sharedPreferencesget = getSharedPreferences("historys", Context.MODE_PRIVATE);
			//getString()第二个参数为缺省值，如果preference中不存在该key，将返回缺省值
			String lastname=sharedPreferencesget.getString("operationname", "");
			if (lastname=="") {
				history1.setText("浏览历史");
				history2.setText("浏览历史");
				history3.setText("浏览历史");
			}
			else {
				String[] res=lastname.split("@");
				if (res.length>=4) {
					history1.setText(res[res.length-1]);
					history2.setText(res[res.length-2]);
					history3.setText(res[res.length-3]);
				}
				if (res.length==3) {
					history1.setText(res[1]);
					history2.setText(res[0]);
					history3.setText("浏览历史");
				}
				
				if (res.length==2) {
					history1.setText(res[0]);
					history2.setText("浏览历史");
					history3.setText("浏览历史");
				}
				
				
				
			}
			
		
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
	public  void torst() {
		
		Toast toast = Toast.makeText(getApplicationContext(),
    			"网络超时,请重试！", Toast.LENGTH_LONG);
    			toast.setGravity(Gravity.CENTER, 0, 0);
    			toast.show();	
		
	}
}