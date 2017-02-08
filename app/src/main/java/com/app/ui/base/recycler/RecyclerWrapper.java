package com.app.ui.base.recycler;

import android.support.annotation.LayoutRes;
import android.support.v4.util.SparseArrayCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;


import com.app.data.entity.BaseEntity;
import com.app.ui.base.holder.BaseHolder;

import java.util.List;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：15:45
 * modify developer：  admin
 * modify time：15:45
 * modify remark：
 *
 * @version 2.0
 */

public  class RecyclerWrapper<Entity extends BaseEntity>
        extends RecyclerView.Adapter<RecyclerView.ViewHolder>
//        implements CreateHolderListener<BaseHolder<Entity>>
{
    @LayoutRes
    private int itemType;
    private RecyclerAdapter<Entity> adapter;
    private static final int TYPE_HEADER = 100000;
    private static final int TYPE_FOOTER = 200000;
    private SparseArrayCompat<View> headerViews = new SparseArrayCompat<>();
    private SparseArrayCompat<View> footerViews = new SparseArrayCompat<>();

    public RecyclerWrapper() {
//        adapter = new RecyclerAdapter<>(this);
    }
    public RecyclerWrapper(int layout) {
        adapter = new RecyclerAdapter<>(layout);
    }

    public void setList(List<Entity> list){
        adapter.setList(list);
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    /**
     * judge that this viewType is headView
     * @param position viewType
     * @return true  this viewType is headView
     */
    private boolean isHeaderViewPos(int position) {
        return position <getHeadersCount();
    }



    /**
     * judge that this viewType is footView
     * @param position viewType
     * @return true  this viewType is footView
     */
    private boolean isFooterViewPos(int position) {
        return position >= getHeadersCount() + getRealItemCount() && position < getHeadersCount() + getRealItemCount() + getFootersCount();
    }

    public void setCount(int count){
        adapter.setCount(count);
    }

    public void addHeaderView(View view) {
        headerViews.put(headerViews.size() + TYPE_HEADER, view);
    }

    public void addFooterView(View view) {
        footerViews.put(footerViews.size() + TYPE_FOOTER, view);
    }

    public void removeHeaderView(View view) {
        int key = headerViews.indexOfValue(view);
        if (key != -1) headerViews.remove(key);
        notifyDataSetChanged();
    }

    public void removeFooterView(View view) {
        int key = footerViews.indexOfValue(view);
        if (key != -1) footerViews.remove(key);
        notifyDataSetChanged();
    }


    public int getRealItemCount() {
        return adapter.getItemCount();
    }

    @Override
    public int getItemCount() {
        return getRealItemCount() + getHeadersCount() + getFootersCount();
    }

    public int getHeadersCount() {
        return headerViews.size();
    }

    public int getFootersCount() {
        return footerViews.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (isHeaderViewPos(position)) {
            return headerViews.keyAt(position);
        } else if (isFooterViewPos(position)) {
            return footerViews.keyAt(position - getHeadersCount() - getRealItemCount());
        }
        return adapter.getItemViewType(position - getHeadersCount());
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (headerViews.get(viewType) != null) {
            return BaseHolder.createRecyclerHolder( headerViews.get(viewType));
        } else if (footerViews.get(viewType) != null) {
            return BaseHolder.createRecyclerHolder( footerViews.get(viewType));
        }
        return adapter.onCreateViewHolder(parent, viewType);
    }



    @SuppressWarnings("unchecked")
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (isHeaderViewPos(position)) return;
        if (isFooterViewPos(position)) return;
        adapter.onBindViewHolder((BaseHolder) holder, position - getHeadersCount());
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        adapter.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            final GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
            final GridLayoutManager.SpanSizeLookup lookup = gridLayoutManager.getSpanSizeLookup();
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    int viewType = getItemViewType(position);
                    if (isHeaderViewPos(viewType) && isFooterViewPos(viewType)) {
                        return gridLayoutManager.getSpanCount();
                    }
                    if (lookup != null)
                        return lookup.getSpanSize(position);
                    return 1;
                }
            });
            gridLayoutManager.setSpanCount(gridLayoutManager.getSpanCount());
        }

    }

    public void addRangItemList(List<Entity> list) {
        addRangItemList(adapter.list.size(), list);
    }

    public void removeItemList(List<Entity> list) {
        adapter.list.removeAll(list);
        notifyDataSetChanged();
    }

    public void clear(){
        adapter.list.clear();
        notifyDataSetChanged();
    }

    public void refresh(List<Entity> list) {
        adapter.list.clear();
        adapter.list.addAll(list);
        notifyDataSetChanged();
    }

//    public void removeItem() {
//        notifyItemRangeInserted();
//        notifyItemRangeRemoved();
//        notifyItemRangeChanged();
//        notifyItemMoved();
//    }

    public void removeItemList(int positionStart, List<Entity> list) {
        if (adapter.list.size() > positionStart + list.size()) {
            adapter.list.removeAll(list);
            notifyItemRangeRemoved(positionStart, list.size());
        }
    }

//    public void changeItemList(int positionStart,List<TimeEntity> list){
////        this.list.set();
//        notifyItemRangeRemoved(positionStart,list.size());
//    }

    public void addRangItemList(int itemStart, List<Entity> list) {
        adapter.list.addAll(itemStart, list);
        notifyDataSetChanged();
//        notifyItemRangeChanged(itemStart + list.size(), getItemCount() - list.size());
//        notifyItemRangeInserted(itemStart, list.size());

    }

    public void addItem(Entity entity, int position) {
        adapter.list.add(position, entity);
        notifyItemInserted(position);
    }

    public void removeItem(int position) {
        adapter.list.remove(position);
        notifyItemRemoved(position);
    }

    public void changeItem(int position, Entity entity) {
//        adapter.list.set(position, TimeEntity);

    }

    public List<Entity> getList() {
        return adapter.getList();
    }

    public void removeAllFooter() {
        footerViews.clear();
//        notifyDataSetChanged();
    }

    ;

    public void removeAllHeader() {
        headerViews.clear();
//        notifyDataSetChanged();
    }

    ;


}
