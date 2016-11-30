package com.cutv.ningbo.ui.activity.main;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioGroup;

import com.cutv.ningbo.R;
import com.cutv.ningbo.inject.qualifier.context.ActivityContext;
import com.cutv.ningbo.ui.base.fragment.BaseFragment;

import java.util.List;

import javax.inject.Inject;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：17:24
 * modify developer：  admin
 * modify time：17:24
 * modify remark：
 *
 * @version 2.0
 */

public class OnMainChecked implements RadioGroup.OnCheckedChangeListener {
    private AppCompatActivity activity;
    private int currentTab = 0;
    private List<BaseFragment> fragments;

    @Inject
    OnMainChecked(@ActivityContext Context context, List<BaseFragment> fragments) {
        this.activity = (AppCompatActivity) context;
        this.fragments = fragments;
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
        FragmentTransaction ft = activity.getSupportFragmentManager().beginTransaction();
        int index = radioGroup.indexOfChild(radioGroup.findViewById(checkedId));
        Fragment fragment = fragments.get(index);
        Fragment beforeFragment = fragments.get(currentTab);
        beforeFragment.onPause();
        ft.hide(beforeFragment);
        if (fragment.isAdded()) fragment.onResume();
        else ft.add(R.id.tab_content, fragment);
        ft.show(fragment);
        ft.commitAllowingStateLoss();
        currentTab = index;
    }
}
