package com.app.ui.util.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

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
public class AlwaysMarqueeTextView extends TextView {
    public AlwaysMarqueeTextView(Context context) {
        super(context);
    }

    public AlwaysMarqueeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AlwaysMarqueeTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean isFocused() {
        return true;
    }
}
