package com.example.cslg.tools;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Gravity;
import android.widget.Toast;

import com.example.cslg.LoadingActivity;
import com.example.cslg.LoginActivity;
import com.example.cslg.Mainmenu;
import com.example.cslg.ScrollUIActivity;
import com.example.cslg.mainSreen;
import com.example.source.Customer;
import com.example.source.Staff;
import com.joboevan.push.tool.Tool;

public class tools {
	public static List<Customer> customers=new ArrayList<Customer>();
	public static Staff _me;
public static String sessionid=null;
//private static String ip="http://192.168.1.37:8080";
private static String ip="http://210.28.164.2:9090";
//登录网址
public static String LoginUrl=ip+"/medicine/LoginAction_checkUser.action";
//药品信息查询网址
//public static String LoginUrl="http://10.16.16.104:8080/ServerForGETMethod/ServerForGETMethod?userRegName=qq&userPassword=qq";
public static String ProUrl=ip+"/medicine/ProductAction_queryProductsByPage.action";
//拜访信息上传
public static String VisitUrl=ip+"/medicine/VisitInfoAction_sendVisitInfo.action";
//修改密码地址
public static String UpdatePasswordUrl=ip+"/medicine/StaffAction_updateUser.action";
//根据药品类型查询网址
public static String MedTypeSearchUrl=ip+"/medicine/ProductAction_queryAllKinds.action";
//根据商品id查库存
public static String KucunSearchUrl=ip+"/medicine/ProductAction_queryKuCunByProId.action";
//查询任务
public static String CustomerUrl=ip+"/medicine/WorksAction_getWorks.action";
//优惠消息网址
public static String YouhuiSearchUrl=ip+"/medicine/DiscountAction_queryDiscount.action";
//回复消息网址
public static String RemsgUrl=ip+"/medicine/NotificationAction_saveNotificationReply.action";
//发送list列表信息给服务器
public static String YugouUrl=ip+"/medicine/OrderAction_orderProduct.action";
//查询单个药品优惠信息
public static String GetYouhuiUrl=ip+"/medicine/DiscountAction_getdiscountByProid.action";

//查询当月工作记录网址
public static String GetVisitInfo=ip+"/medicine/VisitInfoAction_getMonthVisitInfo.action";


public static HttpResponse sendLoginInfo(List<NameValuePair> list,String url) {

	String urlString=url;
	HttpPost httprequest = new HttpPost(urlString);	
	// 想服务器发送验证请求，并将请求参数封装到list对象中
	List<NameValuePair> parmsList = list;
	try {
		if(tools.sessionid!=null)
		{
		httprequest.setHeader("Cookie", tools.sessionid);
		}
		httprequest.setEntity(new UrlEncodedFormEntity(parmsList,
				HTTP.UTF_8));
		DefaultHttpClient	defaultHttpClient =new DefaultHttpClient();
		defaultHttpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 5000);  
		defaultHttpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 5000);
		HttpResponse httpResponse = defaultHttpClient.execute(httprequest);
	
	
		
		
		return httpResponse;
	} catch (Exception e) {
		// TODO: handle exception
		new Mainmenu().torst();
		
	e.printStackTrace();
	return null;
	}

}
//通过传入的HttpResponse获得jspnObject
public static JSONObject ReceiveJSONObject(HttpResponse httpResponse)
	{
	try{

			
	if (httpResponse.getStatusLine().getStatusCode() == 200)
		// 接受服务器回发验证结果
		{
			String strresult = EntityUtils.toString(httpResponse.getEntity());
			JSONObject jspnObject =new JSONObject(strresult);
			
			
			TimerTask task = new TimerTask(){   
			    public void run(){
			    	
			    	  return ;
			     
			    }   
			};   
			Timer timer = new Timer(); 
			timer.schedule(task, 6000); 
			
			
			
			
		return jspnObject;
	}
	else
	{
		System.out.println("网络异常");
	}
	}
	
	catch(Exception ex){}
	return null;
}

public static String getSessionid() {
	return sessionid;
}

public static void setSessionid(String sessionid) {
	tools.sessionid = sessionid;
}
}