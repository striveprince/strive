package com.cutv.ningbo.data.http;

import com.cutv.ningbo.data.exception.ApiException;
import com.cutv.ningbo.data.entity.InfoEntity;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：11:22
 * modify developer：  admin
 * modify time：11:22
 * modify remark：
 *
 * @version 2.0
 */


public class RestfulTransformer<T> implements Observable.Transformer<InfoEntity<T>, T> {
    @Override
    public Observable<T> call(Observable<InfoEntity<T>> observable) {
        return observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

                .flatMap(entity ->
                        Observable.create(subscriber -> {
                            if (!subscriber.isUnsubscribed())
                                try {
//                                    entity.getInfo();
                                    if (entity.isSucc())
                                        subscriber.onNext(entity.getData());
                                    else
                                        subscriber.onError(new ApiException(entity.getInfo()));
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    subscriber.onError(e);
                                } finally {
                                    subscriber.onCompleted();
                                }
                        }));
    }

}
