package com.app.inject.component;


import com.app.inject.module.ActivityModule;
import com.app.inject.scope.ActivityScope;
import com.app.ui.activity.live.content.LiveContentActivity;
import com.app.ui.activity.live.list.LiveListActivity;

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
//    void inject(MainActivity activity);
//    void inject(DetailActivity activity);
//    void inject(LoginActivity activity);
//    void inject(StartActivity activity);
    void inject(LiveListActivity activity);
    void inject(LiveContentActivity activity);
}
