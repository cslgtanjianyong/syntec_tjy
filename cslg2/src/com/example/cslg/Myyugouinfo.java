package com.example.cslg;

import java.text.DecimalFormat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.Thread.MyAsyncGetDazheEduInfo;
import com.example.Thread.MyAsyncPutYugouInfo;
import com.example.cslg.tools.AppManager;
import com.example.cslg.tools.Applicationtool;
import com.example.cslg.tools.tools;
import com.example.source.Customer;
public class Myyugouinfo extends Activity {
public static int discount;
public static Boolean b=false;
private TextView txtcodeID;
private TextView txtcodeName;
private String customerId;
private EditText editTextNum;
private Button shangchuanbutton;
private Spinner SpinnerCustomerName;
private TextView txtyouhuitotalprice;
public static Float priceDouble;
private TextView txttotalprice;
private EditText txtyugoubeizhu;
private String ID;//ҩƷID
private String kucunnum;

private static  String[] m;  //���spinner
   private ArrayAdapter<String> adapter;  
   class SpinnerSelectedListener implements OnItemSelectedListener{  
	  
	    public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,  
	            long arg3) {  
	     
	    }  
	    
	    public void onNothingSelected(AdapterView<?> arg0) {  
	    }  
	}  
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.myyugouup);
		AppManager.getAppManager().addActivity(this);
	m= new String[tools.customers.size()];
		for(int i=0;i<tools.customers.size();i++)
		{if (tools.customers.get(i).getCustomerName().equals("")||tools.customers.get(i).getCustomerName().equals(" ")||tools.customers.get(i).getCustomerName().equals(null)) {
			continue;
		}
		
			m[i]=tools.customers.get(i).getCustomerName();
		}
		init();
		Bundle bundle = new Bundle();
	    bundle = this.getIntent().getExtras();
	    ID=bundle.getString("ID");
	    kucunnum=bundle.getString("kucunnum");
	    txtcodeID.setText(bundle.getString("drugid"));
	    String nString=bundle.getString("dname");
	    txtcodeName.setText(nString);
	    System.out.println(txtcodeID.getText().toString());
	    System.out.println(txtcodeName.getText().toString());
		MyAsyncGetDazheEduInfo myAsyncGetDazheEduInfo=new MyAsyncGetDazheEduInfo(ID);
		myAsyncGetDazheEduInfo.execute();
		
		    //��������ҩƷ�Ż�
		    editTextNum.addTextChangedListener(new TextWatcher() {
				
				@Override
				public void onTextChanged(CharSequence s, int start, int before, int count) {
					// ������ִ��
					if (editTextNum.getText().toString()=="") {
						return;
					}
					if (editTextNum.getText().toString()==""||editTextNum.getText()==null||editTextNum.getText().toString()==" ") {
						shangchuanbutton.setEnabled(false);
					}
				}
				
				@Override
				public void beforeTextChanged(CharSequence s, int start, int count,
						int after) {
					// ����ǰִ��
					
				}
				
				@Override
				public void afterTextChanged(Editable s) {
					// �����ִ��
					
if (editTextNum.getText().toString()=="") {
	return;
}
					String nu=editTextNum.getText().toString();
					if (Integer.parseInt(nu)>Integer.parseInt(kucunnum))
					{
						Toast toast = Toast.makeText(Myyugouinfo.this.getApplicationContext(),
				    			"��������㣡", Toast.LENGTH_SHORT);
				    			toast.setGravity(Gravity.CENTER, 0, 0);
				    			toast.show();	
						
						return;
					}
					    if(discount==0) 
					    {
					    	try {
					    		DecimalFormat   df   =   new   DecimalFormat("#####0.00");   
					      String totpString=  df.format(Double.parseDouble(editTextNum.getText().toString())*priceDouble);  
					      String youp=  df.format(Double.parseDouble(editTextNum.getText().toString())*priceDouble);  
					    	txttotalprice.setText(totpString);
					    	txtyouhuitotalprice.setText(youp);	
					    	}
					    	catch(Exception ex) {txttotalprice.setText("0");
					    	txtyouhuitotalprice.setText("0");	}
					    }
					    else 
					    {
					    	try {
					    		double c=discount;
					    	double ss=c/100;
					    		DecimalFormat   df   =   new   DecimalFormat("#####0.00");   
							      String totpString=  df.format(Double.parseDouble(editTextNum.getText().toString())*priceDouble);  
							      String youp=  df.format(Double.parseDouble(editTextNum.getText().toString())*priceDouble*ss);  
					    	
					    	txttotalprice.setText(totpString);
					    	txtyouhuitotalprice.setText(youp);
					    	}
					    	catch(Exception ex) {txttotalprice.setText("0");
					    	txtyouhuitotalprice.setText("0");}
					    }
				
				}
			});
		
		
		


	    shangchuanbutton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (editTextNum.getText().toString()==""||editTextNum.getText().toString()=="0") {
			
					
					return;
				}
            
               String nu=editTextNum.getText().toString();
				if (Integer.parseInt(nu)>Integer.parseInt(kucunnum))
				{
					Toast toast = Toast.makeText(Myyugouinfo.this.getApplicationContext(),
			    			"��������㣡", Toast.LENGTH_SHORT);
			    			toast.setGravity(Gravity.CENTER, 0, 0);
			    			toast.show();	
					
					return;
				}
				if (SpinnerCustomerName.getSelectedItem().toString()==""||SpinnerCustomerName.getSelectedItem().toString()==null) {
					Toast toast = Toast.makeText(Myyugouinfo.this.getApplicationContext(),
			    			"���Ȳ鿴��������", Toast.LENGTH_SHORT);
			    			toast.setGravity(Gravity.CENTER, 0, 0);
			    			toast.show();	
					return;
				}
               
				//ǰ��Ϊ�Լ���id������Ϊ�˿�id
				 Intent intentw = new Intent(Myyugouinfo.this,LoadingActivity.class); 
		         startActivity(intentw); 
			
				for(Customer c:tools.customers)
				{
					if(c.getCustomerName().equals(SpinnerCustomerName.getSelectedItem()))
					{
						customerId=String.valueOf(c.getId());
					}
				}
				
				
				
				MyAsyncPutYugouInfo myAsyncGetYouhuiInfo =new MyAsyncPutYugouInfo("1",ID,editTextNum.getText().toString(),customerId, txtyouhuitotalprice.getText().toString(),txtyugoubeizhu.getText().toString(),Myyugouinfo.this,SpinnerCustomerName.getSelectedItem().toString(),txtcodeName.getText().toString(),editTextNum.getText().toString(),txtyouhuitotalprice.getText().toString());	
				myAsyncGetYouhuiInfo.execute();
					
				
			}
		});
	}
	
private void init() {
	SpinnerCustomerName = (Spinner) findViewById(R.id.SpinnerCustomerName);  
	 //����ѡ������ArrayAdapter��������  
	
	
    adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,m);  
  
    //���������б�ķ��  
    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);  
      
    //��adapter ��ӵ�spinner��  
    SpinnerCustomerName.setAdapter(adapter);  
      
    //����¼�Spinner�¼�����    
    SpinnerCustomerName.setOnItemSelectedListener(new SpinnerSelectedListener());  
      
    //����Ĭ��ֵ  
    SpinnerCustomerName.setVisibility(View.VISIBLE);  
    txtcodeID=(TextView)findViewById(R.id.txtcodeID);
    txtcodeName=(TextView)findViewById(R.id.txtcodeName);
    editTextNum=(EditText)findViewById(R.id.editTextNum);
    shangchuanbutton=(Button)findViewById(R.id.btnputyugou);
 txtyouhuitotalprice=(TextView)findViewById(R.id.txtyouhuitotalprice);
  txttotalprice=(TextView)findViewById(R.id.txttotalprice);
  txtyugoubeizhu=(EditText)findViewById(R.id.txtyugoubeizhu);
}
public  void savehistory(String yugouname,String yugoudrugname,String yugoudrugnum,String yugoudrugprice) {
	 SharedPreferences sharedPreferencesget = getSharedPreferences("yugouhistorys", Context.MODE_PRIVATE);
		//getString()�ڶ�������Ϊȱʡֵ�����preference�в����ڸ�key��������ȱʡֵ
		String lastname=sharedPreferencesget.getString("yugouname", "");
		String lasttime=sharedPreferencesget.getString("yugoutime", "");
		String lastdrugname=sharedPreferencesget.getString("yugoudrugname", "");
		String lastdrugnum=sharedPreferencesget.getString("yugoudrugnum", "");
		String lastdrugprice=sharedPreferencesget.getString("yugoudrugprice", "");
	 if (lastname=="") {
		SharedPreferences sharedPreferences =getSharedPreferences("yugouhistorys", Context.MODE_PRIVATE);
	Editor editor = sharedPreferences.edit();//��ȡ�༭��
	editor.putString("yugouname", yugouname+"@");
	editor.putString("yugoudrugname", yugoudrugname+"@");
	editor.putString("yugoudrugnum", yugoudrugnum+"@");
	editor.putString("yugoudrugprice", yugoudrugprice+"@");
	editor.putString("yugoutime", Applicationtool.sysytemtime().toString()+"@");
	editor.commit();//�ύ�޸�
	}
	 else {
		 SharedPreferences sharedPreferences =getSharedPreferences("yugouhistorys", Context.MODE_PRIVATE);
			Editor editor = sharedPreferences.edit();//��ȡ�༭��
			editor.putString("yugouname",lastname+yugouname+"@");
			editor.putString("yugoudrugname",lastdrugname+ yugoudrugname+"@");
			editor.putString("yugoudrugnum",lastdrugnum+ yugoudrugnum+"@");
			editor.putString("yugoudrugprice",lastdrugprice+ yugoudrugprice+"@");
			
			editor.putString("yugoutime", lasttime+Applicationtool.sysytemtime().toString()+"@");
			editor.commit();//�ύ�޸�
	}
	 
	
}
public void dereturn() {
	
	 SharedPreferences sharedPreferences =getSharedPreferences("yugouhistorys", Context.MODE_PRIVATE);
		Editor editor = sharedPreferences.edit();//��ȡ�༭��
		editor.remove("yugouhistorys");
		editor.commit();//�ύ�޸�
}
}