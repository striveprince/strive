package com.cutv.ningbo.uim.base.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;

import com.cutv.ningbo.uim.base.model.inter.ItemEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：17:33
 * modify developer：  admin
 * modify time：17:33
 * modify remark：
 *
 * @version 2.0
 */


public class ViewPagerAdapter<E extends ItemEvent> extends PagerAdapter implements ILayoutAdapter<E> {
    private List<E> list = new ArrayList<>();
    private int count;

    @Override
    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return false;
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public boolean setList(int position, List<E> e, int type) {
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
    public boolean setEntity(int position, E e, int type) {
        if (type == 0x00) {
            addRangItem(position, e);
        } else if (type == 0x01) {
            addRangItem(getList().size(), e);
        } else if (type == 0x20) {
            remove(e);
        } else return false;
        return true;
    }

    private void removeAllList(List<E> e) {
        list.removeAll(e);
        notifyDataSetChanged();
    }

    private void refresh(List<E> e) {
        list.clear();
        list.addAll(e);
        notifyDataSetChanged();
    }

    private void addRangItemList(int position, List<E> e) {
        list.addAll(position,e);
        notifyDataSetChanged();
    }

    private void remove(E e) {
        list.remove(e);
        notifyDataSetChanged();
    }

    private void addRangItem(int position, E e) {
        list.add(position,e);
        notifyDataSetChanged();
    }

    @Override
    public List<E> getList() {
        return list;
    }

    @Override
    public int size() {
        return list.size();
    }
}
