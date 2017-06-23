package com.read.group.inject.component;

//import com.read.group.ui.activity.main.fragment.home.HomeHolder;
import com.read.group.inject.module.ViewHolderModule;
import com.read.group.inject.module.ViewModelModule;
import com.read.group.inject.scope.HolderScope;

import dagger.Component;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：9:26
 * modify developer：  admin
 * modify time：9:26
 * modify remark：
 *
 * @version 2.0
 */
@HolderScope
@Component(dependencies = AppComponent.class,modules = {ViewHolderModule.class,ViewModelModule.class})
public interface ViewHolderComponent {
//    void inject(HomeHolder viewModel);
}
