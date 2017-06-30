package com.read.group.base.adapter.list;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.android.databinding.library.baseAdapters.BR;
import com.read.group.R;
import com.read.group.base.adapter.IModelAdapter;
import com.read.group.base.adapter.IRecyclerAdapter;
import com.read.group.base.annotation.ModelView;
import com.read.group.base.model.inter.Event;
import com.read.group.base.util.BaseUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：10:58
 * modify developer：  admin
 * modify time：10:58
 * modify remark：
 *
 * @version 2.0
 */


public class ListAdapter<E extends Event> extends BaseAdapter implements IRecyclerAdapter<E> {
    private List<E> list = new ArrayList<>();
    private int count = 0;
    private int layoutId;
    private Class<?> c;
    private int layoutIndex;

    @Override
    public int getCount() {
        return count == 0 || count > list.size() ? list.size() : count;
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Context context = parent.getContext();
        ViewDataBinding binding;
        if (convertView == null) {
            if (layoutId == 0) {
                if (c == null) c = list.get(0).getClass();
                ModelView modelView = BaseUtil.findModelView(c);
                if (modelView == null)
                    throw new RuntimeException(c != null ?
                            "this class :" + c.getName() + " need to add @ModelView"
                            : "the clazz == null,please use the method setAdapter(Class clazz) before setAdapter");
                layoutId = modelView.value()[layoutIndex];
            }
            binding = DataBindingUtil.inflate(LayoutInflater.from(context), layoutId, null, false);
            convertView = binding.getRoot();
            convertView.setTag(R.id.holder_list,binding);
        } else {
            binding = (ViewDataBinding)convertView.getTag(R.id.holder_list);
        }
        binding.setVariable(BR.vm,list.get(position));
        binding.executePendingBindings();
        return convertView;
    }

    //    -----------------------------------  IModelAdapter ------------------------
    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public boolean setList(int position, List<E> e, int type) {
        return false;
    }

    @Override
    public boolean setEntity(int position, E e, int type) {
        return false;
    }

    @Override
    public List<E> getList() {
        return list;
    }

    @Override
    public int size() {
        return list.size();
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setLayoutIndex(int layoutIndex) {
        this.layoutIndex = layoutIndex;
    }

    @Override
    public void setLayoutId(@LayoutRes int layoutId) {
        this.layoutId = layoutId;
    }
}
