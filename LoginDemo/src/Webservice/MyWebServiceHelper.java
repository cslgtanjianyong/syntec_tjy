package Webservice;
import tools.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParserFactory;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.AndroidHttpTransport;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import tools.tools;

import android.app.DownloadManager.Request;
import android.util.Log;
public class MyWebServiceHelper {
	 //WSDL文档中的命名空间     
		private static final String targetNameSpace="http://webservice.nana.dsc.com/";
	    //WSDL文档中的URL    
		private static final String WSDL="http://saturn.syntecclub.com.tw:8080/NaNaWeb/services/PortalIntegrationEFGP?wsdl";
	       	
		private static final String getOnlineUsers="getOnlineUsers";
		
		private static final String checkPortalUserPassword ="checkPortalUserPassword";
		//http://saturn.syntecclub.com.tw:8080/NaNaWeb/services/VamIntegrationEFGP?method=getOnlineUsers
		private static final String fetchProcInstanceWithSerialNo="fetchProcInstanceWithSerialNo";
	    /********
	     * 获得州，国内外省份和城市信息
	     * @return      */     
		public  void  getLoginInformation(){
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
				Object result =(Object)envelope.getResponse();

			    Log.i("UserInfo","-------------------------------------");
			    Log.i("UserInfo","------------"+result+"---------------");
			    Log.i("UserInfo","-------------------------------------");
			    System.out.println("执行中");
			    SAXParserFactory spf = SAXParserFactory.newInstance(); 
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (XmlPullParserException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        

	    }
}
