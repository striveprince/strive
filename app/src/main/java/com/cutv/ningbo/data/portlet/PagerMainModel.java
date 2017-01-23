package com.cutv.ningbo.data.portlet;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.RadioButton;

import com.cutv.ningbo.R;
import com.cutv.ningbo.ui.activity.main.fragment.home.HomePageFragment;
import com.cutv.ningbo.ui.activity.main.fragment.news.NewsFragment;
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
    private static final BaseFragment[] fragments = new BaseFragment[]{
            new HomePageFragment(),
            new NewsFragment(),
            new HomePageFragment(),
            new HomePageFragment(),
            new HomePageFragment()
    };
    @Override
    public BaseFragment getItem(int position,Context context) {
        return fragments[position];
    }

    @Override
    public RadioButton getView(int position,Context context) {
        RadioButton radioButton = (RadioButton) LayoutInflater.from(context).inflate(R.layout.view_radio_main,null);
        radioButton.setCompoundDrawablesWithIntrinsicBounds(0,drawables[position],0,0);
        radioButton.setText(context.getString(texts[position]));
        if(position == 0)radioButton.setChecked(true);
        return radioButton;
    }

}
