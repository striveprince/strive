package com.cutv.ningbo.uim.base.adapter.recycler;

import android.support.v4.util.SparseArrayCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.cutv.ningbo.uim.base.BaseUtil;
import com.cutv.ningbo.uim.base.adapter.IRecyclerAdapter;
import com.cutv.ningbo.uim.base.model.inter.Event;

import java.util.List;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：16:25
 * modify developer：  admin
 * modify time：16:25
 * modify remark：
 *
 * @version 2.0
 */


public class RecyclerAdapter1<E extends Event> extends RecyclerView.Adapter implements IRecyclerAdapter<E> {
    private RecyclerAdapter<E> adapter;
    private static final int TYPE_HEADER = 100000;
    private static final int TYPE_FOOTER = 200000;
    private SparseArrayCompat<View> headerViews = new SparseArrayCompat<>();
    private SparseArrayCompat<View> footerViews = new SparseArrayCompat<>();

    public RecyclerAdapter1() {
        adapter = new RecyclerAdapter<>();
    }


    /**
     * judge that this viewType is headView
     *
     * @param position viewType
     * @return true  this viewType is headView
     */
    private boolean isHeaderViewPos(int position) {
        return position < getHeadersCount();
    }


    /**
     * judge that this viewType is footView
     *
     * @param position viewType
     * @return true  this viewType is footView
     */
    private boolean isFooterViewPos(int position) {
        return position >= getHeadersCount() + getRealItemCount() && position < getHeadersCount() + getRealItemCount() + getFootersCount();
    }

    public void setCount(int count) {
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
            return RecyclerHolder.createRecyclerHolder(headerViews.get(viewType));
        } else if (footerViews.get(viewType) != null) {
            return RecyclerHolder.createRecyclerHolder(footerViews.get(viewType));
        }
        return adapter.onCreateViewHolder(parent, viewType);
    }


    @SuppressWarnings("unchecked")
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (isHeaderViewPos(position)) return;
        if (isFooterViewPos(position)) return;
        adapter.onBindViewHolder((RecyclerHolder) holder, position - getHeadersCount());
    }


    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        adapter.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            final GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return getItemViewType(position) == TYPE_HEADER ? gridLayoutManager.getSpanCount() : 1;
                }
            });
            gridLayoutManager.setSpanCount(gridLayoutManager.getSpanCount());
        }
    }


    @Override
    public void clear() {

    }

    private void addRangItemList(int itemStart, List<E> list) {
        adapter.getList().addAll(itemStart, list);
        notifyDataSetChanged();
    }

    private void refresh(List<E> list) {
        adapter.getList().clear();
        adapter.getList().addAll(list);
        notifyDataSetChanged();
    }

    private void removeAllList(List<E> e) {
        adapter.getList().removeAll(e);
        notifyDataSetChanged();
    }


    @Override
    public boolean setList(int position, List<E> e, int type) {
        if (type == 0x00) {
            addRangItemList(position, e);
        } else if (type == 0x01) {
            addRangItemList(getRealItemCount(), e);
        } else if (type == 0x10) {
            refresh(e);
        } else if (type == 0x20) {
            removeAllList(e);
        } else return false;
        return true;
    }


    @Override
    public boolean setEntity(int position, E e, int type) {
        if (type == 0x00) {
            addRangItem(position, e);
        } else if (type == 0x01) {
            addRangItem(getList().size(), e);
        } else if (type == 0x20) {
            remove(e);
        } else return false;
        return true;
    }

    private void remove(E e) {
        adapter.getList().remove(e);
        notifyDataSetChanged();
    }

    private void addRangItem(int position, E e) {
        adapter.getList().add(position,e);
        notifyDataSetChanged();
    }
    @Override
    public List<E> getList() {
        return adapter.getList();
    }

    public void setLayoutId(int layoutId) {
        adapter.setLayoutId(layoutId);
    }

    public void setClass(Class c, int layoutIndex) {
        setLayoutId(BaseUtil.getLayoutId(layoutIndex, c));
    }

    @Override
    public int size() {
        return getRealItemCount();
    }

    @Override
    public void modelView(int index, Event layoutModel, int type) {

    }
}
