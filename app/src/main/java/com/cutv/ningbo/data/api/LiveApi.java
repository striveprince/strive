package com.cutv.ningbo.data.api;

import com.cutv.ningbo.data.entity.HomeSlideEntity;
import com.cutv.ningbo.data.entity.InfoEntity;
import com.cutv.ningbo.data.entity.PrivateInfoEntity;

import java.util.List;
import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.Query;
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
    Observable<InfoEntity<PrivateInfoEntity>>
    getNBTVList(@Query("cellphone") int cellphone, @Query("password") String password);

}
