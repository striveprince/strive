package com.cutv.ningbo.ui.util.rotary;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.android.annotations.NonNull;
import com.cutv.ningbo.ui.base.adapter.pager.PagerListener;
import com.cutv.ningbo.ui.base.adapter.pager.ViewPagerAdapter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import timber.log.Timber;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：10:44
 * modify developer：  admin
 * modify time：10:44
 * modify remark：
 *
 * @version 2.0
 */
public class PagerChangeUtil<CL extends PagerModel> implements ViewPager.OnPageChangeListener, RadioGroup.OnCheckedChangeListener {
    private ViewPager viewPager;
    private int lastIndex = 0;
    private ImageView animView;
    private int count = 0;
    private Listener<CL> listener;
    private int currentLeft = 0;
    private RadioGroup group;

    private boolean loop = false;
    /**
     * the appropriate adapter
     */
    private PagerListener<CL> adapter;

    public void setAdapter(PagerListener<CL> adapter) {
        this.adapter = adapter;
    }

    private List<CL> changeListeners = new ArrayList<>();

    public void setListener(Listener<CL> listener) {
        this.listener = listener;
    }

    /**
     * @param animView view move with the radio button and view pager
     */
    public void setAnimView(ImageView animView) {
        this.animView = animView;
    }

    /**
     * init the data,all the params can't be  null;
     *
     * @param data      the data
     * @param viewPager viewPager
     * @param group     click the appropriate radioButton to go to the corresponding page;
     */
    public void setData(Collection<CL> data, @NonNull ViewPager viewPager, @NonNull RadioGroup group) {
        this.viewPager = viewPager;
        this.group = group;
        if (group != null) group.removeAllViews();
        LayoutInflater inflater = LayoutInflater.from(viewPager.getContext());
        if (count == 0) count = data.size();
        if (data != null) {
            changeListeners.clear();
            for (CL cl : data) {
                changeListeners.add(cl);
                RadioButton rb = cl.getRadioButton(inflater);
                if (listener != null) listener.initPager(cl, rb);
                if (group != null && rb != null) group.addView(rb);
            }
            adapter.setList(changeListeners);
            if (adapter instanceof PagerAdapter)viewPager.setAdapter((PagerAdapter) adapter);
            if(loop && adapter instanceof ViewPagerAdapter)((ViewPagerAdapter) adapter).setCount(Integer.MAX_VALUE);
        }
        viewPager.addOnPageChangeListener(this);
        if (group != null) {
            group.setOnCheckedChangeListener(this);
            ((RadioButton) group.getChildAt(0)).setChecked(true);
        }
    }

    public void setLoop(boolean loop) {
        this.loop = loop;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        group.check(group.getChildAt(position%changeListeners.size()).getId());
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        View view = group.findViewById(checkedId);
        int indexOfChild = group.indexOfChild(view);
        if (view != null) {
            if (animView != null) {
                TranslateAnimation animation = new TranslateAnimation(currentLeft, view.getLeft(), 0f, 0f);
                animation.setInterpolator(new LinearInterpolator());
                animation.setDuration(100);
                animation.setFillAfter(true);
                animView.startAnimation(animation);
            }
            viewPager.setCurrentItem(indexOfChild);
            currentLeft = view.getLeft();
            if (listener != null)
                listener.checkIndexPager(indexOfChild, lastIndex, group);
//            int mIndex = group.getChildCount() - 1 > 2 ? 2 : group.getChildCount();
//            if(horizontalScrollView!=null)horizontalScrollView.smoothScrollTo((indexOfChild >1?currentLeft:0)-group.getChildAt(mIndex).getLeft(),0);
            lastIndex = indexOfChild;
        }
    }

    public interface Listener<T> {
        void checkIndexPager(int index, int lastIndex, RadioGroup group);

        void initPager(T t, RadioButton radioButton);
    }
}
