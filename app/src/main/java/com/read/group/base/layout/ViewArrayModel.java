package com.read.group.base.layout;

import android.databinding.Bindable;
import android.databinding.Observable;
import android.databinding.ObservableBoolean;
import android.databinding.PropertyChangeRegistry;
import android.support.annotation.NonNull;

import com.read.group.BR;
import com.read.group.base.util.BaseUtil;
import com.read.group.base.adapter.IModelAdapter;
import com.read.group.base.annotation.AdapterEntity;
import com.read.group.base.model.ViewHttpModel;
import com.read.group.base.model.inter.Event;

import java.util.ArrayList;
import java.util.List;

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


public class ViewArrayModel<E extends Event, Adapter extends IModelAdapter<E>> extends ViewHttpModel<List<E>>implements Observable {
    private List<E> list = new ArrayList<>();
    public ObservableBoolean refreshing = new ObservableBoolean(true);
    public ObservableBoolean emptyVisibility = new ObservableBoolean(false);
    protected Adapter adapter;

    public void setAdapter(@NonNull Class<? extends Event> clazz, Object... args) {
        AdapterEntity entity = BaseUtil.findAdapterEntity(clazz);
        if (entity != null) adapter = (Adapter) BaseUtil.newInstance(entity.adapter(), args);
        notifyPropertyChanged(BR.adapter);
    }

    @Override
    public List<E> getData() {
        return list;
    }

    public void setRefreshing(boolean refreshing) {
        this.refreshing.set(refreshing);
    }

    @Override
    public void call(List<E> es) {
        if (refreshing.get()) es.clear();
        this.list.addAll(es);
        if (adapter != null)
            adapter.setList(offset, es, offset > 0 ? 0x00 : 0x10);
        emptyVisibility.set(getData().isEmpty());
    }

    public List<E> getList() {
        return list;
    }

    @Bindable
    public Adapter getAdapter() {
        return adapter;
    }

    private transient PropertyChangeRegistry mCallbacks;

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
