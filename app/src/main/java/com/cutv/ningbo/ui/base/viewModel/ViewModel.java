package com.cutv.ningbo.ui.base.viewModel;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.cutv.ningbo.ui.base.respond.Respond;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：16:14
 * modify developer：  admin
 * modify time：16:14
 * modify remark：
 *
 * @version 2.0
 */

public interface ViewModel<RD extends Respond> {
    void attachView(RD respond, Bundle savedInstanceState);

    void saveInstanceState(Bundle outState);

    void detachView();

    void restoreInstanceState(@NonNull Bundle savedInstanceState);

}


