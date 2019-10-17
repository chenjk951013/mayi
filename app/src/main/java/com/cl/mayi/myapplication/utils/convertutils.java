package com.cl.mayi.myapplication.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by yang on 2019/3/31.
 */

public class convertutils {
    /**
     * 判断邮箱格式是否正确
     */
    public static boolean isEmail(String email) {
        String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);
        return m.matches();
    }
    //手机中间4位是*号
    public static String getStarMobile(String mobile) {
        if (!TextUtils.isEmpty(mobile)) {
            if (mobile.length() >= 11)
                return mobile.substring(0, 3) + "****" + mobile.substring(7, mobile.length());
        } else {
            return "";
        }
        return mobile;
    }
    //邮箱中间4位是*号
    public static String getStarEmail(String mobile) {
        if (!TextUtils.isEmpty(mobile)) {
            if (mobile.length() >= 8)
                return mobile.substring(0, 3) + "***" + mobile.substring(7, mobile.length());
        } else {
            return "";
        }
        return mobile;
    }
    /**
     * 判断手机格式是否正确
     */
    public static boolean isCellphone(String str) {
        Pattern pattern = Pattern.compile("1[0-9]{10}");
        Matcher matcher = pattern.matcher(str);
        if (matcher.matches()) {
            return true;
        } else {
            return false;
        }
    }
    /**
     * 获取手机IMEI号
     *
     * 需要动态权限: android.permission.READ_PHONE_STATE
     */
    @SuppressLint("MissingPermission")
    public static String getIMEI(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(context.TELEPHONY_SERVICE);

     //  String imei = telephonyManager.getDeviceId();
       String androidID = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        String id = androidID + Build.SERIAL;

        return id;
    }

    //时间戳转字符串
    public static String getStrTime(String seconds) {
        String format = "yyyy-MM-dd HH:mm:ss";
        if (seconds == null || seconds.isEmpty() || seconds.equals("null")) {
            return "";
        }
        if (format == null || format.isEmpty()) format = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(Long.valueOf(seconds)));
    }
    //时间戳转字符串
    public static String getStrTime1(String seconds) {

        String format = " HH:mm:ss";
        if (seconds == null || seconds.isEmpty() || seconds.equals("null")) {
            return "";
        }
        if (format == null || format.isEmpty()) format = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(Long.valueOf(seconds)));
    }
    public static String converdouble(String total) {
        DecimalFormat df = new DecimalFormat("##0.00");
        df.setMaximumFractionDigits(2);//这里是小数位
        Double d = new Double(total);
        return df.format(d);

    }
    /**
     * 提供精确的乘法运算。
     *
     * @param v1 被乘数
     * @param v2 乘数
     * @return 两个参数的积
     */
    public static BigDecimal mul3(BigDecimal v1, BigDecimal v2) {
//        BigDecimal b1 = new BigDecimal(v1);
//        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        double v = v1.multiply(v2).doubleValue();
        // double c = Double.parseDouble(calculateProfit(v));
        if (2< 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        BigDecimal b = new BigDecimal(Double.toString(v));
        BigDecimal one = new BigDecimal("1");
        return b.divide(one, 2, BigDecimal.ROUND_DOWN);


    }
    public static BigDecimal mul(BigDecimal v1, BigDecimal v2) {
//        BigDecimal b1 = new BigDecimal(v1);
//        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        double v = v1.multiply(v2).doubleValue();
        // double c = Double.parseDouble(calculateProfit(v));
        if (2< 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        BigDecimal b = new BigDecimal(Double.toString(v));
        BigDecimal one = new BigDecimal("1");
        return b.divide(one, 4, BigDecimal.ROUND_DOWN);


    }
}
