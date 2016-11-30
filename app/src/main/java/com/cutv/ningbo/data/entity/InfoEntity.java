package com.cutv.ningbo.data.entity;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：14:58
 * modify developer：  admin
 * modify time：14:58
 * modify remark：
 *
 * @version 2.0
 */

public class InfoEntity<T> extends BaseEntity{
    private boolean succ;
    private String info;
    private T data;

    public boolean isSucc() {
        return succ;
    }

    public void setSucc(boolean succ) {
        this.succ = succ;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
