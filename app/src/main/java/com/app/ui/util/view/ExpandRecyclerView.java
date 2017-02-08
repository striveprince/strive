package com.app.ui.util.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.app.R;
import com.app.data.entity.BaseEntity;
import com.app.databinding.ExpandRecyclerViewBinding;
import com.app.ui.base.adapter.recycler.RecyclerWrapper;
import com.app.ui.base.viewModel.RecyclerBindViewModel;
import com.app.ui.base.viewModel.RecyclerViewModel;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：14:19
 * modify developer：  admin
 * modify time：14:19
 * modify remark：
 *
 * @version 2.0
 */


public class ExpandRecyclerView<Entity extends BaseEntity> extends FrameLayout {
    private ExpandRecyclerViewBinding binding;
    private RecyclerViewModel<Entity> viewModel;
    private LinearLayoutManager mLayoutManager;
    public ExpandRecyclerView(Context context) {
        this(context,null);
    }

    public ExpandRecyclerView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ExpandRecyclerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs,defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ExpandRecyclerView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context,attrs,defStyleAttr);
    }


    public void init(Context context,AttributeSet attrs,int defStyleAttr){
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.expandRecyclerView);
        int itemType = ta.getResourceId(R.styleable.expandRecyclerView_item,0);
        int footType = ta.getResourceId(R.styleable.expandRecyclerView_foot,0);
        int headType = ta.getResourceId(R.styleable.expandRecyclerView_head,0);
        boolean isRefreshable = ta.getBoolean(R.styleable.expandRecyclerView_isRefreshable,true);
        boolean isReverse = ta.getBoolean(R.styleable.expandRecyclerView_isReverse,false);
        boolean isPage = ta.getBoolean(R.styleable.expandRecyclerView_isPage,true);
        int num = ta.getResourceId(R.styleable.expandRecyclerView_num,1);
        binding = DataBindingUtil.inflate(LayoutInflater.from(context),R.layout.expand_recycler_view,this,true);
        if(num == 1)mLayoutManager = new LinearLayoutManager(context);
        else mLayoutManager = new GridLayoutManager(context,num);
        binding.recyclerView.setLayoutManager(mLayoutManager);
        binding.recyclerView.setItemAnimator(new DefaultItemAnimator());
        binding.swipeRefreshLayout.setEnabled(isRefreshable);
        ta.recycle();
        viewModel = new RecyclerViewModel<>();
        viewModel.setPageFlag(isPage);
        setViewModel(viewModel);
        RecyclerWrapper<Entity> adapter = new RecyclerWrapper<>();
        adapter.setItemType(itemType);
        binding.recyclerView.setAdapter(adapter);
        adapter.addHeaderView(View.inflate(context,headType,null));
        adapter.addFooterView(View.inflate(context,footType,null));
        if(isReverse){
            mLayoutManager.setStackFromEnd(true);//列表再底部开始展示，反转后由上面开始展示
            mLayoutManager.setReverseLayout(true);//列表翻转
            binding.recyclerView.setLayoutManager(mLayoutManager);
        }
    }

    public void setViewModel(RecyclerViewModel viewModel) {
        binding.setVm(viewModel);
    }


}
