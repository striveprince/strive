package com.cutv.ningbo.ui.util.rotary;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import java.util.List;

public class ChildPagerAdapter extends PagerAdapter {

		private List<View> views;

		public ChildPagerAdapter(List<View> views) {
			this.views = views;
		}

		@Override
		public int getCount() {
			return views.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		@Override
		public void destroyItem(View container, int position, Object object) {
			// 将指定的view从viewPager中移除
			((ViewPager) container).removeView(views.get(position));
		}

		@Override
		public Object instantiateItem(View container, int position) {
			// 将view添加到viewPager中
			((ViewPager) container).addView(views.get(position));
			return views.get(position);
		}
}