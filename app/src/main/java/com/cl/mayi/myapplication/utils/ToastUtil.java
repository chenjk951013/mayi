package com.cl.mayi.myapplication.utils;

import android.content.Intent;
import android.os.Looper;
import android.widget.Toast;

import com.cl.mayi.myapplication.LoginActivity;
import com.cl.mayi.myapplication.MyApplication;
import com.cl.mayi.myapplication.user.UserUtil;


/**
 * Created by yang on 2019/3/20.
 */

public class ToastUtil {
    public static void showToast(String msg) {

        if (isMainThread()) {
            Toast.makeText(MyApplication.getContext(), msg, Toast.LENGTH_SHORT).show();
        } else {
            Looper.prepare();
            Toast.makeText(MyApplication.getContext(), msg, Toast.LENGTH_SHORT).show();
            Looper.loop();
        }

    }

    public static void showresultToast(String msg, int status) {
        if (status == 104) {

            ActivityManagement.finishAllActivity();
            UserUtil.clearCache();
            Intent intent = new Intent(MyApplication.getContext(), LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            MyApplication.getContext().startActivity(intent);
            Toast.makeText(MyApplication.getContext(), msg, Toast.LENGTH_SHORT).show();
        } else {
            if (isMainThread()) {
                Toast.makeText(MyApplication.getContext(), msg, Toast.LENGTH_SHORT).show();
            } else {
                Looper.prepare();
                Toast.makeText(MyApplication.getContext(), msg, Toast.LENGTH_SHORT).show();
                Looper.loop();
            }
        }
    }

    public static void showLongToast(String msg) {
        if (isMainThread()) {
            Toast.makeText(MyApplication.getContext(), msg, Toast.LENGTH_LONG).show();
        } else {
            Looper.prepare();
            Toast.makeText(MyApplication.getContext(), msg, Toast.LENGTH_LONG).show();
            Looper.loop();
        }
    }

    public static boolean isMainThread() {
        return Looper.getMainLooper().getThread() == Thread.currentThread();
    }

}
