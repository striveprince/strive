package com.cutv.ningbo.inject.module;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.cutv.ningbo.DknbApplication;
import com.cutv.ningbo.data.entity.PrivateInfoEntity;
import com.cutv.ningbo.data.save.SharePreferenceUtil;
import com.cutv.ningbo.inject.qualifier.context.AppContext;
import com.cutv.ningbo.inject.qualifier.context.FragmentContext;
import com.cutv.ningbo.inject.scope.ApplicationScope;
import com.cutv.ningbo.inject.scope.FragmentScope;

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
    private final DknbApplication app;
    public AppModule(DknbApplication app) {
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

    @ApplicationScope
    @Provides
    PrivateInfoEntity provideInfoEntity(){
        return SharePreferenceUtil.getNingInstance(app).getAllDto(PrivateInfoEntity.class);
    }

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
