package com.example.cslg;

import com.example.cslg.tools.AppManager;
import com.example.cslg.tools.Applicationtool;

import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.Thread.MyAsyncPutVisitInfo;

public class VisitInfo extends Activity {  
	public static boolean b=false;
	private static String customerid;
	private static String customername;
	   private EditText editvisittime ; //拜访时间
	   private EditText  editvisitor;//拜访者
	   private EditText editvisitdst;//拜访目的
	   private EditText   editvisitcontent;//拜访内容
	   private Button btnselectdate = null;
	   private Button   btnSubmitvisit;
	  private static final int DATE_DIALOG_ID = 1;  
	  private static final int SHOW_DATAPICK = 0;  
	  private int mYear;  
	   private int mMonth;  
	  private int mDay;  
	 private Button btnSubmit;
	 private void init() {
		 //初始化桌面控件
		
		  editvisittime = (EditText) findViewById(R.id.editvisittime);
			btnselectdate= (Button) findViewById(R.id.btnselectdate);
			btnSubmit=(Button) findViewById(R.id.btnSubmitvisit);
			editvisitor= (EditText) findViewById(R.id.editvisitor);
			editvisitdst= (EditText) findViewById(R.id.editvisitdst);
			editvisitcontent= (EditText) findViewById(R.id.editvisitcontent);
	 }
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.visitinfo);
		AppManager.getAppManager().addActivity(this);
		init();
		
		
		try
		{
			Bundle extras = getIntent().getExtras(); 

		
	    customerid=extras.getString("customerid");
	    customername=extras.getString("customername");
	    editvisitor.setText(customername);
	    System.out.println("nihao"+customername);
	    }
		catch(Exception ex){}
		btnSubmit.setOnTouchListener(new datesubmitafter());//提交拜访记录
	
		btnselectdate.setOnClickListener(new DateButtonOnClickListener());
		final Calendar c = Calendar.getInstance();
		mYear = c.get(Calendar.YEAR);
		mMonth = c.get(Calendar.MONTH);

		mDay = c.get(Calendar.DAY_OF_MONTH);
		setDateTime();

	}  
	  
	   
	  
	    private void setDateTime() {  
	  
	       final Calendar c = Calendar.getInstance();  
	  
	       mYear = c.get(Calendar.YEAR);  
	  
	       mMonth = c.get(Calendar.MONTH);  
	  
	       mDay = c.get(Calendar.DAY_OF_MONTH);  
	  
	   
	  
	       updateDisplay();  
	  
	    }  
	    /** 
	 
	     * 更新日期 
	 
	     */  
	   private void updateDisplay() {  
	  
		   editvisittime.setText(new StringBuilder().append(mYear).append("-").append(  
	  
	              (mMonth + 1) < 10 ? "0" + (mMonth + 1) : (mMonth + 1)).append("-").append(  
	  
	              (mDay < 10) ? "0" + mDay : mDay));  
	  
	    }  
	  
	   /** 
	 
	     * 日期控件的事件 
	 
	     */  
	   private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {  
	  
	       public void onDateSet(DatePicker view, int year, int monthOfYear,  
	  
	              int dayOfMonth) {  
	   mYear = year;  
	   mMonth = monthOfYear;  
	   mDay = dayOfMonth;  
	   updateDisplay();  
	   }  
	  };  
	  
	    /** 
	 
	     * 选择日期Button的事件处理 
	 
	     * 
	 
	     *  
	 
	     * 
	 
	     */  
	 

	  //提交前
	    class datesubmitafter implements  
		  
        android.view.View.OnTouchListener {  

    public void onClick(View v) {  	
 	  

			
			
    }
    //提交数据
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		
		
		Button upStepBtn = (Button) v;  
        if(event.getAction() == MotionEvent.ACTION_DOWN){  
        	 //网络数据等待界面
      	   Intent intent = new Intent(VisitInfo.this,LoadingActivity.class); 
           startActivity(intent); 
           }else if(event.getAction() == MotionEvent.ACTION_UP){  
     	   MyAsyncPutVisitInfo myAsyncPutVisitInfo=new MyAsyncPutVisitInfo(editvisittime.getText().toString().trim(), editvisitdst.getText().toString().trim(), customerid, editvisitcontent.getText().toString().trim(),VisitInfo.this);
    	   myAsyncPutVisitInfo.execute();
           savehistory("提交拜访记录");
          
        } 
		return false;
	}  

 }  

	    //
	  class DateButtonOnClickListener implements  
	  
      android.view.View.OnClickListener {  

  @Override  

  public void onClick(View v) {  

      Message msg = new Message();  

      if (btnselectdate.equals((Button) v)) {  

         msg.what = VisitInfo.SHOW_DATAPICK;  

      }  

      VisitInfo.this.saleHandler.sendMessage(msg);  

  }  

}  
	  
	    @Override  
	  
	    protected Dialog onCreateDialog(int id) {  
	  
	       switch (id) {  
	  
	       case DATE_DIALOG_ID:  
	  
	           return new DatePickerDialog(this, mDateSetListener, mYear, mMonth,  
	  
	                  mDay);  
	  
	       }  
	  
	       return null;  
	  
	    }  
	  
	   
	  
	    @Override  
	  
	    protected void onPrepareDialog(int id, Dialog dialog) {  
	  
	       switch (id) {  
	  
	       case DATE_DIALOG_ID:  
	  
	           ((DatePickerDialog) dialog).updateDate(mYear, mMonth, mDay);  
	  
	           break;  
	  
	       }  
	  
	    }  
	  
	   
	  
	    /** 
	 
	     * 处理日期控件的Handler 
	 
	     */  
	  
	    Handler saleHandler = new Handler() {  
	  
	       @Override  
	  
	       public void handleMessage(Message msg) {  
	  
	           switch (msg.what) {  
	  
	           case VisitInfo.SHOW_DATAPICK:  
	  
	              showDialog(DATE_DIALOG_ID);  
	  
	              break;  
	  
	           }  
	  
	       }  
	  
	    };  
	    private void exitloading() {
			AppManager.getAppManager().finishActivity(LoadingActivity.class);
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