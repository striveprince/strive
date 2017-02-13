package com.app.ui.base.bindingAdapter;

import android.databinding.InverseBindingAdapter;
import android.view.View;

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
    @InverseBindingAdapter(attribute = "android:visibility", event = "android:textAttrChanged")
    public static int getVisibility(View view) {
        return view.getVisibility();
    }


}
