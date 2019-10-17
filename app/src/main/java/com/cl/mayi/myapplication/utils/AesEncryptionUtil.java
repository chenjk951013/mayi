package com.cl.mayi.myapplication.utils;


import java.io.UnsupportedEncodingException;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by yang on 2019/6/29.
 */

public class AesEncryptionUtil {
    /** 算法/模式/填充 **/

    private static final String CipherMode = "AES/CBC/PKCS5Padding";


    /** 创建密钥 **/

    private static SecretKeySpec createKey(String key) {

        byte[] data = null;

        if (key == null) {

            key = "";

        }

        StringBuffer sb = new StringBuffer(16);

        sb.append(key);

        while (sb.length() < 16) {

            sb.append("0");

        }

        if (sb.length() > 16) {

            sb.setLength(16);

        }





        try {

            data = sb.toString().getBytes("UTF-8");

        } catch (UnsupportedEncodingException e) {

            e.printStackTrace();

        }

        return new SecretKeySpec(data, "AES");

    }





    private static IvParameterSpec createIV(String password) {

        byte[] data = null;

        if (password == null) {

            password = "";

        }

        StringBuffer sb = new StringBuffer(16);

        sb.append(password);

        while (sb.length() < 16) {

            sb.append("0");

        }

        if (sb.length() > 16) {

            sb.setLength(16);

        }





        try {

            data = sb.toString().getBytes("UTF-8");

        } catch (UnsupportedEncodingException e) {

            e.printStackTrace();

        }

        return new IvParameterSpec(data);

    }





    /** 加密字节数据 **/

    public static byte[] encrypt(byte[] content, String password, String iv) {

        try {

            SecretKeySpec key = createKey(password);

            Cipher cipher = Cipher.getInstance(CipherMode);

            cipher.init(Cipher.ENCRYPT_MODE, key, createIV(iv));

            byte[] result = cipher.doFinal(content);

            return result;

        } catch (Exception e) {

            e.printStackTrace();

        }

        return null;

    }





    /** 加密(结果为16进制字符串) **/

    public static String encrypt(String content, String password, String iv) {

        byte[] data = null;

        try {

            data = content.getBytes("UTF-8");

        } catch (Exception e) {

            e.printStackTrace();

        }

        data = encrypt(data, password, iv);

        String result = byte2hex(data);

        return result;

    }





    /** 解密字节数组 **/

    public static byte[] decrypt(byte[] content, String password, String iv) {

        try {

            SecretKeySpec key = createKey(password);

            Cipher cipher = Cipher.getInstance(CipherMode);

            cipher.init(Cipher.DECRYPT_MODE, key, createIV(iv));

            byte[] result = cipher.doFinal(content);

            return result;

        } catch (Exception e) {

            e.printStackTrace();

        }

        return null;

    }





    /** 解密(输出结果为字符串) **/

    public static String decrypt(String content, String password, String iv) {

        byte[] data = null;

        try {

            data = hex2byte(content);

        } catch (Exception e) {

            e.printStackTrace();

        }

        data = decrypt(data, password, iv);

        if (data == null)

            return null;

        String result = null;

        try {

            result = new String(data, "UTF-8");

        } catch (UnsupportedEncodingException e) {

            e.printStackTrace();

        }

        return result;

    }





    /** 字节数组转成16进制字符串 **/

    public static String byte2hex(byte[] b) { // 一个字节的数，

        StringBuffer sb = new StringBuffer(b.length * 2);

        String tmp = "";

        for (int n = 0; n < b.length; n++) {

            // 整数转成十六进制表示

            tmp = (Integer.toHexString(b[n] & 0XFF));

            if (tmp.length() == 1) {

                sb.append("0");

            }

            sb.append(tmp);

        }

        return sb.toString().toUpperCase(); // 转成大写

    }





    /** 将hex字符串转换成字节数组 **/

    private static byte[] hex2byte(String inputString) {

        if (inputString == null || inputString.length() < 2) {

            return new byte[0];

        }

        inputString = inputString.toLowerCase();

        int l = inputString.length() / 2;

        byte[] result = new byte[l];

        for (int i = 0; i < l; ++i) {

            String tmp = inputString.substring(2 * i, 2 * i + 2);

            result[i] = (byte) (Integer.parseInt(tmp, 16) & 0xFF);

        }

        return result;

    }
    public static void main(String[] args) throws Exception {

//        Map<String,String >map=new HashMap<>();
//        map.put("11","11");
//        System.out.println( HttpKit.get("https://yrtest.ty574.com/juhaowan/third/gameCoin"));
//        String content = "{\"accountId\":32,\"token\":\"b14fad089098ac82f8cf9695f28d8380\"}";
        String content="12345678";
        decrypt("data=19B2E898433524308C54A3248AE7958B0F6D7D4BBBC3880A853EAEAFC82A7D4E802B8CD0EA2565EC8E3AC1DC8B7B8927EEAD94B86CF6503B3EDB66A5E33306B449B98436B23F45F6B10E2690B1C72A06C368FE0A8F5C467F6808B12345597626&source=A1561826016898","HB3btdogsandroid","dna");
        System.out.println(""+ decrypt("data=19B2E898433524308C54A3248AE7958B0F6D7D4BBBC3880A853EAEAFC82A7D4E802B8CD0EA2565EC8E3AC1DC8B7B8927EEAD94B86CF6503B3EDB66A5E33306B449B98436B23F45F6B10E2690B1C72A06C368FE0A8F5C467F6808B12345597626&source=A1561826016898","HB3btdogsandroid","dna"));
      //  System.out.println(""+ encrypt("123456","HB3btdogsandroid","dna"));
//    //    encrypt
//        System.out.println("加密前：" + content);
//        System.out.println("加密密钥和解密密钥：" + AES_KEY);
//        String encrypt = encrypt(content,AES_KEY);
//        System.out.println("加密后：" + encrypt);
//        String decrypt = aesDecrypt(encrypt, AES_KEY);
//        System.out.println("解密后：" + decrypt);
//
//
////        System.out.printf("月/日/年格式：%",new Date());
////
////        String content = "<p>恭喜您在%tF红包斗地主胜局榜日榜中获得第%d名，奖励%s%s。</p>";
////        String str=String.format(content,new Date(),1,"1","1");
////        System.out.println(  TextUtils.isNumeric("123"));
//
//        System.out.println(aesDecrypt("Bq6UBf4GgUga9+V2MFq8Aw==", AES_KEY));



    }
}
