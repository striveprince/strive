package com.read.group.ui;

import android.app.Application;

import com.read.group.inject.component.AppComponent;

/**
 * Created by apple on 2017/6/23.
 */

public class ReadApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
    }

    public static AppComponent getAppComponent() {
        return null;
    }
}
