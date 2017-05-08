package com.cutv.ningbo.uim.base.layout;

import android.content.Context;
import android.content.res.TypedArray;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.cutv.ningbo.BR;
import com.cutv.ningbo.R;
import com.cutv.ningbo.uim.base.BaseUtil;
import com.cutv.ningbo.uim.base.adapter.IModelAdapter;
import com.cutv.ningbo.uim.base.annotation.AdapterEntity;
import com.cutv.ningbo.uim.base.annotation.ModelView;
import com.cutv.ningbo.uim.base.cycle.CycleContainer;
import com.cutv.ningbo.uim.base.cycle.DataBindingActivity;
import com.cutv.ningbo.uim.base.layout.model.MediaControlModel;
import com.cutv.ningbo.uim.base.model.inter.Http;
import com.cutv.ningbo.uim.base.model.inter.HttpArray;
import com.cutv.ningbo.uim.base.model.inter.Model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：14:13
 * modify developer：  admin
 * modify time：14:13
 * modify remark：
 *
 * @version 2.0
 */

public class DataBindingLayout<T, Binding extends ViewDataBinding>
        extends FrameLayout
        implements CycleContainer<Binding>, Model{
    private int index;
    private ViewLayoutModel<T> model;
    private Binding binding;

    public DataBindingLayout(@NonNull Context context) {
        this(context, null);
    }

    public DataBindingLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DataBindingLayout(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    /**
     * @param context      context
     * @param attrs        attrs
     * @param defStyleAttr defStyleAttr
     *                     layout type
     *                     <flag name="pager" value="0x10" />
     *                     <flag name="frame" value="0x20" />
     *                     <flag name="gallery" value="0x30" />
     *                     <flag name="flow" value="0x40" />
     *                     <flag name="media" value="0x50" />
     */
    public void init(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.layout);
        int type = array.getInteger(R.styleable.layout_type, 0);
        switch (type) {
            case 0x50:
                model = new MediaControlModel<>();
                break;
            default:
                throw new RuntimeException();
        }
        bindModelView(context, array,model);
    }

    protected void bindModelView(Context context, TypedArray array,ViewLayoutModel<T> model) {
        index = array.getInteger(R.styleable.layout_index, 0);
        ModelView modelView = model.getModelView();
        if (modelView.cycle()) addViewSet(this);
        int[] values = modelView.value();
        if (index >= values.length) index = 0;
        int layoutId = modelView.value()[index];
        binding = DataBindingUtil.inflate(LayoutInflater.from(context), layoutId, this, true);
        array.recycle();
        getModel().attachView(this,index);
    }

    public ViewLayoutModel<T> getModel() {
        return model;
    }

    @Override
    public Set<Model> addViewSet(View view) {
        Set<Model> set = new HashSet<>();
        BaseUtil.addViewSet(set, view);
        return set;
    }


    @Override
    public DataBindingActivity getDataActivity() {
        if (getContext() instanceof DataBindingActivity) {
            return (DataBindingActivity) getContext();
        }
        return null;
    }

    @Override
    public void attachView(CycleContainer cycleContainer,int model_index) {

    }

    @Override
    public void onResume() {
        getModel().onResume();
    }

    @Override
    public void onPause() {
        getModel().onPause();
    }

    @Override
    public void detachView() {
        getModel().detachView();
    }

    @Override
    public Binding getBinding() {
        return binding;
    }


    /**
     * @param http the http interface ,it ues to transform the Observable
     * @param args the array of adapter constructor params for example:
     *             {@link FragmentPagerAdapter}
     *             FragmentAdapter adapter = new FragmentAdapter(fm);
     *             and the args is {fm}
     */
    public void setHttpObservable(Http<T> http,Object...args) {
        setHttpObservable(http, 0,args);
    }

    /**
     * @param http the http interface ,it ues to transform the Observable
     * @param holder_index the index of model ,and {@link ModelView} layoutId = ModelView.value()[holder_index] default = 0;
     * @param args the array of adapter constructor params for example:
     *             {@link FragmentPagerAdapter}
     *             FragmentAdapter adapter = new FragmentAdapter(fm);
     *             and the args is {fm}
     */
    public void setHttpObservable(Http<T> http, int holder_index,Object...args) {
        getModel().setRcHttp(http);
        getModel().setHolder_index(holder_index);
        if (http instanceof HttpArray) {
            Class<?> c  = BaseUtil.getInterfacesGenericType(http.getClass(), HttpArray.class);
            setAdapter(c,args);
        }
        getModel().onHttp();
        getModel().attachView(this,index);
    }

    /**
     * @param data the data
     * @param args the array of adapter constructor params for example:
     *             {@link FragmentPagerAdapter}
     *             FragmentAdapter adapter = new FragmentAdapter(fm);
     *             and the args is {fm}
     */
    public void setData(T data,Object... args){
        setData(data,0,args);
    }

    /**
     * @param data the data
     * @param holder_index the index of model ,and {@link ModelView} layoutId = ModelView.value()[holder_index] default = 0;
     * @param args the array of adapter constructor params for example:
     *             {@link FragmentPagerAdapter}
     *             FragmentAdapter adapter = new FragmentAdapter(fm);
     *             and the args is {fm}
     */
    public void setData(T data,int holder_index,Object...args){
        getModel().setHolder_index(holder_index);
        if (data != null && data instanceof List) {
            List list = (List)data;
            if(!list.isEmpty()){
                Class<?> c  = list.get(0).getClass();
                setAdapter(c,args);
            }
        }
        getModel().attachView(this,index);
    }

    /**
     * @param args the array of adapter constructor params for example:
     *             {@link FragmentPagerAdapter}
     *             FragmentAdapter adapter = new FragmentAdapter(fm);
     *             and the args is {fm} or{adapter}
     */
    void setAdapter(Class<?> c,Object...args){
    }
}
