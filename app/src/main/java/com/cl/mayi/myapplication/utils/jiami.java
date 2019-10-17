package com.cl.mayi.myapplication.utils;



import java.util.Map;

import static com.cl.mayi.myapplication.utils.AesEncryptionUtil.encrypt;


/**
 * Created by yang on 2019/6/29.
 */

public class jiami {
    public static Map<String, String> encryption(Map<String, String> maps) {
        maps.put("source", "A" + System.currentTimeMillis());

        String encryption = encrypt(String.valueOf(maps.get("data")), "HB3btdogsandroid", "dna");
        maps.put("data", encryption);

        return maps;
    }
}
