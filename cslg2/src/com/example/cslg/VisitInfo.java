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
	   private EditText editvisittime ; //�ݷ�ʱ��
	   private EditText  editvisitor;//�ݷ���
	   private EditText editvisitdst;//�ݷ�Ŀ��
	   private EditText   editvisitcontent;//�ݷ�����
	   private Button btnselectdate = null;
	   private Button   btnSubmitvisit;
	  private static final int DATE_DIALOG_ID = 1;  
	  private static final int SHOW_DATAPICK = 0;  
	  private int mYear;  
	   private int mMonth;  
	  private int mDay;  
	 private Button btnSubmit;
	 private void init() {
		 //��ʼ������ؼ�
		
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
		btnSubmit.setOnTouchListener(new datesubmitafter());//�ύ�ݷü�¼
	
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
	 
	     * �������� 
	 
	     */  
	   private void updateDisplay() {  
	  
		   editvisittime.setText(new StringBuilder().append(mYear).append("-").append(  
	  
	              (mMonth + 1) < 10 ? "0" + (mMonth + 1) : (mMonth + 1)).append("-").append(  
	  
	              (mDay < 10) ? "0" + mDay : mDay));  
	  
	    }  
	  
	   /** 
	 
	     * ���ڿؼ����¼� 
	 
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
	 
	     * ѡ������Button���¼����� 
	 
	     * 
	 
	     *  
	 
	     * 
	 
	     */  
	 

	  //�ύǰ
	    class datesubmitafter implements  
		  
        android.view.View.OnTouchListener {  

    public void onClick(View v) {  	
 	  

			
			
    }
    //�ύ����
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		
		
		Button upStepBtn = (Button) v;  
        if(event.getAction() == MotionEvent.ACTION_DOWN){  
        	 //�������ݵȴ�����
      	   Intent intent = new Intent(VisitInfo.this,LoadingActivity.class); 
           startActivity(intent); 
           }else if(event.getAction() == MotionEvent.ACTION_UP){  
     	   MyAsyncPutVisitInfo myAsyncPutVisitInfo=new MyAsyncPutVisitInfo(editvisittime.getText().toString().trim(), editvisitdst.getText().toString().trim(), customerid, editvisitcontent.getText().toString().trim(),VisitInfo.this);
    	   myAsyncPutVisitInfo.execute();
           savehistory("�ύ�ݷü�¼");
          
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
	 
	     * �������ڿؼ���Handler 
	 
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
	   		//getString()�ڶ�������Ϊȱʡֵ�����preference�в����ڸ�key��������ȱʡֵ
	   		String lastname=sharedPreferencesget.getString("operationname", "");
	   		String lasttime=sharedPreferencesget.getString("operationtime", "");
	   	 if (lastname=="") {
	   		SharedPreferences sharedPreferences =getSharedPreferences("historys", Context.MODE_PRIVATE);
	   	Editor editor = sharedPreferences.edit();//��ȡ�༭��
	   	editor.putString("operationname", operationname+"@");
	   	editor.putString("operationtime", Applicationtool.sysytemtime().toString()+"@");
	   	editor.commit();//�ύ�޸�
	   	}
	   	 else {
	   		 SharedPreferences sharedPreferences =getSharedPreferences("historys", Context.MODE_PRIVATE);
	   			Editor editor = sharedPreferences.edit();//��ȡ�༭��
	   			editor.putString("operationname",lastname+operationname+"@");
	   			editor.putString("operationtime", lasttime+Applicationtool.sysytemtime().toString()+"@");
	   			editor.commit();//�ύ�޸�
	   	}
	   	 
	   	
	   }

	} 