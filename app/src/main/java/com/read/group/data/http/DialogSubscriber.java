package com.read.group.data.http;

import android.content.Context;

import rx.functions.Action1;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：15:37
 * modify developer：  admin
 * modify time：15:37
 * modify remark：
 *
 * @version 2.0
 */


public class DialogSubscriber<T> extends RestfulSubscriber<T> {
    public DialogSubscriber(Context context, RestfulObserver<T> observer, Action1<Throwable> action1, OnCompletedListener<T> onCompletedListener) {
        super(context, observer, action1,onCompletedListener);
        setDialog(LoadingDialog.createLoadingDialog1(context));
    }


}
