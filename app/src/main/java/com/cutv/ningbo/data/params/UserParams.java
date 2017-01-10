package com.cutv.ningbo.data.params;

import android.content.Context;

import com.cutv.ningbo.data.save.SharePreferenceUtil;
import com.cutv.ningbo.inject.qualifier.context.AppContext;

import javax.inject.Inject;

/**
 * Created by apple on 17/1/4.
 */

public class UserParams implements DesParams{
    private int tczeidt;
    @Inject
    @AppContext
    Context context;

    public int getTczeidt() {
        return tczeidt;
    }

    public void setTczeidt(int tczeidt) {
        this.tczeidt = tczeidt;
    }

    @Inject
    public UserParams() {
        if (context != null)
            tczeidt = SharePreferenceUtil.getNingInstance(context).getShare().getInt("id", 0);
    }

}
