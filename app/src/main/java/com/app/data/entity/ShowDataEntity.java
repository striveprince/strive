package com.app.data.entity;

import java.lang.reflect.Field;

/**
 * project：cutv_ningbo
 * description：
 * create developer： Arvin
 * create time：2016/2/20 15:52.
 * modify developer：  Arvin
 * modify time：2016/2/20 15:52.
 * modify remark：
 *
 * @version 2.0
 */
public class ShowDataEntity extends BaseEntity {
    //  private ShowDataEntity dataDto = new ShowDataEntity();
    private boolean videoShow;
    private int id, category_id, status, uid, group_id, view_count, praise,
            order_num, forward_count, comment_count;
    private String title, index_img, content;
//    private ShowTagsDto[] tags;
    private long create_time;
//    private ShowVideoDto video;
//    private ShowUserDto user;

    public ShowDataEntity newInstance() {
        ShowDataEntity dto = new ShowDataEntity();
        for (Field f : dto.getClass().getDeclaredFields()) {
            f.setAccessible(true);
            char[] cs = f.getName().toCharArray();
            if (cs[0] >= 97 && cs[0] <= 122) cs[0] -= 32;//匹配ascii码表，发现是小写字母的话，-32变为小写
            String keyU = String.valueOf(cs);
            try {
                dto.getClass().getDeclaredMethod("set" + keyU, f.getType()).invoke(dto, f.get(this));
            } catch (Exception e) {
//                Logger.w(Logger.CLASS, "class:" + f.getType() + " name:" + f.getName());
            }
        }
//        Logger.i(Logger.CLASS,"ShowDataEntity:"+dto.toString());
        return dto;
    }



    public boolean isVideoShow() {
        return videoShow;
    }

    public void setVideoShow(boolean isVideoShow) {
        this.videoShow = isVideoShow;
    }

    public int getForward_count() {
        return forward_count;
    }

    public void setForward_count(int forward_count) {
        this.forward_count = forward_count;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getGroup_id() {
        return group_id;
    }

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
    }

    public int getView_count() {
        return view_count;
    }

    public void setView_count(int view_count) {
        this.view_count = view_count;
    }

    public int getPraise() {
        return praise;
    }

    public void setPraise(int praise) {
        this.praise = praise;
    }

    public int getOrder_num() {
        return order_num;
    }

    public void setOrder_num(int order_num) {
        this.order_num = order_num;
    }

    public int getComment_count() {
        return comment_count;
    }

    public void setComment_count(int comment_count) {
        this.comment_count = comment_count;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIndex_img() {
        return index_img;
    }

    public void setIndex_img(String index_img) {
        this.index_img = index_img;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getCreate_time() {
        return create_time;
    }
/*
    public String getTime() {
        Date d = new Date(create_time * 1000);
        SimpleDateFormat sf = new SimpleDateFormat(SetInfo.FORMAT_DATE_SECOND, Locale.CHINA);
        return sf.format(d);

    }*/

    public void setCreate_time(long create_time) {
        this.create_time = create_time;
    }


}
