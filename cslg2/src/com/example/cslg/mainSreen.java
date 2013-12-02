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
		//��ʼ���ؼ�
		init();
		
		getCustomers();
		//������Ϣ����
				try{
					boolean result = PushManager.getInstance().connect (
					getApplicationContext(),true);    //true��ʾ�Զ�������false��ʾ���Զ�������Ĭ����false 
					}catch(Exception e){}
			
	

		//��ȡ�Ѿ����˺�
		SharedPreferences sharedPreferences = getSharedPreferences("myinfo", Context.MODE_PRIVATE);
		//getString()�ڶ�������Ϊȱʡֵ�����preference�в����ڸ�key��������ȱʡֵ
		if (!sharedPreferences.getString("mynum", "").equals("")) {
			if (sharedPreferences.getString("isautologin", "").equals("true")) {
				//�Զ���½
				txtname.setText(sharedPreferences.getString("mynum", ""));
				txtpwd.setText(sharedPreferences.getString("mypwd", ""));
				chusernum.setChecked(true);
				chuserCode.setChecked(true);
				isConnectInternet();
				if (isConnect==true) {
					   Intent intent = new Intent(mainSreen.this,LoadingActivity.class); 
					      startActivity(intent); 
					//�����֤��������
				shenFenYanzhen();
				}
			}
			else {
//				����ס����
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
		      startActivity(new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS));//���������������ý���  
			}
		});
		
		chusernum.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				
				if (!isChecked) {
				   	SharedPreferences sharedPreferences = mainSreen.this.getSharedPreferences("myinfo", Context.MODE_PRIVATE);
					Editor editor = sharedPreferences.edit();//��ȡ�༭��
				
					editor.putString("mynum", "");
					editor.putString("mypwd", "");
					editor.putString("isremcode","false");
					editor.putString("isautologin","false");
			
				editor.commit();//�ύ�޸�
				
				}
				
				
			}
		});
		
		
		chuserCode.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					chusernum.setChecked(true);
				 	SharedPreferences sharedPreferences = mainSreen.this.getSharedPreferences("myinfo", Context.MODE_PRIVATE);
					Editor editor = sharedPreferences.edit();//��ȡ�༭��
					editor.putString("isremcode","true");
					editor.putString("isautologin","true");
				editor.commit();//�ύ�޸�
				}
				if (!isChecked) {
				   	SharedPreferences sharedPreferences = mainSreen.this.getSharedPreferences("myinfo", Context.MODE_PRIVATE);
					Editor editor = sharedPreferences.edit();//��ȡ�༭��
				
					editor.putString("isautologin","false");
				editor.commit();//�ύ�޸�
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
	
			//�����֤��������
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
 	public static void RememberPassword(mainSreen m) {
		if (m.chusernum.isChecked()&&!m.chuserCode.isChecked()) {
		   	SharedPreferences sharedPreferences = m.getSharedPreferences("myinfo", Context.MODE_PRIVATE);
				Editor editor = sharedPreferences.edit();//��ȡ�༭��
				editor.putString("mynum", m.txtname.getText().toString());
				editor.putString("mypwd", m.txtpwd.getText().toString());
				editor.putString("isremcode","true");
				editor.putString("isautologin","false");
			editor.commit();//�ύ�޸�
			}
		if (m.chuserCode.isChecked()) {
		   	SharedPreferences sharedPreferences = m.getSharedPreferences("myinfo", Context.MODE_PRIVATE);
				Editor editor = sharedPreferences.edit();//��ȡ�༭��
				editor.putString("mynum", m.txtname.getText().toString());
				editor.putString("mypwd", m.txtpwd.getText().toString());
				editor.putString("isremcode","true");
				editor.putString("isautologin","true");
			editor.commit();//�ύ�޸�
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
