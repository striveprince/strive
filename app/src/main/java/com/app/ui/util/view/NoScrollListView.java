package com.app.ui.util.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * project：cutv_ningbo
 * description：
 * create developer： Arvin
 * create time：2015/12/8 9:59.
 * modify developer：  Arvin
 * modify time：2015/12/8 09:59.
 * modify remark：
 * @version 2.0
 */
public class NoScrollListView extends ListView {
    public NoScrollListView(Context context) {
        super(context);
    }

    public NoScrollListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NoScrollListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 设置不滚动
     */
    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);

    }
}
