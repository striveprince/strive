package com.app.ui.video.control;

import android.view.View;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：13:46
 * modify developer：  admin
 * modify time：13:46
 * modify remark：
 *
 * @version 2.0
 */


public class IjkVideoViewModel{
    private LandscapeVideoModel landscapeVideoModel = new LandscapeVideoModel();
    private PortraitVideoModel portraitVideoModel = new PortraitVideoModel();
    private IOrientation orientation = portraitVideoModel;
    public String text;
    public boolean play;
    public boolean landscape;
    public int show;
    public int progress;
    public int vis;
    public int seekBarVis = View.VISIBLE;
    public int bufferPercentage;
    public int progressMax = 1000;

    public void onPlayClick(View view){

    }

    public void onOrientationClick(View view){
        orientation = orientation == portraitVideoModel?landscapeVideoModel:portraitVideoModel;
        landscape = orientation == landscapeVideoModel;
    }


}
