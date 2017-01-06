package com.dafen.xuejie.utils;

import android.content.SharedPreferences;

import java.lang.reflect.Field;

/**
 * SP工具类
 * Created by UserBean on 2016/9/22.
 */
public class SpUtils {

    /**
     * 将指定bean 以 字段名为键 字段值 为 值 的形式写入到SP
     * @param editor    SPEditor
     * @param bean      被写入的bean
     */
    public static void  write(SharedPreferences.Editor editor, Object bean) {

        for (Field field : bean.getClass().getDeclaredFields()) {
            String key = field.getName();
            if (key != null) {

                try {

                    Object valueObj = field.get(bean);
                    if (valueObj != null) {
                        String value = valueObj.toString();
                        editor.putString(key, value);
                    }

                } catch (IllegalAccessException e) {
                    Debug.e(e);
                }

            }
        }

        editor.apply();
    }

    /**
     * 从指定bean键为sp键将sp数据读出写入到bean
     * @param sp SP
     * @param bean      Bean对象
     * @return
     */
    public static void read(SharedPreferences sp, Object bean) {

        for (Field field : bean.getClass().getDeclaredFields()) {
            String key = field.getName();
            if (key != null) {

                String value = sp.getString(key, null);

                try {
                    BeanUtils.toBeanField(BeanUtils.MODE.ERROR_DEFALUT, bean, key, value);
                } catch (Exception e) {
                    Debug.e(e);
                }

            }
        }

    }

}
