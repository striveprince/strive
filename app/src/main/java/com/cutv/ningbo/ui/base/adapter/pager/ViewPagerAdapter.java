package com.cutv.ningbo.ui.base.adapter.pager;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.cutv.ningbo.ui.util.rotary.ChangeListener;

import java.util.List;

import javax.inject.Inject;

public class ViewPagerAdapter<T extends ChangeListener<? extends View>> extends PagerAdapter
        implements PagerListener<T>{
    private List<? extends T> list;
    private int count;

    @Override
    public void setList(List<? extends T> list) {
        this.list = list;
        this.count = list.size();
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View v = list.get(position % count).getT();
        if (container.equals(v.getParent())) container.removeView(v);
        container.addView(v);
        return v;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(list.get(position%count).getT());
    }

}
