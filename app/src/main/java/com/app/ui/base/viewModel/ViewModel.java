package com.app.ui.base.viewModel;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.app.ui.base.respond.Respond;

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

public interface ViewModel<RD extends Respond> extends Model {
    void attachView(RD respond, Bundle savedInstanceState);

    void saveInstanceState(Bundle outState);

    void detachView();

    void restoreInstanceState(@NonNull Bundle savedInstanceState);

}


