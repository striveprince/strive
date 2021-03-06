package com.cutv.ningbo.ui.base.adapter.pager;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.cutv.ningbo.data.portlet.PagerModel;

import java.util.List;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：9:25
 * modify developer：  admin
 * modify time：9:25
 * modify remark：
 * @version 2.0
 */
public class FragmentViewPagerAdapter<F extends PagerModel<? extends Fragment>> extends FragmentPagerAdapter
        implements PagerListener<F> {
    private List<? extends F> list;
    private Context context;

    public FragmentViewPagerAdapter(FragmentManager fm,Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public void setList(List<? extends F> list) {
        this.list = list;
    }

    @Override
    public Fragment getItem(int position) {
        return list == null?null:list.get(position).getItem(position,context);
    }

    @Override
    public int getCount() {
        return list == null?0:list.size();
    }

}
