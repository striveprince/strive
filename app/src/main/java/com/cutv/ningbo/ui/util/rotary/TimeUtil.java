package com.cutv.ningbo.ui.util.rotary;

import android.os.Handler;

import java.io.File;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

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
    public static final HashSet<TimeEntity> hashSet = new HashSet<>();

    private static TimeUtil util = new TimeUtil();

    public static TimeUtil getInstance() {
        return util;
    }

    private TimeUtil() {
        handler.postDelayed(runnable, 50);
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            for (TimeEntity timeEntity : hashSet) timeEntity.getTurn();
            handler.postDelayed(runnable, 1000);
        }
    };

    public void add(TimeEntity timeEntity) {
        hashSet.add(timeEntity);
    }

    public void switching(TimeEntity timeEntity, int state) {
        if (state == 0 ^ hashSet.contains(timeEntity)) {
            handler.removeCallbacks(runnable);
            boolean b = state == 0 ? hashSet.add(timeEntity) : hashSet.remove(timeEntity);
            handler.postDelayed(runnable, 500);
        }
    }


    public void destroy() {
        hashSet.clear();
        handler.removeCallbacksAndMessages(null);
    }


}
