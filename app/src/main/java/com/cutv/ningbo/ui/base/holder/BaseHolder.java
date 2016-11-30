package com.cutv.ningbo.ui.base.holder;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.cutv.ningbo.BR;
import com.cutv.ningbo.DknbApplication;
import com.cutv.ningbo.data.entity.BaseEntity;
import com.cutv.ningbo.inject.component.DaggerViewHolderComponent;
import com.cutv.ningbo.inject.component.ViewHolderComponent;
import com.cutv.ningbo.inject.module.ViewHolderModule;
import com.cutv.ningbo.ui.base.adapter.HolderGetListener;
import com.cutv.ningbo.ui.base.adapter.HolderSendListener;
import com.cutv.ningbo.ui.base.adapter.OnItemClickListener;
import com.cutv.ningbo.ui.base.adapter.RecyclerBindViewListener;
import com.cutv.ningbo.ui.base.respond.Respond;
import com.cutv.ningbo.ui.base.viewModel.HolderViewModel;

import javax.inject.Inject;

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
public class BaseHolder<Entity extends BaseEntity, VM extends HolderViewModel<Respond, Entity>, Binding extends ViewDataBinding>
        extends RecyclerView.ViewHolder
        implements
        OnItemClickListener<Entity>,
        RecyclerBindViewListener<Entity>,
        HolderGetListener {

    @Inject
    public VM viewModel;
    protected Binding binding;
    private Entity entity = null;
    private HolderSendListener<Entity> listener;

    public BaseHolder(Context context, @LayoutRes int layout) {
        super(LayoutInflater.from(context).inflate(layout, null));
    }

    public BaseHolder(View itemView) {
        super(itemView);
    }

    private ViewHolderComponent viewHolderComponent;

    protected final ViewHolderComponent viewHolderComponent() {
        if (viewHolderComponent == null) {
            viewHolderComponent = DaggerViewHolderComponent.builder()
                    .appComponent(DknbApplication.getAppComponent())
                    .viewHolderModule(new ViewHolderModule(itemView))
                    .build();
        }
        return viewHolderComponent;
    }


    public void bindContentView(@NonNull View view) {
        if (viewModel == null) throw new IllegalStateException("viewModel must not be null and should be injected via viewHolderComponent().inject(this)");
        viewModel.attachView((Respond) this, null);
        binding = DataBindingUtil.bind(view);
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
        viewModel.bind(entity,position);
        binding.setVariable(BR.vm, viewModel);
        binding.setVariable(BR.entity, entity);
        binding.executePendingBindings();
    }

    @Override
    public void getBaseHolder(BaseHolder holder) {

    }

    public void setListener(HolderSendListener<Entity> listener) {
        this.listener = listener;
    }

    public void setLastHolder(BaseHolder<Entity, VM, Binding> holder) {
        if (listener != null) listener.setBaseHolder(holder);
    }

    public Entity getLastDto() {
        return entity;
    }
}
