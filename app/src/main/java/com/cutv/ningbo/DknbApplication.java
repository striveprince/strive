package com.cutv.ningbo;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.cutv.ningbo.inject.component.AppComponent;
import com.cutv.ningbo.inject.component.DaggerAppComponent;
import com.cutv.ningbo.inject.module.AppModule;
import com.duanqu.qupai.jni.ApplicationGlue;

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
public class DknbApplication extends Application {
    private static AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
        if(BuildConfig.DEBUG) { Timber.plant(new Timber.DebugTree()); }

        System.loadLibrary("gnustl_shared");
//        System.loadLibrary("ijkffmpeg");//目前使用微博的ijkffmpeg会出现1K再换wifi不重连的情况
        System.loadLibrary("qupai-media-thirdparty");
//        System.loadLibrary("alivc-media-jni");
        System.loadLibrary("qupai-media-jni");
        ApplicationGlue.initialize(this);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public static AppComponent getAppComponent() {
        return mAppComponent;
    }


}
