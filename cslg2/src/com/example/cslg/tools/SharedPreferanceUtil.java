package com.example.cslg.tools;
//保存密码工具类
import android.R.bool;
import android.R.integer;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SharedPreferanceUtil {
private SharedPreferences sp;
private Editor editor;
private final static String sp_name="mydata";
private final static int MODE =Context.MODE_WORLD_READABLE+Context.MODE_WORLD_WRITEABLE;
public SharedPreferanceUtil(Context context) {
	sp=context.getSharedPreferences(sp_name, MODE);
	editor=sp.edit();
	
	
}
	
public boolean save(String key,String value) {
	editor.putString(key, value);
	return editor.commit();
	
}
public String read(String key){
	String string=null;
	string=sp.getString(key, null);
	return string;
	
}
}
