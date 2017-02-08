package com.app.ui.base.recycler;

import android.content.Context;
import android.util.AttributeSet;

import com.app.data.entity.BaseEntity;

import java.util.List;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：16:36
 * modify developer：  admin
 * modify time：16:36
 * modify remark：
 *
 * @version 2.0
 */

public class ExpandMatchRecyclerView<Entity extends BaseEntity> extends ExpandRecyclerView<List<Entity>,Entity>{

    public ExpandMatchRecyclerView(Context context) {
        this(context,null);
    }

    public ExpandMatchRecyclerView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ExpandMatchRecyclerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setRespond(this);
    }

    public ExpandMatchRecyclerView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        setRespond(this);
    }

    @Override
    public List<Entity> transform(List<Entity> entities) {
        return entities;
    }
}
