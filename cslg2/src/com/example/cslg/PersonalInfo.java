package com.example.cslg;

import org.apache.http.HttpResponse;

import com.example.Thread.MyAsyncChangePassword;
import com.example.cslg.tools.AppManager;
import com.example.cslg.tools.Applicationtool;
import com.example.cslg.tools.SharedPreferanceUtil;
import com.example.cslg.tools.tools;

import android.R.integer;
import android.app.Activity; 
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.ConnectivityManager;
import android.os.Bundle; 
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent; 
import android.view.View; 
import android.view.Window;
import android.view.View.OnClickListener; 
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout; 
import android.widget.TextView;
import android.widget.Toast; 
public class PersonalInfo extends Activity { 
private Button txtedit;
private TextView txtuserid;
private TextView txtusername;
private TextView txtuserRegName;
private TextView txtlevel;
public static HttpResponse httpResponse;
private TextView txtpwd;
private TextView txtmail;
private boolean isConnect=false;
private EditText editpwd;
private EditText editmail;

private Button btn_backmain;
private String state=null;
@Override 
protected void onCreate(Bundle savedInstanceState) { 
super.onCreate(savedInstanceState); 
this.requestWindowFeature(Window.FEATURE_NO_TITLE);
setContentView(R.layout.personalnfo); 
AppManager.getAppManager().addActivity(this);
init();
//确认密码
editpwd.setOnClickListener(new View.OnClickListener() {
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent = new Intent();
		intent.setClass(PersonalInfo.this,Codetextqueren.class);
	

		
		
        /*new一个Bundle对象，并将要传递的数据传入*/ 
        Bundle bundle = new Bundle();  
        
        bundle.putString("sex","男");  
        
        /*将Bundle对象assign给Intent*/ 
        intent.putExtras(bundle);  
        
        /*调用Activity EX03_11_1*/ 
        startActivityForResult(intent,0);  
		
	}
});
//提交个人信息更改
txtedit.setOnClickListener(new View.OnClickListener() {
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		 //网络数据等待界面
   	   Intent intent = new Intent(PersonalInfo.this,LoadingActivity.class); 
      startActivity(intent); 
	}
});

//返回主页面
btn_backmain.setOnClickListener(new View.OnClickListener() {
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent = new Intent();
		intent.setClass(PersonalInfo.this,Mainmenu.class);
		startActivity(intent);
		PersonalInfo.this.finish();
	}
});


//判断是查看个人信息页面还是编辑个人信息页面
Bundle extras = getIntent().getExtras(); 

state=extras.getString("state"); 
if(state.equals("0")) {
	
	txtedit.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
		
			if(txtedit.getText().equals("编辑")) {
		//编辑个人信息		
			txtedit.setText("提交");
			editpwd.setVisibility(View.VISIBLE);
			editmail.setVisibility(View.VISIBLE);
			editpwd.setText(txtpwd.getText());
			editmail.setText(txtmail.getText());
		    txtpwd.setVisibility(View.INVISIBLE);
			txtmail.setVisibility(View.INVISIBLE);
			
			
			}
			else{
	        	txtedit.setText("编辑");
				//提交个人信息
	        	invisibleetittext();
	        	
			}
			}
			
		
	});
}
else {
	
	editpwd.setVisibility(View.INVISIBLE);
	editmail.setVisibility(View.INVISIBLE);
	
	txtpwd.setVisibility(View.VISIBLE);
	txtmail.setVisibility(View.VISIBLE);

	txtedit.setVisibility(View.INVISIBLE);
}


}
protected void onActivityResult(int requestCode,int resultCode,Intent data)
{
	switch (resultCode)  
    {   
      case RESULT_OK:  
        /* 取得来自Activity2的数据，并显示于画面上 */    
        Bundle bunde = data.getExtras();  
        String sex = bunde.getString("sex");  
        
          
        editpwd.setText(sex);  
       
        break;  
      default:  
        break;  
     }   
	}

private void init() {
	//初始化控件
	txtedit=(Button)findViewById(R.id.btnedit);
	btn_backmain=(Button)findViewById(R.id.btn_backmain);
	txtpwd=(TextView)findViewById(R.id.txtpwd);
	txtmail=(TextView)findViewById(R.id.textView11);
	editpwd=(EditText)findViewById(R.id.editCode);
	editmail=(EditText)findViewById(R.id.editMainAddress);
	
	txtuserid=(TextView)findViewById(R.id.txtuserid);
	txtusername=(TextView)findViewById(R.id.txtusername);
	txtuserRegName=(TextView)findViewById(R.id.txtuserRegName);
	txtlevel=(TextView)findViewById(R.id.txtlevel);
	
	
	editpwd.setVisibility(View.INVISIBLE);
	editmail.setVisibility(View.INVISIBLE);
	
	txtpwd.setText(tools._me.getUserPassword());
	txtuserid.setText(tools._me.getUserId());
	txtusername.setText(tools._me.getUserName());
	txtuserRegName.setText(tools._me.getUserRegName());
	txtmail.setText(tools._me.getUserEail());
	txtlevel.setText("业务员");
	
	
}


private void invisibleetittext() {
	
	 Intent intentw = new Intent(PersonalInfo.this,LoadingActivity.class); 
    startActivity(intentw); 
	//影藏编辑框
	editpwd.setVisibility(View.INVISIBLE);
	editmail.setVisibility(View.INVISIBLE);
	txtpwd.setVisibility(View.VISIBLE);
	txtmail.setVisibility(View.VISIBLE);
String beizhuString="备注";
	MyAsyncChangePassword myAsyncChangePassword=new MyAsyncChangePassword(editpwd.getText().toString().trim(), beizhuString.trim(), editmail.getText().toString().trim(),PersonalInfo.this);
	myAsyncChangePassword.execute();
	savehistory("更改个人信息");
} 

@Override
public boolean onKeyDown(int keyCode, KeyEvent event)//主要是对这个函数的复写
{
 // TODO Auto-generated method stub

 if((keyCode == KeyEvent.KEYCODE_BACK)&&(event.getAction() == KeyEvent.ACTION_DOWN))
 {
	 Intent intent = new Intent(PersonalInfo.this,Mainmenu.class); 
     startActivity(intent); 
     PersonalInfo.this.finish();
     overridePendingTransition(R.anim.slide_up_in, R.anim.slide_down_out);
  return true;
 }
 return super.onKeyDown(keyCode, event);
}
public  void savehistory(String operationname) {
 	 SharedPreferences sharedPreferencesget = getSharedPreferences("historys", Context.MODE_PRIVATE);
 		//getString()第二个参数为缺省值，如果preference中不存在该key，将返回缺省值
 		String lastname=sharedPreferencesget.getString("operationname", "");
 		String lasttime=sharedPreferencesget.getString("operationtime", "");
 	 if (lastname=="") {
 		SharedPreferences sharedPreferences =getSharedPreferences("historys", Context.MODE_PRIVATE);
 	Editor editor = sharedPreferences.edit();//获取编辑器
 	editor.putString("operationname", operationname+"@");
 	editor.putString("operationtime", Applicationtool.sysytemtime().toString()+"@");
 	editor.commit();//提交修改
 	}
 	 else {
 		 SharedPreferences sharedPreferences =getSharedPreferences("historys", Context.MODE_PRIVATE);
 			Editor editor = sharedPreferences.edit();//获取编辑器
 			editor.putString("operationname",lastname+operationname+"@");
 			editor.putString("operationtime", lasttime+Applicationtool.sysytemtime().toString()+"@");
 			editor.commit();//提交修改
 	}
 	 
 	
 }

}