package com.example.cslg.tools;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import org.jsoup.Jsoup;

public class City {

	private static String ip;
	private static String city;

	// 得到IP地址
	public void getCityIP() {
		URL url;
		URLConnection conn = null;
		InputStream is = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		String str = "";
		org.jsoup.nodes.Document doc;
		try {
			url = new URL("http://city.ip138.com/city.asp");
			conn = url.openConnection();
			is = conn.getInputStream();
			isr = new InputStreamReader(is);
			br = new BufferedReader(isr);
			String input = "";
			while ((input = br.readLine()) != null) {
				str += input;
			}
			doc = Jsoup.parse(str);
			String ip1 = doc.body().text();
			int start = ip1.indexOf("[");
			int end = ip1.indexOf("]");
			setIp(ip1.substring(start + 1, end));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//根据IP得到城市
	public void getCityByIp() {
		try {
			URL url = new URL("http://whois.pconline.com.cn/ip.jsp?ip=" + getIp());
			HttpURLConnection connect = (HttpURLConnection) url
					.openConnection();
			InputStream is = connect.getInputStream();
			ByteArrayOutputStream outStream = new ByteArrayOutputStream();
			byte[] buff = new byte[256];
			int rc = 0;
			while ((rc = is.read(buff, 0, 256)) > 0) {
				outStream.write(buff, 0, rc);
				
			}
			System.out.println(outStream);
			byte[] b = outStream.toByteArray();
			
			// 关闭
			outStream.close();
			is.close();
			connect.disconnect();
			String address = new String(b,"GBK");
			if (address.startsWith("北")||address.startsWith("上")||address.startsWith("重")){
				setCity(address.substring(0,address.indexOf("市")));
			}
			if (address.indexOf("省") != -1) {
				setCity(address.substring(address.indexOf("省") + 1, address.indexOf("市")));
			}
			System.out.println(getCity());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
	public static String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

}
