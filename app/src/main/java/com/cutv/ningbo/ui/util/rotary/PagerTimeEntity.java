package com.cutv.ningbo.ui.util.rotary;

import android.support.annotation.LayoutRes;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.cutv.ningbo.ui.base.adapter.pager.PagerListener;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

/**
 * Created by apple on 17/1/18.
 */

public class PagerTimeEntity<CL extends ChangeListener> extends TimeEntity<CL>
        implements RotateListener<CL> , ViewPager.OnPageChangeListener,PagerChangeUtil.Listener<CL>{
    private PagerChangeUtil<CL> util = new PagerChangeUtil<>();
    private List<CL> list = new ArrayList<>();
    private ViewPager pager;
    public PagerTimeEntity(List<CL> list, ViewPager view,PagerListener<CL> adapter) {
        this(3,list, view,adapter);
    }


    public PagerTimeEntity(int totalTime, List<CL> list, ViewPager view, PagerListener<CL> adapter) {
        super(totalTime, list, view);
        util.setListener(this);
        util.setAdapter(adapter);
        this.list = list;
        this.pager = view;
        view.addOnPageChangeListener(this);
    }

    public void setAnimView(ImageView view){
        util.setAnimView(view);
    }

    public void setHorizontalScrollView(HorizontalScrollView scrollView){
        util.setHorizontalScrollView(scrollView);
    }

    public void refreshRadioToGroup(boolean addRadio){
        util.refreshRadioToGroup(addRadio);
    }

    public void initData(RadioGroup group){
        util.setData(list,pager,group);
        addRotateListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void nextRotate(CL CL, View view) {
        pager.setCurrentItem(getIndex());
    }

    @Override
    public void onPageSelected(int position) {
        if (position < list.size()) {
            Timber.i("entity:%1d",position);
            setIndex(position);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        boolean b = state == 0?TimeUtil.hashSet.add(this):TimeUtil.hashSet.remove(this);
        if(!b){Timber.e("page scroll failed");}
    }

    @Override
    public void checkIndexPager(int index, RadioButton radioButton, RadioButton lastRadioButton) {

    }

    @Override
    public void initPager(CL cl, RadioButton radioButton) {

    }

}
