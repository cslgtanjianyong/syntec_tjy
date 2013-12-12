package com.example.logindemo;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
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
import android.R.string;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.util.Log;
import android.util.Xml;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import Webservice.*;

public class WorkitemActivity extends Activity {

	public TextView text_userId;
	public ListView workingProcesslist;
	private void Init() {
		workingProcesslist=(ListView)this.findViewById(R.id.list_processShow);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_workitem);
		Init();

		
		MyWebServiceHelper myWebServiceHelper=new MyWebServiceHelper();
		String xmlStr=myWebServiceHelper.getToDoWorkItem().toString();
		Log.i("2222222222222",xmlStr);
		if(xmlStr.equals("<list/>")){		
				  Dialog alertDialog = new AlertDialog.Builder(this). 
			                 setTitle("提示"). 
			                 setMessage("没有进行中的流程或者网络异常请"). 
			                 setIcon(R.drawable.ic_launcher). 
			                 create(); 
			         alertDialog.show(); 
		
		
		}
		else
		{
			tools.processWorkingList=tools.xmlToListProcess(xmlStr);//取到流程列表list
			String id=String.valueOf(tools.processWorkingList.size());
		
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	  


}
