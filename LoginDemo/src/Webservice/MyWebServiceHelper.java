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
	 //WSDL�ĵ��е������ռ�     
		private static final String targetNameSpace="http://webservice.nana.dsc.com/";
	    //WSDL�ĵ��е�URL    
		private static final String WSDL="http://saturn.syntecclub.com.tw:8080/NaNaWeb/services/PortalIntegrationEFGP?wsdl";
	       	
		private static final String getOnlineUsers="getOnlineUsers";
		
		private static final String checkPortalUserPassword ="checkPortalUserPassword";
		//http://saturn.syntecclub.com.tw:8080/NaNaWeb/services/VamIntegrationEFGP?method=getOnlineUsers
		private static final String fetchProcInstanceWithSerialNo="fetchProcInstanceWithSerialNo";
	    /********
	     * ����ݣ�������ʡ�ݺͳ�����Ϣ
	     * @return      */     
		public  Object  getResult(){
	    	Log.i("UserInfo","-------------������-----------/n");
	        List<String> provinces=new ArrayList<String>();
	        SoapObject soapObject=new SoapObject(targetNameSpace,fetchProcInstanceWithSerialNo);
	      //soapObject.addProperty("pUserId", "C1302000");//���õķ������������ֵ�����ݾ�����Ҫ��ѡ�ɲ�ѡ��   �û���
	      //soapObject.addProperty("pEncryptedPwd", "C1302");//���õķ������������ֵ�����ݾ�����Ҫ��ѡ�ɲ�ѡ�� ����         
	        soapObject.addProperty("pProcessInstanceSerialNo","CN_ResignationApplication00000002");
	        SoapSerializationEnvelope envelope=new SoapSerializationEnvelope(SoapEnvelope.VER11);     
	        envelope.setOutputSoapObject(soapObject);//     
	        envelope.bodyOut=soapObject;    
	        Log.i("UserInfo","-------------������-----------/n");
	        AndroidHttpTransport httpTranstation=new AndroidHttpTransport(tools.WORKFLOW_WSDL);	
	        httpTranstation.debug=true;
	        //����HttpTransportSE httpTranstation=new HttpTransportSE(WSDL);         
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
}
