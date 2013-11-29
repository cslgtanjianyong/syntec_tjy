package com.example.logindemo;


import java.util.List;

import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import Webservice.*;
public class MainActivity extends Activity {

	public Button btn_Login,btn_Cancel;
	public EditText text_UserName,text_Password;
	private void Init()
	{
		 btn_Login=(Button)this.findViewById(R.id.bton_Login);
		 btn_Cancel=(Button)this.findViewById(R.id.bton_Cancel);
		 text_UserName=(EditText)this.findViewById(R.id.text_UserName);
		 text_Password=(EditText)this.findViewById(R.id.text_PassWord);
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Init();
		btn_Login.setOnClickListener(new View.OnClickListener() {
		Runnable sendable = new Runnable() {		
				    @Override	
				    public void run() {	
				        // TODO Auto-generated method stub	
				    	MyWebServiceHelper myWebServiceHelper=new MyWebServiceHelper();
				    	myWebServiceHelper.getLoginInformation();
				    }
				
				};
	
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new Thread(sendable).start();		 
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
