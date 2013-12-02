package com.example.cslg;


import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Toast;

import com.example.cslg.tools.AppManager;
import com.example.cslg.tools.Applicationtool;
public class Notebook extends Activity {
private MultiAutoCompleteTextView multiAutoCompleteTextViewnotebook;
private Button btnbaocun;
private Button btn_cancernotebook;
public void init() 
{//初始化控件
	multiAutoCompleteTextViewnotebook=(MultiAutoCompleteTextView)findViewById(R.id.multiAutoCompleteTextViewnotebook);
	btnbaocun=(Button)findViewById(R.id.btnbaocun);
	btn_cancernotebook=(Button)findViewById(R.id.btn_cancernotebook);
}	

	
protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.notebook);
		AppManager.getAppManager().addActivity(this);
		//初始化控件
		init();
		//读取上次行程事件
		SharedPreferences sharedPreferences = getSharedPreferences("note", Context.MODE_PRIVATE);
		//getString()第二个参数为缺省值，如果preference中不存在该key，将返回缺省值
		if (sharedPreferences.getString("notecontent", "")=="") {
			multiAutoCompleteTextViewnotebook.setText("");
		}else {
			multiAutoCompleteTextViewnotebook.setText("您于"+sharedPreferences.getString("notetime", "")+"有一份备忘:\n"+sharedPreferences.getString("notecontent", ""));
				
		}
		
		btn_cancernotebook.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Notebook.this,Mainmenu.class); 
				 startActivity(intent); 
				  Notebook.this.finish();
				  overridePendingTransition(R.anim.slide_up_in, R.anim.slide_down_out);
				
			}

	});
		///
		btnbaocun.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				
				SharedPreferences sharedPreferences = getSharedPreferences("note", Context.MODE_PRIVATE);
				Editor editor = sharedPreferences.edit();//获取编辑器
				editor.putString("notecontent", multiAutoCompleteTextViewnotebook.getText().toString());
				editor.putString("notetime",Applicationtool.sysytemtime().toString());
				
				boolean q= editor.commit();//提交修改
				if (q) {
					 Toast.makeText(Notebook.this,"成功保存", 1).show();
					 retrunmethod();
				}
				else {
					 Toast.makeText(Notebook.this,"保存失败！", 1).show();
				}
			}

	});
		
		
}
private void retrunmethod() {
	Intent intent = new Intent(Notebook.this,Mainmenu.class); 
	 startActivity(intent); 
	  Notebook.this.finish();
	  overridePendingTransition(R.anim.slide_up_in, R.anim.slide_down_out);
}
@Override
public boolean onKeyDown(int keyCode, KeyEvent event)//主要是对这个函数的复写
{
 // TODO Auto-generated method stub

 if((keyCode == KeyEvent.KEYCODE_BACK)&&(event.getAction() == KeyEvent.ACTION_DOWN))
 {
	 retrunmethod();
  return true;
 }
 return super.onKeyDown(keyCode, event);
}


}