package com.app.data.api;

import com.app.data.entity.InfoEntity;

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
    Observable<InfoEntity> liveList();
}
