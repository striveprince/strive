package com.cutv.ningbo.uim.base.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;

import com.cutv.ningbo.uim.base.model.inter.ItemEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：22:15
 * modify developer：  admin
 * modify time：22:15
 * modify remark：
 *
 * @version 2.0
 */


public class FragmentAdapter<F extends ItemEvent<? extends Fragment,View>> extends FragmentPagerAdapter
        implements ILayoutAdapter<F>
{
    private List<F> list = new ArrayList<>();
    private int count = -1;
    public FragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public void setCount(int count) {
        this.count = count;
    }


    @Override
    public Fragment getItem(int position) {
        return list.get(position).getHolder();
    }

    @Override
    public int getCount() {
        return count==-1?list.size():count;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return list.get(position).getHolderTitle();
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean setList(int position, List<F> e, int type) {
        if (type == 0x00) {
            addRangItemList(position, e);
        } else if (type == 0x01) {
            addRangItemList(getList().size(), e);
        } else if (type == 0x10) {
            refresh(e);
        } else if (type == 0x20) {
            removeAllList(e);
        } else return false;
        return true;

    }

    @Override
    public boolean setEntity(int position, F e, int type) {
        if (type == 0x00) {
            addRangItem(position, e);
        } else if (type == 0x01) {
            addRangItem(getList().size(), e);
        } else if (type == 0x20) {
            remove(e);
        } else return false;
        return true;
    }

    @Override
    public List<F> getList() {
        return list;
    }

    @Override
    public void clear() {
        list.clear();
        notifyDataSetChanged();
    }

    private void removeAllList(List<F> e) {
        list.removeAll(e);
        notifyDataSetChanged();
    }

    private void refresh(List<F> e) {
        list.clear();
        list.addAll(e);
        notifyDataSetChanged();
    }

    private void addRangItemList(int position, List<F> e) {
        list.addAll(position,e);
        notifyDataSetChanged();
    }

    private void remove(F e) {
        list.remove(e);
        notifyDataSetChanged();
    }

    private void addRangItem(int position, F e) {
        list.add(position,e);
        notifyDataSetChanged();
    }
}
