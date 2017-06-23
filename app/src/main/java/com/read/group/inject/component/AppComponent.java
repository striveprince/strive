package com.read.group.inject.component;

import android.content.Context;
import android.content.res.Resources;

import com.read.group.data.api.LiveApi;
import com.read.group.data.api.NbtvApi;
import com.read.group.data.api.ShakeApi;
import com.read.group.data.api.TopicApi;
import com.read.group.data.api.UserApi;
import com.read.group.data.save.SharePreferenceUtil;
import com.read.group.inject.module.AppModule;
import com.read.group.inject.module.DataModule;
import com.read.group.inject.module.NetWorkModule;
import com.read.group.inject.qualifier.context.AppContext;
import com.read.group.inject.qualifier.preference.NingSharePreference;
import com.read.group.inject.scope.ApplicationScope;

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
    @AppContext
    Context context();
    Resources resources();
    UserApi getUserApi();
    TopicApi getTopicApi();
    NbtvApi getNbtvApi();
    ShakeApi getShakeApi();
    LiveApi getLiveApi();

    @NingSharePreference
    SharePreferenceUtil NingUtil();
}
