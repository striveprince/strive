package com.app.inject.module;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;

import com.app.inject.qualifier.context.FragmentContext;
import com.app.inject.qualifier.manager.ChildFragmentManager;
import com.app.inject.qualifier.manager.SupportFragmentManager;
import com.app.inject.scope.FragmentScope;
import com.app.ui.base.fragment.BaseFragment;

import dagger.Module;
import dagger.Provides;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：13:38
 * modify developer：  admin
 * modify time：13:38
 * modify remark：
 *
 * @version 2.0
 */
@FragmentScope
@Module
public class FragmentModule {
    private final BaseFragment fragment;

    public FragmentModule(BaseFragment fragment) {
        this.fragment = fragment;
    }

    @FragmentScope
    @Provides
    @FragmentContext
    Context provideContext() {
        return fragment.getActivity();
    }

    @FragmentScope
    @Provides
    @SupportFragmentManager
    FragmentManager provideManager() {
        return fragment.getFragmentManager();
    }

    @FragmentScope
    @Provides
    @ChildFragmentManager
    FragmentManager provideChildManager() {
        return fragment.getChildFragmentManager();
    }

    @FragmentScope
    @Provides
    LayoutInflater provideLayoutInflater(@FragmentContext Context context){
        return LayoutInflater.from(context);
    }

    @FragmentScope
    @Provides
    DisplayMetrics provideDisplayMetrics(@FragmentContext Context context){
        return context.getResources().getDisplayMetrics();
    }


}
