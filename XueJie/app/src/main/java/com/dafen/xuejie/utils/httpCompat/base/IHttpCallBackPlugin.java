package com.dafen.xuejie.utils.httpCompat.base;

/**
 * .Http第三方框架扩展接口
 * Created by _Ms on 2016/10/13.
 */
public interface IHttpCallBackPlugin<T> {

    /**
     * 请求网络前
     */
    void onBefore();

    /**
     * 解析成功
     * @param result
     */
    void onSucess(T result);

    /**
     * 请求网络失败
     * @param e    失败信息
     */
    void onError(Exception e);

    /**
     * 请求网络完毕后
     */
    void onAfter();

}
