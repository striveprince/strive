package com.cutv.ningbo.ui.activity.main;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioButton;

import com.cutv.ningbo.R;
import com.cutv.ningbo.data.portlet.PagerModel;
import com.cutv.ningbo.ui.activity.main.fragment.home.HomePageFragment;
import com.cutv.ningbo.ui.activity.main.fragment.interact.InteractFragment;
import com.cutv.ningbo.ui.activity.main.fragment.mall.MallFragment;
import com.cutv.ningbo.ui.activity.main.fragment.news.NewsFragment;
import com.cutv.ningbo.ui.activity.main.fragment.video.VideoFragment;
import com.cutv.ningbo.ui.base.fragment.BaseFragment;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：16:00
 * modify developer：  admin
 * modify time：16:00
 * modify remark：
 *
 * @version 2.0
 */


public class PagerMainModel implements PagerModel<BaseFragment> {
    private int position;
    private static final int[] drawables = {
            R.drawable.main_selector_radio_me,
            R.drawable.main_selector_radio_news,
            R.drawable.main_selector_radio_tv,
            R.drawable.main_selector_radio_interact,
            R.drawable.main_selector_radio_mail};
    private static final int[] texts = {
            R.string.main_tadio_home_pager,
            R.string.main_radio_news,
            R.string.main_radio_tv,
            R.string.main_radio_interact,
            R.string.main_radio_mail
    };
//    private static final int[] mainIds = {
//            R.id.main_home,
//            R.id.main_news,
//            R.id.main_video,
//            R.id.main_interact,
//            R.id.main_mall
//    };
    private BaseFragment fragment;

    public PagerMainModel(int position) {
        this.position = position;
        switch (position){
            case 0:fragment = new HomePageFragment();break;
            case 1:fragment = new NewsFragment();break;
            case 2:fragment = new InteractFragment();break;
            case 3:fragment = new VideoFragment();break;
            case 4:fragment = new MallFragment();break;
        }
    }

    @Override
    public BaseFragment getItem(int position,Context context) {
        return fragment;
    }

    public int getScrollVisible(){
        switch (position){
            case 1:return View.VISIBLE;
            case 2:return View.VISIBLE;
        }
        return View.GONE;
    }

    public boolean isViewVisible(){
        switch (position){
            case 0:return true;
        }
        return false;
    }

    public boolean isImage(){
        switch (position){
            case 0:return true;
        }
        return false;
    }

    public String getText(){
        switch (position){
            case 3:return "互动";
            case 4:return "商城";
        }
        return "";
    }

    @Override
    public RadioButton getView(int position,Context context) {
        RadioButton radioButton = (RadioButton) LayoutInflater.from(context).inflate(R.layout.view_radio_main,null);
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        radioButton.setWidth(dm.widthPixels/5);
        radioButton.setCompoundDrawablesWithIntrinsicBounds(0,drawables[position],0,0);
        radioButton.setText(context.getString(texts[position]));
//        radioButton.setId(mainIds[position]);
//        if(position == 0)radioButton.setChecked(true);
        return radioButton;
    }


}
