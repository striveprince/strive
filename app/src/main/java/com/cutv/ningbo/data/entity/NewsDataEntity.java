package com.cutv.ningbo.data.entity;


import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.RadioButton;

import com.cutv.ningbo.R;
import com.cutv.ningbo.ui.activity.main.fragment.news.content.NewsContentFragment;
import com.cutv.ningbo.data.portlet.PagerModel;

/**
 * project：cutv_ningbo
 * description：
 * create developer： Arvin
 * create time：2015/12/17 13:34.
 * modify developer：  Arvin
 * modify time：2015/12/17 13:34.
 * modify remark：
 * @version 2.0`
 */
public class NewsDataEntity extends BaseEntity implements PagerModel<NewsContentFragment> {
    private int id;
    private String name,index_img,description,source;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIndex_img() {
        return index_img;
    }

    public void setIndex_img(String index_img) {
        this.index_img = index_img;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }


    @Override
    public NewsContentFragment getItem(int position,Context context) {
        NewsContentFragment fragment = new NewsContentFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("channelId",id);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public RadioButton getView(int position,Context context) {
        RadioButton rb = (RadioButton) LayoutInflater.from(context).inflate(R.layout.view_radio_news_tag, null);
        rb.setText(name);
        DisplayMetrics dm = rb.getContext().getResources().getDisplayMetrics();
        ViewGroup.LayoutParams params = rb.getLayoutParams() == null ?
                new ViewGroup.LayoutParams(dm.widthPixels/5, ViewGroup.LayoutParams.MATCH_PARENT)
                : rb.getLayoutParams();
        params.width = dm.widthPixels/5;
        rb.setLayoutParams(params);
        return rb;
    }

}
