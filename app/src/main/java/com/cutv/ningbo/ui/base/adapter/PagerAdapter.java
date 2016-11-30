package com.cutv.ningbo.ui.base.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioGroup;


import com.cutv.ningbo.R;
import com.cutv.ningbo.ui.base.fragment.BaseFragment;

import java.util.List;

public class PagerAdapter implements RadioGroup.OnCheckedChangeListener {
    private List<BaseFragment> fragments; // 一个tab页面对应一个Fragment
    private RadioGroup rgs; // 用于切换tab
    private FragmentActivity fragmentActivity; // Fragment所属的Activity
    private int fragmentContentId; // Activity中所要被替换的区域的id
    private int currentTab = 0; // 当前Tab页面索引


    public void setFragments(List<BaseFragment> fragments,FragmentActivity fragmentActivity,RadioGroup rgs,int fragmentContentId) {
        this.fragments = fragments;
        this.rgs = rgs;
        this.fragmentActivity = fragmentActivity;
        this.fragmentContentId = fragmentContentId;


//        FragmentTransaction ft = fragmentActivity.getSupportFragmentManager().beginTransaction();
//        ft.add(fragmentContentId, fragments.get(0));
//        ft.commitAllowingStateLoss();
        rgs.setOnCheckedChangeListener(this);
        rgs.check(R.id.main_radio_homepager);

    }



    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
        for(int i = 0; i < rgs.getChildCount(); i++){


            if(rgs.getChildAt(i).getId() == checkedId){
                Fragment fragment = fragments.get(i);
                FragmentTransaction ft = fragmentActivity.getSupportFragmentManager().beginTransaction();
                fragments.get(currentTab).onPause(); // 暂停当前tab
                if(fragment.isAdded()){
                    fragment.onResume(); // 启动目标tab的onResume()
                }else{
                    ft.add(fragmentContentId, fragment);
                }
                for(int current = 0; current < fragments.size(); current++){
                    if(i == current){
                        ft.show(fragment);
                    }else{
                        ft.hide(fragment);
                    }
                    ft.commitAllowingStateLoss();
                }

                ft.commitAllowingStateLoss();
                break;
            }
        }
    }

    /**
     * 切换tab
     * @param idx
     */
//    private void showTab(int idx){
//
//        NewsContentFragment fragment = fragments.get(i);
//        FragmentTransaction ft = fragmentActivity.getSupportFragmentManager().beginTransaction();
//        fragments.get(currentTab).onPause(); // 暂停当前tab
//        if(fragment.isAdded()){
//            fragment.onResume(); // 启动目标tab的onResume()
//        }else{
//            ft.add(fragmentContentId, fragment);
//        }
//
//        for(int i = 0; i < fragments.size(); i++){
////            NewsContentFragment fragment = fragments.get(i);
////            FragmentTransaction ft = fragmentActivity.getSupportFragmentManager().beginTransaction();
//            if(idx == i){
//                ft.show(fragment);
//            }else{
//                ft.hide(fragment);
//            }
//            ft.commitAllowingStateLoss();
//        }
//        currentTab = idx; // 更新目标tab为当前tab
//    }





}
