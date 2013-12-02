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
{//��ʼ���ؼ�
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
		//��ʼ���ؼ�
		btnsendButton.setOnTouchListener(new View.OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
								if (page1_login_user_editmsgcontent.getText().toString()==null||page1_login_user_editmsgcontent.getText().toString()=="") {
					Toast toast = Toast.makeText(Returnmsg.this.getApplicationContext(),"�����뷴�����ݣ���", Toast.LENGTH_SHORT);
			    			toast.setGravity(Gravity.CENTER, 0, 0);
			    			toast.show();
			    			return false;
				}
				if(event.getAction() == MotionEvent.ACTION_DOWN){  
		        	 		        	//ȷ�Ϸ�����Ϣ������Ϊ��
		        		        	    //�������ݵȴ�����
		      	  		        }else if(event.getAction() == MotionEvent.ACTION_UP){  
		        	
		        	if (page1_login_user_editmsgcontent.getText().toString()==null||page1_login_user_editmsgcontent.getText().toString()=="") {
						
		        		Toast toast = Toast.makeText(Returnmsg.this.getApplicationContext(),
				    			"�����뷴�����ݣ���", Toast.LENGTH_SHORT);
				    			toast.setGravity(Gravity.CENTER, 0, 0);
				    			toast.show();
				    			return false;
					}
				 Intent intentw = new Intent(Returnmsg.this,LoadingActivity.class); 
		         startActivity(intentw); 
		        	
		    		MyAsyncReturnMsg myAsyncReturnMsg=new MyAsyncReturnMsg(flag,page1_login_user_editmsgcontent.getText().toString().trim(),Returnmsg.this);
					myAsyncReturnMsg.execute();
				
					savehistory("����֪ͨ");
					try{
																//��ȡjson�е�flagֵ���ҽ����ж�
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
	 
	
}}