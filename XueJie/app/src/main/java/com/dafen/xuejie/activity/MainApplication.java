package com.dafen.xuejie.activity;

import android.app.Activity;
import android.app.Application;
import android.content.Context;


import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.dafen.xuejie.utils.httpCompat.Http;
import com.dafen.xuejie.utils.httpCompat.okHttpCompat.CompatGuide;
import com.lzy.okhttputils.OkHttpUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * Application 入口初始
 * <p/>
 * Created by _Ms on 2016/9/21.
 */
public class MainApplication extends Application {
    private static MainApplication instance;
    private RequestQueue requestQueue;
    /**
     * ApplicationContext
     */
    private static Context sContext;

    /**
     * 存储App所有已开启Activity
     */
    private static List<Activity> sActivityList;

    // 用于存放倒计时时间
    public static Map<String, Long> map;
    @Override
    public void onCreate() {
        super.onCreate();

        /*
        ApplicationContext
         */
        sContext = this;
        instance = this;
        /*
        ActivityList
         */
        sActivityList = new ArrayList<>();
        OkHttpUtils.init(this);
        Http.init(new CompatGuide());


        /**
         * 设置volley
         */
        requestQueue = Volley.newRequestQueue(this);

    }


    /**
     * 获取ApplicationContext
     *
     * @return
     */
    public static Context getContext() {
        return sContext;
    }

    /**
     * 销毁所有Activity记录中的Activity
     */
    public static void destoryAllActivity() {

        // 遍历销毁所有Activity
        synchronized (sActivityList) {
            ArrayList<Activity> copyActivityList = new ArrayList<>(sActivityList);

            for (Activity activity : copyActivityList) {
                activity.finish();
            }

            // Kill MySelf
            android.os.Process.killProcess(android.os.Process.myPid());
        }

    }

    /**
     * 添加Activity到Activity总记录
     *
     * @param activity Activity
     */
    public static synchronized void addActivity(Activity activity) {
        sActivityList.add(activity);
    }

    /**
     * 从Activity总记录中移除Activity
     *
     * @param activity
     */
    public static synchronized void removeActivity(Activity activity) {
        sActivityList.remove(activity);
    }
    public static MainApplication getInstance() {
        return instance;
    }
    public RequestQueue getRequestQueue() {
        return requestQueue;
    }
    // 缓存地址
    public static String getCachePath() {
        return "/mnt/sdcard/xuejie/cache";
    }
}
