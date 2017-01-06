package com.dafen.xuejie.utils.httpCompat.base;

import java.io.IOException;

/**
 * Http第三方框架二次封装·HttpRequest转换接口
 * Created by _Ms on 2016/10/11.
 */
public interface IHttpRequest<R extends IHttpRequest> {

    /**
     * 设置URL
     * @param url
     * @return
     */
    R url(String url);

    /**
     * 获取请求URL
     * @return
     */
    String url();

    /**
     * 获取请求METHOD
     * @return method->string
     */
    String method();

    /**
     * 添加Http请求头信息
     * @param key      key
     * @param value    value
     * @return
     */
    R addHeader(String key, String value);

    /**
     * 移除Http请求头信息
     * @param key      key
     * @return
     */
    R removeHeader(String key);

    /**
     *
     * @return
     */
    R removeAllHeader();

    /**
     * 给请求添加POST参数
     * @param key params key
     *  @param value params value
     * @return CompatRequest 链式编程
     */
    R addParams(String key, CharSequence value);

    /**
     * 移除POST参数
     * @param key    key
     * @return
     */
    R removeParams(String key);

    /**
     * 同步开始执行网络请求
     * @throws IOException
     */
    IHttpResponse excute() throws IOException;

    /**
     * 异步开始执行网络请求
     * @param callBack    回调
     */
    void excute(IResponseParser parser, IHttpCallBack callBack);

}
