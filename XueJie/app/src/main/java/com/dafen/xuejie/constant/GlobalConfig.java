package com.dafen.xuejie.constant;

import android.os.Environment;

import com.dafen.xuejie.activity.MainApplication;

import java.io.File;
import java.lang.reflect.Field;


/**
 * 全局配置
 * <p/>
 * Created by _Ms on 2016/9/21.
 */
public class GlobalConfig {

    /**
     * 是否调试模式
     */
    public static final boolean IS_DEBUG = true;

    /**
     * 非调试模式下是否重启
     */
    public static final boolean IS_CATCH_NO_TRY_EXCEPTION = true;

    /**
     * 程序英文标识，用于对各ASCII编码情况下的命名用途
     */
    public static final String APP_EN_NAME = "XUEJIE";

    /**
     * 公司英文标识，用于对ASCII编码情况下的命名用途
     */
    public static final String COMPANY_EN_NAME = "XUEJIE";

    /**
    *初始化全局DATA目录
    */
    static {
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            // 有SD情况，Cache存放到SD
            DIR_DATA = String.format(
                    "%s%s%s%s%s",
                    Environment.getExternalStorageDirectory().getAbsolutePath(),
                    File.separator,
                    COMPANY_EN_NAME, // 公司英文标识
                    File.separator,
                    APP_EN_NAME // 程序英文标识
            );
        } else {
            // 无SD情况Data\Dir目录
            DIR_DATA = MainApplication.getContext().getCacheDir().getAbsolutePath();
        }

    }

    /**
     * 服务器地址
     */
    public static final String SERVER_HOST = IS_DEBUG ? "192.168.0.188" : "115.115.115.115" ;

    /**
     * 全局数据目录
     */
    public static final String DIR_DATA;

    /**
     * 全局缓存位置
     */
    public static final String DIR_CACHE = String.format("%s%s%s", DIR_DATA, File.separator, "Cache");

    /**
     * 全局下载位置
     */
    public static final String DIR_DOWNLOAD = String.format("%s%s%s", DIR_DATA, File.separator, "Download");

    /**
     * 捕获到未捕获异常重启时间
     */
    public static final long UNCATCH_EXCEPTION_RESTART_TIME = 3000;

    /**
     * 获取全局设置的字符串形式
     * @return 全局设置的字符串形式
     */
    public static String getGlobalMessage() {
        StringBuffer sb = new StringBuffer();

        for (Field field : GlobalConfig.class.getDeclaredFields()) {
            String name = field.getName();
            Object value = null;
            try {
                value = field.get(null);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            sb.append(String.format("%s: %s\n", name, value));
        }
        return sb.toString();
    }

}
