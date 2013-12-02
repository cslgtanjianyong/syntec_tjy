package com.example.cslg.mycontrols;

import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TabHost;

import com.example.cslg.R;



/** 缁ф壙 TabHost 缁勪欢锛屽甫鏈夊垏鍏ュ垏鍑虹殑婊戝姩鍔ㄧ敾鏁堟灉銆�*/
public class AnimationTabHost extends TabHost {

	private Animation slideLeftIn;// 浠庡睆骞曞乏杈硅繘鏉�
	private Animation slideLeftOut;// 浠庡睆骞曞乏杈瑰嚭鍘�
	private Animation slideRightIn;// 浠庡睆骞曞彸杈硅繘鏉�
	private Animation slideRightOut;// 浠庡睆骞曞彸杈瑰嚭鍘�

	/** 璁板綍鏄惁鎵撳紑鍔ㄧ敾鏁堟灉 */
	private boolean isOpenAnimation;
	/** 璁板綍褰撳墠鏍囩椤电殑鎬绘暟 */
	private int mTabCount;

	public AnimationTabHost(Context context, AttributeSet attrs) {
		super(context, attrs);
		/** 鍒濆鍖栭粯璁ゅ姩鐢�*/
		slideLeftIn = AnimationUtils.loadAnimation(context,
				R.anim.slide_left);
		slideLeftOut = AnimationUtils.loadAnimation(context,
				R.anim.slide_left);
		slideRightIn = AnimationUtils.loadAnimation(context,
				R.anim.slide_right);
		slideRightOut = AnimationUtils.loadAnimation(context,
				R.anim.slide_right);
		isOpenAnimation = false;// 鍔ㄧ敾榛樿鍏抽棴

	}

	/**
	 * 璁剧疆鏄惁鎵撳紑鍔ㄧ敾鏁堟灉
	 * 
	 * @param isOpenAnimation
	 *            true锛氭墦寮�
	 */
	public void setOpenAnimation(boolean isOpenAnimation) {
		this.isOpenAnimation = isOpenAnimation;
	}

	/**
	 * 
	 * @return 杩斿洖褰撳墠鏍囩椤电殑鎬绘暟
	 */

	public int getTabCount() {
		return mTabCount;
	}

	@Override
	public void addTab(TabSpec tabSpec) {
		mTabCount++;
		super.addTab(tabSpec);
	}

	// 閲嶅啓setCurrentTab(int index) 鏂规硶锛岃繖閲屽姞鍏ュ姩鐢伙紒鍏抽敭鐐瑰氨鍦ㄨ繖銆�
	@Override
	public void setCurrentTab(int index) {
		// 鍒囨崲鍓嶆墍鍦ㄩ〉鐨勯〉闈�
		int mCurrentTabID = getCurrentTab();
		if (null != getCurrentView()) {
			// 绗竴娆¤缃�Tab 鏃讹紝璇ュ�涓�null銆�
			if (isOpenAnimation) {
				// 绂诲紑鐨勯〉闈�
				// 寰幆鏃讹紝鏈〉鍒扮涓�〉
				if (mCurrentTabID == (mTabCount - 1) && index == 0) {
					getCurrentView().startAnimation(slideLeftOut);
				}
				// 寰幆鏃讹紝棣栭〉鍒版湯椤�
				else if (mCurrentTabID == 0 && index == (mTabCount - 1)) {
					getCurrentView().startAnimation(slideRightOut);
				}
				// 鍒囨崲鍒板彸杈圭殑鐣岄潰锛屼粠宸﹁竟绂诲紑
				else if (index > mCurrentTabID) {
					getCurrentView().startAnimation(slideLeftOut);
				}
				// 鍒囨崲鍒板乏杈圭殑鐣岄潰锛屼粠鍙宠竟绂诲紑
				else if (index < mCurrentTabID) {
					getCurrentView().startAnimation(slideRightOut);
				}
			}
		}
		// 璁剧疆褰撳墠椤�
		super.setCurrentTab(index);

		if (isOpenAnimation) {
			// 褰撳墠椤佃繘鏉ユ槸鍔ㄧ敾
			// 寰幆鏃讹紝鏈〉鍒扮涓�〉
			if (mCurrentTabID == (mTabCount - 1) && index == 0) {
				getCurrentView().startAnimation(slideRightIn);
			}
			// 寰幆鏃讹紝棣栭〉鍒版湯椤�
			else if (mCurrentTabID == 0 && index == (mTabCount - 1)) {
				getCurrentView().startAnimation(slideLeftIn);
			}
			// 鍒囨崲鍒板彸杈圭殑鐣岄潰锛屼粠鍙宠竟杩涙潵
			else if (index > mCurrentTabID) {
				getCurrentView().startAnimation(slideRightIn);
			}
			// 鍒囨崲鍒板乏杈圭殑鐣岄潰锛屼粠宸﹁竟杩涙潵
			else if (index < mCurrentTabID) {
				getCurrentView().startAnimation(slideLeftIn);
			}
		}
	}
}
