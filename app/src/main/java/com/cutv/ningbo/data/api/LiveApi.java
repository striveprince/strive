package com.cutv.ningbo.data.api;

import com.cutv.ningbo.data.entity.InfoEntity;
import com.cutv.ningbo.data.entity.PrivateInfoEntity;
import com.cutv.ningbo.data.params.LoginParams;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：10:03
 * modify developer：  admin
 * modify time：10:03
 * modify remark：
 *
 * @version 2.0
 */


public interface LiveApi {
    //http://live.dknb.nbtv.cn/live/login
    String LiveLogin = "live/login";

    @POST(LiveLogin)
    Observable<InfoEntity<PrivateInfoEntity>> login(@Body LoginParams params);


}
