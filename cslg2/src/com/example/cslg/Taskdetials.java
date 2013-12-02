package com.example.cslg;

import com.example.cslg.tools.AppManager;
import com.example.cslg.tools.tools;

import android.app.Activity; 
import android.content.Intent;
	import android.os.Bundle; 
	
	import android.view.View; 
import android.view.Window;
	import android.view.View.OnClickListener; 
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

	public class Taskdetials extends Activity { 
private TextView taskcontenText;
private TextView txtvisitorname;
private TextView edittaskbeizh;
	private Button btnsubmitvisit;
	private Button	btn_backtaskdetials;
	private String customerid=" ";
	private String customername=" ";
	private String linkname;
	private String linkadress;
	private String linktel;
	private String linkbeizhu;
	
	private String customerSF;
	private String customerCity;
	private String customerBusinessType;
	private int customercredit;
	@Override 
	protected void onCreate(Bundle savedInstanceState) { 
	super.onCreate(savedInstanceState); 
	this.requestWindowFeature(Window.FEATURE_NO_TITLE);
	setContentView(R.layout.taskdetials); 
	AppManager.getAppManager().addActivity(this);
	//初始化控件
	init();
	Bundle extras = getIntent().getExtras(); 
    customerid=extras.getString("customerid");
    customername=extras.getString("customername");
    linkname=extras.getString("linkname");
    linkadress=extras.getString("linkadress");
    linktel=extras.getString("linktel");
    linkbeizhu=extras.getString("linkbeizhu");

    customerSF=extras.getString("customerSF");
    customerCity=extras.getString("customerCity");
    customerBusinessType=extras.getString("customerBusinessType");
    customercredit=extras.getInt("customercredit");


    final String[] resStrings=customername.split("：");
    if(resStrings[1]==null) {return;}
   
    	 taskcontenText.setText("联系方式："+linktel+"\n"+"所在地\t\t："+customerSF+customerCity+"\n"+"联系地址："+linkadress+"\n"+"联系人\t\t："+linkname+"\n"+"客户商业类型："+customerBusinessType+"\n"+"客户积分："+customercredit);
   
    	 edittaskbeizh.setText("客户备注："+linkbeizhu);
    txtvisitorname.setText(resStrings[1]);
    btn_backtaskdetials.setOnClickListener(new OnClickListener() {
    	
    	@Override
    	public void onClick(View v) {
    		// TODO Auto-generated method stub
    	
    	
    		Taskdetials.this.finish();
    		
    	}

    	
    });
    //提交拜访记录
	btnsubmitvisit.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
	
		String name=resStrings[1].toString();
		Intent intent = new Intent(Taskdetials.this,VisitInfo.class); 
		intent.putExtra("customerid", customerid); 
		intent.putExtra("customername",name); 
		
        startActivity(intent); 
		
		
	}

	
});

 

	
	
	
	}
	
	private void init() {
		btn_backtaskdetials=(Button)findViewById(R.id.btn_backtaskdetials);
		btnsubmitvisit=(Button)findViewById(R.id.btnsubmitvisit);
		taskcontenText=(TextView)findViewById(R.id.edittaskcontent);
		txtvisitorname=(TextView)findViewById(R.id.txtvisitorname);
		edittaskbeizh=(TextView)findViewById(R.id.edittaskbeizhu);
	} 
	} 