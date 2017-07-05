package com.read.group.data.api;

import com.read.group.data.entity.InfoEntity;

import retrofit2.http.POST;
import retrofit2.http.Part;
import rx.Observable;

/**
 * Created by apple on 2017/7/5.
 */

public interface ReadApi {
    // https://book.anniecathy.com/
    //    @POST(LiveLogin)
//    Observable<InfoEntity<PrivateInfoEntity>> login(@Body LoginParams params);

    @POST
    Observable<InfoEntity<String>> register(@Part("phone")int phone,@Part("phone")String password,@Part("code")int code);

}
