package com.read.group.data.portlet;

import android.view.View;

/**
 * Created by apple on 17/1/21.
 */

public interface PagerViewModel<Binding extends View> extends PagerModel<Binding> {
    View.OnClickListener getOnClick();
    String getImageUrl();
}
