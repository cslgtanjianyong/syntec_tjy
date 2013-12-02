package com.example.cslg;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.example.cslg.mycontrols.MyScrollLayout;
import com.example.cslg.tools.AppManager;

import android.view.Window;



public  class ScrollUIActivity extends Activity {
	
	public static String type;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.scrolluiactivity);
		AppManager.getAppManager().addActivity(this);
		
		Bundle extras = getIntent().getExtras(); 

		type=extras.getString("stateSc"); 
		
		
	}
public static  void jumplogin() {
//	
//	Intent intent = new Intent(ScrollUIActivity.this,mainSreen.class); 
//    startActivity(intent); 
//  
	AppManager.getAppManager().AppExit(ScrollUIActivity.class);
    
	
}


}