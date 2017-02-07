package com.app.inject.module;


import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;

import com.app.inject.qualifier.context.ActivityContext;
import com.app.inject.scope.ActivityScope;

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
    private final AppCompatActivity activity;

    public ActivityModule(AppCompatActivity activity) {
        this.activity = activity;
    }

    @ActivityContext
    @Provides
    @ActivityScope
    Context provideActivityContext() {
        return activity;
    }

//    @Provides
//    @ActivityScope
//    List<BaseFragment> provideFragments() {
//        List<BaseFragment> list = new ArrayList<>();
//        list.add(new HomePageFragment());
//        list.add(new NewsFragment());
////        list.add(new NewsFragment());
////        list.add(new VideoFragment());
////        list.add(new InteractFragment());
////        list.add(new MallFragment());
//        return list;
//    }

    @Provides
    @ActivityScope
    FragmentManager provideFragmentManager() {
        return activity.getSupportFragmentManager();
    }

    @Provides
    @ActivityScope
    DisplayMetrics provideDisplayMetrics(){return activity.getResources().getDisplayMetrics();}

    @Provides
    @ActivityScope
    LayoutInflater provideLayoutInflater(){return LayoutInflater.from(activity);}
}
