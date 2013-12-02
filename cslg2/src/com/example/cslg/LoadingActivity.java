package com.example.cslg;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cslg.tools.AppManager;

public class LoadingActivity extends Activity{
private TextView sdsd;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);	
		setContentView(R.layout.loading);
		AppManager.getAppManager().addActivity(this);
		sdsd=(TextView)findViewById(R.id.sdsdds);
		
		
		sdsd.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
			
				
			}
		});

		
		
		
		TimerTask task = new TimerTask(){   
		    public void run(){
		    	LoadingActivity.this.finish();
		    
		    }   
		};   
		Timer timer = new Timer(); 
		timer.schedule(task, 5000); 
		
//		Toast toast = Toast.makeText(LoadingActivity.this.getApplicationContext(),
//    			"网络异常，请重试!", Toast.LENGTH_LONG);
//    			toast.setGravity(Gravity.CENTER, 0, 0);
//    			toast.show();
//		
		
   }
	public static void finishmyselsf() {
		
		AppManager.getAppManager().finishActivity(LoadingActivity.class);
	}
	
	
	
 	public Boolean  isConnectInternet() {
 		 boolean isConnect=false;
		ConnectivityManager con = (ConnectivityManager) getSystemService(Activity.CONNECTIVITY_SERVICE);
		boolean wifi = con.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
				.isConnectedOrConnecting();
		boolean internet = con.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
				.isConnectedOrConnecting();
		if (wifi || internet) {
		isConnect=true;
			
		} else {isConnect=false;
		// 新建提示框提示消息
			

		}
		return isConnect;
		 
	}
 	public  void alertinternrt() {
 		
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