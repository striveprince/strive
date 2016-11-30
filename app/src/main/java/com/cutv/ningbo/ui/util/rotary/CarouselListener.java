package com.cutv.ningbo.ui.util.rotary;

import android.view.View;

public interface CarouselListener<Type> {
        void nextTurn(Type type, View view);
    }