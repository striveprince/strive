package com.cutv.ningbo.ui.util.rotary;

import android.databinding.DataBindingUtil;
import android.support.annotation.LayoutRes;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.cutv.ningbo.R;
import com.cutv.ningbo.databinding.ImageViewBinding;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：17:10
 * modify developer：  admin
 * modify time：17:10
 * modify remark：
 *
 * @version 2.0
 */

public class ViewPagerTimeEntity<Type> extends TimeEntity<Type> implements RotateListener<Type>, ViewPager.OnPageChangeListener {
    private ViewPager pager;
    private View[] navs;
    private InjectImageListener<Type> listener;
    private @LayoutRes int itemLayoutId,pointLayout;
    private LinearLayout linearLayout;
    private  LayoutInflater inflater;

    public ViewPagerTimeEntity(int totalTime, List<Type> list, ViewPager pager, int itemLayoutId) {
        super(totalTime, list, pager);
        this.itemLayoutId = itemLayoutId;
        inflater =  LayoutInflater.from(pager.getContext());
    }

    public ViewPagerTimeEntity(List<Type> list, ViewPager pager, int itemViewId) {
        this(3, list, pager,itemViewId);
    }

    public ViewPagerTimeEntity<Type> setInjectImageListener(InjectImageListener<Type> listener) {
        this.listener = listener;
        return this;
    }

    public void setPoint(LinearLayout linearLayout, @LayoutRes int pointLayout){
        this.linearLayout = linearLayout;
        this.pointLayout = pointLayout;
    }

    private View layoutInflater(int layoutId){
        return inflater.inflate(layoutId,null);
    }

    public void init() {
        if (getView() instanceof ViewPager) {
            ViewPager pager = (ViewPager) getView();
            DisplayMetrics dm = pager.getContext().getResources().getDisplayMetrics();
            List<View> imageList = new ArrayList<>();
            navs = new ImageView[list.size()];
            addRotateListener(this);
            this.pager = pager;
            int i = 0;
            int side = (int) (10 * dm.density);
            for (Type t : list) {
                View imageView = layoutInflater(itemLayoutId);
                ImageViewBinding binding = DataBindingUtil.bind(imageView);
                if (listener != null) listener.onImageBinding(binding, t);
                imageList.add(imageView);
                View rb = layoutInflater(pointLayout);
                navs[i] = rb;
                rb.setLayoutParams(new ViewGroup.LayoutParams(side, side));
                if (linearLayout != null) linearLayout.addView(rb);
                i++;
            }
            if (navs.length != 0) navs[0].setBackgroundResource(R.mipmap.ratio_on);
            this.pager.setAdapter(new ChildPagerAdapter(imageList));
            this.pager.addOnPageChangeListener(this);
        }
    }

    @Override
    public void nextRotate(Type type, View view) {
        pager.setCurrentItem(getIndex());
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        boolean b = state == 0?TimeUtil.hashSet.add(this):TimeUtil.hashSet.remove(this);
        if(!b){Timber.e("this ");}
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        if (position < list.size()) {
            navs[getLastIndex()].setBackgroundResource(R.mipmap.ratio_off);
            setIndex(position);
            navs[getLastIndex()].setBackgroundResource(R.mipmap.ratio_off);
            navs[getIndex()].setBackgroundResource(R.mipmap.ratio_on);
        }
    }

    public interface InjectImageListener<Type> {
        void onImageBinding(ImageViewBinding binding, Type t);
    }
}
