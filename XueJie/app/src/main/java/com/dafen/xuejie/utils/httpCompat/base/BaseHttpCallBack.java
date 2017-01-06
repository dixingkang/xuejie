package com.dafen.xuejie.utils.httpCompat.base;

import com.dafen.xuejie.utils.CheckUtils;
import com.dafen.xuejie.utils.Debug;

import java.util.HashSet;
import java.util.Set;



/**
 * 三方网络请求框架二次封装 通用回调
 * Created by _Ms on 2016/10/10.
 */
public abstract class BaseHttpCallBack<T> implements IHttpCallBack<T> {

    /**
     * 扩展插件列表
     */
    private Set<IHttpCallBackPlugin> mExpandSet;

    /**
     * Response解析器
     */
    private IResponseParser<T> mResponseParser;

    @Override
    public void onBefore() {
        // 回调插件
        if (mExpandSet != null) {
            for (IHttpCallBackPlugin plugin : mExpandSet) {
                plugin.onBefore();
            }
        }

    }

    @Override
    public void onSucess(T result) {
        // 回调插件
        if (mExpandSet != null) {
            for (IHttpCallBackPlugin plugin : mExpandSet) {
                plugin.onSucess(result);
            }
        }

    }

    @Override
    public void onError(Exception e) {
        Debug.e(e);

        // 回调插件
        if (mExpandSet != null) {
            for (IHttpCallBackPlugin plugin : mExpandSet) {
                plugin.onError(e);
            }
        }

    }

    @Override
    public void onAfter() {
        // 回调插件
        if (mExpandSet != null) {
            for (IHttpCallBackPlugin plugin : mExpandSet) {
                plugin.onAfter();
            }
        }

    }

    @Override
    public final void setResponseParser(IResponseParser<T> parser) {
        CheckUtils.notNull(parser);

        mResponseParser = parser;
    }

    @Override
    public final IResponseParser<T> getResponseParser() {
        return mResponseParser;
    }

    /**
     * 添加扩展插件到请求回调
     *
     * @param expand 扩展插件对象实现
     */
    @Override
    public final IHttpCallBack<T> addPlugin(IHttpCallBackPlugin expand) {
        CheckUtils.notNull(expand);

        if (mExpandSet == null) {
            mExpandSet = new HashSet<>();
        }

        mExpandSet.add(expand);
        return this;
    }

    /**
     * 移除扩展插件到请求回调
     *
     * @param expand 扩展插件对象实现
     */
    @Override
    public final IHttpCallBack<T> removePlugin(IHttpCallBackPlugin<T> expand) {

        if (expand == null) {
            return this;
        }

        if (mExpandSet == null) {
            return this;
        }

        mExpandSet.remove(expand);

        if (mExpandSet.size() == 0) {
            mExpandSet = null;
        }

        return this;
    }
}
