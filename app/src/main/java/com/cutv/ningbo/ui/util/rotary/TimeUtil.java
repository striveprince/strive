package com.cutv.ningbo.ui.util.rotary;

import android.os.Handler;

import java.util.HashSet;

import timber.log.Timber;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：11:04
 * modify developer：  admin
 * modify time：11:04
 * modify remark：
 *
 * @version 2.0
 */


public class TimeUtil {
    private static Handler handler = new Handler();
    public static HashSet<TimeEntity> hashSet = new HashSet<>();

    private static TimeUtil util = new TimeUtil();

    public static TimeUtil getInstance() {
        return util;
    }

    private TimeUtil() {handler.postDelayed(runnable,50);}

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            for (TimeEntity timeEntity : hashSet) timeEntity.getTurn();
//            hashSet.forEach(TimeEntity::getTurn);
            handler.postDelayed(runnable, 1000);
        }
    };

    public void start(TimeEntity timeEntity) {
        hashSet.add(timeEntity);
    }

    public boolean stop(TimeEntity timeEntity) {
        return hashSet.remove(timeEntity);
    }

    public void destroy() {
        hashSet.clear();
        handler.removeCallbacksAndMessages(null);
    }


}
