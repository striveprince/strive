package com.app.data.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：17:06
 * modify developer：  admin
 * modify time：17:06
 * modify remark：
 *
 * @version 2.0
 */

public class Util {
    public static boolean isNetworkConnected(Context context) {
        if (context == null) return false;
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        return ni != null && ni.isConnectedOrConnecting();
    }
}
