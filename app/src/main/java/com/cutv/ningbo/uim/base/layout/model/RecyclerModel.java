package com.cutv.ningbo.uim.base.layout.model;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.cutv.ningbo.BR;
import com.cutv.ningbo.R;
import com.cutv.ningbo.uim.base.BaseUtil;
import com.cutv.ningbo.uim.base.adapter.IRecyclerAdapter;
import com.cutv.ningbo.uim.base.adapter.recycler.RecyclerAdapter1;
import com.cutv.ningbo.uim.base.annotation.ModelView;
import com.cutv.ningbo.uim.base.cycle.CycleContainer;
import com.cutv.ningbo.uim.base.layout.ViewArrayModel;
import com.cutv.ningbo.uim.base.model.inter.Event;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：9:19
 * modify developer：  admin
 * modify time：9:19
 * modify remark：
 *
 * @version 2.0
 */

@ModelView(value = R.layout.layout_recycler_view,name = {BR.layout})
public class RecyclerModel<E extends Event> extends ViewArrayModel<E, IRecyclerAdapter<E>> {
    public final ObservableBoolean loading = new ObservableBoolean(false);

    public ObservableField<String> empty = new ObservableField<>("");
    private SwipeRefreshLayout.OnRefreshListener onRefreshListener = () -> onHttp(0, false);
    private LinearLayoutManager layoutManager;
    private DefaultItemAnimator itemAnimator = new DefaultItemAnimator();

    private int lastVisibleItem = 0;
    private boolean pageFlag = true;

    @Override
    public void attachView(CycleContainer cycleContainer,int model_index) {
        super.attachView(cycleContainer,model_index);
        if(layoutManager==null)layoutManager = new LinearLayoutManager(cycleContainer.getDataActivity());
    }

    @Override
   public void setAdapter(@NonNull Class<?extends Event> clazz, Object...args) {
        if (adapter == null) {
            adapter = new RecyclerAdapter1<>();
            notifyPropertyChanged(BR.adapter);
        }
        adapter.setLayoutId(BaseUtil.getLayoutId(getHolder_index(), clazz));
    }


    public void onScrollBottom() {}

    private RecyclerView.OnScrollListener scrollListener = new RecyclerView.OnScrollListener() {
        private int dy = 0;

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if (adapter == null) return;
            if (newState == RecyclerView.SCROLL_STATE_IDLE
                    && lastVisibleItem + 1 >= adapter.size()
                    && !loading.get()) {
                if (pageFlag && dy > 0) onHttp(adapter.size(), false);
                onScrollBottom();
            }
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            lastVisibleItem = ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastVisibleItemPosition();
            this.dy = dy;
        }
    };

    public RecyclerView.OnScrollListener getScrollListener() {
        return scrollListener;
    }

    public void setPageFlag(boolean pageFlag) {
        this.pageFlag = pageFlag;
    }

    public SwipeRefreshLayout.OnRefreshListener getOnRefreshListener() {
        return onRefreshListener;
    }


    public void setLayoutManager(LinearLayoutManager layoutManager) {
        this.layoutManager = layoutManager;
    }

    public LinearLayoutManager getLayoutManager() {
        return layoutManager;
    }

    public void setItemAnimator(DefaultItemAnimator itemAnimator) {
        this.itemAnimator = itemAnimator;
    }

    @Override
    public IRecyclerAdapter<E> getAdapter() {
        return super.getAdapter();
    }

    public DefaultItemAnimator getItemAnimator() {
        return itemAnimator;
    }
}

