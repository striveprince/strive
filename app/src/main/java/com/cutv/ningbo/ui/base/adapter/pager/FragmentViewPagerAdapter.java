package com.cutv.ningbo.ui.base.adapter.pager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.cutv.ningbo.inject.qualifier.manager.ChildFragmentManager;
import com.cutv.ningbo.ui.util.rotary.ChangeListener;

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
public class FragmentViewPagerAdapter<F extends ChangeListener<? extends Fragment>> extends FragmentPagerAdapter
        implements PagerListener<F>{
    private List<? extends F> list;

    public FragmentViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public void setList(List<? extends F> list) {
        this.list = list;
    }

    @Override
    public Fragment getItem(int position) {
        return list == null?null:list.get(position).getT();
    }

    @Override
    public int getCount() {
        return list == null?0:list.size();
    }

}
