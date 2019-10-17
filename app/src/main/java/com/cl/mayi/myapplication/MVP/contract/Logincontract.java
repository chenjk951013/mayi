package com.cl.mayi.myapplication.MVP.contract;



import com.cl.mayi.myapplication.base.interfaces.IView;
import com.google.gson.JsonObject;

import java.util.Map;

import io.reactivex.Observable;

/**
 * Created by yang on 2019/4/24.
 */

public interface Logincontract {
    interface View extends IView {


        void loginFail(String msg, int status);

        void loginSuccess(String data, int type);
    }

    interface Presenter {
        void getlogin(Map<String, String> maps, int type);

        //注册
        void getregister(Map<String, String> maps, int type);

        //获取验证码
        void getisValid(Map<String, String> maps);

        void getloginpwd(Map<String, String> maps, int type);

        void appClient(Map<String, String> maps, int type);



    }

    interface Model {
        Observable<JsonObject> getlogin(Map<String, String> maps);

        Observable<JsonObject> getregister(Map<String, String> maps);

        Observable<JsonObject> getisValid(Map<String, String> maps);

        Observable<JsonObject> getloginpwd(Map<String, String> maps);

        Observable<JsonObject> appClient(Map<String, String> maps);



    }
}
