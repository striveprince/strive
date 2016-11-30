package com.cutv.ningbo.data.entity;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * project：cutv_ningbo
 * description：
 * create developer： Arvin
 * create time：2016/1/19 16:48.
 * modify developer：  Arvin
 * modify time：2016/1/19 16:48.
 * modify remark：
 *
 * @version 2.0
 */
public class HomeDataEntity extends BaseEntity implements Parcelable {

    private List<HomeSlideEntity> slide, application, gonggao, activity, news, biaotai;

    public List<HomeSlideEntity> getSlide() {
        return slide;
    }

    public void setSlide(List<HomeSlideEntity> slide) {
        this.slide = slide;
    }

    public List<HomeSlideEntity> getApplication() {
        return application;
    }

    public void setApplication(List<HomeSlideEntity> application) {
        this.application = application;
    }

    public List<HomeSlideEntity> getGonggao() {
        return gonggao;
    }

    public void setGonggao(List<HomeSlideEntity> gonggao) {
        this.gonggao = gonggao;
    }

    public List<HomeSlideEntity> getActivity() {
        return activity;
    }

    public void setActivity(List<HomeSlideEntity> activity) {
        this.activity = activity;
    }

    public List<HomeSlideEntity> getNews() {
        return news;
    }

    public void setNews(List<HomeSlideEntity> news) {
        this.news = news;
    }

    public List<HomeSlideEntity> getBiaotai() {
        return biaotai;
    }

    public void setBiaotai(List<HomeSlideEntity> biaotai) {
        this.biaotai = biaotai;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.slide);
        dest.writeTypedList(this.application);
        dest.writeTypedList(this.gonggao);
        dest.writeTypedList(this.activity);
        dest.writeTypedList(this.news);
        dest.writeTypedList(this.biaotai);
    }

    public HomeDataEntity() {
    }

    protected HomeDataEntity(Parcel in) {
        this.slide = in.createTypedArrayList(HomeSlideEntity.CREATOR);
        this.application = in.createTypedArrayList(HomeSlideEntity.CREATOR);
        this.gonggao = in.createTypedArrayList(HomeSlideEntity.CREATOR);
        this.activity = in.createTypedArrayList(HomeSlideEntity.CREATOR);
        this.news = in.createTypedArrayList(HomeSlideEntity.CREATOR);
        this.biaotai = in.createTypedArrayList(HomeSlideEntity.CREATOR);
    }

    public static final Parcelable.Creator<HomeDataEntity> CREATOR = new Parcelable.Creator<HomeDataEntity>() {
        @Override
        public HomeDataEntity createFromParcel(Parcel source) {
            return new HomeDataEntity(source);
        }

        @Override
        public HomeDataEntity[] newArray(int size) {
            return new HomeDataEntity[size];
        }
    };
}
