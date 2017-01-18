package com.cutv.ningbo.ui.activity.login;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.cutv.ningbo.data.api.LiveApi;
import com.cutv.ningbo.data.params.LoginParams;
import com.cutv.ningbo.data.save.SharePreferenceUtil;
import com.cutv.ningbo.inject.qualifier.context.ActivityContext;
import com.cutv.ningbo.inject.qualifier.preference.NingSharePreference;
import com.cutv.ningbo.inject.scope.ActivityScope;
import com.cutv.ningbo.ui.base.respond.Respond;
import com.cutv.ningbo.ui.base.viewModel.BaseViewModel;

import javax.inject.Inject;

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

    public String cellPhone = "";
    public String password = "";

    @Inject
    @NingSharePreference
    SharePreferenceUtil util;

    @Inject
    LoginViewModel(LiveApi api, @ActivityContext Context context) {
        super(context);
        this.api = api;
    }

    public void onLoginClick(View view){
        Toast.makeText(view.getContext(),"cellPhone:"+cellPhone+"\npassword:"+password,Toast.LENGTH_SHORT).show();
        LoginParams params = new LoginParams();
        params.setCczyidt(cellPhone);
        params.setPczyidt(password);
        http(api.login(params),util::setAllDto);
    }


}
