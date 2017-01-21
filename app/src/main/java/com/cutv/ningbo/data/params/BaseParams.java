package com.cutv.ningbo.data.params;

import android.util.Base64;

/**
 * Created by apple on 17/1/6.
 */

public class BaseParams extends DknbEncryptParams {

    @Override
    public String encrypt(String json) {
        byte[] data = Base64.encode(json.getBytes(), Base64.NO_WRAP);
        return new String(data, Base64.NO_WRAP);
    }
}
