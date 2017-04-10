package com.app.ui.video.socket;

/**
 * Created by maomao on 2017/3/9.
 */

public class LiveSocketVoteDto implements WebSocketDto {


    /**
     * id : 26
     * inid : 41
     * room_id : 117
     * option_title : 哈哈哈哈
     * option_image : http://cdn.dknb.nbtv.cn/attachment/app-image/1703/09103546081f89d2943cdfd905a4bed0a3942390.jpg
     * option_count : 0
     * order_num : 0
     * update_time : 0
     */

    private int id;
    private int inid;
    private int room_id;
    private String option_title;
    private String option_image;
    private int option_count;
    private int order_num;
    private long update_time;
    private int sum;

    private boolean isCheck;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getInid() {
        return inid;
    }

    public void setInid(int inid) {
        this.inid = inid;
    }

    public int getRoom_id() {
        return room_id;
    }

    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }

    public String getOption_title() {
        return option_title;
    }

    public void setOption_title(String option_title) {
        this.option_title = option_title;
    }

    public String getOption_image() {
        return option_image;
    }

    public void setOption_image(String option_image) {
        this.option_image = option_image;
    }

    public int getOption_count() {
        return option_count;
    }

    public void setOption_count(int option_count) {
        this.option_count = option_count;
    }

    public int getOrder_num() {
        return order_num;
    }

    public void setOrder_num(int order_num) {
        this.order_num = order_num;
    }

    public long getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(long update_time) {
        this.update_time = update_time;
    }


    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
}

    public void setCheck(int id) {
        if (this.id == id)
            isCheck = true;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }
}
