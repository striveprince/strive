package com.read.group.base.model;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.view.View;
import android.view.ViewGroup;

import com.android.databinding.library.baseAdapters.BR;
import com.read.group.base.annotation.ModelView;
import com.read.group.base.cycle.CycleContainer;
import com.read.group.base.cycle.DataBindingActivity;
import com.read.group.base.model.inter.Event;
import com.read.group.base.model.inter.EventModel;
import com.read.group.base.model.inter.Model;

import java.lang.ref.WeakReference;
import java.util.HashSet;
import java.util.Set;

import timber.log.Timber;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：15:58
 * modify developer：  admin
 * modify time：15:58
 * modify remark：
 * it's use to bind activity fragment or layout
 *
 * @version 2.0
 */

public class ViewModel<T extends CycleContainer> extends ViewEvent implements EventModel<T> {
    private transient WeakReference<T> weakReference;
    private transient Set<Model> set;
//    private int eventId = 0;

    @CallSuper
    @Override
    public void attachView(T t, int model_index) {
        weakReference = new WeakReference<>(t);
        t.getBinding().setVariable(model_index < getModelView().name().length ? getModelView().name()[model_index] : BR.vm, this);
        registerEvent();
        set = addViewSet(t.getBinding().getRoot());
//        eventId = getModelView().event()[model_index];
    }

    @Override
    public void event(int eventId, View view,Object...args) {
        Event event = eventSet.get(eventId);
        if (event != null) event.onEvent(view, this,args);
//        else resumeSet.put(eventId,new Entity(this,view,args));
    }

    @Override
    public T getT() {
        T t = null;
        if (weakReference != null) t = weakReference.get();
        if (t == null)
            Timber.e(weakReference == null ? "weakReference ==null" : "cycleContainer object == null");
        return t;
    }

    @CallSuper
    @Override
    public void onResume() {
        if (set != null) for (Model model : set) model.onResume();
//        Entity entity = resumeSet.get(eventId);
//        if(entity!=null) event(eventId,entity.getView(),entity.getArgs());
    }

    @CallSuper
    @Override
    public void onPause() {
        if (set != null) for (Model model : set) model.onPause();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (set != null) for (Model model : set) model.onSaveInstanceState(outState);
    }

    @CallSuper
    @Override
    public void onStop() {
        if (set != null) for (Model model : set) model.onStop();
    }

    @CallSuper
    @Override
    public void onStarted() {
        if (set != null) for (Model model : set) model.onStarted();
    }

    @CallSuper
    @Override
    public void detachView() {
        if (getT() != null) {
            unRegisterEvent();
            if (set != null) {
                for (Model model : set) model.detachView();
                set.clear();
            }
            weakReference.clear();
        }
    }

    public DataBindingActivity getDataActivity() {
        if (getT() != null)
            return getT().getDataActivity();
        return null;
    }


    public ViewDataBinding getBinding() {
        if (getT() != null)
            return getT().getBinding();
        return null;
    }

    private Set<Model> addViewSet(View view) {
        Set<Model> set = new HashSet<>();
        if(getModelView().cycle()>0)addViewSet(set, view);
        return set.size() > 0 ? set : null;
    }

    private void addViewSet(Set<Model> set, View view) {
        if (view instanceof Model) {
            Model model = (Model) view;
            ModelView modelView = model.getModelView();
            if (modelView != null)
                switch (modelView.cycle()) {
                    case 0:return;
                    case 1:set.add(model);
                        return;
                    case 2:set.add(model);
                    case 3:if (view instanceof ViewGroup) {
                        ViewGroup viewGroup = (ViewGroup) view;
                        for (int index = 0; index < viewGroup.getChildCount(); index++) {
                            View child = viewGroup.getChildAt(index);
                            addViewSet(set, child);
                        }
                        break;
                    }
                }
        } else {
            if (view instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view;
                for (int index = 0; index < viewGroup.getChildCount(); index++) {
                    View child = viewGroup.getChildAt(index);
                    addViewSet(set, child);
                }
            }
        }
    }
}
