package com.app.ui.base.viewModel;

import android.content.Context;
import android.databinding.ObservableBoolean;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.app.data.entity.BaseEntity;
import com.app.data.entity.InfoEntity;
import com.app.data.http.RestfulSubscriber;
import com.app.data.http.RestfulTransformer;
import com.app.ui.base.recycler.RecyclerWrapper;
import com.app.ui.base.respond.Respond;

import rx.Observable;
import rx.subscriptions.CompositeSubscription;
import timber.log.Timber;

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
 */

public class RecyclerViewModel<T,Entity extends BaseEntity> extends BaseViewModel<Respond.TransformRespond<T,Entity>>{
    protected final CompositeSubscription compositeSubscription = new CompositeSubscription();
    public final ObservableBoolean loading = new ObservableBoolean(false);
    private RecyclerWrapper<Entity> adapter = new RecyclerWrapper<>();
    private boolean pageFlag = false;
    public RecyclerViewModel(Context context) {
        super(context);
    }
    private Observable<InfoEntity<T>> observable;


    private SwipeRefreshLayout.OnRefreshListener onRefreshListener = ()->onHttp(0);

    public SwipeRefreshLayout.OnRefreshListener getOnRefreshListener() {
        return onRefreshListener;
    }

    public RecyclerView.OnScrollListener getOnScrollListener() {
        return scrollListener;
    }

    public void onHttp(int offset) {
        onHttp(offset, observable);
    }

    public void onHttp(View view) {
        onHttp(0);
    }

    @Override
    public void attachView(Respond.TransformRespond<T, Entity> tEntityTransformRespond, Bundle savedInstanceState) {
        super.attachView(tEntityTransformRespond, savedInstanceState);
        onHttp(0);
    }



    @Override
    public void detachView() {
        super.detachView();
        compositeSubscription.clear();
    }


    public void setObservable(Observable<InfoEntity<T>> observable) {
        this.observable = observable;
        onHttp(0,observable);
    }

    private void onHttp(int offset, Observable<InfoEntity<T>> observable) {
        loading.set(true);
        if (observable == null) return;
        RestfulSubscriber<T> subscriber = new RestfulSubscriber<>(getContext(),
                t -> {
                    if (offset == 0) adapter.clear();
                    adapter.addRangItemList(getRespond().transform(t));
                },
                e -> Timber.e(e, "load failed"),this::onCompleted);
        compositeSubscription.add(observable.compose(new RestfulTransformer<>())
                .subscribe(subscriber));
    }

    @CallSuper
    protected void onCompleted(T t,Throwable e) {
        loading.set(false);
        getRespond().onCompleted(e, t, adapter.getRealItemCount());
    }

    public void setPageFlag(boolean pageFlag) {
        this.pageFlag = pageFlag;
    }

    public void onScrollBottom(){}

    private RecyclerView.OnScrollListener scrollListener = new RecyclerView.OnScrollListener() {
        int lastVisibleItem = 0;
        private int dy = 0;

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if (newState == RecyclerView.SCROLL_STATE_IDLE
                    && lastVisibleItem + 1 >= adapter.getItemCount()
                    && !loading.get()) {
                if (pageFlag&&dy>0) onHttp(adapter.getRealItemCount());
                onScrollBottom();
            }
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            lastVisibleItem = ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastVisibleItemPosition();
            this.dy = dy;
        }
    };




}

