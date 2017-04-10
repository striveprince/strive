/*
 * Copyright (C) 2006 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.app.ui.video;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.InverseBindingMethods;
import android.graphics.PixelFormat;
import android.media.AudioManager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityManager;
import android.widget.FrameLayout;
import android.widget.MediaController;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import com.app.R;
import com.app.databinding.IjkmediaControllerBinding;
import com.app.ui.video.control.IjkVideoViewModel;
import com.app.ui.video.control.MediaPlayerControl;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Formatter;
import java.util.Locale;

public class IjkMediaController extends FrameLayout {
    private MediaPlayerControl mPlayer;
    private final Context mContext;
    private View mAnchor;
    private View mDecor;
    private WindowManager mWindowManager;
    private WindowManager.LayoutParams mDecorLayoutParams;
    private static final int sDefaultTimeout = 3000;
    private StringBuilder mFormatBuilder = new StringBuilder();
    private Formatter mFormatter = new Formatter(mFormatBuilder, Locale.getDefault());

    private IjkVideoViewModel model = new IjkVideoViewModel();

    public IjkMediaController(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initFloatingWindowLayout();
        initFloatingWindow();
    }

    public IjkMediaController(Context context) {
        this(context, null);
    }

    private Window getWindow() {
        Window mWindow;
        try {
            Class clazz = Class.forName("com.android.internal.policy.impl.PhoneWindow");
            Constructor constructor = clazz.getDeclaredConstructor(Context.class);
            mWindow = (Window) constructor.newInstance(mContext);
//            mWindow = PolicyManager.makeNewWidow(mContext);//反射调用
//            Class clazz = Class.forName("com.android.internal.policy.PolicyManager");
//            Method method = clazz.getDeclaredMethod("makeNewWidow");//静态方法
//           mWindow = method.invoke(null,mContext);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return mWindow;
    }

    private void initFloatingWindow() {
        mWindowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        Window mWindow = getWindow();
        mWindow.setWindowManager(mWindowManager, null, null);
        mWindow.requestFeature(Window.FEATURE_NO_TITLE);
        mDecor = mWindow.getDecorView();
        mDecor.setOnTouchListener(mTouchListener);
        mWindow.setContentView(this);
        mWindow.setBackgroundDrawableResource(android.R.color.transparent);
        mWindow.setVolumeControlStream(AudioManager.STREAM_MUSIC);
        setFocusable(true);
        setFocusableInTouchMode(true);
        setDescendantFocusability(ViewGroup.FOCUS_AFTER_DESCENDANTS);
        requestFocus();
    }

    private void initFloatingWindowLayout() {
        mDecorLayoutParams = new WindowManager.LayoutParams();
        WindowManager.LayoutParams p = mDecorLayoutParams;
        p.gravity = Gravity.TOP | Gravity.START;
        p.height = LayoutParams.WRAP_CONTENT;
        p.x = 0;
        p.format = PixelFormat.TRANSLUCENT;
        p.type = WindowManager.LayoutParams.TYPE_APPLICATION_PANEL;
        p.flags |= WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM
                | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                | WindowManager.LayoutParams.FLAG_SPLIT_TOUCH;
        p.token = null;
        p.windowAnimations = 0; // android.R.style.DropDownAnimationDown;
    }

    private void updateFloatingWindowLayout() {
        int[] anchorPos = new int[2];
        mAnchor.getLocationOnScreen(anchorPos);
        mDecor.measure(MeasureSpec.makeMeasureSpec(mAnchor.getWidth(), MeasureSpec.AT_MOST),
                MeasureSpec.makeMeasureSpec(mAnchor.getHeight(), MeasureSpec.AT_MOST));
        WindowManager.LayoutParams p = mDecorLayoutParams;
        p.width = mAnchor.getWidth();
        p.x = anchorPos[0] + (mAnchor.getWidth() - p.width) / 2;
        p.y = anchorPos[1] + mAnchor.getHeight() - mDecor.getMeasuredHeight();
    }

    private final OnLayoutChangeListener mLayoutChangeListener =
            new OnLayoutChangeListener() {
                @Override
                public void onLayoutChange(View v, int left, int top, int right,
                                           int bottom, int oldLeft, int oldTop, int oldRight,
                                           int oldBottom) {
                    updateFloatingWindowLayout();
                    if (model.play) {
                        mWindowManager.updateViewLayout(mDecor, mDecorLayoutParams);
                    }
                }
            };

    private final OnTouchListener mTouchListener = new OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                if (model.play) {
                    hide();
                }
            }
            return false;
        }
    };

    public void setMediaPlayer(MediaPlayerControl player) {
        mPlayer = player;
        model.play = player.isPlaying();
    }

    /**
     * Set the view that acts as the anchor for the control view.
     * This can for example be a VideoView, or your Activity's main view.
     * When VideoView calls this method, it will use the VideoView's parent
     * as the anchor.
     *
     * @param view The view to which to anchor the controller when it is visible.
     */
    public void setAnchorView(View view) {
        if (mAnchor != null) {
            mAnchor.removeOnLayoutChangeListener(mLayoutChangeListener);
        }
        mAnchor = view;
        if (mAnchor != null) {
            mAnchor.addOnLayoutChangeListener(mLayoutChangeListener);
        }

        FrameLayout.LayoutParams frameParams = new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        );

        removeAllViews();
        View v = makeControllerView();
        addView(v, frameParams);
    }

    /**
     * Create the view that holds the widgets that control playback.
     * Derived classes can override this to create their own.
     *
     * @return The controller view.
     * @hide This doesn't work as advertised
     */
    protected View makeControllerView() {
        LayoutInflater inflate = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        IjkmediaControllerBinding binding = DataBindingUtil.inflate(inflate, R.layout.ijkmedia_controller, this, true);
        binding.setVm(model);
        return binding.getRoot();
    }


    /**
     * Show the controller on screen. It will go away
     * automatically after 3 seconds of inactivity.
     */
    public void show() {
        show(sDefaultTimeout);
    }

    /**
     * Show the controller on screen. It will go away
     * automatically after 'timeout' milliseconds of inactivity.
     *
     * @param timeout The timeout in milliseconds. Use 0 to show
     *                the controller until hide() is called.
     */
    public void show(int timeout) {
        if (!model.play && mAnchor != null) {
            setProgress();
            updateFloatingWindowLayout();
            mWindowManager.addView(mDecor, mDecorLayoutParams);
        }
        model.play = mPlayer.isPlaying();
        post(mShowProgress);
        if (timeout != 0) {
            removeCallbacks(mFadeOut);
            postDelayed(mFadeOut, timeout);
        }
    }


    public boolean isShowing() {
        return model.play;
    }

    /**
     * Remove the controller from the screen.
     */
    public void hide() {
        if (mAnchor == null)
            return;

        if (model.play) {
            try {
                removeCallbacks(mShowProgress);
                mWindowManager.removeView(mDecor);
            } catch (IllegalArgumentException ex) {
                Log.w("MediaController", "already removed");
            }
            model.play = false;
        }
    }

    private final Runnable mFadeOut = this::hide;

    private final Runnable mShowProgress = new Runnable() {
        @Override
        public void run() {
            int pos = setProgress();
            if (model.play && mPlayer.isPlaying()) {
                postDelayed(mShowProgress, 500 - (pos % 500));
            }
        }
    };

    private String stringForTime(long timeMs) {
        long totalSeconds = timeMs / 1000;
        long seconds = totalSeconds % 60;
        long minutes = (totalSeconds / 60) % 60;
        long hours = totalSeconds / 3600;
        mFormatBuilder.setLength(0);
        if (hours > 0) {
            return mFormatter.format("%d:%02d:%02d", hours, minutes, seconds).toString();
        } else {
            return mFormatter.format("%02d:%02d", minutes, seconds).toString();
        }
    }

    private String stringForTime(long position, long totalSeconds) {
        return stringForTime(position) + "/" + stringForTime(totalSeconds);
    }

    private int setProgress() {
        if (mPlayer == null) {
            return 0;
        }
        int position = mPlayer.getCurrentPosition();
        int duration = mPlayer.getDuration();
        long pos = model.progressMax * position / duration;
        int progressPosition = model.progress * duration / model.progressMax;
        if (position - progressPosition < 500) {
            model.progress = (int) pos;
        } else {
            mPlayer.seekTo(progressPosition);
        }
        model.bufferPercentage = mPlayer.getBufferPercentage() * 10;
        model.text = stringForTime(position, duration);
        return position;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                show(0); // show until hide is called
                break;
            case MotionEvent.ACTION_UP:
                show(sDefaultTimeout); // start timeout
                break;
            case MotionEvent.ACTION_CANCEL:
                hide();
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public boolean onTrackballEvent(MotionEvent ev) {
        show(sDefaultTimeout);
        return false;
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        int keyCode = event.getKeyCode();
        final boolean uniqueDown = event.getRepeatCount() == 0
                && event.getAction() == KeyEvent.ACTION_DOWN;
        if (keyCode == KeyEvent.KEYCODE_HEADSETHOOK
                || keyCode == KeyEvent.KEYCODE_MEDIA_PLAY_PAUSE
                || keyCode == KeyEvent.KEYCODE_SPACE) {
            if (uniqueDown) {
                doPauseResume();
                show(sDefaultTimeout);
            }
            return true;
        } else if (keyCode == KeyEvent.KEYCODE_MEDIA_PLAY) {
            if (uniqueDown && !mPlayer.isPlaying()) {
                mPlayer.start();

                show(sDefaultTimeout);
            }
            return true;
        } else if (keyCode == KeyEvent.KEYCODE_MEDIA_STOP
                || keyCode == KeyEvent.KEYCODE_MEDIA_PAUSE) {
            if (uniqueDown && mPlayer.isPlaying()) {
                mPlayer.pause();
                show(sDefaultTimeout);
            }
            return true;
        } else if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN
                || keyCode == KeyEvent.KEYCODE_VOLUME_UP
                || keyCode == KeyEvent.KEYCODE_VOLUME_MUTE
                || keyCode == KeyEvent.KEYCODE_CAMERA) {
            return super.dispatchKeyEvent(event);
        } else if (keyCode == KeyEvent.KEYCODE_BACK || keyCode == KeyEvent.KEYCODE_MENU) {
            if (uniqueDown) {
                hide();
            }
            return true;
        }
        show(sDefaultTimeout);
        model.play = mPlayer.isPlaying();
        return super.dispatchKeyEvent(event);
    }

    private final View.OnClickListener mPauseListener = v -> {
        doPauseResume();
        show(sDefaultTimeout);
    };


    private void doPauseResume() {
        if (mPlayer.isPlaying()) {
            mPlayer.pause();
        } else {
            mPlayer.start();
        }
        model.play = mPlayer.isPlaying();
    }

//    private final OnSeekBarChangeListener mSeekListener = new OnSeekBarChangeListener() {
//        @Override
//        public void onStartTrackingTouch(SeekBar bar) {
//            show(3600000);
////            mDragging = true;
//            removeCallbacks(mShowProgress);
//        }
//
//        @Override
//        public void onProgressChanged(SeekBar bar, int progress, boolean fromuser) {
//            if (!fromuser) {
//                return;
//            }
//            long duration = mPlayer.getDuration();
//            long newPosition = (duration * progress) / model.progressMax;
//            mPlayer.seekTo((int) newPosition);
//            model.text = stringForTime(newPosition, duration);
//        }
//
//        @Override
//        public void onStopTrackingTouch(SeekBar bar) {
////            mDragging = false;
//            setProgress();
//            model.play = mPlayer.isPlaying();
//            show(sDefaultTimeout);
//            post(mShowProgress);
//        }
//    };

    @Override
    public CharSequence getAccessibilityClassName() {
        return IjkMediaController.class.getName();
    }

    private final View.OnClickListener mRewListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int pos = mPlayer.getCurrentPosition();
            pos -= 5000; // milliseconds
            mPlayer.seekTo(pos);
            setProgress();

            show(sDefaultTimeout);
        }
    };

    private final View.OnClickListener mFfwdListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int pos = mPlayer.getCurrentPosition();
            pos += 15000; // milliseconds
            mPlayer.seekTo(pos);
            setProgress();

            show(sDefaultTimeout);
        }
    };
}
