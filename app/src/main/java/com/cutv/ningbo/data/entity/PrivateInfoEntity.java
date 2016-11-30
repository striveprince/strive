package com.cutv.ningbo.data.entity;

/**
 * project：cutv_ningbo
 * description：
 * create developer： Arvin
 * create time：2015/12/17 15:38.
 * modify developer：  Arvin
 * modify time：2015/12/17 15:38.
 * modify remark：
 * @version 2.0
 */
public class PrivateInfoEntity extends BaseEntity{
    private int integral, id= 0, sex,  address_id, expires, login_status, status,age;
    private String username, cellphone, nickname, avatar, constellation, job, interest, school,
            home, email, introduction, token, fresh_token, secretkey,wx_uid,qq_uid,weibo_uid,my_invite_code,others_invite_code;//,localavatar;
    private long token_create_time, last_login_time,checkin_time=0;

    public long getCheckin_time() {
        return checkin_time;
    }

    public void setCheckin_time(long checkin_time) {
        this.checkin_time = checkin_time;
    }

    public int getIntegral() {
        return integral;
    }

//    public String getLocalavatar() {
//        return localavatar;
//    }
//
//    public void setLocalavatar(String localavatar) {
//        this.localavatar = localavatar;
//    }

    public void setIntegral(int integral) {
        this.integral = integral;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAddress_id() {
        return address_id;
    }

    public void setAddress_id(int address_id) {
        this.address_id = address_id;
    }

    public int getExpires() {
        return expires;
    }

    public void setExpires(int expires) {
        this.expires = expires;
    }

    public String getSecretkey() {
        return secretkey;
    }

    public void setSecretkey(String secretkey) {
        this.secretkey = secretkey;
    }

    public int getLogin_status() {
        return login_status;
    }

    public void setLogin_status(int login_status) {
        this.login_status = login_status;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getConstellation() {
        return constellation;
    }

    public void setConstellation(String constellation) {
        this.constellation = constellation;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getFresh_token() {
        return fresh_token;
    }

    public void setFresh_token(String fresh_token) {
        this.fresh_token = fresh_token;
    }

    public long getToken_create_time() {
        return token_create_time;
    }

    public void setToken_create_time(long token_create_time) {
        this.token_create_time = token_create_time;
    }

    public long getLast_login_time() {
        return last_login_time;
    }

    public void setLast_login_time(long last_login_time) {
        this.last_login_time = last_login_time;
    }

    public String getWx_uid() {
        return wx_uid;
    }

    public void setWx_uid(String wx_uid) {
        this.wx_uid = wx_uid;
    }

    public String getQq_uid() {
        return qq_uid;
    }

    public void setQq_uid(String qq_uid) {
        this.qq_uid = qq_uid;
    }

    public String getWeibo_uid() {
        return weibo_uid;
    }

    public void setWeibo_uid(String weibo_uid) {
        this.weibo_uid = weibo_uid;
    }

    public String getMy_invite_code() {
        return my_invite_code;
    }

    public void setMy_invite_code(String my_invite_code) {
        this.my_invite_code = my_invite_code;
    }

    public String getOthers_invite_code() {
        return others_invite_code;
    }

    public void setOthers_invite_code(String others_invite_code) {
        this.others_invite_code = others_invite_code;
    }

}
