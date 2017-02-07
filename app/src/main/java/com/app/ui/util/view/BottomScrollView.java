package com.app.ui.util.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;
/**
 * project：cutv_ningbo
 * description：
 * create developer： Arvin
 * create time：2016/5/19 10:54.
 * modify developer：  Arvin
 * modify time：2016/5/19 10:54.
 * modify remark：
 *
 * @version 2.0
 */
public class BottomScrollView extends ScrollView{
    private ScrollViewListener scrollViewListener;

    public BottomScrollView(Context context) {
        super(context);
    }

    public BottomScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BottomScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setScrollViewListener(ScrollViewListener scrollViewListener) {
        this.scrollViewListener = scrollViewListener;
    }

    @Override
    protected void onScrollChanged(int x, int y, int oldx, int oldy) {
        super.onScrollChanged(x, y, oldx, oldy);
        if (scrollViewListener != null) {
            scrollViewListener.onScrollChanged(this, x, y, oldx, oldy);
        }
    }

    public int getComputeVerticalScrollRange(){
        return computeVerticalScrollRange();
    }

    public interface ScrollViewListener {
        void onScrollChanged(BottomScrollView scrollView, int x, int y, int oldx, int oldy);
    }

}
