package com.cutv.ningbo.uim.base.layout.rotate;

import android.os.Handler;

import java.util.HashMap;

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
    private static final HashMap<TimeEntity,Boolean> hashMap = new HashMap<>();
    private static TimeUtil util = new TimeUtil();

    public static TimeUtil getInstance() {
        return util;
    }

    private TimeUtil() {
        handler.postDelayed(runnable, 500);
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            for (TimeEntity timeEntity : hashMap.keySet())
                if(hashMap.get(timeEntity))timeEntity.getTurn();
            handler.postDelayed(runnable, 1000);
        }
    };

    public void add(TimeEntity timeEntity) {
        hashMap.put(timeEntity,true);
    }

    public void switching(TimeEntity timeEntity, int state) {
        if (state == 0 ^ hashMap.get(timeEntity)) {
            boolean b = state == 0 ? hashMap.put(timeEntity,true) : hashMap.put(timeEntity,false);
        }
    }

    public void remove(TimeEntity timeEntity){
        hashMap.remove(timeEntity);
    }


    public void destroy() {
        hashMap.clear();
        handler.removeCallbacksAndMessages(null);
    }


}
