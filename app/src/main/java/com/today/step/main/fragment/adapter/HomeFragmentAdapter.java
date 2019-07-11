package com.today.step.main.fragment.adapter;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 *viewpager+imageview滑动适配器
 *轮播图适配器
 **/
public class HomeFragmentAdapter extends PagerAdapter {
	private List<View> list;
	//private int FAKE_BANNER_SIZE =100;
	//private int DEFAULT_BANNER_SIZE = 5;

	public HomeFragmentAdapter(List<View> list) {
		this.list = list;
	}

	/**表示ViewPager的屏幕个数*/
	@Override
	public int getCount() {
		//return FAKE_BANNER_SIZE;
		return list.size();
	}

	/**表示key：o和value：view是否属于同一个键值对，即o对应的值是否是view */
	@Override
	public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
		return view == o;
	}

	//初始化位置为position的屏幕的界面，返回值为键值对的key，而真正的View是key所对应的value
	@NonNull
	@Override
	public Object instantiateItem(@NonNull ViewGroup container, int position) {

		//position %= DEFAULT_BANNER_SIZE;

		View view = list.get(position);
		container.addView(view);

		return view;
	}

	//当位置为position的屏幕不再使用时，销毁它，典型的行为是将此屏幕的View对象从container中remove掉
	@Override
	public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
		container.removeView(list.get(position));
	}

}