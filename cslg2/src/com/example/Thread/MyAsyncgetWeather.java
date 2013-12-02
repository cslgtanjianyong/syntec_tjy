package com.example.Thread;

import java.util.List;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.os.AsyncTask;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.example.cslg.Mainmenu;
import com.example.cslg.Weather;
import com.example.cslg.mainSreen;
import com.example.cslg.tools.Applicationtool;
import com.example.cslg.tools.CallWebService;
import com.example.cslg.tools.Weatherlist;
import com.example.cslg.tools.WebServiceUtil;

public class MyAsyncgetWeather extends AsyncTask<String,Integer,String> {//继承AsyncTask  
	private static final String NAMESPACE = "http://WebXml.com.cn/";

	// WebService地址
	private static String URL = "http://www.webxml.com.cn/webservices/weatherwebservice.asmx";
	private static final String METHOD_NAME = "getWeatherbyCityName";
   
	private static String SOAP_ACTION = "http://WebXml.com.cn/getWeatherbyCityName";
	private String weatherToday;
	private SoapObject detail;
	private String weatherNow;
	private String weatherWillBe;
	private String _city;
	private Mainmenu _mainMenu;
	private String weathersky;
	private HttpTransportSE ht = new HttpTransportSE(URL);
	private List<Weatherlist> _wliSt;
	private	SoapSerializationEnvelope envelope;
	private Weather _weaWeather;
	public MyAsyncgetWeather(String city,List<Weatherlist> wList,Weather weather) 
	{_city=city;
	_wliSt=wList;
	_weaWeather=weather;
	}
	/////

	////
	public MyAsyncgetWeather(String city,List<Weatherlist> wList,Mainmenu mainmenu,String temp) 
	{_city=city;
	_wliSt=wList;
	_mainMenu=mainmenu;
	}
    @Override  
    protected String doInBackground(String... params) {//处理后台执行的任务，在后台线程执行  
       
     
        try {  
  

		SoapObject rpc = new SoapObject(NAMESPACE, METHOD_NAME);
		rpc.addProperty("theCityName", _city);
		envelope = new SoapSerializationEnvelope(SoapEnvelope.VER10);
		//SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER10);
		envelope.bodyOut = rpc;
		envelope.dotNet = true;
		envelope.setOutputSoapObject(rpc);
	

		ht.debug = true;
		Thread.sleep(500);
		ht.call(SOAP_ACTION, envelope);
		Thread.sleep(500);
		detail = (SoapObject) envelope.getResponse();
        
        return "t";
        } 
        
        catch (Exception e) {  
        e.printStackTrace();
            return "f";  
        }  
         
  
    }  
      
  
	private void save(SoapObject detail ) {
		
		
		String date = detail.getProperty(6).toString();
		String skyString= detail.getProperty(10).toString();
		// 当天天气
		weatherToday = "\n天气：" + date.split(" ")[1];
		weatherToday = weatherToday + "\n气温："+ detail.getProperty(5).toString();
		weatherToday = weatherToday + "\n风力："+ detail.getProperty(7).toString() + "\n";
		weathersky=skyString.split("：")[5];
		
		 String	sk=weathersky.split("；")[0];
		 String ss=detail.getProperty(1).toString();
		 String uptime=detail.getProperty(4).toString();
		
		if (_wliSt!=null) {
			_wliSt.clear();
		}
		
		Weatherlist wliWeatherlist=new Weatherlist();
		
		wliWeatherlist.set_wendu(weatherToday);
		wliWeatherlist.set_level(sk);
		wliWeatherlist.set_uptime(uptime);
		_wliSt.add(wliWeatherlist);
		

	}
	


	
	
	  protected void onPostExecute(String result) {// 后台任务执行完之后被调用，在ui线程执行   
	     
		  
		  try {
			  
			  if(result=="t")
		      {
		    //  mainSreen.f=true;
		  	save(detail);
		     
		      if (_weaWeather!=null) {
		    	  Weather.progressBarupdateweather.setVisibility(View.INVISIBLE);
			      Weather.btnupdateweather.setVisibility(View.VISIBLE);
			      _weaWeather.txtuptime.setText("更新时间："+ Applicationtool.sysytemtime().toString());
				Toast toast = Toast.makeText(_weaWeather.getApplicationContext(),
		    			"天气更新成功！", Toast.LENGTH_SHORT);
		    			toast.setGravity(Gravity.CENTER, 0, 0);
		    			toast.show();
			}
		      
		      
		      }
		      else
		      {
		    	  if (_weaWeather!=null) {
		    	  Weather.progressBarupdateweather.setVisibility(View.INVISIBLE);
		    	  Weather.btnupdateweather.setVisibility(View.VISIBLE);
			    
					 Toast toast = Toast.makeText(_weaWeather.getApplicationContext(),
			    			"网络异常，天气更新失败！", Toast.LENGTH_SHORT);
			    			toast.setGravity(Gravity.CENTER, 0, 0);
			    			toast.show();
				}
		    	 
			      
		      }
		} catch (Exception e) {
			// TODO: handle exception
			 Toast toast = Toast.makeText(_mainMenu.getApplicationContext(),
		    			"网络异常，天气更新失败！", Toast.LENGTH_SHORT);
		    			toast.setGravity(Gravity.CENTER, 0, 0);
		    			toast.show();
			
		}
		  
		
	  
	        }  
	      
	       
	    protected void onPreExecute() {// 在doInBackground(Params...)之前被调用，在ui线程执行   
	        super.onPreExecute();  
	    }  

	    protected void onCancelled() {// 在ui线程执行   
	        super.onCancelled();  
	    }  
}  
  

