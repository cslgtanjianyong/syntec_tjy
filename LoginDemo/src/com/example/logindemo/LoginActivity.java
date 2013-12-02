package com.example.logindemo;

import java.io.StringReader;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.kxml2.kdom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import tools.Process;
import tools.tools;

import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.util.Xml;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import Webservice.*;

public class LoginActivity extends Activity {

	public Button btn_Login, btn_Cancel;
	public EditText text_UserName, text_Password;

	private void Init() {
		btn_Login = (Button) this.findViewById(R.id.bton_Login);
		btn_Cancel = (Button) this.findViewById(R.id.bton_Cancel);
		text_UserName = (EditText) this.findViewById(R.id.text_UserName);
		text_Password = (EditText) this.findViewById(R.id.text_PassWord);	
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		Init();
		btn_Login.setOnClickListener(new View.OnClickListener() {
		Runnable sendable = new Runnable() {
				@Override
				public void run() {
					// TODO Auto-generated method stub
					MyWebServiceHelper myWebServiceHelper = new MyWebServiceHelper();
					Object result = myWebServiceHelper.getResult();
					String xmlStr =result.toString();				    
				    Process process=tools.xmlToProcess(xmlStr);
				}
			};
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
//				new Thread(sendable).start();
				tools.userId=text_UserName.getText().toString();
				Intent intent=new Intent(LoginActivity.this,WorkitemActivity.class);
				startActivity(intent);
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
