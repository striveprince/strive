package com.read.group.ui.home;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.read.group.base.Router;
import com.read.group.base.aop.Permission;
import com.read.group.base.cycle.DataBindingActivity;
import com.read.group.databinding.ActivityHomeBinding;

/**
 * Created by apple on 2017/6/23.
 */
@Route(path = Router.home)
public class HomeActivity extends DataBindingActivity<HomeModel,ActivityHomeBinding>{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Permission(Manifest.permission.CAMERA)
    public void onButtonClick(View view){

    }
}

