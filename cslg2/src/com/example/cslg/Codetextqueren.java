package com.example.cslg;


import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cslg.tools.AppManager;
import com.example.cslg.tools.CodequerenList;
public class Codetextqueren extends Activity { 
private Button btnquren;
private Button btncancer;
private EditText editpwd2;
private EditText editpwd;
private List<CodequerenList> codetextquerens=new ArrayList<CodequerenList>();
@Override 
protected void onCreate(Bundle savedInstanceState) { 
super.onCreate(savedInstanceState); 
this.requestWindowFeature(Window.FEATURE_NO_TITLE);
setContentView(R.layout.codetextqueren); 
AppManager.getAppManager().addActivity(this);
init();

editpwd.setText(null);
editpwd2.setText(null);
btncancer.setOnClickListener(new View.OnClickListener() {
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Codetextqueren.this.finish();
	}
});
btnquren.setOnClickListener(new View.OnClickListener() {
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
	if(editpwd.getText().toString().equals(null)||editpwd2.getText().toString().equals(null)) {
		Toast.makeText(getApplicationContext(),"密码不能为空值！",1).show();
		
	}	
	String newpwd=editpwd.getText().toString();
	 Bundle bunde = xs();  
      
    /* 取得Bundle对象中的数据 */ 
   
  
Codetextqueren.this.finish();
	
	}
});


//重新输入密码检查
editpwd2.addTextChangedListener(new TextWatcher() {
	
	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		// TODO Auto-generated method stub
		
			if (editpwd.getText().toString().equals(editpwd2.getText().toString())) {
				btnquren.setEnabled(true);
				codetextquerens.clear();
				CodequerenList co=new CodequerenList();
			co.setCode(editpwd2.getText().toString());
			codetextquerens.add(co);
		} else {
			
		btnquren.setEnabled(false);
		}
	}
	
	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void afterTextChanged(Editable s) {
		// TODO Auto-generated method stub
		if (!editpwd.getText().toString().equals(editpwd2.getText().toString())) {
			
			Toast.makeText(getApplicationContext(),"您两次密码不一致！",1).show();
		}
	}
});

}




private void init() {
	//初始化控件
	btnquren=(Button)findViewById(R.id.btnpwdqueren);
	btncancer=(Button)findViewById(R.id.btnpwdcancer);
	editpwd2=(EditText)findViewById(R.id.editpwd2);
	editpwd=(EditText)findViewById(R.id.editpwd);
}




private Bundle xs() {
	Intent intent = new Intent();
	
    /*new一个Bundle对象，并将要传递的数据传入*/ 
    Bundle bunde = new Bundle();  
    
    bunde.putString("sex",editpwd2.getText().toString());  
    
    /*将Bundle对象assign给Intent*/ 
    intent.putExtras(bunde);  
   
    Codetextqueren.this.setResult(RESULT_OK, intent);  
	return bunde;
}



} 