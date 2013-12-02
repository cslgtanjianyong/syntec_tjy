package tools;


import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;


import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.xml.sax.InputSource;

import android.util.Log;

public class tools {
	//��¼�ĵ�ַwsdl
	public static final String Login_WSDL="http://saturn.syntecclub.com.tw:8080/NaNaWeb/services/PortalIntegrationEFGP?wsdl";
	
	//workflow��wsdl
	public static final String WORKFLOW_WSDL="http://saturn.syntecclub.com.tw:8080/NaNaWeb/services/WorkflowService?wsdl";
	
	//ȡ���û��������ڲ����������ˮ�ŵ�method ��ҪpProcessIds����Ϊ�գ�pUserId������Ϊ�գ�
	public static final String fetchToDoWorkItem="fetchToDoWorkItem";
	//
	public static String userId="1";
	
	public static List<Process> processWorkingList=new ArrayList<Process>();
	
	public static Process xmlToProcess(String xmlDoc) {     
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
	public static List<Process> xmlToListProcess(String xmlDoc) {     
				// ����һ���µ��ַ���     
				Log.i("11111111111111111111",xmlDoc);
				List<Process> processWorkingList=new ArrayList<Process>();
				
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
			       
			        Log.i("111111111111111111",String.valueOf(root.getChildren().get(0).toString()));
			   
			        List<Element> classes = root.getChildren();
			        classes.size();
			        for(Element e:classes)
			        {
			        	Process p=new Process();
			            p.setOID(e.getChild("workItemOID").getText());
			            Log.i("11111111111111111", e.getChild("workItemOID").getText());
			            p.setActivityId(e.getChild("processSerialNumber").getText());
			            p.setActivityId(e.getChild("activityId").getText());
			            processWorkingList.add(p);
			        }    
			   } catch (JDOMException e) {     
			       e.printStackTrace();     
			    } catch (IOException e) {     
			       e.printStackTrace();     
			    }     
			return processWorkingList;
			}  
 

}
