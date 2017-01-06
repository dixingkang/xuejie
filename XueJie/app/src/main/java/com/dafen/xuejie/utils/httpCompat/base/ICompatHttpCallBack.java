package com.dafen.xuejie.utils.httpCompat.base;

/**
 * 适配CallBack实现接口
 * Created by _Ms on 2016/12/12.
 */
public interface ICompatHttpCallBack<T> {

    void setCompatCallBack(IHttpCallBack<T> callBack);
}
