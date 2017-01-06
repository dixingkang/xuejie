package com.dafen.xuejie.utils;

/**
 * 参数校验工具类
 * Created by UserBean on 2016/9/26.
 */
public class CheckUtils {

    /**
     * 校验参数不能为Null
     * @param obj
     */
    public static void notNull(Object... obj) {
        notNull(null, obj);
    }

    /**
     * 校验参数不能为Null
     * @param msg 提示信息
     * @param obj
     */
    public static void notNull(String msg, Object... obj) {
        if (obj == null) {
            throw new NullPointerException();
        }

        for (Object o : obj) {
            if (o == null) {
                throw new NullPointerException(msg);
            }
        }
    }

}
