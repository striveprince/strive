package com.cutv.ningbo.ui.activity.main;

import android.content.Context;
import android.os.Bundle;

import com.cutv.ningbo.data.api.UserApi;
import com.cutv.ningbo.data.entity.InfoEntity;
import com.cutv.ningbo.data.http.RestfulSubscriber;
import com.cutv.ningbo.data.http.RestfulTransformer;
import com.cutv.ningbo.inject.qualifier.context.ActivityContext;
import com.cutv.ningbo.inject.qualifier.preference.UserSharePreference;
import com.cutv.ningbo.ui.base.respond.Respond;
import com.cutv.ningbo.ui.base.viewModel.InitEntityViewModel;

import javax.inject.Inject;

import rx.Observable;
import timber.log.Timber;

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
public class MainViewModel extends InitEntityViewModel {
    private UserApi api;
    @Inject
    public MainViewModel(@ActivityContext Context context, UserApi api) {
        super(context);
        this.api = api;
    }


    @Override
    public void attachView(Respond.HttpInitRespond httpInitRespond, Bundle savedInstanceState) {
        super.attachView(httpInitRespond, savedInstanceState);
        compositeSubscription.add(api.getScore(0)
                .compose(new RestfulTransformer<>())
                .subscribe(new RestfulSubscriber<>(context, t -> {}, Timber::i,(t, e) -> {})));
    }
}
