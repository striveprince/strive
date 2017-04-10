package com.app.ui.base.bindingAdapter;

import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.app.data.portlet.PagerModel;

import java.util.Collection;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：16:47
 * modify developer：  admin
 * modify time：16:47
 * modify remark：
 *
 * @version 2.0
 */


public class RadioGroupBindingAdapter {
    @BindingAdapter("checked")
    public static void addRadioButton(RadioGroup group, RadioGroup.OnCheckedChangeListener listener) {
        group.setOnCheckedChangeListener(listener);
    }
    @BindingAdapter("addRadio")
    public static void addRadioButton(RadioGroup group, Collection<? extends PagerModel> collection) {
        group.removeAllViews();
        int i = -1;
        for (PagerModel model : collection) {
            View view = model.getView(++i, group.getContext());
            group.addView(view);
        }
        if(i>=0)((RadioButton)group.getChildAt(0)).setChecked(true);
    }
}
