package com.cutv.ningbo.uim.base.layout;

import android.content.Context;
import android.content.res.TypedArray;
import android.databinding.ViewDataBinding;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.util.AttributeSet;

import com.cutv.ningbo.R;
import com.cutv.ningbo.uim.base.layout.model.PagerModel;
import com.cutv.ningbo.uim.base.layout.model.RecyclerModel;
import com.cutv.ningbo.uim.base.model.ViewHttpModel;
import com.cutv.ningbo.uim.base.model.inter.Event;

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


public class DataArrayLayout<E extends Event, Binding extends ViewDataBinding> extends DataBindingLayout<List<E>, Binding> {
    private ViewArrayModel<E,?> model;

    public DataArrayLayout(@NonNull Context context) {
        super(context);
    }

    public DataArrayLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public DataArrayLayout(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * @param context      context
     * @param attrs        attrs
     * @param defStyleAttr defStyleAttr
     *                     layout type
     *                     <flag name="recycler" value="0x00" />
     *                     <flag name="pager" value="0x10" />
     *                     <flag name="frame" value="0x20" />
     *                     <flag name="gallery" value="0x30" />
     *                     <flag name="flow" value="0x40" />
     */
    @Override
    public void init(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.layout);
        int type = array.getInteger(R.styleable.layout_type, 0);
        switch (type) {
            case 0x00:
                model = new RecyclerModel<>();
                ((RecyclerModel) model).setRefreshing(array.getBoolean(R.styleable.layout_refresh, true));
                ((RecyclerModel) model).setPageFlag(array.getBoolean(R.styleable.layout_page, true));
                int num = array.getInteger(R.styleable.layout_page, 1);
                ((RecyclerModel) model).setLayoutManager(num > 1 ? new GridLayoutManager(context, num) : new LinearLayoutManager(context));
                break;
            case 0x10:
                model = new PagerModel();
                ((PagerModel)model).setLoop(array.getInteger(R.styleable.layout_loop, -1));
                ((PagerModel)model).setCount(array.getInteger(R.styleable.layout_count, 0));
                ((PagerModel)model).setRotate(array.getBoolean(R.styleable.layout_rotate, false));
                break;
            case 0x20:
                break;
            case 0x30:
                break;
            default:
                super.init(context, attrs, defStyleAttr);
                return;
        }
        model.setEnable(array.getBoolean(R.styleable.layout_enable, true));
        model.setHolder_index(array.getInteger(R.styleable.layout_holder_index, 0));
        bindModelView(context, array, model);
    }

    @Override
    void setAdapter(Class c, Object... args) {
        super.setAdapter(c, args);
        model.setAdapter(c,args);
    }

    @Override
    public ViewHttpModel<List<E>> getModel() {
        return model;
    }

}
