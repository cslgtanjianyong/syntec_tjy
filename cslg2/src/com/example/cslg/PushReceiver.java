package com.example.cslg;

import java.sql.Date;
import java.text.SimpleDateFormat;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore.Audio;
import android.util.Log;

import com.joboevan.push.tool.Consts;

public class PushReceiver extends BroadcastReceiver{
	private static int num=0;
	private static Context contextc;
	private static String msg=null;
	private static String flag=null;
	public static void createNote(String contextString,String flag,Context context1)
	{ 		
		// Applicationtool.zhengdong();
		SimpleDateFormat    formatter    =   new    SimpleDateFormat    ("yyyy年MM月dd日    HH:mm:ss     ");     
		Date    curDate    =   new    Date(System.currentTimeMillis());//获取当前时间     
		String    str    =    formatter.format(curDate);     

		NotificationManager mNM = (NotificationManager) context1.getSystemService(Context.NOTIFICATION_SERVICE); 
		// 创建一个Notification对象  
		int icon = R.drawable.notificationicon;  
		CharSequence tickerText = "惠好制药有限公司通知！";  
		long when = System.currentTimeMillis();  
		@SuppressWarnings("deprecation")
		Notification notification = new Notification(icon, tickerText, when);  
	
		notification.flags |=Notification.FLAG_AUTO_CANCEL;  
	
		notification.defaults=Notification.DEFAULT_ALL;
	notification.sound = Uri.parse("file:///sdcard/notification/ringer.mp3");
notification.sound = Uri.withAppendedPath(Audio.Media.INTERNAL_CONTENT_URI, "6");

		// 定义Notification的title、message、和pendingIntent  
		Context context = context1;  
		CharSequence contentTitle = "公司通知"; 
		try {
			JSONObject jsonObject = new JSONObject(contextString.toString().trim());
			contextString=jsonObject.getString("content");
			//Applicationtool.Vibrate(Mainmenu.this,1000);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		CharSequence contentText = contextString  ;
		Intent notificationIntent = new Intent(context1,Returnmsg.class);
		notificationIntent.putExtra("msgContextString", contextString);
		Returnmsg.contentString=contextString;
		Returnmsg.flag=flag;
		notificationIntent.putExtra("flag", flag);
		SharedPreferences settings = contextc.getSharedPreferences("Mynotification", 0);
		SharedPreferences.Editor editor = settings.edit();
		if(settings.getInt("num", 0)==0) 
		{
			editor.putInt("num", 0);
		}
		else 
		{
			num=settings.getInt("num",0);
		}
		editor.putString("flag"+(num+1), flag);
		editor.putString("msgContextString"+(num+1), contextString);
		editor.putString("datetime"+(num+1), str);
		editor.putInt("num", num+1);
		editor.commit();
		PendingIntent contentIntent = PendingIntent.getActivity(context1, 0, notificationIntent, 0);  
		notification.setLatestEventInfo(context, contentTitle, contentText, contentIntent);  
		// 通知状态栏显示Notification  
		final int HELLO_ID = 1;  
		mNM.notify(HELLO_ID, notification);  
	}
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		contextc=context;
		String action = intent.getAction();
		if (Consts.getACTION_RECEIVER_KEY(context).equals(action)) {
			Bundle bundle = intent.getExtras();
			String key = bundle.getString("key");
			if (Consts.MESSAGE_KEY_CONNECT.equals(key)) {
				// 连接结果
				int value = bundle.getInt("value");
				switch (value) {
				case Consts.RESULT_Y:
					Log.w("Log", "连接成功");
					break;
				case Consts.RESULT_N:
					
					Log.w("Log", "连接失败");

					
					break;
				}
			} else if (Consts.MESSAGE_KEY_LOGIN.equals(key)) {
				// 登陆结果
				int value = bundle.getInt("value");
				switch (value) {
				case Consts.RESULT_Y:
					Log.w("Log", "登陆成功");
					
					break;
				case Consts.RESULT_N:
					Log.w("Log", "登陆失败");
					
					break;
				}
			} else if(Consts.MESSAGE_KEY_SETALIAS.equals(key)){
				int value = bundle.getInt("value");
				switch (value) {
				case Consts.RESULT_Y:
					Log.w("Log", "别名设置成功");
					
					break;

				default:
					break;
				}
			}else if (Consts.MESSAGE_KEY_CUSTOM.equals(key)) {
				// 接收自定义推送信息
				String value = bundle.getString("value");
				msg=value;
				Log.w("Log", "推送信息->" + value);
			}else if(Consts.MESSAGE_KEY_PUSHSTATECHANGED.equals(key)){		//客户端与服务端的连接状态
				int value = bundle.getInt("value");
				switch (value) {
				case Consts.PUSH_CONNECTSTATE_CONNECTING:	//通道连接
					
					Log.e("Log", "推送已连接------------------>");
					
					break;

				case Consts.PUSH_CONNECTSTATE_DISCONNECT:	//通道断开
					
					Log.e("Log", "推送已断了------------");
					
					
					break;
				}
			}else if(Consts.ACTION_RECEIVER_VERSION.equals(key)){
				int value = bundle.getInt("value");
				switch (value) {
				case Consts.VERSION_LATEST:
					Log.e("Log", "PushTestActivity--------------------------推送版本是最新版本------>");
					break;
				case Consts.VERSION_OLD_PERMIT:
					Log.e("Log", "PushTestActivity--------------------------推送版本是老版本，可用------>");
					break;
				case Consts.VERSION_OLD_REFUSE:
					Log.e("Log", "PushTestActivity--------------------------推送版本是老版本，不可用------>");
					break;
				default:
					break;
				}
			}else if(Consts.MESSAGE_BACK_FLAG.equals(key)){		//接收通知或自定义消息的FLAG值
				flag = bundle.getString("value");
				createNote(msg,flag,context);
				Log.w("Log", "flag值--------------->"+flag);
			    
			}

		}
	};
	}
