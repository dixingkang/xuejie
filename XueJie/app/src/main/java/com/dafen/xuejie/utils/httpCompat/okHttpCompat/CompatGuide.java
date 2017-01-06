package com.dafen.xuejie.utils.httpCompat.okHttpCompat;


import com.dafen.xuejie.utils.httpCompat.base.IHttpBodyRequest;
import com.dafen.xuejie.utils.httpCompat.base.IHttpCompatGuide;
import com.dafen.xuejie.utils.httpCompat.base.IHttpRequest;
import com.lzy.okhttputils.OkHttpUtils;

/**
 * OkHttpUtils引导初始化类
 * Created by _Ms on 2016/12/12.
 */

public class CompatGuide implements IHttpCompatGuide {

    @Override
    public IHttpRequest createGetRequest(String url) {
        return new CompatRequest<CompatRequest>(OkHttpUtils.get(url));
    }

    @Override
    public IHttpRequest createHeadRequest(String url) {
        return new CompatRequest<CompatRequest>(OkHttpUtils.head(url));
    }

    @Override
    public IHttpBodyRequest createPostRequest(String url) {
        return new CompatBodyRequest(OkHttpUtils.post(url));
    }

    @Override
    public IHttpBodyRequest createPutRequest(String url) {
        return new CompatBodyRequest(OkHttpUtils.put(url));
    }

    @Override
    public IHttpBodyRequest createOptionsRequest(String url) {
        return new CompatBodyRequest(OkHttpUtils.options(url));
    }

    @Override
    public IHttpBodyRequest createDeleteRequest(String url) {
        return new CompatBodyRequest(OkHttpUtils.delete(url));
    }
}
