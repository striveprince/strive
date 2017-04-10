package com.app.data.api;

import com.app.data.entity.InfoEntity;
import com.app.data.entity.ShowDataEntity;

import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：9:22
 * modify developer：  admin
 * modify time：9:22
 * modify remark：
 *
 * @version 2.0
 */


public interface UserApi {
//    Observable<InfoEntity> liveList();

    //    public static final String ShowTagListUrl = "https://api.dknb.nbtv.cn/" + "show/list-by-tid";
//@GET("video/list")
//Observable<InfoEntity<LiveListDataDto>> getLiveList();
//    hashMap.put("is_recommend", "1")
    @GET("show/list-by-tid")
    Observable<InfoEntity> showTagList(@QueryMap Map<String,String> map);

    @GET("show/list-by-tid")
    Observable<InfoEntity<ShowDataEntity>> showTagList(@Query("is_recommend") int is_recommend);

}
