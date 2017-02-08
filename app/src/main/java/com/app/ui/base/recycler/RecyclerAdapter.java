package com.app.ui.base.recycler;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.app.data.entity.BaseEntity;
import com.app.ui.base.holder.BaseHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：10:45
 * modify developer：  admin
 * modify time：10:45
 * modify remark：
 *
 * @version 2.0
 */

public class RecyclerAdapter<Entity extends BaseEntity>
        extends RecyclerView.Adapter<BaseHolder<Entity>>
        implements HolderSendListener<Entity> {
    protected List<Entity> list = new ArrayList<>();
    private BaseHolder<Entity> lastHolder;
    private int itemType;
    private int count = 0;

    public RecyclerAdapter(int layout) {
        this.itemType =layout;
    }

    public void setList(List<Entity> list) {
        this.list.clear();
        this.list.addAll(list);
    }

    @Override
    public BaseHolder<Entity> onCreateViewHolder(ViewGroup parent, int viewType) {
        if(itemType == 0)throw new RuntimeException("item must inject");
        return new BaseHolder<>(parent.getContext(),itemType);
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public int getItemCount() {
        boolean flag = count < list.size() && count != 0;
        return flag ? count : list.size();
    }

    @Override
    public void onBindViewHolder(BaseHolder<Entity> holder, int position) {
        holder.setListener(this);
        Entity entity = list.get(position);
        holder.itemView.setOnClickListener(new OnItemListener(entity, holder, position));
        holder.itemView.setOnLongClickListener(new OnItemListener(entity, holder, position));
        holder.onBindViewHolder(list.get(position), position);
    }


    private class OnItemListener implements View.OnClickListener, View.OnLongClickListener {
        private Entity entity;
        private BaseHolder<Entity> baseHolder;
        private int position;

        OnItemListener(Entity entity, BaseHolder<Entity> holder, int position) {
            this.entity = entity;
            this.baseHolder = holder;
            this.position = position;
        }

        @Override
        public boolean onLongClick(View v) {
            return baseHolder.onLongItemClick(entity, position);
        }

        @Override
        public void onClick(View v) {
            baseHolder.onItemClick(entity, position);
        }
    }


    public void onBindViewHolder(BaseHolder baseHolder, Entity entity, int position) {

    }

    public void addRangItemList(List<Entity> list) {
        addRangItemList(this.list.size(), list);
    }

    public void removeItemList(List<Entity> list) {
        this.list.removeAll(list);
        notifyDataSetChanged();
    }

    public void refresh(List<Entity> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

//    public void removeItem() {
//        notifyItemRangeInserted();
//        notifyItemRangeRemoved();
//        notifyItemRangeChanged();
//        notifyItemMoved();
//    }

    public void removeItemList(int positionStart, List<Entity> list) {
        if (this.list.size() > positionStart + list.size()) {
            this.list.removeAll(list);
            notifyItemRangeRemoved(positionStart, list.size());
        }
    }

//    public void changeItemList(int positionStart,List<entity> list){
////        this.list.set();
//        notifyItemRangeRemoved(positionStart,list.size());
//    }

    public void addRangItemList(int itemStart, List<Entity> list) {
        this.list.addAll(itemStart, list);
        notifyItemRangeInserted(itemStart, list.size());
    }

    public void addItem(Entity entity, int position) {
        this.list.add(position, entity);
        notifyItemInserted(position);
    }

    public void removeItem(int position) {
        this.list.remove(position);
        notifyItemInserted(position);
    }

    public void changeItem(int position, Entity entity) {
        list.set(position, entity);
    }

    public List<Entity> getList() {
        return list;
    }


    @Override
    public void setBaseHolder(BaseHolder<Entity> baseHolder) {
        baseHolder.getBaseHolder(lastHolder);
        lastHolder = baseHolder;
    }

}
