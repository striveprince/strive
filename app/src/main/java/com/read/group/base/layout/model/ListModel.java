package com.read.group.base.layout.model;

import android.support.annotation.NonNull;

import com.read.group.BR;
import com.read.group.R;
import com.read.group.base.adapter.IModelAdapter;
import com.read.group.base.adapter.IRecyclerAdapter;
import com.read.group.base.adapter.list.ListAdapter;
import com.read.group.base.adapter.recycler.RecyclerAdapter1;
import com.read.group.base.annotation.ModelView;
import com.read.group.base.layout.ViewArrayModel;
import com.read.group.base.model.inter.Event;
import com.read.group.base.util.BaseUtil;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：9:34
 * modify developer：  admin
 * modify time：9:34
 * modify remark：
 *
 * @version 2.0
 */

@ModelView(value = R.layout.layout_list_view,name = BR.layout)
public class ListModel<E extends Event> extends ViewArrayModel<E, IRecyclerAdapter<E>> {
    @Override
    public void setAdapter(@NonNull Class<? extends Event> clazz, Object... args) {
        if (adapter == null) {
            adapter = new ListAdapter<>();
            ((ListAdapter)adapter).setLayoutIndex(getHolder_index());
            notifyPropertyChanged(BR.adapter);
        }
        adapter.setLayoutId(BaseUtil.getLayoutId(getHolder_index(), clazz));
    }
}
