package com.cl.mayi.myapplication.user;
import android.content.Context;

/**
 * Created by Administrator on 2018/3/19.
 */

public class UserUtil {
    private String TAG = getClass().getName();
    private static APPUserData<User> appUser;
    static Context mContext;

    public static void registerUserUtil(Context context) {
        appUser = new APPUserData<>(context);
    }

    public static User getUser() {
        return appUser.getModel(new User());
    }

    public static void commitUser(User User) {
        appUser.doSave(User);
    }

    public static void clearCache() {
        commitUser(new User());
    }


}
