/*
package com.cutv.ningbo.ui.base.viewModel;

import android.content.Context;
import android.os.Bundle;

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
 * create time：15:40
 * modify developer：  admin
 * modify time：15:40
 * modify remark：
 *
 * @version 2.0
 *//*



public class InitViewModel<T, RD extends Respond.HttpInitRespond<T>> extends BaseViewModel<RD> {
    protected Context context;

    public InitViewModel(Context context) {
        this.context = context;
    }

    @Override
    public void attachView(RD rd, Bundle savedInstanceState) {
        super.attachView(rd, savedInstanceState);
        if (initApi() == null) return;
        http(context,initApi(),t -> getRespond().onInitRespond(true, t));
    }

    public Observable<InfoEntity<T>> initApi() {
        return null;
    }




}
*/
