package com.cutv.ningbo.data.entity;



/**
 * project：cutv_ningbo
 * description：
 * create developer： Arvin
 * create time：2015/12/17 13:34.
 * modify developer：  Arvin
 * modify time：2015/12/17 13:34.
 * modify remark：
 * @version 2.0
 */
public class NewsDataEntity extends BaseEntity {
    private int id;
    private String name,index_img,description,source;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIndex_img() {
        return index_img;
    }

    public void setIndex_img(String index_img) {
        this.index_img = index_img;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @Override
    public String toString() {
        return name;
    }
}
