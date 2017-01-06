package com.dafen.xuejie.utils.httpCompat.base;

import com.dafen.xuejie.utils.Debug;

import java.util.Set;


/**
 * 请求配置兼容抽象类
 * Created by _Ms on 2016/10/11.
 */
public abstract class AbsHttpRequest<R extends IHttpRequest> implements IHttpRequest<R> {

    /**
     * 扩展插件列表
     */
    private Set<IHttpCallBackPlugin> mExpandSet;

    /**
     * 异步执行网络请求
     * @param callBack    回调
     */
    @Override
    public void excute(IResponseParser parser, IHttpCallBack callBack) {

        if (mExpandSet != null) {
            for (IHttpCallBackPlugin plugin : mExpandSet) {
                callBack.addPlugin(plugin);
            }
            mExpandSet.clear();
            mExpandSet = null;
        }

        if (parser == null) {
            callBack.setResponseParser(new IResponseParser.DefaultParser());
        } else {
            callBack.setResponseParser(parser);
        }

        callBack.onBefore();
        excuteRun(callBack);

        Debug.d("HttpRequest, GET: %s", url());
    }

    /**
     * 执行callBack
     * @param callBack
     */
    protected abstract void excuteRun(IHttpCallBack callBack);

}
