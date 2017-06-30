package com.read.group.bindingAdapter;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsSpinner;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterViewAnimator;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.read.group.base.adapter.pager.FragmentAdapter;
import com.read.group.base.adapter.IModelAdapter;
import com.read.group.base.adapter.pager.ViewPagerAdapter;
import com.read.group.base.model.inter.ItemEvent;

import java.util.List;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：9:51
 * modify developer：  admin
 * modify time：9:51
 * modify remark：
 *
 * @version 2.0
 */


public class DataBindingAdapter {
    //    --------------------------ProgressBar------------------------
    @BindingAdapter("android:secondaryProgress")
    public static void setSecondaryProgress(ProgressBar bar, int progress) {
        bar.setSecondaryProgress(progress);
    }


    //    --------------------------RadioButton------------------------
    @BindingAdapter("sound")
    public static void setSoundEffectsEnabled(RadioButton button, boolean sound) {
        button.setSoundEffectsEnabled(sound);
    }

    //    --------------------------RadioGroup------------------------
    @BindingAdapter("checked")
    public static void addCheckedListener(RadioGroup group, RadioGroup.OnCheckedChangeListener listener) {
        if (listener != null) group.setOnCheckedChangeListener(listener);
    }

    @BindingAdapter("radios_mute")
    public static void addRadioButton(RadioGroup group, List<? extends ItemEvent<?, View>> list) {
        group.removeAllViews();
        int i = 0;
        if (list != null)
            for (ItemEvent<?, View> model : list) {
                View button = model.getHolderView();
                button.setSoundEffectsEnabled(false);
                group.addView(button);
//                if (i == 0) button.performClick();
                i++;
            }
    }

    @BindingAdapter("checkedPosition")
    public static void checkPosition(RadioGroup group, int position) {
        if (position < group.getChildCount()) group.check(group.getChildAt(position).getId());
    }

    //    --------------------------RecyclerView------------------------

    @BindingAdapter({"item_anim"})
    public static void setItemAnimator(RecyclerView view, DefaultItemAnimator animator) {
        if (animator != null) view.setItemAnimator(animator);
    }


    @BindingAdapter({"scroll_listener"})
    public static void setOnScroll(RecyclerView view, RecyclerView.OnScrollListener listener) {
        if (listener != null) view.addOnScrollListener(listener);
    }

    @BindingAdapter({"layout_manager"})
    public static void setLayoutManager(RecyclerView view, RecyclerView.LayoutManager layoutManager) {
        if (layoutManager != null) view.setLayoutManager(layoutManager);
    }

    //    --------------------------ImageView------------------------
    @BindingAdapter({"android:src"})
    public static void setImageUrl(ImageView imageView, String imageUrl) {
        Context context = imageView.getContext();
//        Target<GlideDrawable> target = Glide.with(context).load(url).into(imageView);
//        target.onLoadStarted(ContextCompat.getDrawable(context, R.mipmap.loading));
//        target.onLoadFailed(new Exception("url:" + url), ContextCompat.getDrawable(context, R.mipmap.icon_failure));
        Glide.with(context)
                .load(imageUrl)
                .into(imageView);
//                .onLoadStarted(ContextCompat.getDrawable(context, R.mipmap.loading))
    }

    @BindingAdapter("android:src")
    public static void setImage(ImageView view, @DrawableRes int mipmapId) {
        if (mipmapId != 0) view.setImageResource(mipmapId);
    }

    //    --------------------------SeekBar------------------------
    @BindingAdapter("listener")
    public static void setOnSeekBarChangeListener(SeekBar bar, SeekBar.OnSeekBarChangeListener listener) {
        bar.setOnSeekBarChangeListener(listener);
    }

    //    --------------------------TextView------------------------
    @BindingAdapter({"set_int_text"})
    public static void setIntText(TextView textView, int nub) {
        textView.setText(String.valueOf(nub));
    }

    @BindingAdapter({"android:drawableTop"})
    public static void setDrawableTop(TextView view, String image) {
        Context mContext = view.getContext();
        Glide.with(mContext).load(image).asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                Drawable drawable = new BitmapDrawable(mContext.getResources(), resource);
                drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
                Drawable[] drawables = view.getCompoundDrawables();
                view.setCompoundDrawables(drawables[0], drawable, drawables[2], drawables[3]);
            }
        });
    }

    //    --------------------------View------------------------
    @BindingAdapter("android:slide_list")
    public static void setVisibility(View view, int visibility) {
        if (visibility != view.getVisibility()) {
            view.setVisibility(visibility);
        }
    }

    @BindingAdapter({"adapter"})
    public static void setAdapter(View view, IModelAdapter adapter) {
        if (adapter==null)return;
        if (view instanceof RecyclerView && adapter instanceof RecyclerView.Adapter) {
            ((RecyclerView)view).setAdapter((RecyclerView.Adapter) adapter);
        }else if(view instanceof ViewPager && adapter instanceof PagerAdapter){
            ((ViewPager)view).setAdapter((PagerAdapter) adapter);
        }else if(view instanceof AbsListView && adapter instanceof ListAdapter){
            ((ListView)view).setAdapter((ListAdapter) adapter);
        }else if(view instanceof AbsSpinner && adapter instanceof SpinnerAdapter){
            ((AbsSpinner)view).setAdapter((SpinnerAdapter) adapter);
        }else if(view instanceof AdapterViewAnimator && adapter instanceof Adapter){
            ((AdapterViewAnimator)view).setAdapter((Adapter)adapter);
        }else if(view instanceof AdapterView && adapter instanceof Adapter){
            ((AdapterView)view).setAdapter((Adapter)adapter);
        }
    }

    @BindingAdapter("params")
    public static void setLayoutParams(View view, ViewGroup.LayoutParams params) {
        if (params != null) {
            view.setLayoutParams(params);
        }
    }

    @BindingAdapter({"android:background"})
    public static void setBackground(View view, String imageUrl) {
        Context mContext = view.getContext();
        Glide.with(mContext).load(imageUrl).asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                Drawable drawable = new BitmapDrawable(mContext.getResources(), resource);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    view.setBackground(drawable);
                } else {
                    view.setBackgroundDrawable(drawable);
                }
            }
        });
    }

    //    --------------------------ViewPager------------------------
    @BindingAdapter("pager_change")
    public static void addOnPagerChange(ViewPager pager, ViewPager.OnPageChangeListener listener) {
        pager.addOnPageChangeListener(listener);
    }

    @BindingAdapter("currentItem")
    public static void setCurrentItem(ViewPager pager, int currentItem) {
        pager.setCurrentItem(currentItem);
    }

    @BindingAdapter("adapter")
    public static void setAdapter(ViewPager pager, IModelAdapter adapter) {
        if (adapter instanceof ViewPagerAdapter) {
            pager.setAdapter((ViewPagerAdapter) adapter);
        } else if (adapter instanceof FragmentAdapter) {
            pager.setAdapter((FragmentAdapter) adapter);
        }
    }
}
