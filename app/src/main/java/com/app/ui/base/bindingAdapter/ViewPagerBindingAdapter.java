package com.app.ui.base.bindingAdapter;

import android.databinding.BindingAdapter;
import android.support.v4.view.ViewPager;

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

    @BindingAdapter("android:onPagerChange")
    public static void addOnPagerChange(ViewPager pager, ViewPager.OnPageChangeListener listener){
        pager.addOnPageChangeListener(listener);
    }
}
