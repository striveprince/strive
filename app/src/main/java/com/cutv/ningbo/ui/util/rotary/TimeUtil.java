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

    private TimeUtil() {handler.postDelayed(runnable,50);}



    //    Iterator<Map.Entry<String, Object>> it = map.entrySet().iterator();
//    while (it.hasNext()) {
//        Map.Entry<String, Object> entry = it.next();
//        String key = entry.getKey();
//        if (entry.getValue() instanceof File[] || entry.getValue() instanceof File) {
//            params.addParameter(key, map.get(key));
//            it.remove();
//        }
//    }
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            synchronized (hashSet){
                Iterator<TimeEntity> it = hashSet.iterator();
                while (it.hasNext()) it.next().getTurn();
            }

//            for (TimeEntity timeEntity : hashSet) timeEntity.getTurn();
//            hashSet.forEach(TimeEntity::getTurn);
            handler.postDelayed(runnable, 1000);
        }
    };

    public synchronized static boolean start(TimeEntity timeEntity) {
        return hashSet.add(timeEntity);
    }

    public synchronized static boolean stop(TimeEntity timeEntity) {
        return hashSet.remove(timeEntity);
    }

    public void destroy() {
        hashSet.clear();
        handler.removeCallbacksAndMessages(null);
    }


}
