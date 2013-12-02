package com.example.cslg;

import java.lang.reflect.Field;

import android.R.integer;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;
import android.widget.TabWidget;

import com.example.cslg.tools.AppManager;
@SuppressWarnings("deprecation")
public class OPhistory extends TabActivity  implements OnCheckedChangeListener{
	private TabHost tabHost;
	private TabWidget tabWidget;
	Field mBottomLeftStrip;
	Field mBottomRightStrip;
private Button	btn_backmainformjoblist;
public void init() 
{//初始化控件
	btn_backmainformjoblist=(Button)findViewById(R.id.btn_backmainformjoblist);
}	

	
protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.ophistory);
		AppManager.getAppManager().addActivity(this);
		//初始化控件
		init();
		//搜索事件
	
		 makeTab();
		
		
		
		 btn_backmainformjoblist.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				 Intent intent = new Intent(OPhistory.this,Mainmenu.class); 
			     startActivity(intent); 
			     OPhistory.this.finish();
			     overridePendingTransition(R.anim.slide_up_in, R.anim.slide_down_out);
				
			}
		});
		
}

public void makeTab(){
	if(this.tabHost == null){
    	tabHost = getTabHost();
        tabWidget = getTabWidget();
        tabHost.setup();
        tabHost.bringToFront();
        
        TabSpec nearby_tab = tabHost.newTabSpec("nearby_tab");
        TabSpec history_tab = tabHost.newTabSpec("history_tab");
       
        
        nearby_tab.setIndicator("拜访记录",getResources().getDrawable(R.drawable.component_detail_item_bg_p)).setContent(new Intent(this,MyJobList.class));
        history_tab.setIndicator("预购记录",getResources().getDrawable(R.drawable.component_detail_item_bg_p)).setContent(new Intent(this,Yugouhistory.class));
         
        tabHost.addTab(nearby_tab);
        tabHost.addTab(history_tab);
       
        
        if (Integer.valueOf(Build.VERSION.SDK) <= 7) {
			try {
				mBottomLeftStrip = tabWidget.getClass().getDeclaredField ("mBottomLeftStrip");
				mBottomRightStrip = tabWidget.getClass().getDeclaredField ("mBottomRightStrip");
				if(!mBottomLeftStrip.isAccessible()) {
					mBottomLeftStrip.setAccessible(true);
				}
				if(!mBottomRightStrip.isAccessible()){
					mBottomRightStrip.setAccessible(true);
				}
				mBottomLeftStrip.set(tabWidget, getResources().getDrawable (R.drawable.linee));
				mBottomRightStrip.set(tabWidget, getResources().getDrawable (R.drawable.linee));

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			try {
				mBottomLeftStrip = tabWidget.getClass().getDeclaredField("mLeftStrip");
				mBottomRightStrip = tabWidget.getClass().getDeclaredField("mRightStrip");
				if (!mBottomLeftStrip.isAccessible()) {
					mBottomLeftStrip.setAccessible(true);
				}
				if (!mBottomRightStrip.isAccessible()) {
					mBottomRightStrip.setAccessible(true);
				}
				mBottomLeftStrip.set(tabWidget, getResources().getDrawable(R.drawable.linee));
				mBottomRightStrip.set(tabWidget, getResources().getDrawable(R.drawable.linee));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
        
		for (int i =0; i <tabWidget.getChildCount(); i++) {
			
			View vvv = tabWidget.getChildAt(i);
			if(tabHost.getCurrentTab()==i){
				vvv.setBackgroundDrawable(getResources().getDrawable(R.drawable.component_detail_item_bg_n));//按下效果
			}
			else {
				vvv.setBackgroundDrawable(getResources().getDrawable(R.drawable.component_detail_item_bg_p));
			}
		}
		
		tabHost.setOnTabChangedListener(new OnTabChangeListener(){

			@Override
			public void onTabChanged(String tabId) {
				for (int i =0; i < tabWidget.getChildCount(); i++) {
					View vvv = tabWidget.getChildAt(i);
					if(tabHost.getCurrentTab()==i){
						vvv.setBackgroundDrawable(getResources().getDrawable(R.drawable.component_detail_item_bg_n));
					}
					else {
						vvv.setBackgroundDrawable(getResources().getDrawable(R.drawable.component_detail_item_bg_p));
					}
				}
			}
		});
	}
}

private void setCurrentTabWithAnim(int now,int next,String tag)
{
//这个方法是关键，用来判断动画滑动的方向  
	 if(now > next)
	  {
	   tabHost.getCurrentView().startAnimation(AnimationUtils.loadAnimation(this, R.anim.push_up_out));
	   tabHost.setCurrentTabByTag(tag);
	   tabHost.getCurrentView().startAnimation(AnimationUtils.loadAnimation(this, R.anim.push_up_in));
	  }
	  else
	  {
	   tabHost.getCurrentView().startAnimation(AnimationUtils.loadAnimation(this, R.anim.push_left_out));
	   tabHost.setCurrentTabByTag(tag);
	   tabHost.getCurrentView().startAnimation(AnimationUtils.loadAnimation(this, R.anim.push_left_in));
}

}


@Override
public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
	// TODO Auto-generated method stub
	int isch=0;
	if (isChecked&&buttonView.getId()==R.id.tab1) {
		isch=1;
	}
	else {isch=0;}
	;
	int currentTab = tabHost.getCurrentTab();
	  switch (isch)
	  {
	  case 1:
	   setCurrentTabWithAnim(currentTab, 0, "拜访记录");
	   break;
	  case 0:
	   setCurrentTabWithAnim(currentTab, 1, "预购记录");
	   break;
	
	  default:
	   break;
	  }
}

@Override
public boolean onKeyDown(int keyCode, KeyEvent event)//主要是对这个函数的复写
{
 // TODO Auto-generated method stub

 if((keyCode == KeyEvent.KEYCODE_BACK)&&(event.getAction() == KeyEvent.ACTION_DOWN))
 {
	
	 Intent intent = new Intent(OPhistory.this,Mainmenu.class); 
     startActivity(intent); 
     OPhistory.this.finish();
     overridePendingTransition(R.anim.slide_up_in, R.anim.slide_down_out);
  return true;
 }
 return super.onKeyDown(keyCode, event);
}
}