package com.read.group.ui;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.alibaba.android.arouter.launcher.ARouter;
import com.read.group.BuildConfig;
import com.read.group.inject.component.AppComponent;
import com.read.group.inject.component.DaggerAppComponent;
import com.read.group.inject.module.AppModule;

import java.util.Stack;

import timber.log.Timber;

/**
 * Created by apple on 2017/6/23.
 */

public class ReadApplication extends Application implements Application.ActivityLifecycleCallbacks{
    private static ReadApplication application;
    private static AppComponent appComponent;
    private Stack<Activity> stack = new Stack<>();

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        stack.clear();
        appComponent= DaggerAppComponent.builder().appModule(new AppModule(this)).build();
        registerActivityLifecycleCallbacks(this);
        if(BuildConfig.DEBUG){
            Timber.plant(new Timber.DebugTree());
            ARouter.openDebug();
            ARouter.openLog();
        }
        ARouter.init(this);
    }

    public static ReadApplication getApp(){
        return application;
    }

    public Activity getCurrentActivity(){
        return stack.lastElement();
    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        stack.add(activity);
    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        stack.remove(activity);
    }
}
