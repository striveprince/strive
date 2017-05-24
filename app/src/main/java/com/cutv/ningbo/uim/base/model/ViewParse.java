package com.cutv.ningbo.uim.base.model;

import com.cutv.ningbo.uim.base.BaseUtil;
import com.cutv.ningbo.uim.base.annotation.ModelView;
import com.cutv.ningbo.uim.base.model.inter.Parse;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：8:58
 * modify developer：  admin
 * modify time：8:58
 * modify remark：
 *
 * @version 2.0
 */


public class ViewParse implements Parse{
    private transient ModelView modelView;

    @Override
    public ModelView getModelView() {
        if (modelView == null) {
            modelView = BaseUtil.findModelView(getClass());
            if (modelView == null)
                throw new RuntimeException("should to add @ModelView to the class:" + getClass());
        }
        return modelView;
    }
}
