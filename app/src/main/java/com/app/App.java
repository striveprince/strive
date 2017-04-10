package com.app;


import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.os.Build;
import android.os.Bundle;

import com.app.inject.component.AppComponent;
import com.app.inject.component.DaggerAppComponent;
import com.app.inject.module.AppModule;

import java.util.Stack;

import timber.log.Timber;


/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：16:49
 * modify developer：  admin
 * modify time：16:49
 * modify remark：
 *
 * @version 2.0
 */
public class App extends Application{
    private static AppComponent mAppComponent;
    private static Stack<Activity> store;
    @Override
    public void onCreate() {
        super.onCreate();
        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
        if(BuildConfig.DEBUG) { Timber.plant(new Timber.DebugTree()); }
        store = new Stack<>();
        if(Build.VERSION.SDK_INT>Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1){
            registerLifecycleCallbacks();
        }
    }
    public static AppComponent getAppComponent() {
        return mAppComponent;
    }

    public static Activity getCurrentActivity(){
        return store.lastElement();
    }


    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1)
    private void registerLifecycleCallbacks(){
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                store.add(activity);
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
                store.remove(activity);
            }
        });
    }
}
