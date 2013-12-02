package com.example.cslg.yibumethod;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;

import android.os.AsyncTask;
import android.webkit.URLUtil;
import android.widget.ProgressBar;
import android.widget.TextView;

public class AsycTaskClass extends AsyncTask<String, Integer, String> {
private ProgressBar downPress;
private String url;
private TextView tv;
public AsycTaskClass(ProgressBar pb,TextView tv,String url) {
downPress=pb;
this.url=url;
this.tv=tv;
}

@Override
protected void onPreExecute() {
tv.setText("下载中..");
System.out.println("onPreExecute");
}

protected void onProgressUpdate(Integer[] values) {
downPress.setProgress(values[0]);
System.out.println("onProgressUpdate ");
};

protected void onPostExecute(String result) {
System.out.println("onPostExecute "+result);
tv.setText("下载结束...");
};

@Override
protected void onCancelled() {
System.out.println("onCancelled ");
}

@Override
protected String doInBackground(String... params) {
try {
//tv.setText("开始中...");//多次 启动下载task时 此处会报错
HttpURLConnection c=(HttpURLConnection) (new URL(url)).openConnection();
InputStream ips=c.getInputStream();
int max=c.getContentLength();
byte[] bt=new byte[1024];
int len=-1;
int downLen=0;
while((len=ips.read(bt))!=-1)
{
downLen+=len;
int baiF=(downLen*100/max);
publishProgress(baiF);
}

ips.close();
c.disconnect();


} catch (MalformedURLException e) {
e.printStackTrace();
} catch (IOException e) {
e.printStackTrace();
}
return "execute end ";
}

}