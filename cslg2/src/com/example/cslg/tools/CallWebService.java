package com.example.cslg.tools;

import org.ksoap2.serialization.SoapObject;
public interface CallWebService {
	public SoapObject callWebservice(String methodName,String cityName);
	public String handleWebServiceResult();
}
