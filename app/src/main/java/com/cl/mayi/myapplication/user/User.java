package com.cl.mayi.myapplication.user;

/**
 * Created by Administrator on 2018/3/19.
 */

public class User {


    public String getIconName() {
        return iconName;
    }

    public void setIconName(String iconName) {
        this.iconName = iconName;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    private String iconName;//头像名
    private String icon;//头像
    String address;//string	钱包地址
    String inviteCode;    //String	邀请码
    double able;//double	可用币数量
    String nickname;//	string	昵称
    String mobile;//string	手机号吗
    long userId;//long	用户id
    String  email;//string	邮箱地址

    public String getBackdropPicture() {
        return backdropPicture;
    }

    public void setBackdropPicture(String backdropPicture) {
        this.backdropPicture = backdropPicture;
    }

    String  backdropPicture;//string	背景
    String token;
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    public double getAble() {
        return able;
    }

    public void setAble(double able) {
        this.able = able;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }


}
