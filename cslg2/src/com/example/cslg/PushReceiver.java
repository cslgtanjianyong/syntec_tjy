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
		SimpleDateFormat    formatter    =   new    SimpleDateFormat    ("yyyy��MM��dd��    HH:mm:ss     ");     
		Date    curDate    =   new    Date(System.currentTimeMillis());//��ȡ��ǰʱ��     
		String    str    =    formatter.format(curDate);     

		NotificationManager mNM = (NotificationManager) context1.getSystemService(Context.NOTIFICATION_SERVICE); 
		// ����һ��Notification����  
		int icon = R.drawable.notificationicon;  
		CharSequence tickerText = "�ݺ���ҩ���޹�˾֪ͨ��";  
		long when = System.currentTimeMillis();  
		@SuppressWarnings("deprecation")
		Notification notification = new Notification(icon, tickerText, when);  
	
		notification.flags |=Notification.FLAG_AUTO_CANCEL;  
	
		notification.defaults=Notification.DEFAULT_ALL;
	notification.sound = Uri.parse("file:///sdcard/notification/ringer.mp3");
notification.sound = Uri.withAppendedPath(Audio.Media.INTERNAL_CONTENT_URI, "6");

		// ����Notification��title��message����pendingIntent  
		Context context = context1;  
		CharSequence contentTitle = "��˾֪ͨ"; 
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
		// ֪ͨ״̬����ʾNotification  
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
				// ���ӽ��
				int value = bundle.getInt("value");
				switch (value) {
				case Consts.RESULT_Y:
					Log.w("Log", "���ӳɹ�");
					break;
				case Consts.RESULT_N:
					
					Log.w("Log", "����ʧ��");

					
					break;
				}
			} else if (Consts.MESSAGE_KEY_LOGIN.equals(key)) {
				// ��½���
				int value = bundle.getInt("value");
				switch (value) {
				case Consts.RESULT_Y:
					Log.w("Log", "��½�ɹ�");
					
					break;
				case Consts.RESULT_N:
					Log.w("Log", "��½ʧ��");
					
					break;
				}
			} else if(Consts.MESSAGE_KEY_SETALIAS.equals(key)){
				int value = bundle.getInt("value");
				switch (value) {
				case Consts.RESULT_Y:
					Log.w("Log", "�������óɹ�");
					
					break;

				default:
					break;
				}
			}else if (Consts.MESSAGE_KEY_CUSTOM.equals(key)) {
				// �����Զ���������Ϣ
				String value = bundle.getString("value");
				msg=value;
				Log.w("Log", "������Ϣ->" + value);
			}else if(Consts.MESSAGE_KEY_PUSHSTATECHANGED.equals(key)){		//�ͻ��������˵�����״̬
				int value = bundle.getInt("value");
				switch (value) {
				case Consts.PUSH_CONNECTSTATE_CONNECTING:	//ͨ������
					
					Log.e("Log", "����������------------------>");
					
					break;

				case Consts.PUSH_CONNECTSTATE_DISCONNECT:	//ͨ���Ͽ�
					
					Log.e("Log", "�����Ѷ���------------");
					
					
					break;
				}
			}else if(Consts.ACTION_RECEIVER_VERSION.equals(key)){
				int value = bundle.getInt("value");
				switch (value) {
				case Consts.VERSION_LATEST:
					Log.e("Log", "PushTestActivity--------------------------���Ͱ汾�����°汾------>");
					break;
				case Consts.VERSION_OLD_PERMIT:
					Log.e("Log", "PushTestActivity--------------------------���Ͱ汾���ϰ汾������------>");
					break;
				case Consts.VERSION_OLD_REFUSE:
					Log.e("Log", "PushTestActivity--------------------------���Ͱ汾���ϰ汾��������------>");
					break;
				default:
					break;
				}
			}else if(Consts.MESSAGE_BACK_FLAG.equals(key)){		//����֪ͨ���Զ�����Ϣ��FLAGֵ
				flag = bundle.getString("value");
				createNote(msg,flag,context);
				Log.w("Log", "flagֵ--------------->"+flag);
			    
			}

		}
	};
	}
