package com.cutv.ningbo.ui.activity.main;

import android.content.Context;
import android.os.Bundle;

import com.cutv.ningbo.data.api.UserApi;
import com.cutv.ningbo.inject.qualifier.context.ActivityContext;
import com.cutv.ningbo.ui.base.respond.Respond;
import com.cutv.ningbo.ui.base.viewModel.BaseViewModel;

import javax.inject.Inject;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：15:51
 * modify developer：  admin
 * modify time：15:51
 * modify remark：
 *
 * @version 2.0
 */
public class MainViewModel extends BaseViewModel<Respond> {
    private UserApi api;
    @Inject
    public MainViewModel(@ActivityContext Context context, UserApi api) {
        super(context);
        this.api = api;
    }

    @Override
    public void attachView(Respond httpInitRespond, Bundle savedInstanceState) {
        super.attachView(httpInitRespond,savedInstanceState);
        http(api.getScore(0),integer -> {});
    }
}
