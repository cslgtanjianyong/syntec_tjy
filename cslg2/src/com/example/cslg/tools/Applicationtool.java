package com.example.cslg.tools;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Vibrator;

public class Applicationtool {
	 private static Vibrator vibrator=null;
	 
	 
		 public static void Vibrate(final Activity activity, long milliseconds) {   
	            Vibrator vib = (Vibrator) activity.getSystemService(Service.VIBRATOR_SERVICE);   
	            vib.vibrate(milliseconds);   
	     }   
	     public static void Vibrate(final Activity activity, long[] pattern,boolean isRepeat) {   
	            Vibrator vib = (Vibrator) activity.getSystemService(Service.VIBRATOR_SERVICE);   
	            vib.vibrate(pattern, isRepeat ? 1 : -1);   
	     }   
public static String sysytemtime() {
	 	SimpleDateFormat    formatter    =   new    SimpleDateFormat    ("MM月dd日    HH:mm:ss");       
		Date    curDate    =   new    Date(System.currentTimeMillis());//获取当前时间       
		String    time    =    formatter.format(curDate);
		return time; 
		
}

}