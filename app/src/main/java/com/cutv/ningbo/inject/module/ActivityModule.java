package com.cutv.ningbo.inject.module;


import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;

import com.cutv.ningbo.inject.qualifier.context.ActivityContext;
import com.cutv.ningbo.inject.scope.ActivityScope;
import com.cutv.ningbo.ui.activity.main.fragment.home.HomePageFragment;
import com.cutv.ningbo.ui.activity.main.fragment.news.NewsFragment;
import com.cutv.ningbo.ui.base.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import dagger.Module;
import dagger.Provides;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：15:17
 * modify developer：  admin
 * modify time：15:17
 * modify remark：
 *
 * @version 2.0
 */
@Module
public class ActivityModule {
    private final Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    @ActivityContext
    @Provides
    @ActivityScope
    Context provideActivityContext() {
        return activity;
    }

    @Provides
    @ActivityScope
    List<BaseFragment> provideFragments() {
        List<BaseFragment> list = new ArrayList<>();
        list.add(new HomePageFragment());
        list.add(new NewsFragment());
//        list.add(new NewsFragment());
//        list.add(new VideoFragment());
//        list.add(new InteractFragment());
//        list.add(new MallFragment());
        return list;
    }

    @Provides
    @ActivityScope
    FragmentManager provideFragmentManager() {
        if(activity instanceof FragmentActivity)
        return ((FragmentActivity)activity).getSupportFragmentManager();
        return null;
    }

    @Provides
    @ActivityScope
    DisplayMetrics provideDisplayMetrics(){return activity.getResources().getDisplayMetrics();}

    @Provides
    @ActivityScope
    LayoutInflater provideLayoutInflater(){return LayoutInflater.from(activity);}
}
