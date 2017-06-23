package com.read.group.base.annotation;

import com.read.group.base.adapter.IModelAdapter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：10:56
 * modify developer：  admin
 * modify time：10:56
 * modify remark：
 *
 * @version 2.0
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface AdapterEntity {
//    Class<?> entity()  default AdapterEntity.class;
    Class<? extends IModelAdapter> adapter() default IModelAdapter.class;
}

