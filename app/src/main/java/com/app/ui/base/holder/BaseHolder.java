package com.app.ui.base.holder;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.app.BR;
import com.app.data.entity.BaseEntity;
import com.app.ui.base.adapter.recycler.HolderGetListener;
import com.app.ui.base.adapter.recycler.HolderSendListener;
import com.app.ui.base.adapter.recycler.OnItemClickListener;
import com.app.ui.base.adapter.recycler.RecyclerBindViewListener;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：10:52
 * modify developer：  admin
 * modify time：10:52
 * modify remark：
 *
 * @version 2.0
 */
public class BaseHolder<Entity extends BaseEntity>
        extends RecyclerView.ViewHolder
        implements
        OnItemClickListener<Entity>,
        RecyclerBindViewListener<Entity>,
        HolderGetListener {
    private ViewDataBinding binding;
    private Entity entity = null;
    private HolderSendListener<Entity> listener;

    public BaseHolder(Context context, @LayoutRes int layout) {
        this(LayoutInflater.from(context).inflate(layout, null));
    }

    private BaseHolder(View itemView) {
        super(itemView);
        binding = DataBindingUtil.bind(itemView);
    }

    public static BaseHolder createRecyclerHolder(View itemView) {
        return new BaseHolder(itemView);
    }


    @Override
    public void onItemClick(Entity entity, int position) {
        entity.toString();
    }

    @Override
    public boolean onLongItemClick(Entity entity, int position) {
        return true;
    }

    @Override
    public void onBindViewHolder(Entity entity, int position) {
        this.entity = entity;
        binding.setVariable(BR.vm, entity);
        binding.executePendingBindings();
    }

    @Override
    public void getBaseHolder(BaseHolder holder) {

    }

    public void setListener(HolderSendListener<Entity> listener) {
        this.listener = listener;
    }

    public void setLastHolder(BaseHolder<Entity> holder) {
        if (listener != null) listener.setBaseHolder(holder);
    }

    public Entity getLastDto() {
        return entity;
    }
}
