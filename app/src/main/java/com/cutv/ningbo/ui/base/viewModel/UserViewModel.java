package com.cutv.ningbo.ui.base.viewModel;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.cutv.ningbo.data.entity.InfoEntity;
import com.cutv.ningbo.data.entity.PrivateInfoEntity;
import com.cutv.ningbo.data.http.RestfulObserver;
import com.cutv.ningbo.data.http.RestfulSubscriber;
import com.cutv.ningbo.data.save.SharePreferenceUtil;
import com.cutv.ningbo.inject.qualifier.preference.NingSharePreference;
import com.cutv.ningbo.ui.activity.login.LoginActivity;
import com.cutv.ningbo.ui.base.respond.Respond;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Action1;
import timber.log.Timber;

/**
 * Created by apple on 17/1/4.
 */

public class UserViewModel<RD extends Respond> extends BaseViewModel<RD> {
    public UserViewModel(Context context) {
        super(context);
    }

    @Inject
    @NingSharePreference
    SharePreferenceUtil util;


    public <E> void httpLogin(Observable<InfoEntity<E>> observable, RestfulObserver<E> observer) {
        httpLogin(observable, observer, Timber::i, null);
    }
    public <E> void httpLoginToast(Observable<InfoEntity<E>> observable, RestfulObserver<E> observer) {
        httpLogin(observable, observer, Timber::i, null);
    }

    public <E> void httpLogin(Observable<InfoEntity<E>> observable, RestfulObserver<E> observer,
                              Action1<Throwable> action, RestfulSubscriber.OnCompletedListener<E> onCompletedListener) {
        if(util.getShare().getInt("id",0)!=0)
            http(observable, observer, action, onCompletedListener);
        else{
            Intent intent = new Intent(getContext(), LoginActivity.class);
            if(!(getContext() instanceof Activity))
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
            getContext().startActivity(intent);
        }
    }


    public <E> void httpLoginToast(Observable<InfoEntity<E>> observable, RestfulObserver<E> observer,
                              Action1<Throwable> action, RestfulSubscriber.OnCompletedListener<E> onCompletedListener) {
        if(util.getShare().getInt("id",0)!=0)
            httpToast(observable, observer, action, onCompletedListener);
        else{
            Intent intent = new Intent(getContext(), LoginActivity.class);
            if(!(getContext() instanceof Activity))
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
            getContext().startActivity(intent);
        }
    }
}
