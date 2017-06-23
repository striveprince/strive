package com.read.group.data.api;

import com.read.group.data.entity.InfoEntity;

import java.util.List;
import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Query;
import rx.Observable;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：11:28
 * modify developer：  admin
 * modify time：11:28
 * modify remark：
 *
 * @version 2.0
 */

public interface NbtvApi {

    String DKNBList = "?task=get-articles";
    String VideoList = "get-live";
    String NewsColumn = "get-news-column";
    String vods = "get-vods";


//    @GET
//    Observable<InfoEntity<List<HomeSlideEntity>>>
//    getNBTVList(@HeaderMap Map<String, Object> map,
//                @Query("task") String task,
//                @Query("channelId") int channelId,
//                @Query("count") int count,
//                @Query("offset") int offset);
//
//    @GET
//    Observable<InfoEntity<VideosDataEntity>>
//    getVideoList(@Query("task") String task,
//                 @Query("channelName") String channelName);
//
//    @GET
//    Observable<InfoEntity<VideosDataEntity>> getVods(@Query("task") String task, @Query("channelName") String type);
//
//    // http://cmsapi.tools.nbtv.cn/?task=get-articles&channelId=11672&count=25&offset=25
//
//    @GET(DKNBList)
//    Observable<InfoEntity<List<HomeSlideEntity>>> getContentNews(@Query("channelId") int channelId, @Query("count") int count, @Query("offset") int offset);
////    ?task=get-articles&channelId=11684&count=3&offset=0
////    http://cmsapi.tools.nbtv.cn/?task=get-news-column
//    @GET("?task=get-news-column")
//    Observable<InfoEntity<List<NewsDataEntity>>> getTag();
}
