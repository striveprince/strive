package com.read.group.inject.module;

import android.content.Context;
import android.content.res.Resources;

import com.read.group.ui.ReadApplication;
import com.read.group.inject.qualifier.context.AppContext;
import com.read.group.inject.scope.ApplicationScope;

import dagger.Module;
import dagger.Provides;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：10:10
 * modify developer：  admin
 * modify time：10:10
 * modify remark：
 *
 * @version 2.0
 */
@Module
public class AppModule {
    private final ReadApplication app;
    public AppModule(ReadApplication app) {
        this.app = app;
    }


    @AppContext
    @ApplicationScope
    @Provides
    Context getApplicationContext() {
        return app;
    }

    @Provides
    @ApplicationScope
    Resources provideResources() {
        return app.getResources();
    }

//    @ApplicationScope
//    @Provides
//    PrivateInfoEntity provideInfoEntity(){
//        return SharePreferenceUtil.getNingInstance(app).getAllDto(PrivateInfoEntity.class);
//    }

//    @ApplicationScope
//    @Provides
//    DisplayMetrics provideDisplayMetrics(@ApplicationScope Context context){
//        return context.getResources().getDisplayMetrics();
//    }

//    @Provides
//    @UserSharePreference
//    SharePreferenceUtil provideUserInfo(Context context) {
//        return SharePreferenceUtil.getUserInstance(context);
//    }
//    @Provides
//    @NingSharePreference
//    SharePreferenceUtil provideNingInfo(Context context) {
//        return SharePreferenceUtil.getNingInstance(context);
//    }
}
