package com.cutv.ningbo.ui.activity.detail;

import android.content.Context;

import com.cutv.ningbo.inject.qualifier.context.ActivityContext;
import com.cutv.ningbo.ui.base.viewModel.InitViewModel;

import javax.inject.Inject;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：15:41
 * modify developer：  admin
 * modify time：15:41
 * modify remark：
 *
 * @version 2.0
 */


public class DetailViewModel extends InitViewModel{
    @Inject
    public DetailViewModel(@ActivityContext Context context) {
        super(context);
    }
}
