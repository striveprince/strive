package com.app.ui.base.bindingAdapter;

import android.databinding.BindingAdapter;
import android.databinding.InverseBindingAdapter;
import android.databinding.InverseBindingListener;
import android.databinding.adapters.ListenerUtil;
import android.view.View;

import com.app.R;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：14:34
 * modify developer：  admin
 * modify time：14:34
 * modify remark：
 *
 * @version 2.0
 */
public class ViewBindingAdapter {
    @BindingAdapter("android:visibility")
    public static void setVisibility(View view, int visibility) {
        if(visibility!=view.getVisibility()){
            view.setVisibility(visibility);
        }
    }
}
