package com.cutv.ningbo.data.entity;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：14:33
 * modify developer：  admin
 * modify time：14:33
 * modify remark：
 *
 * @version 2.0
 */

public class SystemEntity{
//    private String device;
    private String ststem_id;
    private String system_name;//手机系统
    private String system_edition;//系统版本号
    private String cellphone_model;//手机型号
    private String app_edition;//app版本号
    private String sdk_edition;//sdk版本

    public String getStstem_id() {
        return ststem_id;
    }

    public void setStstem_id(String ststem_id) {
        this.ststem_id = ststem_id;
    }

    public String getSystem_name() {
        return system_name;
    }

    public void setSystem_name(String system_name) {
        this.system_name = system_name;
    }

    public String getSystem_edition() {
        return system_edition;
    }

    public void setSystem_edition(String system_edition) {
        this.system_edition = system_edition;
    }

    public String getCellphone_model() {
        return cellphone_model;
    }

    public void setCellphone_model(String cellphone_model) {
        this.cellphone_model = cellphone_model;
    }

    public String getApp_edition() {
        return app_edition;
    }

    public void setApp_edition(String app_edition) {
        this.app_edition = app_edition;
    }

    public String getSdk_edition() {
        return sdk_edition;
    }

    public void setSdk_edition(String sdk_edition) {
        this.sdk_edition = sdk_edition;
    }


}
