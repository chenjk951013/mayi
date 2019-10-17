package com.cl.mayi.myapplication.utils;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/3/6.
 */

public class ActivityManagement {
    private static ActivityManagement instance;
    private static List<Activity> activityStack = new ArrayList<Activity>();

    private ActivityManagement() {
    }

    public static synchronized ActivityManagement getInstance() {
        if (instance == null) {
            instance = new ActivityManagement();

        }
        return instance;
    }

    public int getActivityNum() {
        return activityStack == null ? 0 : activityStack.size();
    }

    public void addActivity(Activity aty) {
        activityStack.add(aty);
    }

    public void removeActivity(Activity aty) {
        activityStack.remove(aty);
    }

    /**
     * 结束所有Activity
     */
    public static void finishAllActivity() {
        for (int i = 0, size = activityStack.size(); i < size; i++) {
            if (null != activityStack.get(i)) {
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
    }

    /**
     * 清除除了自己以外的Activiti
     */
    public static void finishExceptMyActivity(Activity view) {
        for (Activity avtivity : activityStack) {
            if (view == avtivity) continue;
            avtivity.finish();
        }
    }
}
