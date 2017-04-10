package com.app.ui.base.viewModel;

import android.content.Context;

import com.app.data.entity.BaseEntity;
import com.app.data.entity.InfoEntity;
import com.app.ui.base.respond.Respond;

import rx.Observable;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：10:41
 * modify developer：  admin
 * modify time：10:41
 * modify remark：
 *
 * @version 2.0
 */


public abstract class RecyclerModel<T,Entity extends BaseEntity> extends BaseViewModel<Respond> implements Respond.TransformRespond<T,Entity> {
    public RecyclerModel(Context context) {
        super(context);
    }

    public abstract Observable<InfoEntity<T>> httpInit();

    @Override
    public void onCompleted(Throwable e, T t, int count) {

    }


}
