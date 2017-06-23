package com.read.group.inject.component;


import com.read.group.inject.module.ActivityModule;
import com.read.group.inject.scope.ActivityScope;

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
}