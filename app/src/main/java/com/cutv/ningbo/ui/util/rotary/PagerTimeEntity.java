package com.cutv.ningbo.ui.util.rotary;

import android.databinding.ViewDataBinding;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;

import com.cutv.ningbo.BR;
import com.cutv.ningbo.ui.base.adapter.pager.PagerListener;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

/**
 * Created by apple on 17/1/18.
 */

public class PagerTimeEntity<CL extends PagerModel> extends TimeEntity<CL>
        implements PagerRotateListener<CL>, ViewPager.OnPageChangeListener{
    private PagerChangeUtil<CL> util = new PagerChangeUtil<>();
    private List<CL> list = new ArrayList<>();
    private ViewPager viewPager;
    private ViewDataBinding binding;
    public PagerTimeEntity(List<CL> list, ViewPager container,  PagerListener<CL> adapter) {
        this(3,list, container,adapter);
    }


    public PagerTimeEntity(int totalTime, List<CL> list, ViewPager container, PagerListener<CL> adapter) {
        super(totalTime, list, container);
        util.setAdapter(adapter);
        this.list = list;
        this.viewPager = container;
        container.addOnPageChangeListener(this);
    }

    public void setListener(PagerChangeUtil.Listener<CL> listener){
        util.setListener(listener);
    }

    public void setAnimView(ImageView view){
        util.setAnimView(view);
    }

    public void initData(RadioGroup group,ViewDataBinding binding){
        this.binding = binding;
        util.setData(list,viewPager,group);
        addRotateListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void nextRotate(CL cl, View view) {
        binding.setVariable(BR.slide,cl);
        viewPager.setCurrentItem(getIndex());
    }

    @Override
    public void onPageSelected(int position) {
        if (position < list.size()) {
            setIndex(position);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        boolean b = state == 0?TimeUtil.start(this):TimeUtil.stop(this);
        if(!b){Timber.i("page scroll failed");}
    }

}
