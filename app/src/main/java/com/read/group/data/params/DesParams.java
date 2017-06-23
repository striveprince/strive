package com.read.group.data.params;

import com.read.group.data.util.des.DES;

/**
 * Created by apple on 17/1/6.
 */

public class DesParams extends DknbEncryptParams {
    @Override
    public String encrypt(String json) {
        DES crypt = new DES(DES.DKDBKEY);
        return crypt.encrypt(json);
    }
}
