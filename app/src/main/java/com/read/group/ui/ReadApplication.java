package com.read.group.ui;

import android.app.Application;

import com.read.group.BuildConfig;
import com.read.group.inject.component.AppComponent;
import com.read.group.inject.component.DaggerAppComponent;
import com.read.group.inject.module.AppModule;

import timber.log.Timber;

/**
 * Created by apple on 2017/6/23.
 */

public class ReadApplication extends Application {

    private static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent= DaggerAppComponent.builder().appModule(new AppModule(this)).build();
        if(BuildConfig.DEBUG) Timber.plant(new Timber.DebugTree());
    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }
}
