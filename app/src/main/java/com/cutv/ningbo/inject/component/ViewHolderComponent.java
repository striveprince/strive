package com.cutv.ningbo.inject.component;

import com.cutv.ningbo.inject.module.ViewHolderModule;
import com.cutv.ningbo.inject.module.ViewModelModule;
import com.cutv.ningbo.ui.activity.main.fragment.home.HomeHolder;
import com.cutv.ningbo.inject.scope.HolderScope;

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
    void inject(HomeHolder viewModel);
}
