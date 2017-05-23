package com.cutv.ningbo.uim.base.cycle;

import android.databinding.ViewDataBinding;

import com.cutv.ningbo.uim.base.model.inter.Http;

import rx.Observable;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：10:33
 * modify developer：  admin?
 * modify time：10:33
 * modify remark：
 *
 * @version 2.0
 */


public interface CycleHttpContainer<R,Binding extends ViewDataBinding,T>  extends CycleContainer<Binding,T>,Http<R> {
}
