package com.dafen.xuejie.utils.httpCompat.base;

/**
 * .Http第三方框架二次封装·CallBack转换接口
 * Created by _Ms on 2016/10/10.
 */
public interface IHttpCallBack<T> {

    /**
     * 设置ResponseParser
     * @param parser    responseParser
     */
    void setResponseParser(IResponseParser<T> parser);

    /**
     * 获取ResponseParser
     * @return responsePaser
     */
    IResponseParser<T> getResponseParser();

    /**
     * 请求网络前, 由兼容框架处理
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

    /**
     * 添加扩展插件到请求回调
     * @param plugin    扩展插件对象实现
     */
    IHttpCallBack<T> addPlugin(IHttpCallBackPlugin plugin);

    /**
     * 移除扩展插件到请求回调
     * @param plugin    扩展插件对象实现
     */
    IHttpCallBack<T> removePlugin(IHttpCallBackPlugin<T> plugin);

}
