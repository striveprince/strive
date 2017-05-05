package com.cutv.ningbo;

import android.app.Application;
import android.util.SparseArray;

import com.cutv.ningbo.inject.component.AppComponent;
import com.cutv.ningbo.inject.component.DaggerAppComponent;
import com.cutv.ningbo.inject.module.AppModule;
import com.cutv.ningbo.uim.base.model.inter.Model;

import java.util.Set;

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
    private static final SparseArray<Set<Model>> map = new SparseArray<>();

    @Override
    public void onCreate() {
        super.onCreate();
        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
        if(BuildConfig.DEBUG) { Timber.plant(new Timber.DebugTree()); }
//        CrashHandler.getInstance().init(this);
    }
    public static AppComponent getAppComponent() {
        return mAppComponent;
    }

    public static SparseArray<Set<Model>> getMap() {
        return map;
    }
}
