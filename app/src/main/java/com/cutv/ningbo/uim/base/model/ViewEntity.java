package com.cutv.ningbo.uim.base.model;

import android.databinding.Bindable;
import android.databinding.PropertyChangeRegistry;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.cutv.ningbo.uim.base.BaseUtil;
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

    public void putEvent(int event) {
        eventSet.put(event, this);
    }

    public void removeEvent(int event) {
        eventSet.put(event, this);
    }

    public void event(int eventId, View view, MotionEvent event) {
        eventSet.get(eventId).onEvent(view, event);
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
