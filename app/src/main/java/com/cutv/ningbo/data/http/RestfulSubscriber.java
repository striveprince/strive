package com.cutv.ningbo.data.http;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.cutv.ningbo.data.exception.ApiException;

import javax.inject.Inject;

import rx.Subscriber;
import rx.functions.Action1;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：10:27
 * modify developer：  admin
 * modify time：10:27
 * modify remark：
 *
 * @version 2.0
 */

public class RestfulSubscriber<T> extends Subscriber<T> {

    private RestfulObserver<T> observer;
    private OnCompletedListener<T> onCompletedListener;

    //    private String loadMsg = "请稍候...";
    private Context context;
    private Dialog dialog;
    private Action1<Throwable> action1;
    private Throwable e = null;
    private T t;
//    private FrameLayout layout;

//    public RestfulSubscriber(Context context, RestfulObserver<T> observer, Action1<Throwable> action1) {
//        this.context = context;
//        this.observer = observer;
//        this.action1 = action1;
//    }
//
//    public RestfulSubscriber(Context context, RestfulObserver<T> observer) {
//        this.context = context;
//        this.observer = observer;
//    }

    public RestfulSubscriber(Context context,  RestfulObserver<T> observer, Action1<Throwable> action1, OnCompletedListener<T> onCompletedListener) {
        this.context = context;
        this.observer = observer;
        this.action1 = action1;
        this.onCompletedListener = onCompletedListener;
    }

    @Override
    public void onStart() {
        super.onStart();
        e = null;
        if (dialog != null) {
            dialog.setCancelable(true);
            dialog.setCanceledOnTouchOutside(true);
            dialog.setOnCancelListener(dialog1 -> {
                if (!isUnsubscribed()) unsubscribe();
            });
            dialog.show();
        }
    }

    @Override
    public void onCompleted() {
        if (dialog != null) dialog.dismiss();
        if (onCompletedListener != null) onCompletedListener.onCompleted(t, e);
    }

    private void showToast(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(Throwable e) {
        this.e = e;
        if (e instanceof ApiException) {
            showToast(e.getMessage());
        } else {
            e.printStackTrace();
        }
        if (action1 != null) action1.call(e);
        onCompleted();
    }


    @Override
    public void onNext(T t) {
        this.t = t;
        observer.next(t);
    }

    public void setDialog(Dialog dialog) {
        this.dialog = dialog;
    }

    public interface OnCompletedListener<T> {
        void onCompleted(T t, Throwable e);
    }
}
