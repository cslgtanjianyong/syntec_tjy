package com.example.cslg;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;

import com.example.Thread.MyAsyncSendEmail;
import com.example.cslg.tools.AppManager;
import com.example.cslg.tools.MailSenderInfo;
import com.example.cslg.tools.SimpleMailSender;
import com.example.source.Page;
public class Helpandreturn extends Activity {
private MultiAutoCompleteTextView editsupport;
private EditText editphonenumber;
private EditText editmailaddress;
private Button send; 
private Button btn_backmainhelpandreturn;
public static List<Page> pages= new ArrayList<Page>();
private String softreturnmsg=" ";
public void init() 
{//初始化控件
	editsupport=(MultiAutoCompleteTextView)findViewById(R.id.editsupport);
	editphonenumber=(EditText)findViewById(R.id.editphonenumber);
	editmailaddress=(EditText)findViewById(R.id.editmailaddress);
	send = (Button)findViewById(R.id.sendmail); 
	btn_backmainhelpandreturn = (Button)findViewById(R.id.btn_backmainhelpandreturn); 
}	

	
protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.helpandreturn);
		AppManager.getAppManager().addActivity(this);
		//初始化控件
		init();
		btn_backmainhelpandreturn.setOnClickListener(new View.OnClickListener() 
        {
            @Override 
            public void onClick(View v) 
            { 
            	Helpandreturn.this.finish();                 
             
            } 
        }); 
		//搜索事件
		  send.setOnClickListener(new View.OnClickListener() 
	        {
	            @Override 
	            public void onClick(View v) 
	            { 
	            	 Intent intentw = new Intent(Helpandreturn.this,LoadingActivity.class); 
			         startActivity(intentw); 
	            	softreturnmsg="用户意见和建议:"+editsupport.getText().toString()+"\n"+"用户联系方式:"+editphonenumber.getText().toString()+"\n"+"用户邮箱："+editmailaddress.getText().toString();
	            	
	            	MyAsyncSendEmail sendemai=new MyAsyncSendEmail("smtp.qq.com","25","1442718753@qq.com","469850618718","1442718753@qq.com","cslgsoftteam@163.com","惠好制药移动应用系统用户反馈！",softreturnmsg,Helpandreturn.this);
	            	sendemai.execute();
	                // TODO Auto-generated method stub                    
	             
	            } 
	        }); 
		
}}