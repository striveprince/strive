package com.cutv.ningbo.inject.interceptor;

import android.content.Context;
import android.text.TextUtils;


import com.cutv.ningbo.DknbApplication;
import com.cutv.ningbo.data.entity.PrivateInfoEntity;
import com.cutv.ningbo.data.entity.SystemEntity;
import com.cutv.ningbo.data.save.SharePreferenceUtil;
import com.cutv.ningbo.data.util.Encrypt;
import com.cutv.ningbo.data.util.Util;
import com.cutv.ningbo.inject.qualifier.context.AppContext;
import com.cutv.ningbo.inject.qualifier.preference.NingSharePreference;

import java.io.IOException;

import javax.inject.Inject;

import okhttp3.CacheControl;
import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：10:01
 * modify developer：  admin
 * modify time：10:01
 * modify remark：
 *
 * @version 2.0
 */

public class UserInterceptor implements Interceptor {
    private String device, token;
    private Context context;

    @Inject
    public UserInterceptor(@AppContext Context context) {
        this.context = context;
        token = SharePreferenceUtil.getNingInstance(context).getValue("token",String.class);
        device = SharePreferenceUtil.getSystemInstance(context).getValue("system_name",String.class);
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        String time = Long.toString(System.currentTimeMillis());
        if (Util.isNetworkConnected(context)) {
            request = request.newBuilder()
                    .addHeader("pragma-uuid", "android")
                    .addHeader("User-Agent", "android")
                    .addHeader("pragma-device", TextUtils.isEmpty(device) ? "" : device)
                    .addHeader("pragma-date", time)
                    .addHeader("pragma-tcze", TextUtils.isEmpty(token) ? "" : Encrypt.encrypt(token, device, time))
                    .build();
//            RequestBody body = request.body();
////            request.url()
//            if(body instanceof FormBody){
//
//            }
        }
//        Response response = chain.proceed(request);
//        response.code() == 200;
//        response.body()
        return chain.proceed(request);
    }
}
