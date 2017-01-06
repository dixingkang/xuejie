package com.dafen.xuejie.utils;


import com.google.gson.Gson;

/**
 * 单例，封装Gson
 * Created by UserBean on 2016/9/26.
 */
public class GsonUtil {

    private static Gson mGson;

    private GsonUtil(){}

    public static Gson getInstance() {
        if (mGson == null) {
            synchronized (GsonUtil.class) {
                if (mGson == null) {
                    mGson = new Gson();
                }
            }
        }

        return mGson;
    }
}
