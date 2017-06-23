package com.read.group.data.http;

import java.util.List;

import rx.functions.Func1;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：10:06
 * modify developer：  admin
 * modify time：10:06
 * modify remark：
 *
 * @version 2.0
 */


public class ListFunc1<E> implements Func1<List<E>,List<E>> {
    @Override
    public List<E> call(List<E> es) {
        return es;
    }
}
