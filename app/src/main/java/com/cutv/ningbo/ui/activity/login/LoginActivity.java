package com.cutv.ningbo.ui.activity.login;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.cutv.ningbo.R;
import com.cutv.ningbo.databinding.ActivityLiveLoginBinding;
import com.cutv.ningbo.inject.scope.ActivityScope;
import com.cutv.ningbo.ui.base.activity.BaseActivity;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：10:27
 * modify developer：  admin
 * modify time：10:27
 * modify remark：
 *
 * @version 2.0
 */

@ActivityScope
public class LoginActivity extends BaseActivity<LoginViewModel,ActivityLiveLoginBinding> {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityComponent().inject(this);
        setBindingView(R.layout.activity_live_login,savedInstanceState);

    }


}
