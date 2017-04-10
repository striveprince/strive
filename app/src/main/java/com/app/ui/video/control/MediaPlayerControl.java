package com.app.ui.video.control;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：15:44
 * modify developer：  admin
 * modify time：15:44
 * modify remark：
 *
 * @version 2.0
 */


public interface MediaPlayerControl {
    void    start();
    void    pause();
    int     getDuration();
    int     getCurrentPosition();
    void    seekTo(int pos);
    boolean isPlaying();
    int     getBufferPercentage();
    boolean canPause();
    boolean canSeekBackward();
    boolean canSeekForward();

    /**
     * Get the audio session id for the player used by this VideoView. This can be used to
     * apply audio effects to the audio track of a video.
     * @return The audio session, or 0 if there was an error.
     */
    int     getAudioSessionId();
}
