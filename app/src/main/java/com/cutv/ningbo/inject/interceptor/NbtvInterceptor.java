package com.cutv.ningbo.inject.interceptor;


import android.content.Context;

import com.cutv.ningbo.data.util.Util;
import com.cutv.ningbo.inject.qualifier.context.AppContext;

import java.io.IOException;

import javax.inject.Inject;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

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

public class NbtvInterceptor implements Interceptor {
    private Context context;
    @Inject
    public NbtvInterceptor(@AppContext Context context) {
        this.context = context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        try {
            Request request = chain.request();
            boolean isConnection = Util.isNetworkConnected(context);
//            if (isConnection) {
//                request = request.newBuilder()
//                        .cacheControl(CacheControl.FORCE_CACHE)
//                        .addHeader("User-Agent", "android")
//                        .build();
//            }else{
                request = request.newBuilder()
                        .addHeader("User-Agent", "android")
                        .build();
//            }
//            Response response = chain.proceed(request);
//            response.newBuilder()//有网络，缓存设置1800秒即30分钟，无网络，缓存设置28天
//                    .removeHeader("Pragma")// 清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
////                    .header("Cache-Control",isConnection? "public, max-age=0":"public, only-if-cached, max-stale=2419200")
//                    .header("Cache-Control","public, max-age=0")//:"public, only-if-cached, max-stale=2419200")
//                    .build();
            return chain.proceed(request);

//            Response response = chain.proceed(request);
//        response.code() == 200;
//            return chain.proceed(request);

        }catch (Exception e){
            return new Response.Builder().code(-1).message("error").build();
        }
    }
}
