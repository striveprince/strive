package com.cutv.ningbo.uim.base.model;

import android.databinding.Bindable;
import android.databinding.PropertyChangeRegistry;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.cutv.ningbo.uim.base.BaseUtil;
import com.cutv.ningbo.uim.base.annotation.ModelView;
import com.cutv.ningbo.uim.base.model.inter.Entity;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：11:42
 * modify developer：  admin
 * modify time：11:42
 * modify remark：
 *
 * @version 2.0
 */


public class ViewEntity implements Entity {
    private transient PropertyChangeRegistry mCallbacks;
    private transient ModelView modelView;

    public void event(View view, MotionEvent event) {
        if (getModelView() != null && getModelView().event() != 0) {
            eventSet.put(getModelView().event(), this);
            eventSet.get(getModelView().event()).onEvent(view, event);
        }
    }

    public void removeEvent(int event) {
        eventSet.remove(event);
    }

    @Override
    public ModelView getModelView() {
        if (modelView == null) modelView = BaseUtil.findModelView(getClass());
        return modelView;
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
