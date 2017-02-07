package com.app.ui.base.respond;

import com.app.data.entity.BaseEntity;

import java.util.List;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：17:10
 * modify developer：  admin
 * modify time：17:10
 * modify remark：
 *
 * @version 2.0
 */


public interface Respond {

    interface RecyclerRespond<T> extends Respond {
        void onCompleted(Throwable e, T t,int count);
    }

    interface RadioRespond<T> extends Respond{
        <L extends List<T>>void onCheckedChanged(L collection, int position);
    }

    interface TransformRespond<T, Entity extends BaseEntity>
            extends Respond.RecyclerRespond<T> {
        List<Entity> transform(T t);
    }


}
