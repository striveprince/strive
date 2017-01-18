package com.cutv.ningbo.ui.activity.main;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import com.cutv.ningbo.data.api.UserApi;
import com.cutv.ningbo.data.entity.InfoEntity;
import com.cutv.ningbo.data.entity.PrivateInfoEntity;
import com.cutv.ningbo.data.params.LoginParams;
import com.cutv.ningbo.data.params.UserParams;
import com.cutv.ningbo.data.save.SharePreferenceUtil;
import com.cutv.ningbo.inject.qualifier.context.ActivityContext;
import com.cutv.ningbo.inject.qualifier.preference.NingSharePreference;
import com.cutv.ningbo.ui.base.respond.Respond;
import com.cutv.ningbo.ui.base.viewModel.BaseViewModel;
import com.cutv.ningbo.ui.base.viewModel.UserViewModel;

import java.util.HashMap;

import javax.inject.Inject;

import rx.Observable;

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
public class MainViewModel extends UserViewModel<Respond> {
    private UserApi api;
    @Inject
    @NingSharePreference
    SharePreferenceUtil util;
    @Inject
    UserParams userScore;

    @Inject
    MainViewModel(@ActivityContext Context context, UserApi api) {
        super(context);
        this.api = api;
    }

    @Override
    public void attachView(Respond httpInitRespond, Bundle savedInstanceState) {
        super.attachView(httpInitRespond,savedInstanceState);
        httpLogin(api.getScore(userScore), integer -> {
            Toast.makeText(getContext(),"obtain score success",Toast.LENGTH_SHORT).show();
        });
    }

}
