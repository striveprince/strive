package com.app.ui.activity.live.list;

import android.content.Context;

import com.app.data.api.LiveApi;
import com.app.data.entity.InfoEntity;
import com.app.data.entity.live.LiveListDataDto;
import com.app.data.entity.live.LiveListItemDto;
import com.app.inject.qualifier.context.ActivityContext;
import com.app.ui.base.viewModel.RecyclerModel;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：9:36
 * modify developer：  admin
 * modify time：9:36
 * modify remark：
 *
 * @version 2.0
 */
public class LiveListViewModel extends RecyclerModel<LiveListDataDto,LiveListItemDto> {

    @Inject
    LiveApi api;

    @Inject
    public LiveListViewModel(@ActivityContext Context context) {
        super(context);
    }

    @Override
    public Observable<InfoEntity<LiveListDataDto>> httpInit() {
        return api.getLiveList();


    }

    @Override
    public List<LiveListItemDto> transform(LiveListDataDto liveListDataDto) {
        return liveListDataDto.getRooms();
    }
}
