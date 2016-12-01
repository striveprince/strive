package com.cutv.ningbo.ui.activity.live.login;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.cutv.ningbo.R;
import com.cutv.ningbo.databinding.ActivityLoginBinding;
import com.cutv.ningbo.inject.scope.ActivityScope;
import com.cutv.ningbo.ui.base.activity.BaseActivity;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：9:53
 * modify developer：  admin
 * modify time：9:53
 * modify remark：
 *
 * @version 2.0
 */

@ActivityScope
public class LoginActivity extends BaseActivity<LoginViewModel,ActivityLoginBinding> {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityComponent().inject(this);
        setBindingView(R.layout.activity_login,savedInstanceState);
    }
}
