package com.cutv.ningbo.ui.util.rotary;

import android.databinding.DataBindingUtil;
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

public class ViewPagerTimeEntity<Type> extends TimeEntity<Type> implements CarouselListener<Type>, ViewPager.OnPageChangeListener {
    private ViewPager pager;
    private ImageView[] navs;
    private InjectImageListener<Type> listener;

    public ViewPagerTimeEntity(int totalTime, List<Type> list, ViewPager pager) {super(totalTime, list, pager);}
    public ViewPagerTimeEntity(List<Type> list, ViewPager pager) {
        this(3,list, pager);
    }

    public ViewPagerTimeEntity<Type> setInjectImageListener(InjectImageListener<Type> listener) {
        this.listener = listener;
        return this;
    }

    public void init(LinearLayout linearLayout) {
        if (getView() instanceof ViewPager) {
            ViewPager view = (ViewPager) getView();
            DisplayMetrics dm = view.getContext().getResources().getDisplayMetrics();
            LayoutInflater inflater = LayoutInflater.from(view.getContext());
            List<View> imageList = new ArrayList<>();
            navs = new ImageView[list.size()];
            addCarouselListener(this);
            pager = view;
            int i = 0;
            int side = (int) (10 * dm.density);
            for (Type t : list) {
                View imageView = inflater.inflate(R.layout.image_view, null);
                ImageViewBinding binding = DataBindingUtil.bind(imageView);
                if (listener != null) listener.onImageBinding(binding, t);
                imageList.add(imageView);
                ImageView rb = (ImageView) inflater.inflate(R.layout.view_spot_iv, null);
                navs[i] = rb;
                rb.setLayoutParams(new ViewGroup.LayoutParams(side, side));
                if (linearLayout != null) linearLayout.addView(rb);
                i++;
            }
            if (navs.length != 0) navs[0].setImageResource(R.mipmap.ratio_on);
            pager.setAdapter(new ChildPagerAdapter(imageList));
            pager.addOnPageChangeListener(this);
        }
    }

    @Override public void nextTurn(Type type, View view) {pager.setCurrentItem(getIndex());}
    @Override public void onPageScrollStateChanged(int state) {
//        boolean b = state == 0?TimeUtil.hashSet.add(this):TimeUtil.hashSet.remove(this);
    }
    @Override public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}
    @Override public void onPageSelected(int position) {
        if (position < list.size()) {
            navs[getLastIndex()].setImageResource(R.mipmap.ratio_off);
            setIndex(position);
            navs[getLastIndex()].setImageResource(R.mipmap.ratio_off);
            navs[getIndex()].setImageResource(R.mipmap.ratio_on);
        }
    }

    public interface InjectImageListener<Type> {
        void onImageBinding(ImageViewBinding binding, Type t);
    }
}
