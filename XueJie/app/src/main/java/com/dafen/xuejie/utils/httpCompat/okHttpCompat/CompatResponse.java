package com.dafen.xuejie.utils.httpCompat.okHttpCompat;

import com.dafen.xuejie.utils.CheckUtils;
import com.dafen.xuejie.utils.httpCompat.base.IHttpHeader;
import com.dafen.xuejie.utils.httpCompat.base.IHttpResponse;

import java.io.InputStream;


/**
 * 三方网络框架OkHttpResponse - response转换类
 * Created by _Ms on 2016/10/10.
 */
public class CompatResponse implements IHttpResponse {

    /**
     * OkHttpResponse, 外部传入
     */
    private okhttp3.Response mResponse;

    public CompatResponse(okhttp3.Response response) {
        CheckUtils.notNull(response);

        mResponse = response;
    }

    @Override
    public int code() {
        return mResponse.code();
    }

    @Override
    public String message() {
        return mResponse.message();
    }

    @Override
    public long contentLenth() {
        return mResponse.body().contentLength();
    }

    @Override
    public IHttpHeader header() {
        return new CompatHeader(mResponse.headers());
    }

    @Override
    public InputStream byteStream() {
        return mResponse.body().byteStream();
    }
}
