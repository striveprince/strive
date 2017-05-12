package com.cutv.ningbo.uim.base.layout;

import android.databinding.Bindable;
import android.databinding.ObservableBoolean;
import android.support.annotation.NonNull;

import com.cutv.ningbo.BR;
import com.cutv.ningbo.uim.base.BaseUtil;
import com.cutv.ningbo.uim.base.adapter.IModelAdapter;
import com.cutv.ningbo.uim.base.annotation.AdapterEntity;
import com.cutv.ningbo.uim.base.model.ViewHttpModel;
import com.cutv.ningbo.uim.base.model.inter.Event;

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


public class ViewArrayModel<E extends Event, Adapter extends IModelAdapter<E>> extends ViewHttpModel<List<E>> {
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
}
