package com.cutv.ningbo.data.entity;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

/**
 * project：cutv_ningbo
 * description：
 * create developer： Arvin
 * create time：2016/1/19 16:38.
 * modify developer：  Arvin
 * modify time：2016/1/19 16:38.
 * modify remark：
 *
 * @version 2.0
 */
public class HomeSlideEntity extends BaseEntity implements Parcelable {
    private String title, images, out_url, description, index_img, tags2, image, share_url, maid;
    private int item_id, media_type, id, type;

    public HomeSlideEntity() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getOut_url() {
        return out_url;
    }

    public void setOut_url(String out_url) {
        this.out_url = out_url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getIndex_img() {
        return index_img;
    }

    public void setIndex_img(String index_img) {
        this.index_img = index_img;
    }

    public String getTags2() {
        return tags2;
    }

    public void setTags2(String tags2) {
        this.tags2 = tags2;
    }

    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    public int getMedia_type() {
        return media_type;
    }

    public void setMedia_type(int media_type) {
        this.media_type = media_type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getShare_url() {
        return share_url;
    }

    public void setShare_url(String share_url) {
        this.share_url = share_url;
    }

    public String getMaid() {
        return maid;
    }

    public void setMaid(String maid) {
        this.maid = maid;
    }

    public View.OnClickListener getOnClick(){
        return this::onClick;
    }

    public void onClick(View view){
        Toast.makeText(view.getContext(), toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.images);
        dest.writeString(this.out_url);
        dest.writeString(this.description);
        dest.writeString(this.index_img);
        dest.writeString(this.tags2);
        dest.writeString(this.image);
        dest.writeString(this.share_url);
        dest.writeString(this.maid);
        dest.writeInt(this.item_id);
        dest.writeInt(this.media_type);
        dest.writeInt(this.id);
        dest.writeInt(this.type);
    }

    private HomeSlideEntity(Parcel in) {
        this.title = in.readString();
        this.images = in.readString();
        this.out_url = in.readString();
        this.description = in.readString();
        this.index_img = in.readString();
        this.tags2 = in.readString();
        this.image = in.readString();
        this.share_url = in.readString();
        this.maid = in.readString();
        this.item_id = in.readInt();
        this.media_type = in.readInt();
        this.id = in.readInt();
        this.type = in.readInt();
    }

    public static final Creator<HomeSlideEntity> CREATOR = new Creator<HomeSlideEntity>() {
        @Override
        public HomeSlideEntity createFromParcel(Parcel source) {
            return new HomeSlideEntity(source);
        }

        @Override
        public HomeSlideEntity[] newArray(int size) {
            return new HomeSlideEntity[size];
        }
    };
}
