package com.cutv.ningbo.ui.activity.live.record;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.cutv.ningbo.data.api.LiveApi;
import com.cutv.ningbo.databinding.ActivityLiveRecordBinding;
import com.cutv.ningbo.inject.qualifier.context.ActivityContext;
import com.cutv.ningbo.ui.base.respond.Respond;
import com.cutv.ningbo.ui.base.viewModel.BaseViewModel;


import javax.inject.Inject;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：9:56
 * modify developer：  admin
 * modify time：9:56
 * modify remark：
 *
 * @version 2.0
 */


public class RecordViewModel extends BaseViewModel<Respond> {
    private String url;

    @Inject
    LiveApi api;

    @Inject
    RecordViewModel(@ActivityContext Context context) {
        super(context);
    }

//    @Inject
//    DirectPlayer directPlayer;


    public void setBinding(ActivityLiveRecordBinding binding) {
//        directPlayer.setBinding(binding);
        initDirectLive("");
    }

    @Override
    public void attachView(Respond respond, Bundle savedInstanceState) {
        super.attachView(respond, savedInstanceState);
    }

    /**
     * @param liveUrl 直播流地址
     */
    private void initDirectLive(String liveUrl) {
//        directPlayer.initDirect().setExtraData();

    }

    private boolean ismChecked;

    /**
     * @param view live over
     */
    public void onStartClick(View view) {
        Toast.makeText(getContext(), "onEndClick", Toast.LENGTH_LONG).show();
        initDirectLive("");
//        directPlayer.onStartClick(view);

//            httpNoTransform(api.start(), entity -> {
//                if (entity.isSucc()) {
//                    Toast.makeText(getContext(), "111", Toast.LENGTH_LONG).show();
//                    directPlayer.onStartOrEndClick(view);
//                }
//            });

    }

    public void onEndClick(View view) {

//        directPlayer.onEndClick(view);
//        httpNoTransform(api.start(), entity -> {
//            if (entity.isSucc()) {
//                Toast.makeText(getContext(), "111", Toast.LENGTH_LONG).show();
//                directPlayer.onEndClick(view);
//            }
//        });

    }

//    public void onCameraClick(View view) {
//        directPlayer.onCameraClick(view);
//    }
//
//    public void onBeautyClick(View view) {
//        directPlayer.onBeautyClick(view);
//    }
//
//    public void onVocieClick(View view) {
//        directPlayer.onVocieClick(view);
//    }

//    @BindingAdapter("camerCallback")
//    public void setCameraSurfaceCallback(SurfaceView view,SurfaceHolder.Callback callback){
//        view.getHolder().addCallback(callback);
//    }
//
//    public SurfaceHolder.Callback getCameraSurfaceCallback() {
//        return cameraSurfaceCallback;
//    }
//
//    private final SurfaceHolder.Callback cameraSurfaceCallback = new SurfaceHolder.Callback() {
//        @Override
//        public void surfaceCreated(SurfaceHolder holder) {
//            holder.setKeepScreenOn(true);
////            mPreviewSurface = holder.getSurface();
////            startPreview(holder);
//        }
//
//        @Override
//        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
////            mMediaRecorder.setPreviewSize(width, height);
////            mPreviewWidth = width;
////            mPreviewHeight = height;
//        }
//
//        @Override
//        public void surfaceDestroyed(SurfaceHolder holder) {
////            mPreviewSurface = null;
////            mMediaRecorder.stopRecord();
////            mMediaRecorder.reset();
//        }
//    };

}
