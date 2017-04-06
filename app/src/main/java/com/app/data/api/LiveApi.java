package com.app.data.api;

import com.app.data.entity.InfoEntity;
import com.app.data.entity.live.LiveListDataDto;

import retrofit2.http.GET;
import rx.Observable;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：14:18
 * modify developer：  admin
 * modify time：14:18
 * modify remark：
 *
 * @version 2.0
 */


public interface LiveApi {
    @GET("video/list")
    Observable<InfoEntity<LiveListDataDto>> getLiveList();

}
