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
public class Loginloading extends Activity {

public static List<Page> pages= new ArrayList<Page>();
public void init() 
{//��ʼ���ؼ�

}	

	
protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.extendpage);
		AppManager.getAppManager().addActivity(this);
		//��ʼ���ؼ�
		init();
		//�����¼�
	
		
}}