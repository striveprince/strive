package com.app.inject.component;

import android.content.Context;
import android.content.res.Resources;

import com.app.data.api.ShakeApi;
import com.app.data.api.TopicApi;
import com.app.data.save.SharePreferenceUtil;
import com.app.inject.module.AppModule;
import com.app.inject.module.DataModule;
import com.app.inject.module.NetWorkModule;
import com.app.inject.qualifier.context.AppContext;
import com.app.inject.qualifier.preference.NingSharePreference;
import com.app.inject.scope.ApplicationScope;

import dagger.Component;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：10:08
 * modify developer：  admin
 * modify time：10:08
 * modify remark：
 *
 * @version 2.0
 */

@ApplicationScope
@Component(modules={AppModule.class, NetWorkModule.class,DataModule.class})
public interface AppComponent {
    @AppContext Context context();
    Resources resources();
//    UserApi getUserApi();
    TopicApi getTopicApi();
//    NbtvApi getNbtvApi();
    ShakeApi getShakeApi();
//    LiveApi getLiveApi();

    @NingSharePreference SharePreferenceUtil NingUtil();
}
