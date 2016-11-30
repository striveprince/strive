package com.cutv.ningbo.ui.base.viewModel;

import android.content.Context;

import com.cutv.ningbo.ui.base.respond.Respond;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：16:24
 * modify developer：  admin
 * modify time：16:24
 * modify remark：
 *
 * @version 2.0
 */


public abstract class InitEntityViewModel<T> extends InitViewModel<T, Respond.HttpInitRespond<T>> {

    public InitEntityViewModel(Context context) {
        super(context);
    }
}
