package com.read.group.inject.module;

import com.read.group.BuildConfig;
import com.read.group.data.api.LiveApi;
import com.read.group.data.api.NbtvApi;
import com.read.group.data.api.ReadApi;
import com.read.group.data.api.ShakeApi;
import com.read.group.data.api.TopicApi;
import com.read.group.data.api.UserApi;
import com.read.group.inject.converter.JsonConverterFactory;
import com.read.group.inject.interceptor.NbtvInterceptor;
import com.read.group.inject.interceptor.UserInterceptor;
import com.read.group.inject.scope.ApplicationScope;

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
//    private static final HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();

    @Provides
    @ApplicationScope
    UserApi provideUserApi(OkHttpClient okHttpClient, UserInterceptor userInterceptor) {
//        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = okHttpClient.newBuilder()
//                .addInterceptor(interceptor)
                .addInterceptor(userInterceptor).build();
        return new Retrofit.Builder()
                .baseUrl(USERURL)
                .addConverterFactory(JsonConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io()))
                .callFactory(client)
                .build().create(UserApi.class);
    }

    @Provides
    @ApplicationScope
    ReadApi provideReadApi(OkHttpClient okHttpClient, UserInterceptor userInterceptor) {
//        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = okHttpClient.newBuilder()
//                .addInterceptor(interceptor)
                .addInterceptor(userInterceptor).build();
        return new Retrofit.Builder()
                .baseUrl(USERURL)
                .addConverterFactory(JsonConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io()))
                .callFactory(client)
                .build().create(ReadApi.class);
    }
    @Provides
    @ApplicationScope
    LiveApi provideLiveApi(OkHttpClient okHttpClient, UserInterceptor userInterceptor) {
//        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = okHttpClient.newBuilder()
//                .addInterceptor(interceptor)
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
    NbtvApi provideNbtvApi(OkHttpClient okHttpClient, NbtvInterceptor nbtvInterceptor) {
        OkHttpClient client = okHttpClient.newBuilder()
                .addInterceptor(nbtvInterceptor).build();
        return new Retrofit.Builder()
                .baseUrl(DKNBURL)
                .addConverterFactory(JsonConverterFactory.create())
//                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io()))
                .callFactory(client)
                .build().create(NbtvApi.class);
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

//    Converter.Factory jsonConverter(){
//
//    }
}
