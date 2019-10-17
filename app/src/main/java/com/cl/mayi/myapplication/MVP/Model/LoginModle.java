package com.cl.mayi.myapplication.MVP.Model;



import com.cl.mayi.myapplication.MVP.contract.Logincontract;
import com.cl.mayi.myapplication.network.HttpService;
import com.google.gson.JsonObject;

import java.util.Map;

import io.reactivex.Observable;

/**
 * Created by yang on 2019/4/24.
 */

public class LoginModle implements Logincontract.Model  {

    @Override
    public Observable<JsonObject> getlogin(Map<String, String> maps) {

        return HttpService.getInstance().getlogin(maps);
    }

    @Override
    public Observable<JsonObject> getregister(Map<String, String> maps) {
        return HttpService.getInstance().getregister(maps);
    }

    @Override
    public Observable<JsonObject> getisValid(Map<String, String> maps) {
        return  HttpService.getInstance().getisValid(maps);
    }

    @Override
    public Observable<JsonObject> getloginpwd(Map<String, String> maps) {
        return  HttpService.getInstance().getloginpwd(maps);
    }

    @Override
    public Observable<JsonObject> appClient(Map<String, String> maps) {
        return  HttpService.getInstance().appClient(maps);
    }



}
