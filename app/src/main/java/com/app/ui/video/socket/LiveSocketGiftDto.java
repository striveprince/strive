package com.app.ui.video.socket;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：13:39
 * modify developer：  admin
 * modify time：13:39
 * modify remark：
 *
 * @version 2.0
 */


public class LiveSocketGiftDto implements WebSocketDto{
    /**
     * type : gift
     * gift_id : 1
     * nickname : 123
     * user_id : 1
     * host_name : hi
     */

    private String type;
    private int gift_id;
    private String nickname;
    private int user_id;
    private String host_name;
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getGift_id() {
        return gift_id;
    }

    public void setGift_id(int gift_id) {
        this.gift_id = gift_id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getHost_name() {
        return host_name;
    }

    public void setHost_name(String host_name) {
        this.host_name = host_name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LiveSocketGiftDto dto = (LiveSocketGiftDto) o;

        if (gift_id != dto.gift_id) return false;
        if (user_id != dto.user_id) return false;
        if (!nickname.equals(dto.nickname)) return false;
        return host_name.equals(dto.host_name);

    }

    @Override
    public int hashCode() {
        int result = gift_id;
        result = 31 * result + nickname.hashCode();
        result = 31 * result + user_id;
        result = 31 * result + host_name.hashCode();
        return result;
    }
}
