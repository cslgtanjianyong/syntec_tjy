package tools;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jdom.Document;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.xml.sax.InputSource;

import android.sax.Element;
import android.util.Log;

public class tools {
	//��¼�ĵ�ַwsdl
	public static final String Login_WSDL="http://saturn.syntecclub.com.tw:8080/NaNaWeb/services/PortalIntegrationEFGP?wsdl";
	
	//workflow��wsdl
	public static final String WORKFLOW_WSDL="http://saturn.syntecclub.com.tw:8080/NaNaWeb/services/WorkflowService?wsdl";
	
	//ȡ������������methoad �޲�����Ҫ
	public static final String getOnlineUsers="getOnlineUsers";
	
	//
	public static final String fetchProcInstanceWithSerialNo="getOnlineUsers";
	
	public static Process parse(String xmlDoc) {     
	    // ����һ���µ��ַ���     
		Log.i("11111111111111111111",xmlDoc);
			Process process=new Process();
		    StringReader xmlString = new StringReader(xmlDoc);     
		    // �����µ�����ԴSAX ��������ʹ�� InputSource ������ȷ����ζ�ȡ XML ����     
		    InputSource source = new InputSource(xmlString);     
		    // ����һ���µ�SAXBuilder     
		    SAXBuilder saxb = new SAXBuilder();     
		   
		    List result = null;     
		    try {     
		        result = new ArrayList();     
		        // ͨ������Դ����һ��Document     
		        Document doc = saxb.build(source);     
		        // ȡ�ĸ�Ԫ��     
		        org.jdom.Element root = doc.getRootElement();     
		   
		        // �õ���Ԫ��������Ԫ�صļ���             
		        process.setProcessId(root.getChildText("processId").toString());
		        process.setProcessName(root.getChildText("processName").toString());
		        process.setRequesterId(root.getChildText("requesterId").toString());
		        process.setRequesterName(root.getChildText("requesterName").toString());
		        process.setSerialNo(root.getChildText("serialNo").toString());
		        process.setState(root.getChildText("state").toString());
		        process.setOID(root.getChildText("OID").toString());
		        process.setSubject(root.getChildText("subject").toString());
		        process.setAbortComment(root.getChildText("abortComment").toString());
		      //process.setCreatedTime(Date.valueOf(root.getChildText("createdTime")));
		        
		             
		   } catch (JDOMException e) {     
		       e.printStackTrace();     
		    } catch (IOException e) {     
		       e.printStackTrace();     
		    }     
		return process;
		}  
 

}
