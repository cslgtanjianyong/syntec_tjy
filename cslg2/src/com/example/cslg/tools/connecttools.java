package com.example.cslg.tools;

import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import android.widget.CompoundButton;
import com.joboevan.push.tool.PushManager;
import android.provider.Settings.Secure;
import android.media.audiofx.BassBoost.Settings;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;
import android.content.DialogInterface.*;
public class connecttools {

 	
 	
 		public static boolean isNetworkAvailable(Context context) {
 			ConnectivityManager connectivity = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
 			if (connectivity == null) {
 				Log.i("NetWorkState", "Unavailabel");
 				return false;
 			} else {
 				NetworkInfo[] info = connectivity.getAllNetworkInfo();
 				if (info != null) {
 					for (int i = 0; i < info.length; i++) {
 						if (info[i].getState() == NetworkInfo.State.CONNECTED) {
 							Log.i("NetWorkState", "Availabel");
 							return true;
 						}
 					}
 				}
 			}
 			return false;
 		}

}
