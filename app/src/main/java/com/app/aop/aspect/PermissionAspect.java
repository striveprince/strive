package com.app.aop.aspect;

import android.support.v7.app.AppCompatActivity;

import com.app.App;
import com.app.aop.annotation.RunTimePermission;
import com.app.aop.permission.MPermissionUtils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：10:27
 * modify developer：  admin
 * modify time：10:27
 * modify remark：
 *
 * @version 2.0
 */


public class PermissionAspect {

    @Around("execution(@com.app.aop.annotation.RunTimePermission * *(..)) && @annotation(permission)")
    public void aroundJoinPoint(ProceedingJoinPoint joinPoint, RunTimePermission permission) throws Throwable {
        AppCompatActivity ac = (AppCompatActivity) App.getCurrentActivity();
//        new AlertDialog.Builder(ac)
//                .setTitle("提示")
//                .setMessage("为了应用可以正常使用，请您点击确认申请权限。")
//                .setNegativeButton("取消", null)
//                .setPositiveButton("允许", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//                    }
//                })
//                .create().show();
        MPermissionUtils.requestPermissionsResult(ac, 1, permission.value()
                , new MPermissionUtils.OnPermissionListener() {
                    @Override
                    public void onPermissionGranted() {
                        try {
                            joinPoint.proceed();//获得权限，执行原方法
                        } catch (Throwable e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onPermissionDenied() {
                        MPermissionUtils.showTipsDialog(ac);
                    }
                });
    }
}
