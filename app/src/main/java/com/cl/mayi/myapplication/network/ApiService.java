package com.cl.mayi.myapplication.network;


import com.google.gson.JsonObject;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


public interface ApiService {
    /**
     * 登录
     * @param maps
     */
    @FormUrlEncoded
    @POST("user/mobileLogin")
    Observable<JsonObject> getlogin(@FieldMap Map<String, String> maps);

    /**
     * 注册
     *
     * @param maps
     * @return
     */
    @FormUrlEncoded
    @POST("user/register")
    Observable<JsonObject> getregister(@FieldMap Map<String, String> maps);

    /**
     * 获取验证码
     *
     * @param maps
     * @return
     */
    @FormUrlEncoded
    @POST("user/mobileCode")
    Observable<JsonObject> getisValid(@FieldMap Map<String, String> maps);
    /**
     * 密码登录
     *
     * @param maps
     * @return
     */
    @FormUrlEncoded
    @POST("user/login")
    Observable<JsonObject> getloginpwd(@FieldMap Map<String, String> maps);
    @FormUrlEncoded
    @POST("common/appClient")
    Observable<JsonObject> appClient(@FieldMap Map<String, String> maps);

}
