package com.app.ui.base.viewModel;

import android.content.Context;

import com.app.data.entity.InfoEntity;
import com.app.data.http.RestfulObserver;
import com.app.data.http.RestfulSubscriber;
import com.app.data.save.SharePreferenceUtil;
import com.app.inject.qualifier.preference.NingSharePreference;
import com.app.ui.base.respond.Respond;

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
//            Intent intent = new Intent(getContext(), LoginActivity.class);
//            if(!(getContext() instanceof Activity))
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
//            getContext().startActivity(intent);
        }
    }


    public <E> void httpLoginToast(Observable<InfoEntity<E>> observable, RestfulObserver<E> observer,
                              Action1<Throwable> action, RestfulSubscriber.OnCompletedListener<E> onCompletedListener) {
        if(util.getShare().getInt("id",0)!=0)
            httpToast(observable, observer, action, onCompletedListener);
        else{
//            Intent intent = new Intent(getContext(), LoginActivity.class);
//            if(!(getContext() instanceof Activity))
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
//            getContext().startActivity(intent);
        }
    }
}
