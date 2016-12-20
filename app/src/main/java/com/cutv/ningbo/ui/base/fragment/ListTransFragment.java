package com.cutv.ningbo.ui.base.fragment;

import com.cutv.ningbo.data.entity.BaseEntity;
import com.cutv.ningbo.ui.base.adapter.RecyclerWrapper;
import com.cutv.ningbo.ui.base.viewModel.RecyclerBindViewModel;
import com.cutv.ningbo.ui.base.respond.Respond;

import java.util.List;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：9:27
 * modify developer：  admin
 * modify time：9:27
 * modify remark：
 *
 * @version 2.0
 */


public class ListTransFragment
        <Entity extends BaseEntity,
        VM extends RecyclerBindViewModel<List<Entity>,Entity,Adapter>,
        Adapter extends RecyclerWrapper<Entity,?>>
        extends ListFragment<List<Entity>,Entity,VM,Adapter>
    implements Respond.TransformRespond<List<Entity>,Entity>

{
    @Override
    public List<Entity> transform(List<Entity> entities) {
        return entities;
    }

}
