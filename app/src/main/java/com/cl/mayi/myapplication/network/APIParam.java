package com.cl.mayi.myapplication.network;


import android.annotation.SuppressLint;


import com.cl.mayi.myapplication.user.UserUtil;

import org.json.JSONObject;

import java.math.BigDecimal;


/**
 * Created by yang on 2019/6/20.
 */

public class APIParam {
    /**
     * 注册
     */
    public static String register(String mobile, String mobileCode, String password, String inviteCode, String transPassword,int zodiacId) throws Exception {
        JSONObject jobj = new JSONObject();
        JSONObject logindto = new JSONObject();
        logindto.put("mobile", mobile);
        logindto.put("mobileCode", mobileCode);
        logindto.put("password", password);
        logindto.put("inviteCode", inviteCode);
        logindto.put("transPassword", transPassword);
        logindto.put("zodiacId", zodiacId);
        jobj.put("loginDTO", logindto);
        return jobj.toString();
    }

    /**
     * 验证码
     */
    public static String registerisValid(String mobile) throws Exception {
        JSONObject jobj = new JSONObject();
        JSONObject logindto = new JSONObject();
        logindto.put("mobile", mobile);
        jobj.put("loginDTO", logindto);

        return jobj.toString();
    }

    /**
     * 登录
     */
    @SuppressLint("NewApi")
    public static String login(String mobile, String mobileCode, String deviceCode) throws Exception {

        JSONObject jobj = new JSONObject();
        JSONObject logindto = new JSONObject();
        logindto.put("mobile", mobile);
        logindto.put("mobileCode", mobileCode);
        logindto.put("deviceCode", deviceCode);
        jobj.put("loginDTO", logindto);


        return jobj.toString();
    }

    /**
     * 密码登录
     */
    @SuppressLint("NewApi")
    public static String loginpwd(String mobile, String password, String deviceCode ) throws Exception {
        JSONObject jobj = new JSONObject();
        JSONObject logindto = new JSONObject();
        logindto.put("mobile", mobile);
        logindto.put("password", password);
        logindto.put("deviceCode", deviceCode);
        jobj.put("loginDTO", logindto);

        return jobj.toString();
    }


    public  static String  appClient(String  version) throws Exception {

        JSONObject jobj = new JSONObject();

        jobj.put("appType",2);
        jobj.put("version",version);
        jobj.put("token", UserUtil.getUser().getToken());
        return jobj.toString();
    }


}
