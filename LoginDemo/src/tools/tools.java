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
	//登录的地址wsdl
	public static final String Login_WSDL="http://saturn.syntecclub.com.tw:8080/NaNaWeb/services/PortalIntegrationEFGP?wsdl";
	
	//workflow的wsdl
	public static final String WORKFLOW_WSDL="http://saturn.syntecclub.com.tw:8080/NaNaWeb/services/WorkflowService?wsdl";
	
	//取得在线人数的methoad 无参数需要
	public static final String getOnlineUsers="getOnlineUsers";
	
	//
	public static final String fetchProcInstanceWithSerialNo="getOnlineUsers";
	
	public static Process parse(String xmlDoc) {     
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
 

}
