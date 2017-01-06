package com.dafen.xuejie.view.loading;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

/**
 * 已实现多种加载状态填充的加载状态Layout
 * Created by _Ms on 2016/10/20.
 */
public abstract class AbsLoadingStateFrame extends LoadingStateFrameLayout {

    public AbsLoadingStateFrame(Context context) {super(context);}
    public AbsLoadingStateFrame(Context context, AttributeSet attrs) {super(context, attrs);}
    public AbsLoadingStateFrame(Context context, AttributeSet attrs, int defStyleAttr) {super(context, attrs, defStyleAttr);}

    @Override
    protected final View getStateView(STATE state, View convertView) {

        switch (state) {
            case EMPTY:
                return getEmptyView(convertView);
            case FAIL:
                return getFailView(convertView);
            case LOADING:
                return getLoadingView(convertView);
            case SUCCESS:
                return getSucessView(convertView);
            case UNKNOW:
                return getUnknowView(convertView);
        }
        return null;
    }

    /**
     * 获取成功页面
     * @param convertView
     * @return
     */
    protected abstract View getSucessView(View convertView);

    /**
     * 创建未知页面
     * @param convertView
     * @return
     */
    protected abstract View getUnknowView(View convertView);

    /**
     * 创建加载中页面
     * @param convertView
     * @return
     */
    protected abstract View getLoadingView(View convertView);

    /**
     * 创建失败页面
     *
     * @param convertView
     * @return
     */
    protected abstract View getFailView(View convertView);

    /**
     * 创建结果为空页面
     *
     * @param convertView
     * @return
     */
    protected abstract View getEmptyView(View convertView);


}
