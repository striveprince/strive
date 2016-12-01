package com.cutv.ningbo.ui.activity.live.login;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.cutv.ningbo.data.api.LiveApi;
import com.cutv.ningbo.data.entity.PrivateInfoEntity;
import com.cutv.ningbo.data.http.RestfulSubscriber;
import com.cutv.ningbo.data.http.RestfulTransformer;
import com.cutv.ningbo.inject.qualifier.context.ActivityContext;
import com.cutv.ningbo.ui.base.respond.Respond;
import com.cutv.ningbo.ui.base.viewModel.BaseViewModel;

import javax.inject.Inject;

import rx.subscriptions.CompositeSubscription;
import timber.log.Timber;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：9:58
 * modify developer：  admin
 * modify time：9:58
 * modify remark：
 *
 * @version 2.0
 */


public class LoginViewModel extends BaseViewModel<Respond> {
    private LiveApi api;
    private Context context;

    public String cellPhone = "";
    public String password = "";

    protected final CompositeSubscription compositeSubscription = new CompositeSubscription();
    @Inject
    public LoginViewModel(LiveApi api, @ActivityContext Context context) {
        this.api = api;
        this.context = context;
    }

//    public View.OnClickListener getOnLoginClick(){
////        return view -> onLoginClick(view);
//        return this::onLoginClick;
//    }

    public void onLoginClick(View view){
        Toast.makeText(view.getContext(),"cellPhone:"+cellPhone+"\npassword:"+password,Toast.LENGTH_SHORT).show();
        compositeSubscription.add(api
                .login(Integer.parseInt(cellPhone),password)
                .compose(new RestfulTransformer<>())
                .subscribe(new RestfulSubscriber<>(context,privateInfoEntity -> {}, Timber::e,(privateInfoEntity, e) -> {})));
    }


}
