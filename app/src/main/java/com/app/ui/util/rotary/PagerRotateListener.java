package com.app.ui.util.rotary;

import android.view.View;

public interface PagerRotateListener<Type> {
        void nextRotate(Type type, View view);
    }