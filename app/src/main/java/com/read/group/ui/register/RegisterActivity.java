package com.read.group.ui.register;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.read.group.base.Router;
import com.read.group.base.cycle.DataBindingActivity;
import com.read.group.databinding.ActivityRegisterBinding;

/**
 * Created by apple on 2017/7/5.
 */
//@Route(path = Router.register)
public class RegisterActivity extends DataBindingActivity<RegisterModel,ActivityRegisterBinding> {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
