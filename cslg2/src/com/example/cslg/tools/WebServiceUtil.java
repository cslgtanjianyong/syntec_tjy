package com.example.cslg.tools;
import java.io.IOException;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;
public class WebServiceUtil implements CallWebService {

	
	String namespace ;
	String url ;
	
	public WebServiceUtil(String namespace, String url) {
		
		this.namespace = namespace;
		this.url = url;
	}

	SoapObject ss = null;
	public SoapObject callWebservice(String methodName,String cityName) {

		
		
		SoapObject soapObject = new SoapObject(namespace, methodName);
		soapObject.addProperty("theCityName", cityName);
		
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.bodyOut = soapObject;
		envelope.dotNet = true ;
		
		HttpTransportSE httpTransportSE = new HttpTransportSE(url);
		httpTransportSE.debug = true ; 
		try {
			httpTransportSE.call(namespace+methodName, envelope);
			if( envelope.getResponse() != null ) {
				SoapObject resultObject = (SoapObject) envelope.bodyIn;
				ss = (SoapObject) resultObject.getProperty(methodName+"Result");
				setSs(ss);
				return ss;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			e.printStackTrace();
		}
		return ss;
	}

	@Override
	public String handleWebServiceResult() {
		String resultString = ss.toString(); 
		return resultString;
	}

	public SoapObject getSs() {
		return ss;
	}

	public void setSs(SoapObject ss) {
		this.ss = ss;
	}
}
