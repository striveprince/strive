package com.app.ui.util.rotary;

import android.databinding.ViewDataBinding;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;

import com.app.BR;
import com.app.data.portlet.PagerModel;
import com.app.ui.base.adapter.pager.PagerListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by apple on 17/1/18.
 */

public class PagerTimeEntity<CL extends PagerModel> extends TimeEntity<CL>
        implements PagerRotateListener<CL>, ViewPager.OnPageChangeListener {
    private PagerChangeUtil<CL> util = new PagerChangeUtil<>();
    private List<CL> list = new ArrayList<>();
    private ViewPager viewPager;
    private ViewDataBinding binding;

    public PagerTimeEntity(List<CL> list, ViewPager container, PagerListener<CL> adapter) {
        this(3, list, container, adapter);
    }

    public PagerTimeEntity(int totalTime, List<CL> list, ViewPager container, PagerListener<CL> adapter) {
        super(totalTime, list, container);
        util.setAdapter(adapter);
        this.list = list;
        this.viewPager = container;
        container.addOnPageChangeListener(this);
    }

    public void setListener(PagerChangeUtil.Listener<CL> listener) {
        util.setListener(listener);
    }

    public void setAnimView(ImageView view) {
        util.setAnimView(view);
    }

    public void setLimitLoop(boolean loop){
//        setCount(Integer.MAX_VALUE);
//        util.setLoop(true);
    }

    public void initData(RadioGroup group, ViewDataBinding binding) {
        this.binding = binding;
        util.setData(list, viewPager, group);
        if (list.size() > 0) binding.setVariable(BR.slide, list.get(0));
        addRotateListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void nextRotate(CL cl, View view) {
        viewPager.setCurrentItem(getIndex());
    }

    @Override
    public void onPageSelected(int position) {
        setIndex(position % list.size());
        binding.setVariable(BR.slide, list.get(position % list.size()));
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        TimeUtil.getInstance().switching(this, state);
    }

}
