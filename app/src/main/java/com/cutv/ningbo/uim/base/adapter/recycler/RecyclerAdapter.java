package com.cutv.ningbo.uim.base.adapter.recycler;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.cutv.ningbo.BR;
import com.cutv.ningbo.uim.base.BaseUtil;
import com.cutv.ningbo.uim.base.adapter.IRecyclerAdapter;
import com.cutv.ningbo.uim.base.annotation.ModelView;
import com.cutv.ningbo.uim.base.model.inter.Event;

import java.util.ArrayList;
import java.util.List;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：14:47
 * modify developer：  admin
 * modify time：14:47
 * modify remark：
 *
 * @version 2.0
 */


public class RecyclerAdapter<E extends Event>
        extends RecyclerView.Adapter<RecyclerHolder>
        implements IRecyclerAdapter<E> {
    private int count;
    private List<E> list = new ArrayList<>();
    private Context context;
    private Class clazz;
    private int layoutIndex = 0;
    private int layoutId = 0;

    @Override
    public RecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (context == null) context = parent.getContext();
        if (layoutId == 0) {
            ModelView modelView = BaseUtil.findModelView(clazz);
            if (modelView == null)
                throw new RuntimeException(clazz!=null?
                        "this class :" + clazz.getName() + " need to add @ModelView"
                        :"the clazz == null,please use the method setAdapter(Class clazz) before setAdapter");
            int index = layoutIndex;
            if(layoutIndex>=modelView.value().length)index = 0;
            layoutId = modelView.value()[index];
        }
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), layoutId, null, false);
        return new RecyclerHolder(binding);
    }

    @Override
    public void onBindViewHolder(RecyclerHolder holder, int position) {
        E e = list.get(position);
        holder.getBinding().setVariable(BR.vm,e);
        holder.getBinding().executePendingBindings();
    }

    @Override
    public int getItemCount() {
        boolean flag = count < list.size() && count != 0;
        return flag ? count : list.size();
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public boolean setList(int position, List<E> e, int type) {
        if (type == 0x00) {
            addRangItemList(position, e);
        } else if (type == 0x01) {
            addRangItemList(getItemCount(), e);
        } else if (type == 0x10) {
            refresh(e);
        } else if (type == 0x20) {
            removeAllList(e);
        } else return false;
        return true;
    }

    public void removeAllList(List<E> e) {
        list.removeAll(e);
        notifyDataSetChanged();
    }

    public void refresh(List<E> e) {
        list.clear();
        list.addAll(e);
        notifyDataSetChanged();
    }

    public void addRangItemList(int position, List<E> e) {
        list.addAll(position, e);
        notifyDataSetChanged();
    }

    @Override
    public boolean setEntity(int position, E e, int type) {
        return false;
    }

    @Override
    public List<E> getList() {
        return list;
    }

    public void setCount(int count) {
       this.count = count;
    }

    public void setLayoutId(@LayoutRes int layoutId) {
        this.layoutId = layoutId;
    }

    public void setClazz(Class clazz,int layoutIndex) {
        this.clazz = clazz;
        this.layoutIndex = layoutIndex;
    }

    @Override
    public int size() {
        return getItemCount();
    }

    @Override
    public void modelView(int index, Event layoutModel, int type) {
        throw new RuntimeException("can't add header or footer,please use RecyclerAdapter1 to add header or footer");
    }
}
