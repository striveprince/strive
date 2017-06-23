package com.read.group.inject.component;

import com.read.group.inject.module.FragmentModule;
import com.read.group.inject.scope.FragmentScope;

import dagger.Component;


/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：11:29
 * modify developer：  admin
 * modify time：11:29
 * modify remark：
 *
 * @version 2.0
 */

@FragmentScope
@Component(dependencies = AppComponent.class,modules={FragmentModule.class})
public interface FragmentComponent {
//    void inject(HomePageFragment fragment);
//    void inject(NewsFragment fragment);
//    void inject(NewsContentFragment fragment);
//    void inject(ListFragment fragment);
//    void inject(ViewPagerFragment fragment);
}
