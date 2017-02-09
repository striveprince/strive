package com.app.inject.module;

import com.app.BuildConfig;
import com.app.data.api.LiveApi;
import com.app.data.api.ShakeApi;
import com.app.data.api.TopicApi;
import com.app.data.api.UserApi;
import com.app.inject.converter.JsonConverterFactory;
import com.app.inject.interceptor.NbtvInterceptor;
import com.app.inject.interceptor.UserInterceptor;
import com.app.inject.scope.ApplicationScope;

import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.schedulers.Schedulers;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：10:32
 * modify developer：  admin
 * modify time：10:32
 * modify remark：
 *
 * @version 2.0
 */
@Module
public class NetWorkModule {
    private static final String DKNBURL = "http://cmsapi.tools.nbtv.cn/";
    private static final String TOPICURL = "http://apitopic.dknb.nbtv.cn/";
    private static final String USERURL = "http://api.dknb.nbtv.cn/";
    private static final String SHAKEURL = "http://apishake.dknb.nbtv.cn/";
    private static final String LiveUrl = "http://live.dknb.nbtv.cn/";

    @Provides
    @ApplicationScope
    TopicApi provideTopicApi(OkHttpClient okHttpClient, UserInterceptor userInterceptor) {
        OkHttpClient client = okHttpClient.newBuilder()
                .addInterceptor(userInterceptor).build();
        return new Retrofit.Builder()
                .baseUrl(TOPICURL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io()))
                .callFactory(client)
                .build().create(TopicApi.class);
    }

    @Provides
    @ApplicationScope
    UserApi provideUserApi(OkHttpClient okHttpClient, UserInterceptor userInterceptor) {
        OkHttpClient client = okHttpClient.newBuilder()
                .addInterceptor(userInterceptor).build();
        return new Retrofit.Builder()
                .baseUrl(USERURL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io()))
                .callFactory(client)
                .build().create(UserApi.class);
    }

    @Provides
    @ApplicationScope
    LiveApi provideLiveApi(OkHttpClient okHttpClient, UserInterceptor userInterceptor) {
        OkHttpClient client = okHttpClient.newBuilder()
                .addInterceptor(userInterceptor).build();
        return new Retrofit.Builder()
                .baseUrl(LiveUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io()))
                .callFactory(client)
                .build().create(LiveApi.class);
    }

    @Provides
    @ApplicationScope
    ShakeApi provideShakeApi(OkHttpClient okHttpClient, UserInterceptor userInterceptor) {
        OkHttpClient client = okHttpClient.newBuilder()
                .addInterceptor(userInterceptor).build();
        return new Retrofit.Builder()
                .baseUrl(SHAKEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io()))
                .callFactory(client)
                .build().create(ShakeApi.class);
    }



    @Provides
    @ApplicationScope
    OkHttpClient provideOkHttpClient() {
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder()
                .readTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(new HttpLoggingInterceptor())
                .build().newBuilder();
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClientBuilder.addInterceptor(loggingInterceptor);
        }
        return httpClientBuilder.build();
    }
}
