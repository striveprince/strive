package com.read.group.data.api;

import com.read.group.data.entity.InfoEntity;
import com.read.group.data.params.LoginParams;
import com.read.group.data.params.UserParams;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：14:14
 * modify developer：  admin
 * modify time：14:14
 * modify remark：
 *
 * @version 2.0
 */

public interface UserApi {
    String USERID = "tczeidt";
//    @POST("user/cutv-auto-login") Observable<InfoEntity<PrivateInfoEntity>>
//        getOldUser(@Query("cutv_uid") String cutv_uid, @Query("cutv_token") String cutv_token, @Query("cutv_cellphone") String cutv_cellphone);
//    @GET("common/get-home") Observable<InfoEntity<HomeDataEntity>> getHomePager();
//    @FormUrlEncoded
//    @POST("user/get-integral") Observable<InfoEntity<Integer>> getScore(@Body UserParams params);
//
//    @POST("user/login")Observable<InfoEntity<PrivateInfoEntity>> login(@Body LoginParams params);

}
