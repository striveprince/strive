package com.app.ui.base.viewModel;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.app.data.entity.BaseEntity;
import com.app.ui.base.respond.Respond;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：13:59
 * modify developer：  admin
 * modify time：13:59
 * modify remark：
 *
 * @version 2.0
 */


public abstract class HolderViewModel<Entity extends BaseEntity>{

    private Entity entity;

    public HolderViewModel(Entity entity) {
        this.entity = entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    public Entity getEntity() {
        return entity;
    }
}
