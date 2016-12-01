package com.cutv.ningbo.ui.base.respond;

import com.cutv.ningbo.data.entity.BaseEntity;

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


//    interface HttpTouch extends Respond {
//        void onHttpTouch();
//    }
//
//    interface HttpRespond<T> extends Respond ,HttpInitRespond<T>{
//        void onRespond(boolean success, boolean initialLoading, T t);
//    }
//    interface HttpInitRespond<T> extends Respond{
//        void onInitRespond(boolean success, T t);
//    }

    interface RecyclerRespond<T> extends Respond {
        void onCompleted(Throwable e, T t,int count);
    }


    interface TransformRespond<T, Entity extends BaseEntity>
            extends Respond.RecyclerRespond<T> {
        List<Entity> transform(T t);
    }


}
