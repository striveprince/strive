package com.cutv.ningbo;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;

import com.cutv.ningbo.inject.component.ActivityComponent;
import com.cutv.ningbo.inject.component.AppComponent;
import com.cutv.ningbo.inject.component.DaggerAppComponent;
import com.cutv.ningbo.inject.module.AppModule;
import com.cutv.ningbo.uim.base.ReflectUtil;
import com.cutv.ningbo.uim.base.annotation.ModelView;
import com.cutv.ningbo.uim.base.cycle.CycleContainer;
import com.cutv.ningbo.uim.base.model.ViewModel;

import java.lang.reflect.Method;

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
@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
public class DknbApplication extends Application implements Application.ActivityLifecycleCallbacks {
    private static AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.ICE_CREAM_SANDWICH)
            registerActivityLifecycleCallbacks(this);
    }

    public static AppComponent getAppComponent() {
        return mAppComponent;
    }


    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {}


    @Override
    public void onActivityStarted(Activity activity) {}

    @Override
    public void onActivityResumed(Activity activity) {}

    @Override
    public void onActivityPaused(Activity activity) {}

    @Override
    public void onActivityStopped(Activity activity) {}

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {}

    @Override
    public void onActivityDestroyed(Activity activity) {}
}
