package com.app.ui.base.recycler;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.app.R;
import com.app.data.entity.BaseEntity;
import com.app.data.entity.InfoEntity;
import com.app.databinding.ExpandRecyclerViewBinding;
import com.app.ui.base.respond.Respond;
import com.app.ui.base.viewModel.RecyclerViewModel;

import java.util.List;

import rx.Observable;

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


public class ExpandRecyclerView<T, Entity extends BaseEntity> extends FrameLayout implements Respond.TransformRespond<T, Entity> {
    private ExpandRecyclerViewBinding binding;
    private RecyclerViewModel<T, Entity> viewModel;
    private Respond.TransformRespond<T, Entity> respond;

    public ExpandRecyclerView(Context context) {
        this(context, null);
    }

    public ExpandRecyclerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ExpandRecyclerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ExpandRecyclerView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs, defStyleAttr);
    }

    public void init(Context context, AttributeSet attrs, int defStyleAttr) {
        this.respond = this;
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.expandRecyclerView);
        int itemType = ta.getResourceId(R.styleable.expandRecyclerView_item, 0);
        int empty = ta.getResourceId(R.styleable.expandRecyclerView_empty, 0);
        int headType = ta.getResourceId(R.styleable.expandRecyclerView_head, 0);
        int footType = ta.getResourceId(R.styleable.expandRecyclerView_foot, 0);
        boolean isRefreshable = ta.getBoolean(R.styleable.expandRecyclerView_isRefreshable, true);
        boolean isReverse = ta.getBoolean(R.styleable.expandRecyclerView_isReverse, false);
        boolean isPage = ta.getBoolean(R.styleable.expandRecyclerView_isPage, true);
        int num = ta.getResourceId(R.styleable.expandRecyclerView_num, 1);
        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.expand_recycler_view, this, true);
        LinearLayoutManager mLayoutManager;
        if (num == 1) mLayoutManager = new LinearLayoutManager(context);
        else mLayoutManager = new GridLayoutManager(context, num);
        binding.recyclerView.setLayoutManager(mLayoutManager);
        binding.recyclerView.setItemAnimator(new DefaultItemAnimator());
        binding.swipeRefreshLayout.setEnabled(isRefreshable);
        ta.recycle();
        viewModel = new RecyclerViewModel<>(context);
        viewModel.setPageFlag(isPage);
        binding.setVm(viewModel);
        RecyclerWrapper<Entity> adapter = new RecyclerWrapper<>();
        adapter.setItemType(itemType);
        binding.recyclerView.setAdapter(adapter);
        adapter.addHeaderView(View.inflate(context, headType, null));
        adapter.addFooterView(View.inflate(context, footType, null));
        if (isReverse) {
            mLayoutManager.setStackFromEnd(true);//列表再底部开始展示，反转后由上面开始展示
            mLayoutManager.setReverseLayout(true);//列表翻转
            binding.recyclerView.setLayoutManager(mLayoutManager);
        }
    }

    public ExpandRecyclerView setObservable(Observable<InfoEntity<T>> observable) {
        viewModel.setObservable(observable);
        binding.setVm(viewModel);
        return this;
    }

    public ExpandRecyclerView setRespond(Respond.TransformRespond<T, Entity> respond) {
        this.respond = respond;
        return this;
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        viewModel.detachView();
    }

    public void start(){
        if (respond != null) viewModel.attachView(respond, null);
    }

    @Override
    public List<Entity> transform(T t) {
        return null;
    }

    @Override
    public void onCompleted(Throwable e, T t, int count) {
        binding.emptyView.setVisibility(count == 0?VISIBLE:GONE);
    }
}

