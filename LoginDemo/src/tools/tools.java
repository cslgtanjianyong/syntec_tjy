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
	//登录的地址wsdl
	public static final String Login_WSDL="http://saturn.syntecclub.com.tw:8080/NaNaWeb/services/PortalIntegrationEFGP?wsdl";
	
	//workflow的wsdl
	public static final String WORKFLOW_WSDL="http://saturn.syntecclub.com.tw:8080/NaNaWeb/services/WorkflowService?wsdl";
	
	//取得用户所有正在参与的流程流水号的method 需要pProcessIds（可为空）pUserId（不能为空）
	public static final String fetchToDoWorkItem="fetchToDoWorkItem";
	//
	public static String userId="1";
	
	public static List<Process> processWorkingList=new ArrayList<Process>();
	
	public static Process xmlToProcess(String xmlDoc) {     
	    // 创建一个新的字符串     
		Log.i("11111111111111111111",xmlDoc);
			Process process=new Process();
		    StringReader xmlString = new StringReader(xmlDoc);     
		    // 创建新的输入源SAX 解析器将使用 InputSource 对象来确定如何读取 XML 输入     
		    InputSource source = new InputSource(xmlString);     
		    // 创建一个新的SAXBuilder     
		    SAXBuilder saxb = new SAXBuilder();     
		   
		    List result = null;     
		    try {     
		        result = new ArrayList();     
		        // 通过输入源构造一个Document     
		        Document doc = saxb.build(source);     
		        // 取的根元素     
		        org.jdom.Element root = doc.getRootElement();     
		   
		        // 得到根元素所有子元素的集合             
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
				// 创建一个新的字符串     
				Log.i("11111111111111111111",xmlDoc);
				List<Process> processWorkingList=new ArrayList<Process>();
				
			    StringReader xmlString = new StringReader(xmlDoc);     
			    // 创建新的输入源SAX 解析器将使用 InputSource 对象来确定如何读取 XML 输入     
			    InputSource source = new InputSource(xmlString);     
			    // 创建一个新的SAXBuilder     
			    SAXBuilder saxb = new SAXBuilder();     
			   
			    List result = null;     
			    try {     
			        result = new ArrayList();     
			        // 通过输入源构造一个Document     
			        Document doc = saxb.build(source);     
			        // 取的根元素     
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
