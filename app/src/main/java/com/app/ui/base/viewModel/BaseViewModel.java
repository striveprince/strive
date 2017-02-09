package com.app.ui.base.viewModel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.app.data.entity.InfoEntity;
import com.app.ui.base.recycler.ExpandRecyclerView;
import com.bumptech.glide.Glide;
import com.app.data.http.RestfulObserver;
import com.app.data.http.RestfulSubscriber;
import com.app.data.http.RestfulTransformer;
import com.app.ui.base.respond.Respond;

import rx.Observable;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;
import timber.log.Timber;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：15:47
 * modify developer：  admin
 * modify time：15:47
 * modify remark：
 *
 * @version 2.0
 */


public abstract class BaseViewModel<RD extends Respond> extends BaseObservable implements ViewModel<RD> {

    private RD rd;
    private final CompositeSubscription compositeSubscription = new CompositeSubscription();
    private Context context;

    public BaseViewModel(Context context) {
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

    @Override
    @CallSuper
    public void attachView(RD rd, Bundle savedInstanceState) {
        this.rd = rd;
        if (savedInstanceState != null) {
            restoreInstanceState(savedInstanceState);
        }
    }


    @BindingAdapter({"android:src"})
    public static void setImageUrl(ImageView imageView, String imageUrl) {
        Context context = imageView.getContext();
//        Target<GlideDrawable> target = Glide.with(context).load(url).into(imageView);
//        target.onLoadStarted(ContextCompat.getDrawable(context, R.mipmap.loading));
//        target.onLoadFailed(new Exception("url:" + url), ContextCompat.getDrawable(context, R.mipmap.icon_failure));
        Glide.with(context)
                .load(imageUrl)
                .into(imageView);
//                .onLoadStarted(ContextCompat.getDrawable(context, R.mipmap.loading))
    }


    public RD getRespond() {
        return rd;
    }

    @Override
    public void saveInstanceState(Bundle outState) {

    }

    @Override
    public void restoreInstanceState(@NonNull Bundle savedInstanceState) {

    }

    @Override
    @CallSuper
    public void detachView() {
        rd = null;
        compositeSubscription.clear();
    }


    public <E> void http(Observable<InfoEntity<E>> observable, RestfulSubscriber.OnCompletedListener<E> onCompletedListener) {
        http(observable, t -> {
        }, Timber::i, onCompletedListener);
    }

    public <E> void http(Observable<InfoEntity<E>> observable, RestfulObserver<E> observer) {
        http(observable, observer, Timber::i, null);
    }

    public <E> void httpToast(Observable<InfoEntity<E>> observable, RestfulObserver<E> observer) {
        httpToast(observable, observer, Timber::i, null);
    }

    public <E> void http(Observable<InfoEntity<E>> observable, RestfulObserver<E> observer,
                         RestfulSubscriber.OnCompletedListener<E> onCompletedListener) {
        http(observable, observer, Timber::i, onCompletedListener);
    }

    public <E> void http(Observable<InfoEntity<E>> observable, RestfulObserver<E> observer,
                         Action1<Throwable> action, RestfulSubscriber.OnCompletedListener<E> onCompletedListener) {
        if (observable != null) compositeSubscription.add(observable
                .compose(new RestfulTransformer<>())
                .subscribe(new RestfulSubscriber<>(context, observer, action, onCompletedListener)));
    }

    public <E> void httpToast(Observable<InfoEntity<E>> observable, RestfulObserver<E> observer,
                         Action1<Throwable> action, RestfulSubscriber.OnCompletedListener<E> onCompletedListener) {
        if (observable != null) compositeSubscription.add(observable
                .compose(new RestfulTransformer<E>().setToast(true))
                .subscribe(new RestfulSubscriber<>(context, observer, action, onCompletedListener)));
    }


}
