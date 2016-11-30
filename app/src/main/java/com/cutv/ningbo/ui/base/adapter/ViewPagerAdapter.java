package com.cutv.ningbo.ui.base.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.cutv.ningbo.inject.qualifier.manager.ChildFragmentManager;

import java.util.List;

import javax.inject.Inject;

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
public class ViewPagerAdapter<F extends Fragment> extends FragmentPagerAdapter {
    private List<F> list;
    @Inject
    public ViewPagerAdapter(@ChildFragmentManager FragmentManager fm) {
        super(fm);
    }

    public void setList(List<F> list) {
        this.list = list;
    }

    @Override
    public Fragment getItem(int position) {
        return list == null?null:list.get(position);
    }

    @Override
    public int getCount() {
        return list == null?0:list.size();
    }

}
