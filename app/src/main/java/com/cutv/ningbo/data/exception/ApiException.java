package com.cutv.ningbo.data.exception;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：14:10
 * modify developer：  admin
 * modify time：14:10
 * modify remark：
 *
 * @version 2.0
 */


public class ApiException extends Exception {
    public ApiException() {
    }

    public ApiException(String message) {
        super(message);
    }

    public ApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApiException(Throwable cause) {
        super(cause);
    }

}
