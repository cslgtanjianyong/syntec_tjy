package Webservice;
import tools.*;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParserFactory;

import org.kobjects.base64.Base64;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.AndroidHttpTransport;
import org.ksoap2.transport.HttpTransportSE;
import org.xml.sax.InputSource;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import tools.tools;

import android.R.string;
import android.app.DownloadManager.Request;
import android.util.Log;
import android.util.Xml;
public class MyWebServiceHelper {
	 //WSDL文档中的命名空间     
		private static final String targetNameSpace="http://webservice.nana.dsc.com/";
	    //WSDL文档中的URL    
		private static final String WSDL="http://saturn.syntecclub.com.tw:8080/NaNaWeb/services/PortalIntegrationEFGP?wsdl";
	       	
		private static final String getOnlineUsers="getOnlineUsers";
		
		private static final String checkPortalUserPassword ="checkPortalUserPassword";
		//http://saturn.syntecclub.com.tw:8080/NaNaWeb/services/VamIntegrationEFGP?method=getOnlineUsers
		private static final String fetchProcInstanceWithSerialNo="fetchProcInstanceWithSerialNo";
		
		//取得用户所有正在参与的流程流水号的method 需要pProcessIds（可为空）pUserId（不能为空）
		public static final String fetchToDoWorkItem="fetchToDoWorkItem";
		
	    /********
	     * 根据流程号获得流程简单信息
	     * @return      */     
		public  Object  getResult(){
	    	Log.i("UserInfo","-------------连接中-----------/n");
	        List<String> provinces=new ArrayList<String>();
	        SoapObject soapObject=new SoapObject(targetNameSpace,fetchProcInstanceWithSerialNo);
	      //soapObject.addProperty("pUserId", "C1302000");//调用的方法参数与参数值（根据具体需要可选可不选）   用户名
	      //soapObject.addProperty("pEncryptedPwd", "C1302");//调用的方法参数与参数值（根据具体需要可选可不选） 密码         
	        soapObject.addProperty("pProcessInstanceSerialNo","CN_ResignationApplication00000002");
	        SoapSerializationEnvelope envelope=new SoapSerializationEnvelope(SoapEnvelope.VER11);     
	        envelope.setOutputSoapObject(soapObject);//     
	        envelope.bodyOut=soapObject;    
	        Log.i("UserInfo","-------------连接中-----------/n");
	        AndroidHttpTransport httpTranstation=new AndroidHttpTransport(tools.WORKFLOW_WSDL);	
	        httpTranstation.debug=true;
	        //或者HttpTransportSE httpTranstation=new HttpTransportSE(WSDL);         
	        try {
				httpTranstation.call(targetNameSpace+fetchProcInstanceWithSerialNo, envelope);
				
				SoapObject result =(SoapObject)envelope.bodyIn;
				Log.i("11111111111111","-----------------");
				//SoapObject a=(SoapObject)result.getProperty(0);
				Log.i("11111111111111",result.getProperty(0).toString());
				return result.getProperty(0).toString();
				//SoapObject soap = (SoapObject)result.getProperty(0);
			  
				}
				
			   
			 catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (XmlPullParserException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
	        

	    }
		  /********
	     * 根据登录人ID获取正在处理的流程
	     * @return      */     
		public  Object  getToDoWorkItem(){
	    	Log.i("UserInfo","-------------连接中-----------/n");
	        List<String> provinces=new ArrayList<String>();
	        SoapObject soapObject=new SoapObject(targetNameSpace,fetchToDoWorkItem);
	        soapObject.addProperty("pProcessIds", "");//调用的方法参数与参数值（根据具体需要可选可不选） 密码         
	        soapObject.addProperty("pUserId",tools.userId);
	        SoapSerializationEnvelope envelope=new SoapSerializationEnvelope(SoapEnvelope.VER11);     
	        envelope.setOutputSoapObject(soapObject);//     
	        envelope.bodyOut=soapObject;    
	        Log.i("UserInfo","-------------连接中-----------/n");
	        AndroidHttpTransport httpTranstation=new AndroidHttpTransport(tools.WORKFLOW_WSDL);	
	        httpTranstation.debug=true;
	        //或者HttpTransportSE httpTranstation=new HttpTransportSE(WSDL);         
	        try {
				httpTranstation.call(targetNameSpace+fetchToDoWorkItem, envelope);
				
				SoapObject result =(SoapObject)envelope.bodyIn;
				Log.i("11111111111111","-----------------");
				//SoapObject a=(SoapObject)result.getProperty(0);
				Log.i("11111111111111",result.getProperty(0).toString());
				return result.getProperty(0).toString();
				//SoapObject soap = (SoapObject)result.getProperty(0);
			  
				}
				
			   
			 catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (XmlPullParserException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
	        

	    }
}
