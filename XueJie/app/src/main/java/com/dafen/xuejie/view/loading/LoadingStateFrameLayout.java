package com.dafen.xuejie.view.loading;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import static com.dafen.xuejie.view.loading.LoadingStateFrameLayout.STATE.EMPTY;
import static com.dafen.xuejie.view.loading.LoadingStateFrameLayout.STATE.FAIL;
import static com.dafen.xuejie.view.loading.LoadingStateFrameLayout.STATE.LOADING;
import static com.dafen.xuejie.view.loading.LoadingStateFrameLayout.STATE.SUCCESS;
import static com.dafen.xuejie.view.loading.LoadingStateFrameLayout.STATE.UNKNOW;


/**
 * 多加载状态FrameLayout
 * Created by _Ms on 2016/9/23.
 */
public abstract class LoadingStateFrameLayout extends MultiStateLayout implements MultiStateLayout.StateViewAdapter {

    /**
     * 状态枚举
     */
    public enum STATE {
        LOADING, // 加载中
        SUCCESS, // 成功
        EMPTY, // 空
        FAIL, // 失败
        UNKNOW // 未知状态
    }

    /**
     * 状态枚举数组
     */
    private static STATE[] sStates = {
            LOADING, // 加载中
            SUCCESS, // 成功
            EMPTY, // 空
            FAIL, // 失败
            UNKNOW // 未知状态
    };

    public LoadingStateFrameLayout(Context context) {
        this(context, null);
    }
    public LoadingStateFrameLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
    public LoadingStateFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setViewAdapter(this);
    }

    /**
     * 设置状态
     * @param state    状态值
     */
    public final void setState(STATE state) {

        for (int x = 0; x < sStates.length; x++) {

            if (sStates[x] == state) {

                setState(x);
            }
        }
    }

    @Override
    public final View getMultiStateView(Context context, int position, View convertView) {
        return getStateView(sStates[position], convertView);
    }

    /**
     * 获取多状态View
     * @param state    状态
     * @return View
     */
    protected abstract View getStateView(STATE state, View convertView);

}