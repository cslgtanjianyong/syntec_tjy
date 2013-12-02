package com.example.cslg;


import java.util.ArrayList;
import java.util.List;

import android.R.integer;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.TableLayout;

import com.example.Thread.MyAsyncLogin;
import com.example.cslg.tools.AppManager;
import com.example.cslg.tools.SharedPreferanceUtil;
import com.example.cslg.tools.tools;
import com.example.source.Customer;
import com.joboevan.push.tool.PushManager;
public class mainSreen extends Activity {
public Button btnlogin;
public Button btnsetinternet;
public EditText txtname;
public EditText txtpwd;

public CheckBox chuserCode;
public CheckBox chusernum;
public int width;
private boolean isConnect=false;
private SharedPreferanceUtil util;
public static boolean f=false;
public void init() 
{
	
		
	txtname=(EditText)findViewById(R.id.page1_login_user_edit);
	txtpwd=(EditText)findViewById(R.id.page1_login_passwd_edit);
	
	btnsetinternet=(Button)findViewById(R.id.btnsetinternet);
	btnlogin=(Button)findViewById(R.id.btnlogin);
	chuserCode=(CheckBox)findViewById(R.id.page1_checbox2);
	chusernum=(CheckBox)findViewById(R.id.page1_checbox1);

}	
public   void getCustomers()
{
	SharedPreferences settings = getSharedPreferences("Customers", 0);
	List<Customer> customers=new ArrayList<Customer>();
	for(int i=0;i<settings.getInt("num", 0);i++)
	{
		Customer customer=new Customer();
		customer.setCustomerName(settings.getString("customername"+i, null));
		customer.setCustomerRemark(settings.getString("customerRemark"+i, null));
		customer.setCustomerId(settings.getString("customerid"+i, null));
		customer.setCustomerAddress(settings.getString("customerAddress"+i, null));
		customer.setClinkTell(settings.getString("ClinkTell"+i, null));
		customer.setcLinkname(settings.getString("cLinkname"+i, null));
		customer.setId(settings.getInt("id"+i, 0));	
		customer.setProvince(settings.getString("customerSF"+i, null));
		customer.setCity(settings.getString("customerCity"+i, null));
		customer.setBizType(settings.getString("customerBusinessType"+i, null));
		customer.setCredit(settings.getInt("customercredit"+i, 0));
		customers.add(customer);

		
	}
	tools.customers=customers;
}
@Override
	
protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main);
		AppManager.getAppManager().addActivity(this);
		//初始化控件
		init();
		
		getCustomers();
		//开启消息推送
				try{
					boolean result = PushManager.getInstance().connect (
					getApplicationContext(),true);    //true表示自动重连，false标示不自动重连，默认是false 
					}catch(Exception e){}
			
	

		//读取已经存账号
		SharedPreferences sharedPreferences = getSharedPreferences("myinfo", Context.MODE_PRIVATE);
		//getString()第二个参数为缺省值，如果preference中不存在该key，将返回缺省值
		if (!sharedPreferences.getString("mynum", "").equals("")) {
			if (sharedPreferences.getString("isautologin", "").equals("true")) {
				//自动登陆
				txtname.setText(sharedPreferences.getString("mynum", ""));
				txtpwd.setText(sharedPreferences.getString("mypwd", ""));
				chusernum.setChecked(true);
				chuserCode.setChecked(true);
				isConnectInternet();
				if (isConnect==true) {
					   Intent intent = new Intent(mainSreen.this,LoadingActivity.class); 
					      startActivity(intent); 
					//身份验证方法调用
				shenFenYanzhen();
				}
			}
			else {
//				、记住密码
					txtname.setText(sharedPreferences.getString("mynum", ""));
			txtpwd.setText(sharedPreferences.getString("mypwd", ""));
			chusernum.setChecked(true);
			chuserCode.setChecked(false);
			}
		
		}
			
		btnsetinternet.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent ; 
		      startActivity(new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS));//进入无线网络配置界面  
			}
		});
		
		chusernum.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				
				if (!isChecked) {
				   	SharedPreferences sharedPreferences = mainSreen.this.getSharedPreferences("myinfo", Context.MODE_PRIVATE);
					Editor editor = sharedPreferences.edit();//获取编辑器
				
					editor.putString("mynum", "");
					editor.putString("mypwd", "");
					editor.putString("isremcode","false");
					editor.putString("isautologin","false");
			
				editor.commit();//提交修改
				
				}
				
				
			}
		});
		
		
		chuserCode.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					chusernum.setChecked(true);
				 	SharedPreferences sharedPreferences = mainSreen.this.getSharedPreferences("myinfo", Context.MODE_PRIVATE);
					Editor editor = sharedPreferences.edit();//获取编辑器
					editor.putString("isremcode","true");
					editor.putString("isautologin","true");
				editor.commit();//提交修改
				}
				if (!isChecked) {
				   	SharedPreferences sharedPreferences = mainSreen.this.getSharedPreferences("myinfo", Context.MODE_PRIVATE);
					Editor editor = sharedPreferences.edit();//获取编辑器
				
					editor.putString("isautologin","false");
				editor.commit();//提交修改
				}
				
				
			}
		});
btnlogin.setOnClickListener(new View.OnClickListener() {

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		 
		isConnectInternet();
		
		if (isConnect==true) { 
			Intent intent = new Intent(mainSreen.this,LoadingActivity.class); 
		      startActivity(intent); 
	
			//身份验证方法调用
		shenFenYanzhen();
		}

		
		
	}


});

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
		// 新建提示框提示消息
			new AlertDialog.Builder(this)
					.setTitle("网络提示")
					.setMessage("未检测到网络，请检查网络！")
					.setPositiveButton("确定",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int which) {
								return;
								}
							})

					.show();

		}
	}
 	public static void RememberPassword(mainSreen m) {
		if (m.chusernum.isChecked()&&!m.chuserCode.isChecked()) {
		   	SharedPreferences sharedPreferences = m.getSharedPreferences("myinfo", Context.MODE_PRIVATE);
				Editor editor = sharedPreferences.edit();//获取编辑器
				editor.putString("mynum", m.txtname.getText().toString());
				editor.putString("mypwd", m.txtpwd.getText().toString());
				editor.putString("isremcode","true");
				editor.putString("isautologin","false");
			editor.commit();//提交修改
			}
		if (m.chuserCode.isChecked()) {
		   	SharedPreferences sharedPreferences = m.getSharedPreferences("myinfo", Context.MODE_PRIVATE);
				Editor editor = sharedPreferences.edit();//获取编辑器
				editor.putString("mynum", m.txtname.getText().toString());
				editor.putString("mypwd", m.txtpwd.getText().toString());
				editor.putString("isremcode","true");
				editor.putString("isautologin","true");
			editor.commit();//提交修改
			}
		
		
		
		   m.finish();
	}
 	
 	
	private void shenFenYanzhen() {
		f=false;
		
		MyAsyncLogin myAsyncLogin=new MyAsyncLogin(txtname.getText().toString(), txtpwd.getText().toString(), "3",mainSreen.this);
		myAsyncLogin.execute();
		

		
			if(f)
			{
				Intent intent = new Intent(mainSreen.this,Mainmenu.class); 
		        startActivity(intent); 
		       
			}
			

	

	
	}
 	
}
