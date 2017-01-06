package com.dafen.xuejie.base;

import android.content.Context;
import android.content.SharedPreferences;

import com.dafen.xuejie.utils.SpUtils;


/**
 * SPHelper基类
 * Created by UserBean on 2016/9/22.
 */
public abstract class BaseSPHelper<Bean> {

    /**
     * SharedPreferences
     */
    private SharedPreferences mSharedPreference;
    private SharedPreferences.Editor mEditor;

    private Context mContext;

    public BaseSPHelper(Context context) {
        mContext = context;
    }

    /**
     * 获取SharedPreference
     * @return ShareedPreference
     */
    protected final SharedPreferences getSharedPreference() {
        if (mSharedPreference == null) {
            mSharedPreference = mContext.getSharedPreferences(getSpFileName().toString(), Context.MODE_PRIVATE);
        }
        return mSharedPreference;
    }

    /**
     * 获取SharedPreference.Editor
     * @return ShareedPreference.Editor
     */
    protected final SharedPreferences.Editor getSharedPreferenceEdit() {
        if (mEditor == null) {
            mEditor = getSharedPreference().edit();
        }
        return mEditor;
    }

    /**
     * 设置SP文件名称
     * @return SP文件名称
     */
    protected abstract CharSequence getSpFileName();

    /**
     * 获取Bean实例
     * @return Bean的实例
     */
    protected abstract Bean getBeanInstance();

    /**
     * 获取SP存储数据
     * @return SP数据
     */
    public Bean read() {
        Bean bean = getBeanInstance();
        SpUtils.read(getSharedPreference(), bean);
        return bean;
    }

    /**
     * 填充SP存储数据
     * @param bean     被填充的数据
     */
    public void write(Bean bean) {
        SpUtils.write(getSharedPreferenceEdit(), bean);
    }

}
