package com.cutv.ningbo.uim.bindingAdapter;

import android.databinding.BindingAdapter;
import android.widget.RadioButton;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：10:41
 * modify developer：  admin
 * modify time：10:41
 * modify remark：
 *
 * @version 2.0
 */


public class RadioButtonBindingAdapter {

    @BindingAdapter("sound")
    public static void setSoundEffectsEnabled(RadioButton button,boolean sound){
        button.setSoundEffectsEnabled(sound);
    }
}
