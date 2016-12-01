package com.cutv.ningbo.inject.component;


import com.cutv.ningbo.inject.module.ActivityModule;
import com.cutv.ningbo.inject.scope.ActivityScope;
import com.cutv.ningbo.ui.activity.detail.DetailActivity;
import com.cutv.ningbo.ui.activity.live.login.LoginActivity;
import com.cutv.ningbo.ui.activity.main.MainActivity;
import com.cutv.ningbo.ui.activity.start.StartActivity;
import com.cutv.ningbo.ui.base.activity.BaseActivity;

import dagger.Component;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：14:28
 * modify developer：  admin
 * modify time：14:28
 * modify remark：
 *
 * @version 2.0
 */
@ActivityScope
@Component(dependencies = AppComponent.class,modules={ActivityModule.class})
public interface ActivityComponent {
    void inject(MainActivity activity);
    void inject(DetailActivity activity);
    void inject(LoginActivity activity);
//    void inject(StartActivity activity);
}
