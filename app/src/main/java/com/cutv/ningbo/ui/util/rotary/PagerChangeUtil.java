package com.cutv.ningbo.ui.util.rotary;

import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.android.annotations.NonNull;
import com.cutv.ningbo.ui.base.adapter.pager.ViewPagerAdapter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：10:44
 * modify developer：  admin
 * modify time：10:44
 * modify remark：
 *
 * @version 2.0
 */
public class PagerChangeUtil<CL extends ChangeListener> implements ViewPager.OnPageChangeListener,RadioGroup.OnCheckedChangeListener {
    private ViewPager viewPager;
    private RadioButton lastRadioButton;
    private HorizontalScrollView horizontalScrollView;
    private ImageView animView;
    private @LayoutRes int itemLayoutId,pointLayout;
    private int count = 0;
    private Listener<CL> listener;
    private int currentLeft = 0;
//    private FragmentManager fm;
    /**
     * the appropriate adapter
     */
    @Inject DisplayMetrics dm;
    @Inject LayoutInflater inflater;
    @Inject PagerChangeUtil(){}
//    @Inject ViewPagerAdapter adapter;

    private List<ChangeListener> changeListeners = new ArrayList<>();
//    private List<Fragment> fragmentList = new ArrayList<>();
    public void setListener(Listener<CL> listener) {
        this.listener = listener;
    }

    public void setAnimView(ImageView animView) {this.animView = animView;}

    public void setItemLayoutId(int itemLayoutId) {this.itemLayoutId = itemLayoutId;}

    public void setPointLayout(@LayoutRes int pointLayout){this.pointLayout = pointLayout;}

    public void setHorizontalScrollView(HorizontalScrollView horizontalScrollView) {this.horizontalScrollView = horizontalScrollView;}

//    public void setFm(FragmentManager fm){
//        this.fm = fm;
//    }

    /**
     * init the data,all the params can't be  null;
     * @param data the data
     * @param viewPager viewPager
     * @param radioGroup click the appropriate radioButton to go to the corresponding page;
     */
    public void setData(Collection<CL> data, @NonNull ViewPager viewPager, @NonNull RadioGroup radioGroup){
        this.viewPager = viewPager;
        if(count == 0)count = data.size();
        if(animView !=null&&listener!=null)listener.initAnimView(animView);
        if(data != null){
            changeListeners.clear();
            for(CL CL : data){
                changeListeners.add(CL);
//                if(CL.getT() instanceof View) viewList.add((View)CL.getT());
//                else if(CL.getT() instanceof Fragment) fragmentList.add((Fragment)CL.getT());
                if(itemLayoutId==0)throw new RuntimeException("pointLayout must be injected");
                if (pointLayout != 0) {
                    RadioButton rb = (RadioButton) inflater.inflate(pointLayout,null);
                    rb.setText(CL.getTextName());
                    rb.setCompoundDrawablesWithIntrinsicBounds(CL.left(), CL.top(), CL.right(), CL.bottom());
                    lastRadioButton = rb;
                    if(listener!=null)listener.initPager(CL,rb);
                }
            }
//            adapter.setList(changeListeners);
        }
        radioGroup.setOnCheckedChangeListener(this);
        viewPager.addOnPageChangeListener(this);
        ((RadioButton)radioGroup.getChildAt(0)).setChecked(true);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        View view = group.findViewById(checkedId);
        int indexOfChild = group.indexOfChild(view);
        if(view !=null){
            if(animView !=null){
                TranslateAnimation animation = new TranslateAnimation(currentLeft,view.getLeft(),0f,0f);
                animation.setInterpolator(new LinearInterpolator());
                animation.setDuration(100);
                animation.setFillAfter(true);
                animView.startAnimation(animation);
            }
            viewPager.setCurrentItem(indexOfChild);
            currentLeft = view.getLeft();
            if(listener!=null)listener.checkIndexPager(indexOfChild, (RadioButton)view,lastRadioButton);
            int mIndex = group.getChildCount()-1>2?2:group.getChildCount();
            if(horizontalScrollView!=null)horizontalScrollView.smoothScrollTo((indexOfChild >1?currentLeft:0)-group.getChildAt(mIndex).getLeft(),0);
            lastRadioButton = (RadioButton) view;
        }
    }

    public interface Listener<T>{
        void checkIndexPager(int index,RadioButton radioButton,RadioButton lastRadioButton);
        void initPager(T t,RadioButton radioButton);
        void initAnimView(ImageView animView);
    }
}
