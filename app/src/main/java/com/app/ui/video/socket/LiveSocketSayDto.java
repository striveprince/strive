package com.app.ui.video.socket;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：13:33
 * modify developer：  admin
 * modify time：13:33
 * modify remark：
 *
 * @version 2.0
 */


public class LiveSocketSayDto implements WebSocketDto {
    /**
     * type : say
     * cid : 123
     * nickname : 123
     * user_level : 12
     * content : hello world
     * color : FFFFFF
     * is_host : true
     */
    private int user_id;
    private String type;
    private int cid;
    private String nickname;
    private int user_level;
    private String content;
    private String color;
    private String is_host;
    private int source;

    public int getSource() {
        return source;
    }

    public void setSource(int source) {
        this.source = source;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getUser_level() {
        return user_level;
    }

    public void setUser_level(int user_level) {
        this.user_level = user_level;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getIs_host() {
        return is_host;
    }

    public void setIs_host(String is_host) {
        this.is_host = is_host;
    }
}
