package com.cutv.ningbo.data.http;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：16:26
 * modify developer：  admin
 * modify time：16:26
 * modify remark：
 *
 * @version 2.0
 */


public interface RestfulObserver<T> {
    void next(T t);
}
