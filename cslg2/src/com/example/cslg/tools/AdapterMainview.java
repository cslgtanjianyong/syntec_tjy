package com.example.cslg.tools;


import com.example.cslg.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class AdapterMainview extends BaseAdapter {  
	 private Context context;  
	   
	 public  void ImageAdapter(Context context) {  
	  this.context=context;  
	 }  
	   
	 private Integer[] images = {  
	   //九宫格图片的设置  
	   R.drawable.i1,  
	   R.drawable.i1,  
       R.drawable.i2,  
	   R.drawable.i3,  
	   R.drawable.i4,  
	   R.drawable.i5,  
	   
	   R.drawable.i2,  
	   R.drawable.i3,  
	   R.drawable.i4,  
	   R.drawable.i5,  
	    	   };  
	   
	 private String[] texts = {  
	   //九宫格图片下方文字的设置  
	   "公司通知",  //0
	   "产品优惠",  //1
	   "药品查询",  //2
	   "我的任务",  //3
	   "工作记录",  //4
	   "个人信息",  //5
	   "药品查询",  //2
	   "我的任务",  //3
	   "工作记录",  //4
	   "个人信息",  //5
	   };  
	   
	 //get the number  
	 @Override  
	 public int getCount() {  
	  return images.length;  
	 }  
	 
	 @Override  
	 public Object getItem(int position) {  
	  return position;  
	 }  
	 
	 //get the current selector's id number  
	 @Override  
	 public long getItemId(int position) {  
	  return position;  
	 }  
	 
	 //create view method  
	 @Override  
	 public View getView(int position, View view, ViewGroup viewgroup) {  
	  ImgTextWrapper wrapper;  
	  if(view==null) {  
	   wrapper = new ImgTextWrapper();  
	   LayoutInflater inflater = LayoutInflater.from(context);  
	   view = inflater.inflate(R.layout.item, null);  
	   view.setTag(wrapper);  
	   view.setPadding(15, 15, 15, 15);  //每格的间距  
	  } else {  
	   wrapper = (ImgTextWrapper)view.getTag();  
	  }  
	   
  wrapper.imageView = (ImageView)view.findViewById(R.id.MainActivityImage);  
	  wrapper.imageView.setBackgroundResource(images[position]);  
	  wrapper.textView = (TextView)view.findViewById(R.id.MainActivityText);  
	  wrapper.textView.setText(texts[position]);  
	    
	  return view;  
	 }  
	}  
	 
	class ImgTextWrapper {  
	 ImageView imageView;  
	 TextView textView;  
	   
	}  