package com.example.cslg;

	import com.example.cslg.tools.AppManager;

import android.app.Activity; 
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
	import android.os.Bundle; 
	
	import android.view.View; 
	import android.view.View.OnClickListener; 
import android.widget.Button;
	
	public class ExitApplication extends Activity { 

	private Button btnexit;
	private Button btncancer;
	private void exitapp() {
		AppManager.getAppManager().AppExit(this);
	}
	@Override 
	protected void onCreate(Bundle savedInstanceState) { 
	super.onCreate(savedInstanceState); 
	setContentView(R.layout.exitapplication); 
	AppManager.getAppManager().addActivity(this);
	//初始化控件
	init();

	//退出程序
btnexit.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		exitapp();
	}

	
});

 //取消退出程序

btncancer.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
	
		ExitApplication.this.finish();
	}
});

	
	
	
	}
	public void btncancer(View v) { 
		this.finish(); 
		} 
	private void init() {
		btnexit=(Button)findViewById(R.id.exitExit);
		btncancer=(Button)findViewById(R.id.exitCancerExit);
		
	} 
	} 