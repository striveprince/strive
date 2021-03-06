package com.cutv.ningbo.data.params;

import com.cutv.ningbo.data.save.SharePreferenceUtil;
import com.cutv.ningbo.inject.qualifier.preference.NingSharePreference;

import javax.inject.Inject;

/**
 * Created by apple on 17/1/4.
 */

public class UserParams extends DesParams{
    private int tczeidt;
    private int room_id;
    private int room;
    private String content;
    private int to_uid;

    private SharePreferenceUtil util;
    @Inject
    public UserParams(@NingSharePreference SharePreferenceUtil util) {
        this.util = util;
    }

    public int getTczeidt() {
        return util.getShare().getInt("id", 0);
    }

    public void setTczeidt(int tczeidt) {
        this.tczeidt = tczeidt;
    }


    public int getRoom_id() {
        return room_id;
    }

    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }

    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getTo_uid() {
        return to_uid;
    }

    public void setTo_uid(int to_uid) {
        this.to_uid = to_uid;
    }

}
