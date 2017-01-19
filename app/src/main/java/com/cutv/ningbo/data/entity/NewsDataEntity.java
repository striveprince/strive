package com.cutv.ningbo.data.entity;


import android.os.Bundle;

import com.cutv.ningbo.R;
import com.cutv.ningbo.ui.activity.main.fragment.news.content.NewsContentFragment;
import com.cutv.ningbo.ui.util.rotary.ChangeListener;

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
public class NewsDataEntity extends BaseEntity implements ChangeListener<NewsContentFragment> {
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
    public NewsContentFragment getT() {
        NewsContentFragment fragment = new NewsContentFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("channelId",id);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public String getTextName() {
        return name;
    }

    @Override
    public int left() {
        return 0;
    }

    @Override
    public int right() {
        return 0;
    }

    @Override
    public int top() {
        return 0;
    }

    @Override
    public int bottom() {
        return 0;
    }

    @Override
    public int getLayout() {
        return R.layout.view_radio_news_tag;
    }
}
