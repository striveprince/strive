package com.cutv.ningbo.uim.bindingAdapter;

import android.databinding.BindingAdapter;
import android.widget.SeekBar;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：15:10
 * modify developer：  admin
 * modify time：15:10
 * modify remark：
 *
 * @version 2.0
 */


public class SeekBarBindingAdapter {
    @BindingAdapter("listener")
    public static void setOnSeekBarChangeListener(SeekBar bar,SeekBar.OnSeekBarChangeListener listener){
        bar.setOnSeekBarChangeListener(listener);
    }
}
