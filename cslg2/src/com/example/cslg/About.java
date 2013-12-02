package com.example.cslg;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import com.example.cslg.tools.*;
import com.example.cslg.R;
import com.example.source.Page;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
public class About extends Activity {
	private	TextView textsoft;
	private ImageView	imageViewabout;
	private Button	btn_closezbout;
public static List<Page> pages= new ArrayList<Page>();
public void init() 
{//初始化控件
	textsoft=(TextView)findViewById(R.id.textsoft);
	imageViewabout=(ImageView)findViewById(R.id.imageViewabout);
	btn_closezbout=(Button)findViewById(R.id.btn_closezbout);
}	

	
protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.about);
		AppManager.getAppManager().addActivity(this);
		//初始化控件
		init();
		btn_closezbout.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
About.this.finish();
			}
		});
		
		
		//搜索事件
		imageViewabout.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
Intent intent = new Intent(About.this, Softbook.class);
				
				startActivity(intent);
			}
		});
		textsoft.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
Intent intent = new Intent(About.this, Softbook.class);
				
				startActivity(intent);
			}
		});
		
}}