package com.cl.mayi.myapplication.network;


import android.util.Log;

import com.google.gson.JsonObject;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpService {
    //测试接口
    private static final String BASE_URL = "http://ant-api.cciasia.org/ant-api/";


    //正式接口
 //  private static final String BASE_URL = "http://api.kukuku.site/api/";

//    //邀请好友
//    public static final String BASE_URL1 = "http://h5.kukuku.site/starsky_register.html?code=";
//
//    //咨询h5请求链接
//    public static final String BASE_URL2 = "https://h5.bitdogs.net/btg_information.html?userId=";
    //测试账号 13567112198 201741
    //注册邀请码 CTB1304
    private ApiService apiService;


    private static class Wrapper {
        static HttpService httpService = new HttpService();
    }

    private HttpService() {
        /**打印retrofit信息部分
         */
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                //打印retrofit日志
                Log.e("RetrofitLog", "retrofitBack = " + message);
            }
        });
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()//okhttp设置部分，此处还可再设置网络参数
                .addInterceptor(loggingInterceptor)//打印日志
                .connectTimeout(10, TimeUnit.SECONDS) //设置请求超时
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)//此client是为了打印信息
                //    .client(RetrofitUtils.getInstance().addTimeOut(10).addHttpLog().build())  //构建自己的OkHttpClient
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        apiService = retrofit.create(ApiService.class);
    }


    public static HttpService getInstance() {
        return Wrapper.httpService;
    }

    /**
     * d
     */

    public Observable<JsonObject> getlogin(Map<String, String> maps) {


        return apiService.getlogin(maps);
    }

    public Observable<JsonObject> getregister(Map<String, String> maps) {
        return apiService.getregister(maps);
    }

    public Observable<JsonObject> getisValid(Map<String, String> maps) {
        return apiService.getisValid(maps);
    }

    public Observable<JsonObject> getloginpwd(Map<String, String> maps) {
        return apiService.getloginpwd(maps);
    }

    public Observable<JsonObject> appClient(Map<String, String> maps) {

        return apiService.appClient(maps);
    }



}
