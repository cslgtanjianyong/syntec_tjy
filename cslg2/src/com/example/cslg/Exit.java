package com.example.cslg;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cslg.tools.AppManager;
import com.example.cslg.tools.JsonTolist;
import com.example.cslg.tools.tools;
import com.example.source.KuCunInfo;
public class Exit extends Activity { 
private LinearLayout layout; 
private TextView txtdrugid;
private TextView txtdrugname;
private TextView txtpronum;
private TextView txtname;
private TextView drugcreatetime;
private TextView drugintprice;
private TextView drugbrizhu;
private TextView drugyouxiaoqi;
private TextView  drugoutprice;

private TextView drugdrugnameenglish;
private TextView drugdrugnamehuaxue;
private TextView  drugguige;
private Button btnyugou;
private Button exitB;
private String id;
private String name;
private String IDD;
private String drugname;
private String numString;
@Override 
protected void onCreate(Bundle savedInstanceState) { 
super.onCreate(savedInstanceState); 
setContentView(R.layout.drugdetials); 
AppManager.getAppManager().addActivity(this);
//初始化控件
txtdrugid=(TextView)findViewById(R.id.txtdrugid);
txtdrugname=(TextView)findViewById(R.id.txtdrugname);
txtpronum=(TextView)findViewById(R.id.txtproductnum);
drugdrugnameenglish=(TextView)findViewById(R.id.txtdrugnameenglish);
drugdrugnamehuaxue=(TextView)findViewById(R.id.txtdrugnamehuaxue);
drugguige=(TextView)findViewById(R.id.txtdrugguige);


txtname=(TextView)findViewById(R.id.txtdrugname);
drugcreatetime=(TextView)findViewById(R.id.txtdrugcreatetime);
drugintprice=(TextView)findViewById(R.id.txtintprice);
drugbrizhu=(TextView)findViewById(R.id.txtbeizhu);
drugyouxiaoqi=(TextView)findViewById(R.id.txteffectivetime);
drugoutprice=(TextView)findViewById(R.id.txtoutprice);
btnyugou=(Button)findViewById(R.id.btnyugou);
exitB=(Button)findViewById(R.id.exitB);
//获取Druginfo的传值

Bundle extras = getIntent().getExtras(); 

numString=String.valueOf(extras.getInt("drugnum"));
txtdrugid.setText(extras.getString("drugid")); 
txtpronum.setText(numString+extras.getString("drugDanweiString"));
id=extras.getString("drugid");
name=extras.getString("drugname");
 
drugcreatetime.setText(extras.getString("drugcreatetime")); 
drugintprice.setText(extras.getString("drugintprice"));
drugoutprice.setText(extras.getString("drugoutprice")); 
  drugyouxiaoqi.setText(extras.getString("drugyouxiaoqi"));
  drugbrizhu.setText(extras.getString("drugbrizhu"));
  txtname.setText(extras.getString("drugname"));
 IDD=extras.getString("ID");
 
 drugdrugnameenglish.setText(extras.getString("drugdrugnameenglish"));
 drugdrugnamehuaxue.setText(extras.getString("drugdrugnamehuaxue"));
 drugguige.setText(extras.getString("drugguige"));
 
    exitB.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
			
			Exit.this.finish();
		}
	});
//跳转到预购界面
  btnyugou.setOnClickListener(new View.OnClickListener() {
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
				Intent intent = new Intent(Exit.this,Myyugouinfo.class); 
		System.out.println(id);
		intent.putExtra("drugid",id);
		intent.putExtra("dname", name);
		String nString=name;
		intent.putExtra("ID", IDD);
		intent.putExtra("kucunnum", numString);
		intent.putExtra("price",drugoutprice.getText());
		Myyugouinfo.priceDouble=Float.valueOf(drugoutprice.getText().toString());
        startActivity(intent); 
	}
});
//查看商品库存
  
  txtpronum.setOnClickListener(new View.OnClickListener() {
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		
		
		
	}
});
layout=(LinearLayout)findViewById(R.id.exit_layout); 
layout.setOnClickListener(new OnClickListener() { 
@Override 
public void onClick(View v) { 
// TODO Auto-generated method stub 
Toast.makeText(getApplicationContext(), "提示：点击窗口外部关闭窗口！", 
Toast.LENGTH_SHORT).show(); 
} 
}); 
} 
@Override 
public boolean onTouchEvent(MotionEvent event){ 
finish(); 
return true; 
} 
public void exitbutton1(View v) { 
this.finish(); 
} 
public void exitbutton0(View v) { 
this.finish(); 

} 

@Override
public boolean onKeyDown(int keyCode, KeyEvent event)//主要是对这个函数的复写
{
 // TODO Auto-generated method stub

 if((keyCode == KeyEvent.KEYCODE_BACK)&&(event.getAction() == KeyEvent.ACTION_DOWN))
 {
	Exit.this.finish();
          
  return true;
 }
 return super.onKeyDown(keyCode, event);
}


} 