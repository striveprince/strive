package com.app.ui.video.control;

import com.app.ui.video.socket.LiveSocketGiftDto;
import com.app.ui.video.socket.LiveSocketInteractDto;
import com.app.ui.video.socket.LiveSocketSayDto;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：10:55
 * modify developer：  admin
 * modify time：10:55
 * modify remark：
 *
 * @version 2.0
 */


public interface IOrientation {

    void showCount(int count);

    void showBarrage(LiveSocketSayDto dto);

    void switchView();

    void showBar();

    void showGift(LiveSocketGiftDto dto);

    void showInteract(LiveSocketInteractDto dto);

    void share();
}
