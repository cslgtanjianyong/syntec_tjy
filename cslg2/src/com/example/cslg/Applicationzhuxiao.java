package com.example.cslg;


import com.example.cslg.tools.AppManager;

import android.app.Activity; 
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle; 

import android.view.Gravity;
import android.view.View; 
import android.view.View.OnClickListener; 
import android.widget.Button;
import android.widget.Toast;

public class Applicationzhuxiao extends Activity { 

private Button btnexit;
private Button btncancer;
private void exitapp() {
	AppManager.getAppManager().AppExit(this);
}

@Override 
protected void onCreate(Bundle savedInstanceState) { 
super.onCreate(savedInstanceState); 
setContentView(R.layout.applicationzhuxiao); 
AppManager.getAppManager().addActivity(this);
//初始化控件
init();

//退出程序
btnexit.setOnClickListener(new OnClickListener() {

@Override
public void onClick(View v) {
	// TODO Auto-generated method stub
	Intent intent22 = new Intent(Applicationzhuxiao.this,
				mainSreen.class);
		AppManager.getAppManager().finishAllActivity();
		
		
		SharedPreferences sharedPreferences = getSharedPreferences("myinfo", Context.MODE_PRIVATE);
	Editor editor = sharedPreferences.edit();//获取编辑器
	editor.putString("isautologin", "false");
	
	editor.commit();//提交修改
		
	
		startActivity(intent22);
		
		
	
		Toast toast = Toast.makeText(Applicationzhuxiao.this.getApplicationContext(),
 			"注销成功", Toast.LENGTH_LONG);
 			toast.setGravity(Gravity.CENTER, 0, 0);
 			toast.show();
 			AppManager.getAppManager().finishActivity(Mainmenu.class);
		Applicationzhuxiao.this.finish();

}


});


//取消退出程序

btncancer.setOnClickListener(new OnClickListener() {

@Override
public void onClick(View v) {
	// TODO Auto-generated method stub

	Applicationzhuxiao.this.finish();
}
});




}
public void btncancer(View v) { 
	this.finish(); 
	} 
private void init() {
	btnexit=(Button)findViewById(R.id.exitzhuxiao);
	btncancer=(Button)findViewById(R.id.exitCancerzhuxiao);
	
} 
} 