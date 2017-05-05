package com.cutv.ningbo.uim.bindingAdapter;

import android.databinding.BindingAdapter;
import android.widget.ProgressBar;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：9:20
 * modify developer：  admin
 * modify time：9:20
 * modify remark：
 *
 * @version 2.0
 */


public class ProgressBarBindingAdapter {
    @BindingAdapter("android:secondaryProgress")
    public static void setSecondaryProgress(ProgressBar bar, int progress){
        bar.setSecondaryProgress(progress);
    }
}
