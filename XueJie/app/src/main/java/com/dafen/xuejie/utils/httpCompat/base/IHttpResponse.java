package com.dafen.xuejie.utils.httpCompat.base;

import java.io.InputStream;

/**
 * Http第三方框架二次封装·Response转换接口
 * Created by _Ms on 2016/10/10.
 */
public interface IHttpResponse {

    /**
     * 获取返回码
     * @return 返回码
     */
    int code();

    /**
     * 获取请求结果信息
     * @return 结果信息
     */
    String message();

    /**
     * 获取Head信息
     * @return Head
     */
    long contentLenth();

    IHttpHeader header();

    /**
     * 获取网络请求字节输入流
     * @return 网络请求字节输入流
     */
    InputStream byteStream();
}
