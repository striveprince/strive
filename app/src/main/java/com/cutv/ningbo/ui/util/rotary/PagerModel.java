package com.cutv.ningbo.ui.util.rotary;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.RadioButton;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：12:11
 * modify developer：  admin
 * modify time：12:11
 * modify remark：
 *
 * @version 2.0
 */


public interface PagerModel<Item> {
    Item getItem(Context context);
    RadioButton getRadioButton(LayoutInflater inflater);
}
