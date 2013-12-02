package com.example.cslg;


import java.util.ArrayList;
import java.util.List;

import org.ksoap2.serialization.SoapObject;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.Thread.MyAsyncgetWeather;
import com.example.cslg.tools.AppManager;
import com.example.cslg.tools.Weatherlist;


public class Weather extends Activity {
	public static ImageButton btnupdateweather;
private	RelativeLayout relativeLayoutweather;
private  TextView txtwendu;
private  TextView txtcity;
private  String weatherToday="z";
private  SoapObject detail;

private  String weathersky;
public  TextView txtuptime;
private  TextView txtskylevel;
private  TextView btntishi;
private List<Weatherlist> wList2=new ArrayList<Weatherlist>();
public static ProgressBar  progressBarupdateweather;

public void init() 
{//��ʼ���ؼ�
	btnupdateweather=(ImageButton)findViewById(R.id.btnupdateweather);
	txtwendu=(TextView)findViewById(R.id.txtwendu);
	txtcity=(TextView)findViewById(R.id.textcityq);
	txtuptime=(TextView)findViewById(R.id.txtuptime);
	txtskylevel=(TextView)findViewById(R.id.txtskylevel);
	btntishi=(TextView)findViewById(R.id.textView4);	
	progressBarupdateweather=(ProgressBar)findViewById(R.id.progressBarupdateweather);	
	progressBarupdateweather.setVisibility(View.INVISIBLE);
	relativeLayoutweather=(RelativeLayout)findViewById(R.id.relativeLayoutweather);	
}	

	
@SuppressWarnings("unchecked")
protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		 setTheme(R.style.Transparent);   
 setContentView(R.layout.skyweatherr);
		
		AppManager.getAppManager().addActivity(this);
		//��ʼ���ؼ�
		init();
	
		@SuppressWarnings("unused")
		Bundle extras = getIntent().getExtras(); 
	wList2=	 (ArrayList<Weatherlist>)getIntent().getSerializableExtra("wList"); 

	
	txtcity.setText(extras.getString("txtcitymainmenu")); 
	
if (wList2==null) {
	txtskylevel.setText("�����������Ϣ��");
	txtuptime.setText("�����������Ϣ��");
	txtwendu.setText("�����������Ϣ��");
	
	
}
else {if (wList2.size()==0) {
	
	
}
else {
	txtskylevel.setText(wList2.get(0).get_level());
	txtuptime.setText("����ʱ�䣺"+wList2.get(0).get_uptime());
	txtwendu.setText("����"+wList2.get(0).get_wendu());
}
	
	
}

relativeLayoutweather.setOnClickListener(new OnClickListener() { 
@Override 
public void onClick(View v) { 
// TODO Auto-generated method stub 

	
	Weather.this.finish();
	overridePendingTransition(R.anim.zoom_enter,
			R.anim.zoom_exit);
} 
}); 
      
      
btnupdateweather.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				progressBarupdateweather.setVisibility(View.VISIBLE);
				btnupdateweather.setVisibility(View.INVISIBLE);
				txtuptime.setText("���ڸ���");
				 MyAsyncgetWeather task = new MyAsyncgetWeather(txtcity.getText().toString(),wList2,Weather.this);  
		         task.execute(); 
//				SharedPreferences shared =getSharedPreferences("cs", Context.MODE_PRIVATE);   
//				Editor editor = shared.edit();//��ȡ�༭��
//				editor.clear();
//				editor.putString("uptime", it.get_uptime());
//				editor.putString("level",  it.get_level());
//				editor.putString("wendu", it.get_wendu());
//				editor.commit();//�ύ�޸�
				

			}
		});
	if (wList2!=null) {
		for (Weatherlist it : wList2) {
			if (wList2.size()==1) {
				
			
			SharedPreferences sharedPreferences = getSharedPreferences("cs", Context.MODE_PRIVATE);
			Editor editor = sharedPreferences.edit();//��ȡ�༭��
			editor.putString("uptime", it.get_uptime());
			editor.putString("level",  it.get_level());
			editor.putString("wendu", it.get_wendu());
			editor.commit();//�ύ�޸�
	}
	
}
		
	}	
	


	SharedPreferences sharedPreferences = getSharedPreferences("cs", Context.MODE_PRIVATE);
	//getString()�ڶ�������Ϊȱʡֵ�����preference�в����ڸ�key��������ȱʡֵ
	
	txtskylevel.setText(sharedPreferences.getString("level", ""));
	txtwendu.setText("����"+sharedPreferences.getString("wendu", ""));
	txtuptime.setText("����ʱ��:"+sharedPreferences.getString("uptime", ""));
	
	String str=txtwendu.getText().toString();
	if (str.indexOf("")!=-1) {
		btntishi.setText("������ʾ��Ϣ����");
	}
	
	if(str.indexOf("��")!=-1){
		btntishi.setText("���������ܺã����˳��аݷÿͻ���");
   
  }
	
	
	if (str.indexOf("��")!=-1) {
		btntishi.setText("�����������꣬���鲻Ҫ��Լ�ͻ���");
	}
	if (str.indexOf("��")!=-1) {
		btntishi.setText("��������һ�㣬���԰��ų��аݷÿͻ���");
	}
	
	
	
}

public   void parseWeather()
		{
	
	txtskylevel.setText("");
	txtwendu.setText("");
	txtuptime.setText("����ʱ�䣺");
	String date = detail.getProperty(6).toString();
	String skyString= detail.getProperty(10).toString();
	// ��������
	weatherToday = "\n������" + date.split(" ")[1];
	weatherToday = weatherToday + "\n���£�"+ detail.getProperty(5).toString();
	weatherToday = weatherToday + "\n������"+ detail.getProperty(7).toString() + "\n";
	weathersky=skyString.split("��")[5];
	
	 String	sk=weathersky.split("��")[0];
	 String	 ss=detail.getProperty(1).toString();
	 String uptime=detail.getProperty(4).toString();
 //

//save();
}

private void torst() {
	Toast.makeText(this, "���Ժ�", Toast.LENGTH_SHORT).show();
}

@Override
public boolean onKeyDown(int keyCode, KeyEvent event)//��Ҫ�Ƕ���������ĸ�д
{
 // TODO Auto-generated method stub

 if((keyCode == KeyEvent.KEYCODE_BACK)&&(event.getAction() == KeyEvent.ACTION_DOWN))
 {
	Weather.this.finish();
	overridePendingTransition(R.anim.zoom_enter,
			R.anim.zoom_exit);
  return true;
 }
 return super.onKeyDown(keyCode, event);
}


}