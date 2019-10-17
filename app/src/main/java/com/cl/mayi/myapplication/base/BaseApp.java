package com.cl.mayi.myapplication.base;

import android.app.Application;

public class BaseApp extends Application {
    private static BaseApp sApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        sApplication = this;
    }

    public static BaseApp getApplication() {
        return sApplication;
    }
}
