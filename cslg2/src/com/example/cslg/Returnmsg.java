package com.example.cslg;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.Thread.MyAsyncReturnMsg;
import com.example.cslg.tools.*;
import com.example.cslg.R;
import com.example.source.KuCunInfo;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
public class Returnmsg extends Activity {
public static Boolean b=false;
public  static String  contentString;;
private Button btnsendButton;
private EditText  page1_login_user_editmsgcontent;
public static String flag;
private EditText txtcompanymsg;
public void init() 
{//初始化控件
	btnsendButton=(Button)findViewById(R.id.btnsendmsg);
	page1_login_user_editmsgcontent=(EditText)findViewById(R.id.page1_login_user_editmsgcontent);
	txtcompanymsg=(EditText)findViewById(R.id.txtcompanymsg);
}	

	
protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.returnmsg);
		AppManager.getAppManager().addActivity(this);
		init();
		getIntent();
	    txtcompanymsg.setText(contentString);
	    
		AppManager.getAppManager().addActivity(this);
		//初始化控件
		btnsendButton.setOnTouchListener(new View.OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
								if (page1_login_user_editmsgcontent.getText().toString()==null||page1_login_user_editmsgcontent.getText().toString()=="") {
					Toast toast = Toast.makeText(Returnmsg.this.getApplicationContext(),"请输入反馈内容？！", Toast.LENGTH_SHORT);
			    			toast.setGravity(Gravity.CENTER, 0, 0);
			    			toast.show();
			    			return false;
				}
				if(event.getAction() == MotionEvent.ACTION_DOWN){  
		        	 		        	//确认反馈信息不可以为空
		        		        	    //网络数据等待界面
		      	  		        }else if(event.getAction() == MotionEvent.ACTION_UP){  
		        	
		        	if (page1_login_user_editmsgcontent.getText().toString()==null||page1_login_user_editmsgcontent.getText().toString()=="") {
						
		        		Toast toast = Toast.makeText(Returnmsg.this.getApplicationContext(),
				    			"请输入反馈内容？！", Toast.LENGTH_SHORT);
				    			toast.setGravity(Gravity.CENTER, 0, 0);
				    			toast.show();
				    			return false;
					}
				 Intent intentw = new Intent(Returnmsg.this,LoadingActivity.class); 
		         startActivity(intentw); 
		        	
		    		MyAsyncReturnMsg myAsyncReturnMsg=new MyAsyncReturnMsg(flag,page1_login_user_editmsgcontent.getText().toString().trim(),Returnmsg.this);
					myAsyncReturnMsg.execute();
				
					savehistory("反馈通知");
					try{
																//获取json中的flag值并且进行判断
				          										} 
					catch(Exception ex){
					
						ex.printStackTrace();
					}	
					
		        	
		        }
				return false;
			}
		});

		
	
		
} public  void savehistory(String operationname) {
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
	 
	
}}