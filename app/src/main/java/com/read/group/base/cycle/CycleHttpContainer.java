package com.read.group.base.cycle;

import android.databinding.ViewDataBinding;

import com.read.group.base.model.inter.Http;

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
