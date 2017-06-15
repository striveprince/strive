package com.cutv.ningbo.test;

import android.view.MotionEvent;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：16:31
 * modify developer：  admin
 * modify time：16:31
 * modify remark：
 *
 * @version 2.0
 */


public class Util {


    //    public static final int ACTION_DOWN             = 0;单点触摸动作
//
//    public static final int ACTION_UP               = 1;单点触摸离开动作
//    public static final int ACTION_MOVE             = 2;触摸点移动动作
//    public static final int ACTION_CANCEL           = 3;触摸动作取消
//    public static final int ACTION_OUTSIDE          = 4;触摸动作超出边界
//    public static final int ACTION_POINTER_DOWN     = 5;多点触摸动作
//    public static final int ACTION_POINTER_UP       = 6;多点离开动作
//            以下是一些非touch事件
//    public static final int ACTION_HOVER_MOVE       = 7;
//    public static final int ACTION_SCROLL           = 8;
//    public static final int ACTION_HOVER_ENTER      = 9;
//    public static final int ACTION_HOVER_EXIT       = 10;
    public static String getAction(MotionEvent event){
        String action = "";
        switch (event.getAction()){
            case 0:action = "ACTION_DOWN";break;
            case 1:action = "ACTION_UP";break;
            case 2:action = "ACTION_MOVE";break;
            case 3:action = "ACTION_CANCEL";break;
            case 4:action = "ACTION_OUTSIDE";break;
            case 5:action = "ACTION_POINTER_DOWN";break;
            case 6:action = "ACTION_POINTER_UP";break;
            case 7:action = "ACTION_HOVER_MOVE";break;
            case 8:action = "ACTION_SCROLL";break;
            case 9:action = "ACTION_HOVER_ENTER";break;
            case 10:action = "ACTION_HOVER_EXIT";break;
            default:break;
        }
        return action;
    }
}
