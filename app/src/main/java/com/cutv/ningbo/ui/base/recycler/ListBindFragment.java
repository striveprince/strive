/*
package com.cutv.ningbo.ui.base.recycler;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cutv.ningbo.R;
import com.cutv.ningbo.data.entity.BaseEntity;
import com.cutv.ningbo.databinding.ViewRecyclerViewBinding;
import com.cutv.ningbo.inject.qualifier.context.FragmentContext;
import com.cutv.ningbo.ui.base.adapter.RecyclerWrapper;
import com.cutv.ningbo.ui.base.fragment.BaseFragment;
import com.cutv.ningbo.ui.base.respond.Respond;

import java.util.List;

import javax.inject.Inject;

import timber.log.Timber;

*/
/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：13:51
 * modify developer：  admin
 * modify time：13:51
 * modify remark：
 *
 * @version 2.0
 *//*



public abstract class ListBindFragment<
        Entity extends BaseEntity,
        VM extends RecyclerBindViewModel<?,Entity>,
        Adapter extends RecyclerWrapper<Entity,?>>

        extends BaseFragment<VM, ViewRecyclerViewBinding>

        implements Respond.RecyclerRespond<Entity>{

    @Inject
    public Adapter adapter;
    @Inject
    @FragmentContext
    Context context;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return setAndBindContentView(inflater, container, R.layout.view_recycler_view, savedInstanceState);
    }


    //
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel.onRefresh(true);
        binding.swipeRefreshLayout.setRefreshing(true);
        binding.swipeRefreshLayout.setOnRefreshListener(() -> viewModel.onRefresh(false));
        binding.recyclerView.addOnScrollListener(onScrollListener);

        binding.swipeRefreshLayout.setColorSchemeResources(
                R.color.swiperefresh_color1,
                R.color.swiperefresh_color2,
                R.color.swiperefresh_color3,
                R.color.swiperefresh_color4);

        binding.recyclerView.setAdapter(adapter);
    }


    @Override
    public void onRefresh(boolean success, boolean initialLoading, List<Entity> list) {
        binding.swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onMore(boolean success, List<Entity> list) {
        binding.swipeRefreshLayout.setRefreshing(false);
    }

    private RecyclerView.OnScrollListener onScrollListener = new RecyclerView.OnScrollListener() {
        private int lastVisibleItem = 0;
        int offset = 0;
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);

            if (newState == RecyclerView.SCROLL_STATE_IDLE
                    && lastVisibleItem + 1 >= adapter.getItemCount()
                    && !binding.swipeRefreshLayout.isRefreshing()) {
                offset = adapter.getRealItemCount();
                viewModel.onMore(offset);
                onScrollBottom();
            }
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            lastVisibleItem = ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastVisibleItemPosition();
        }
    };

    public void setHeader(View v){
        adapter.removeAllHeader();
        adapter.addHeaderView(v);
    }

    public void setFooter(View v){
        adapter.removeAllFooter();
        adapter.addFooterView(v);
    }


    public void onScrollBottom(){
        Timber.i("scroll to end");
    }
}
*/
