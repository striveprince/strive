package com.cutv.ningbo;

import android.content.Context;

import com.cutv.ningbo.data.api.UserApi;
import com.cutv.ningbo.data.entity.HomeDataEntity;
import com.cutv.ningbo.data.entity.HomeSlideEntity;
import com.cutv.ningbo.data.http.RestfulTransformer;
import com.cutv.ningbo.inject.qualifier.context.FragmentContext;
import com.cutv.ningbo.ui.base.respond.Respond;
import com.cutv.ningbo.ui.activity.main.fragment.home.HomePageViewModel;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.modules.junit4.PowerMockRunner;

import rx.Observable;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：11:17
 * modify developer：  admin
 * modify time：11:17
 * modify remark：
 *
 * @version 2.0
 */

@RunWith(PowerMockRunner.class)
public class HomePageViewModelUnitTest {
    @Rule RxSchedulersOverrideRule rxSchedulersOverrideRule = new RxSchedulersOverrideRule();
    @Mock UserApi api;
    @Mock @FragmentContext Context context;
    @Mock Respond.TransformRespond<HomeDataEntity,HomeSlideEntity> respond;
//    @Mock Respond.RecyclerRespond respond;

    @Mock HomePageViewModel viewModel;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
//        viewModel = new HomePageViewModel(context,api);
//        viewModel.attachView(respond,null);
    }

    @Test
    public void onRefresh_success() {
//        HomeDataEntity infoEntity = new HomeDataEntity();
//        doReturn(Observable.just(infoEntity)).when(api).getHomePager().compose(new RestfulTransformer<>());
//        viewModel.onRefresh(true);
//        verify(respond,times(1)).transform(dataEntity)
//        verify(respond,times(1)).onRespond(eq(true),eq(true),Matchers.any());
        HomeDataEntity entity = new HomeDataEntity();
        doReturn(Observable.just(entity)).when(api).getHomePager().compose(new RestfulTransformer<>());
    }


}
