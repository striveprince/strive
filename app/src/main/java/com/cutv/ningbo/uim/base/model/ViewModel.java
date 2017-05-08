package com.cutv.ningbo.uim.base.model;

import android.databinding.Bindable;
import android.databinding.PropertyChangeRegistry;
import android.view.MotionEvent;
import android.view.View;

import com.cutv.ningbo.uim.base.annotation.ModelView;
import com.cutv.ningbo.uim.base.cycle.CycleContainer;
import com.cutv.ningbo.uim.base.cycle.DataBindingActivity;
import com.cutv.ningbo.uim.base.model.inter.EventModel;

import java.lang.ref.WeakReference;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：15:58
 * modify developer：  admin
 * modify time：15:58
 * modify remark：
 * it's use to bind activity fragment or layout
 * @version 2.0
 */


public class ViewModel<T extends CycleContainer>  implements EventModel<T> {
    private transient PropertyChangeRegistry mCallbacks;
    private transient WeakReference<T> weakReference;

    @Override
    public void attachView(T t,int model_index) {
        weakReference = new WeakReference<>(t);
        if (t.getModelView().event() != 0) {
            putEvent(t.getModelView().event());
        }
    }

    public T getT(){
        return weakReference.get();
    }

    @Override
    public void onResume() {
    }

    @Override
    public void onPause() {
    }

    @Override
    public void detachView() {
        if (weakReference != null) {
            removeEvent(weakReference.get().getModelView().event());
            weakReference.clear();
        }
    }

    public DataBindingActivity getDataActivity(){
        if(weakReference!=null&&weakReference.get()!=null)
            return weakReference.get().getDataActivity();
        return null;
    }



    public DataBindingActivity getBinding(){
        if(weakReference!=null&&weakReference.get()!=null)
            return weakReference.get().getDataActivity();
        return null;
    }

    public ModelView getModelView(){
        if(weakReference!=null&&weakReference.get()!=null)
            return weakReference.get().getModelView();
        return null;
    }

    public void putEvent(int event) {
        eventSet.put(event,this);
    }

    public void removeEvent(int event) {
        eventSet.put(event,this);
    }

    public void event(int eventId, View view, MotionEvent event) {
        eventSet.get(eventId).onEvent(view,event);
    }

    @Override
    public void onEvent(View view, MotionEvent event) {

    }

    @Override
    public void addOnPropertyChangedCallback(OnPropertyChangedCallback callback) {
        synchronized (this) {
            if (mCallbacks == null) {
                mCallbacks = new PropertyChangeRegistry();
            }
        }
        mCallbacks.add(callback);
    }

    @Override
    public void removeOnPropertyChangedCallback(OnPropertyChangedCallback callback) {
        synchronized (this) {
            if (mCallbacks == null) {
                return;
            }
        }
        mCallbacks.remove(callback);
    }

    /**
     * Notifies listeners that all properties of this instance have changed.
     */
    public void notifyChange() {
        synchronized (this) {
            if (mCallbacks == null) {
                return;
            }
        }
        mCallbacks.notifyCallbacks(this, 0, null);
    }

    /**
     * Notifies listeners that a specific property has changed. The getter for the property
     * that changes should be marked with {@link Bindable} to generate a field in
     * <code>BR</code> to be used as <code>fieldId</code>.
     *
     * @param fieldId The generated BR id for the Bindable field.
     */
    public void notifyPropertyChanged(int fieldId) {
        synchronized (this) {
            if (mCallbacks == null) {
                return;
            }
        }
        mCallbacks.notifyCallbacks(this, fieldId, null);
    }


}
