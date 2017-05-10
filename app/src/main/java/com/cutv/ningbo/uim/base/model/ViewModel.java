package com.cutv.ningbo.uim.base.model;

import android.databinding.Bindable;
import android.databinding.PropertyChangeRegistry;
import android.databinding.ViewDataBinding;
import android.view.MotionEvent;
import android.view.View;

import com.cutv.ningbo.BR;
import com.cutv.ningbo.uim.base.BaseUtil;
import com.cutv.ningbo.uim.base.annotation.ModelView;
import com.cutv.ningbo.uim.base.cycle.CycleContainer;
import com.cutv.ningbo.uim.base.cycle.DataBindingActivity;
import com.cutv.ningbo.uim.base.model.inter.EventModel;

import java.lang.ref.WeakReference;

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


public class ViewModel<T extends CycleContainer> implements EventModel<T> {
    private transient PropertyChangeRegistry mCallbacks;
    private transient WeakReference<T> weakReference;
    private transient ModelView modelView;

    public void event(View view, MotionEvent event) {
        if (getModelView() != null && getModelView().event() != 0) {
            eventSet.put(getModelView().event(), this);
            eventSet.get(getModelView().event()).onEvent(view, event);
        }
    }

    @Override
    public ModelView getModelView() {
        if (modelView == null) {
            modelView = BaseUtil.findModelView(getClass());
            if(modelView== null)throw new RuntimeException("should to add @ModelView to the class:"+getClass());
        }
        return modelView;
    }

    @Override
    public void onEvent(View view, MotionEvent event) {

    }

    @Override
    public void attachView(T t, int model_index) {
        weakReference = new WeakReference<>(t);
        t.getBinding().setVariable(getModelView().name()[model_index], this);
    }

    @Override
    public T getT() {
        if (weakReference != null)
            return weakReference.get();
        else Timber.e("weakReference == null");
        return null;
    }

    @Override
    public void onResume() {
    }

    @Override
    public void onPause() {
    }

    @Override
    public void detachView() {
        if (getT() != null) {
            eventSet.remove(getModelView().event());
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
