package com.cutv.ningbo.uim.base.layout;

import android.view.View;

import com.cutv.ningbo.uim.base.cycle.CycleContainer;
import com.cutv.ningbo.uim.base.model.ViewModel;
import com.cutv.ningbo.uim.base.model.inter.Http;

import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;
import timber.log.Timber;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：9:32
 * modify developer：  admin
 * modify time：9:32
 * modify remark：
 *
 * @version 2.0
 */


public class ViewLayoutModel<R> extends ViewModel<CycleContainer> implements Action1<R> {
    private R r;
    private Http<R> rcHttp;
    private final CompositeSubscription compositeSubscription = new CompositeSubscription();
    private int holder_index;
    private Class clazz;
    private boolean enable = true;
    protected int offset = 0;

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }

    public Class getClazz() {
        return clazz;
    }

    public int getHolder_index() {
        return holder_index;
    }

    public void setHolder_index(int holder_index) {
        this.holder_index = holder_index;
    }

    public void setRcHttp(Http<R> rcHttp) {
        this.rcHttp = rcHttp;
    }

    public void onHttp(int offset,boolean cache){
        this.offset = offset;
        if(rcHttp != null)compositeSubscription.add(rcHttp.http(offset,cache).subscribe(this, Timber::i));
    }

    public void onHttp(View view){
        onHttp(0,false);
    }


    @Override
    public void call(R r) {
        this.r = r;
    }

    public R getData(){
        return r;
    }

    @Override
    public void detachView() {
        super.detachView();
        compositeSubscription.clear();
    }

    public void onHttp() {
        onHttp(0,false);
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public boolean getEnable(){
        return enable;
    };
}
