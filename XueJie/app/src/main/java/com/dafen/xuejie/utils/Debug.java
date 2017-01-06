package com.dafen.xuejie.utils;

import android.util.Log;

import com.dafen.xuejie.constant.GlobalConfig;


/**
 * 异常工具类
 * Created by _Ms on 2016/9/28.
 */
public class Debug {

    public static final String TAG_EXCEPTION_PRINT = "ExceptionPrint";

    public enum LOGTYPE {
        VERBOSE,
        DEBUG,
        INFO,
        WARN,
        ERROR
    }

    /**
     * 异常信息打印头
     */
    private static final String EXCEPTION_HEAD = String.format("#Exception:%s", GlobalConfig.APP_EN_NAME);

    /** ExceptonPrint */
    public static void e(Throwable e) {exceptionPrint(e, LOGTYPE.ERROR);}
    public static void w(Throwable e) {exceptionPrint(e, LOGTYPE.WARN);}
    public static void d(Throwable e) {exceptionPrint(e, LOGTYPE.DEBUG);}
    public static void i(Throwable e) {exceptionPrint(e, LOGTYPE.INFO);}
    public static void v(Throwable e) {exceptionPrint(e, LOGTYPE.VERBOSE);}

    /** Log */
    public static void e(String format, Object... args) {LogUtil.e(format, args);}
    public static void w(String format, Object... args) {LogUtil.w(format, args);}
    public static void d(String format, Object... args) {LogUtil.d(format, args);}
    public static void i(String format, Object... args) {LogUtil.i(format, args);}
    public static void v(String format, Object... args) {LogUtil.v(format, args);}

    /**
     * Toast Log
     * @param msg    msg
     */
    public static void toast(CharSequence msg) {
        if (GlobalConfig.IS_DEBUG) {
            String finalMsg = String.format("DEBUG:\n%s", msg);
            ToastUtils.showLong(finalMsg);
        }
    }

    /**
     * 在调试模式下打印异常
     *
     * @param e 异常
     */
    public static void exceptionPrint(Throwable e, LOGTYPE   logType) {
        if (!GlobalConfig.IS_DEBUG) {return;}
        if (e == null) {return;}

        String finalStr = TextFormatUtils.format2TableStr(
                EXCEPTION_HEAD,
                e.toString(),
                stacks2str(e.getStackTrace())
        );

        finalStr = String.format(
                "\n%s",
                finalStr
        );

        /*
        Log打印
         */
        switch (logType) {
            case DEBUG:
                Log.d(TAG_EXCEPTION_PRINT, finalStr);
                break;
            case ERROR:
                Log.e(TAG_EXCEPTION_PRINT, finalStr);
                break;
            case VERBOSE:
                Log.v(TAG_EXCEPTION_PRINT, finalStr);
                break;
            case WARN:
                Log.w(TAG_EXCEPTION_PRINT, finalStr);
                break;
            case INFO:
            default:
                Log.i(TAG_EXCEPTION_PRINT, finalStr);
        }
    }

    /**
     * Stack Array To String
     *
     * @param stackTrace stackArray
     * @return string
     */
    private static String stacks2str(StackTraceElement[] stackTrace) {

        StringBuffer buffer = new StringBuffer();

        for (StackTraceElement stack : stackTrace) {

            String stackStr = stack.toString();

            buffer.append(stackStr).append("\n");
        }

        return buffer.toString();
    }
}
