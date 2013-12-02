package com.example.cslg;



import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import com.example.cslg.tools.AppManager;
import com.example.cslg.tools.Newssoftbook;
import com.example.cslg.R;
import com.example.source.Page;

import android.os.Bundle;
import android.os.Handler;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AbsListView.OnScrollListener;
@SuppressLint("ResourceAsColor")
public class Softbook extends Activity implements
OnScrollListener {
	private ListView listView;
	private int visibleLastIndex = 0; // 最后的可视项索引
	private int visibleItemCount; // 当前窗口可见项总数
	
	private PaginationAdapter adapter;

	
	private Handler handler = new Handler();


public void init() 
{//初始化控件
	listView = (ListView) findViewById(R.id.listViewsoft);

	initializeAdapter();

	listView.setAdapter(adapter);

	listView.setOnScrollListener(this);
}	

	
protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.softbook);
		AppManager.getAppManager().addActivity(this);
		//初始化控件
		init();
	
		
}
@Override
public void onScrollStateChanged(AbsListView view, int scrollState) {

	int itemsLastIndex = adapter.getCount() - 1; // 数据集最后一项的索引

	int lastIndex = itemsLastIndex + 1;

	if (scrollState == OnScrollListener.SCROLL_STATE_IDLE

	&& visibleLastIndex == lastIndex) {

		// 如果是自动加载,可以在这里放置异步加载数据的代码

	}

}
@Override
public void onScroll(AbsListView view, int firstVisibleItem,

int visibleItemCount, int totalItemCount) {

	


}

/**
 * 
 * 初始化ListView的适配器
 */
private void initializeAdapter() {

	List<Newssoftbook> news = new ArrayList<Newssoftbook>();

	

	Newssoftbook items = new Newssoftbook();

		items.setTitle("软件说明" );

		items.setContent("本软件（惠好制药有限公司移动应用系统）使用与Android2.3及以上的固件，如果在其他的手机系统上使用本软件，对于出现的任何问题，本公司不承担责任。本软件的下载和安装完全免费，在使用的过程中产生的数据流量费用，由运营商收取。");
		news.add(items);

		Newssoftbook items2 = new Newssoftbook();

		items2.setTitle("权利声明 " );

		items2.setContent("本软件的一切知识产权，以及与软件相关的所有信息内容，包括但不限于：文字表述及其组合、图标、图饰、图像、图表、色彩、界面设计、版面框架、有关数据、附加程序、印刷材料或电子文档等均为本公司所有，受著作权法和国际著作权条约以及其他知识产权法律法规的保护。");
		
		news.add(items2);
		
		Newssoftbook items3 = new Newssoftbook();

		items3.setTitle("权利限制 " );

		items3.setContent("禁止反向工程、反向编译：不得改动编译在程序文件内部的任何资源。除法律、法规明文规定允许上述活动外，用户必须遵守此协议限制。\n组件分割:本软件产品是作为一个单一产品而被授予许可使用, 用户不得将各个部分分开用于任何目的。\n个别授权: 如需进行商业性的销售、复制、分发，包括但不限于软件销售、预装、捆绑等，必须获得本公司的书面授权和许可。\n保留权利：本协议未明示授权的其他权利仍归本公司所有， 用户使用其他权利时必须获得本公司的书面同意。\n用户使用须知\n本软件提供可以上传、下载可以通过网页程序启动相关联的客户端应用程序等功能。\n本软件由本公司安全中心提供产品支持。\n软件的修改和升级：本公司保留为用户提供本软件的修改、升级版本的权利。\n本软件不含有任何旨在破坏用户计算机数据和获取用户隐私信息的恶意代码，不含有任何跟踪、监视用户计算机的功能代码，不会监控用户网上、网下的行为，不会收集用户使用其它软件、文档等个人信息，不会泄漏用户隐私。\n用户应在遵守法律及本协议的前提下使用本软件。用户无权实施包括但不限于下列行为：\n不得删除或者改变本软件上的所有权利管理电子信息；\n不得故意避开或者破坏著作权人为保护本软件著作权而采取的技术措施；\n用户不得利用本软件误导、欺骗他人;\n破坏本软件系统或网站的正常运行，故意传播计算机病毒等破坏性程序；\n本分享平台主要用于用户分享自己桌面，美化桌面和相关信息发布，如用户上传数据或者桌面主题，壁纸，鼠标指针，桌面图标等各类素材涉及到版权问题请勿上传，此行为已经在客户端约束协议和网站约束协议进行重点声明，如不接受协议条款不能进行注册，在此“魔法桌面”重申本分享平台只提供合法用户交流场所，不对非法提交附有联带责任。\n对于从非本公司指定站点下载的本软件产品以及从非本公司发行的介质上获得的本软件产品，本公司无法保证该软件是否感染计算机病毒、是否隐藏有伪装的特洛伊木马程序或者黑客软件，使用此类软件，将可能导致不可预测的风险，建议用户不要轻易下载、安装、使用，本公司不承担任何由此产生的一切法律责任。\n隐私权保护：为了更好地改进软件和服务，在用户安装、卸载本软件以及清理插件时，本软件会向本公司安全中心服务器报告以上行为的发生，具体报告方法为访问服务器的一个页面，服务器根据该页面的被访问次数统计以上行为的发生次数；当用户电脑中存在无法识别的运行项、启动项以及无法分类的应用软件时，本软件会向本公司安全中心服务器回传软件名以及命令行数据。本公司不会将此数据与用户的个人身份信息相关联。");
		
		news.add(items3);
	


	adapter = new PaginationAdapter(news);

}
class PaginationAdapter extends BaseAdapter {

	List<Newssoftbook> newsItems;

	public PaginationAdapter(List<Newssoftbook> newsitems) {

		this.newsItems = newsitems;

	}

	@Override
	public int getCount() {

		return newsItems.size();
	}

	@Override
	public Object getItem(int position) {

		return newsItems.get(position);

	}

	@Override
	public long getItemId(int position) {

		return position;

	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {

		if (view == null) {

			view = getLayoutInflater().inflate(R.layout.listsoftbook, null);

		}

		// 新闻标题

		TextView tvTitle = (TextView) view.findViewById(R.id.newstitle);

		tvTitle.setText(newsItems.get(position).getTitle());

		// 新闻内容

		TextView tvContent = (TextView) view.findViewById(R.id.newscontent);

		tvContent.setText(newsItems.get(position).getContent());

		return view;

	}

	/**
	 * 
	 * 添加数据列表项
	 * 
	 * @param newsitem
	 */

	public void addNewsItem(Newssoftbook newsitem) {

		newsItems.add(newsitem);

	}

}

}