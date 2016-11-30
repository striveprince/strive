/*
package com.cutv.ningbo.ui.base.viewModel;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.cutv.ningbo.data.entity.BaseEntity;
import com.cutv.ningbo.data.entity.InfoEntity;
import com.cutv.ningbo.data.http.RestfulSubscriber;
import com.cutv.ningbo.data.http.RestfulTransformer;
import com.cutv.ningbo.ui.base.respond.Respond;

import rx.Observable;
import rx.subscriptions.CompositeSubscription;
import timber.log.Timber;

*/
/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：9:08
 * modify developer：  admin
 * modify time：9:08
 * modify remark：VM module, business logic to deal with this module; 业务逻辑在这里处理
 *
 * @version 2.0
 *//*

public abstract class RecyclerViewModel<T, Entity extends BaseEntity>
        extends InitViewModel<T,Respond.TransformRespond<T, Entity>>
        implements Respond.SwipeRefreshTouch {

    private final CompositeSubscription compositeSubscription = new CompositeSubscription();
    public abstract Observable<InfoEntity<T>> moreApi(int offset);

    public abstract Observable<InfoEntity<T>> refreshApi();

    public RecyclerViewModel(Context context) {
        super(context);
    }

    @Override
    public void onRefresh(boolean initialLoading) {
        compositeSubscription.add(
                refreshApi().compose(new RestfulTransformer<>())
                        .subscribe(new RestfulSubscriber<>(context,
                                t -> {
                                    getRespond().onRespond(true, initialLoading, t);
                                    getRespond().onRefresh(true, initialLoading, getRespond().transform(t));
                                },
                                throwable -> {
                                    getRespond().onRefresh(false, initialLoading, null);
                                    Timber.e(throwable, "load failed");
                                }))
        );
    }


    @Override
    public void onMore(int offset) {
        if (moreApi(offset) == null) return;
        moreApi(offset).compose(new RestfulTransformer<>()).subscribe(
                new RestfulSubscriber<>(context, t -> getRespond().onMore(true,getRespond().transform(t)), Timber::e)
        );
    }

    @Override
    public void detachView() {
        super.detachView();
        compositeSubscription.clear();
    }

    @Override
    public void saveInstanceState(Bundle outState) {

    }

    @Override
    public void restoreInstanceState(@NonNull Bundle savedInstanceState) {

    }
}
*/
