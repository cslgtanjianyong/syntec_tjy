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

import android.net.ConnectivityManager;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
public class Extendpage extends Activity {
	private boolean isConnect=false;
public static List<Page> pages= new ArrayList<Page>();
public void init() 
{//初始化控件

}	

	
protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.extendpage);
		AppManager.getAppManager().addActivity(this);
		//初始化控件
		init();
		//搜索事件
	
		
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
}}