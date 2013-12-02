package com.example.cslg;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import android.R.integer;
import android.app.AlertDialog;
import android.app.ExpandableListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.cslg.tools.AppManager;
import com.example.cslg.tools.Morehistorylist;
import com.example.cslg.tools.NewsOpreationList;
import com.example.cslg.tools.Todayhistorlist;
import com.example.cslg.tools.Yestodayhistorylist;

public class OperationHistory extends ExpandableListActivity  {


private Button btnclear;
private Button btn_backoperationhistory;
private Handler handler = new Handler();
private int newsize;
private List<NewsOpreationList> news = new ArrayList<NewsOpreationList>();
private List<Todayhistorlist> todayhistorlists = new ArrayList<Todayhistorlist>();
private List<Yestodayhistorylist> yestodayhistorylists = new ArrayList<Yestodayhistorylist>();
private List<Morehistorylist> morehistorylists = new ArrayList<Morehistorylist>();
////
List<String> group;           //组列表
List<List<String>> child;     //子列表
ContactsInfoAdapter adapter;  //数据适配器
public void init() 
{//初始化控件
	btnclear=(Button)findViewById(R.id.btnclear);
	btn_backoperationhistory=(Button)findViewById(R.id.btn_backoperationhistory);
}	

	
protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.operationhistory);
		AppManager.getAppManager().addActivity(this);
		//初始化控件
		init();
		 getExpandableListView().setBackgroundResource(R.color.white);
	        
	        initializeData();
	        getExpandableListView().setAdapter(new ContactsInfoAdapter());
	        getExpandableListView().setCacheColorHint(0);  //设置拖动列表的时候防止出现黑色背景
	        btn_backoperationhistory.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					 Intent intent = new Intent(OperationHistory.this,Mainmenu.class); 
				     startActivity(intent); 
				          OperationHistory.this.finish();
				          overridePendingTransition(R.anim.slide_up_in, R.anim.slide_down_out);
					
					

				}

			});
		btnclear.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
			
				clearhistory();
				
				
				
				

			}

		});

	

	}


/**
 * 初始化组、子列表数据
 */
private void initializeData(){
	group = new ArrayList<String>();
	child = new ArrayList<List<String>>();
	SimpleDateFormat  formatter=new SimpleDateFormat("MM月dd日");       
	Date curDate=new Date(System.currentTimeMillis());//获取当前时间       
	String todaytime=formatter.format(curDate);
	
	Calendar cal =Calendar.getInstance();
	  cal.add(Calendar.DATE,-1);
	  String yesterday = new SimpleDateFormat( "MM月dd日").format(cal.getTime());
	  
	 SharedPreferences sharedPreferencesget = getSharedPreferences("historys", Context.MODE_PRIVATE);
		//getString()第二个参数为缺省值，如果preference中不存在该key，将返回缺省值
		String lastname=sharedPreferencesget.getString("operationname", "");
		String lasttime=sharedPreferencesget.getString("operationtime", "");
		
	if (lastname=="") {
	 return;  	
	}
		String[] resnameStrings=lastname.split("@");
	String[] restimeStrings =lasttime.split("@");
	
	

	
	for(int i=0;i<restimeStrings.length;i++) 
	{
		
	
		String[] reresStrings=restimeStrings[i].split(" ");
			String    lasttimeformatterl=reresStrings[0];
		
		if (lasttimeformatterl.equals(todaytime)) {
		Todayhistorlist tolistTodayhistorlist=new Todayhistorlist();
		tolistTodayhistorlist.setContent(restimeStrings[i]+"\t"+resnameStrings[i]);
		todayhistorlists.add(tolistTodayhistorlist);
		
		}
	
		if (lasttimeformatterl.equals(yesterday)) {
			Yestodayhistorylist yeslistYestodayhistorylist=new Yestodayhistorylist();
			yeslistYestodayhistorylist.setContent(restimeStrings[i]+"\t"+resnameStrings[i]);
			yestodayhistorylists.add(yeslistYestodayhistorylist);
		}
		if (!lasttimeformatterl.equals(todaytime)&&!lasttimeformatterl.equals(yesterday)) {
			Morehistorylist morelistMorehistorylist =new Morehistorylist();
			morelistMorehistorylist.setContent(restimeStrings[i]+"\t"+resnameStrings[i]);
			morehistorylists.add(morelistMorehistorylist);
			
		}
		
		
	}
	
	String[] todaysStrings=new String[todayhistorlists.size()];
	String[] zuotStrings=new String[yestodayhistorylists.size()];
	String[] moresStrings=new String[morehistorylists.size()];
	for(int m=0;m<todayhistorlists.size();m++) {
		todaysStrings[m]=todayhistorlists.get(m).getContent();
	}
for(int m=0;m<yestodayhistorylists.size();m++) {
	zuotStrings[m]=yestodayhistorylists.get(m).getContent();
	}
for(int m=0;m<morehistorylists.size();m++) {
	moresStrings[m]=morehistorylists.get(m).getContent();
}

	
	
	addInfo("今天",todaysStrings);
	addInfo("昨天",zuotStrings);
	addInfo("更久",moresStrings);
	
	
}

/**
 * 模拟给组、子列表添加数据
 * @param g-group
 * @param c-child
 */
private void addInfo(String g,String[] c){
	group.add(g);
	List<String> childitem = new ArrayList<String>();
	for(int i=0;i<c.length;i++){
		childitem.add(c[i]);
	}
	child.add(childitem);
}

class ContactsInfoAdapter extends BaseExpandableListAdapter{

	
	//-----------------Child----------------//
	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return child.get(groupPosition).get(childPosition);
	}
	
	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}
	
	@Override
	public int getChildrenCount(int groupPosition) {
		return child.get(groupPosition).size();
	}
	
	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		String string = child.get(groupPosition).get(childPosition); 
		return getGenericView(string);
	}
	
	//----------------Group----------------//
	@Override
	public Object getGroup(int groupPosition) {
		return group.get(groupPosition);
	}				

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}	
	
	@Override
	public int getGroupCount() {
		return group.size();
	}	
	
	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,View convertView, ViewGroup parent)
	{
		String string = group.get(groupPosition);  
	
		return getGenericView(string);
	}

	//创建组/子视图  
    public TextView getGenericView(String s) {  
        // Layout parameters for the ExpandableListView  
        AbsListView.LayoutParams lp = new AbsListView.LayoutParams(  
                ViewGroup.LayoutParams.FILL_PARENT, 40);

        TextView text = new TextView(OperationHistory.this);  
        text.setLayoutParams(lp);  
     
        text.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);  
     
        text.setPadding(50, 0, 0, 0);  
        text.setTextSize(20);
        text.setText(s);  
   
        return text;  
    }  
	
	
	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return false;
	}		

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return true;
	}
	
}




	
	

@Override
public boolean onKeyDown(int keyCode, KeyEvent event)//主要是对这个函数的复写
{
 // TODO Auto-generated method stub

 if((keyCode == KeyEvent.KEYCODE_BACK)&&(event.getAction() == KeyEvent.ACTION_DOWN))
 {
	 Intent intent = new Intent(OperationHistory.this,Mainmenu.class); 
     startActivity(intent); 
          OperationHistory.this.finish();
          overridePendingTransition(R.anim.slide_up_in, R.anim.slide_down_out);
  return true;
 }
 return super.onKeyDown(keyCode, event);
}
public void cleanlist(){
	
	if(newsize>0){
	
//		news.removeAll(news);
//		adapter.notifyDataSetChanged();
//		listView.setAdapter(adapter);
		
	}
}


private void clearhistory() {
	new AlertDialog.Builder(this)
	.setTitle("系统提示")
	.setMessage("您确定清除历史记录！")
	.setPositiveButton("确定",
			new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog,
						int which) {
			
					SharedPreferences sharedPreferences =getSharedPreferences("historys", Context.MODE_PRIVATE);
	Editor editor = sharedPreferences.edit();//获取编辑器
	editor.putString("operationname", "");
	editor.putString("operationtime", "");

	boolean isclear=editor.commit();//提交修改
	if (isclear) {
		getExpandableListView().setVisibility(View.INVISIBLE);
	}	
					
					
				}
			})
			.setNegativeButton("取消", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog,
						int which) {
				
				}
			})

	.show();
	
	
	

}

}
