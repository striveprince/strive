package com.read.group.base.model.inter;

import rx.Observable;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：14:29
 * modify developer：  admin
 * modify time：14:29
 * modify remark：
 *
 * @version 2.0
 * @param <R>
 */


public interface Http<R> {
    Observable<R> http(int offset, boolean refresh);
}
