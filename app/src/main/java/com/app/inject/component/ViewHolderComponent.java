package com.app.inject.component;

import com.app.inject.module.ViewHolderModule;
import com.app.inject.module.ViewModelModule;
import com.app.inject.scope.HolderScope;

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
