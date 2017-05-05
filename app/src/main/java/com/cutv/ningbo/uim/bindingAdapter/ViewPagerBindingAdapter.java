package com.cutv.ningbo.uim.bindingAdapter;

import android.databinding.BindingAdapter;
import android.support.v4.view.ViewPager;

import com.cutv.ningbo.uim.base.adapter.FragmentAdapter;
import com.cutv.ningbo.uim.base.adapter.IModelAdapter;
import com.cutv.ningbo.uim.base.adapter.ViewPagerAdapter;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：16:41
 * modify developer：  admin
 * modify time：16:41
 * modify remark：
 *
 * @version 2.0
 */


public class ViewPagerBindingAdapter {

    @BindingAdapter("pager_change")
    public static void addOnPagerChange(ViewPager pager, ViewPager.OnPageChangeListener listener) {
        pager.addOnPageChangeListener(listener);
    }

    @BindingAdapter("currentItem")
    public static void setCurrentItem(ViewPager pager, int currentItem) {
        pager.setCurrentItem(currentItem);
    }

    @BindingAdapter("adapter")
    public static void setAdapter(ViewPager pager, IModelAdapter adapter){
        if(adapter instanceof ViewPagerAdapter){
            pager.setAdapter((ViewPagerAdapter)adapter);
        }else if(adapter instanceof FragmentAdapter){
            pager.setAdapter((FragmentAdapter)adapter);
        }
    }
//    @BindingAdapter("adapter")
//    public static void setAdapter(ViewPagerAdapter pager, AdapterListener adapterListener) {
//
//        if (adapterListener!=null&&adapterListener.getSize() > 0)
//            if (adapterListener instanceof FragmentAdapter)
//                pager.setAdapter((FragmentAdapter) adapterListener);
//            else if (adapterListener instanceof ViewPagerAdapter) {
//                pager.setAdapter((ViewPagerAdapter) adapterListener);
//            }
//    }


}
