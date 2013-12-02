package com.example.cslg;
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
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.example.cslg.tools.AppManager;
import com.example.cslg.tools.Applicationtool;
import com.example.cslg.tools.SharedPreferanceUtil;
public class Systemset extends Activity {
		private Button btneixt;
		private Button btn_back_system;
private RelativeLayout btnhelp;
private RelativeLayout btnabout;
private RelativeLayout Rlnerearpersoninfo;
private RelativeLayout RlnerearINFO;
private RelativeLayout softinstruction;
private RelativeLayout softmianzeshengming;
		public void init() 
		{softinstruction=(RelativeLayout)findViewById(R.id.softinstruction);	
			Rlnerearpersoninfo=(RelativeLayout)findViewById(R.id.Rlnerearpersoninfo);	
			softmianzeshengming=(RelativeLayout)findViewById(R.id.softmianzeshengming);
			RlnerearINFO=(RelativeLayout)findViewById(R.id.RlnerearINFO);	
			btn_back_system=(Button)findViewById(R.id.btn_back_system);	
			btneixt=(Button)findViewById(R.id.btnout);
			btnhelp=(RelativeLayout)findViewById(R.id.btnhelp);	
			btnabout=(RelativeLayout)findViewById(R.id.btnabout);	
		}	
	
		protected void onCreate(Bundle savedInstanceState) {
				super.onCreate(savedInstanceState);
				this.requestWindowFeature(Window.FEATURE_NO_TITLE);
				setContentView(R.layout.systemset);
				AppManager.getAppManager().addActivity(this);	
				init() ;
				btnhelp.setOnClickListener(new View.OnClickListener() {
				
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						savehistory("帮助与反馈");
						Intent intent = new Intent(Systemset.this,Helpandreturn.class); 
				        startActivity(intent); 
				        
					}
				});
				btn_back_system.setOnClickListener(new View.OnClickListener() {
				
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						 Intent intent = new Intent(Systemset.this,Mainmenu.class); 
					     startActivity(intent); 
					          Systemset.this.finish();
					}
				});
				RlnerearINFO.setOnClickListener(new View.OnClickListener() {
				
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Intent intent = new Intent(Systemset.this,
								PersonalInfo.class);
			
						intent.putExtra("state", "0");
						startActivity(intent);
						savehistory("查看个人信息");
						Systemset.this.finish();
					}
				});
				softmianzeshengming.setOnClickListener(new View.OnClickListener() {
				
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						savehistory("免责声明");
						Intent intent = new Intent(Systemset.this,
								Softmianzeshengming.class);
							
							startActivity(intent);
					
							Systemset.this.finish();
					}
				});
				Rlnerearpersoninfo.setOnClickListener(new View.OnClickListener() {
				
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						savehistory("切换城市");
						Intent intent = new Intent(Systemset.this,
								SearchCity.class);
							
							startActivity(intent);
							Systemset.this.finish();
					}
				});
				
				btnabout.setOnClickListener(new View.OnClickListener() {
				
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Intent intent = new Intent(Systemset.this,About.class); 
				        startActivity(intent); 
					}
				});
				btneixt.setOnClickListener(new View.OnClickListener() {
				
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Intent intent = new Intent(Systemset.this,ExitApplication.class); 
				        startActivity(intent); 
					}
				});
				softinstruction.setOnClickListener(new View.OnClickListener() {
				
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						savehistory("软件说明");
						Intent intentq = new Intent(Systemset.this,ScrollUIActivity.class);
					     String	typeString = "systemsofthelp";
					     	intentq.putExtra("stateSc", typeString);
							startActivity(intentq);
					}
				});
				
				
}
		
		@Override
		public boolean onKeyDown(int keyCode, KeyEvent event)//主要是对这个函数的复写
		{
		 // TODO Auto-generated method stub

		 if((keyCode == KeyEvent.KEYCODE_BACK)&&(event.getAction() == KeyEvent.ACTION_DOWN))
		 {
			 Intent intent = new Intent(Systemset.this,Mainmenu.class); 
		     startActivity(intent); 
		          Systemset.this.finish();
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
