package com.cutv.ningbo.inject.component;

import android.content.Context;
import android.content.res.Resources;

import com.cutv.ningbo.data.api.LiveApi;
import com.cutv.ningbo.data.api.NbtvApi;
import com.cutv.ningbo.data.api.ShakeApi;
import com.cutv.ningbo.data.api.TopicApi;
import com.cutv.ningbo.data.api.UserApi;
import com.cutv.ningbo.inject.module.AppModule;
import com.cutv.ningbo.inject.module.DataModule;
import com.cutv.ningbo.inject.module.NetWorkModule;
import com.cutv.ningbo.inject.qualifier.context.AppContext;
import com.cutv.ningbo.inject.scope.ApplicationScope;

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
    UserApi getUserApi();
    TopicApi getTopicApi();
    NbtvApi getNbtvApi();
    ShakeApi getShakeApi();
    LiveApi getLiveApi();
}
