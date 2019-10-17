package com.cl.mayi.myapplication;

import android.app.Application;
import android.content.Context;


/**
 * Created by yang on 2019/3/20.
 */

public class MyApplication extends Application {
    private static Context appContext;
    private static MyApplication mApplication;
    @Override
    public void onCreate() {
        super.onCreate();
        appContext = this.getApplicationContext();
        mApplication=this;
    }

    public static synchronized MyApplication getApplicationInstance() {
        if (mApplication == null) {
            mApplication = new MyApplication();
        }
        return mApplication;
    }



    public static Context getContext() {
        return appContext;
    }

}
