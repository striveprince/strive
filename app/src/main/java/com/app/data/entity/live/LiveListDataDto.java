package com.app.data.entity.live;

import com.app.data.entity.BaseEntity;

import java.util.List;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：14:21
 * modify developer：  admin
 * modify time：14:21
 * modify remark：
 *
 * @version 2.0
 */


public class LiveListDataDto extends BaseEntity {
    private transient  int id;
    private List<LiveListItemDto> recommend,rooms;
    private LiveListNoticeDto notice;

    public List<LiveListItemDto> getRecommend() {
        return recommend;
    }

    public void setRecommend(List<LiveListItemDto> recommend) {
        this.recommend = recommend;
    }

    public List<LiveListItemDto> getRooms() {
        return rooms;
    }

    public void setRooms(List<LiveListItemDto> rooms) {
        this.rooms = rooms;
    }

    public LiveListNoticeDto getNotice() {
        return notice;
    }

    public void setNotice(LiveListNoticeDto notice) {
        this.notice = notice;
    }
}
