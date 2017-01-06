package com.dafen.xuejie.utils.httpCompat.okHttpCompat;

import com.dafen.xuejie.utils.httpCompat.base.IHttpBodyRequest;
import com.lzy.okhttputils.request.BaseBodyRequest;

import java.io.File;


/**
 * OkHttpCompatBodyRequest
 * Created by _Ms on 2016/12/25.
 */
public class CompatBodyRequest extends CompatRequest<IHttpBodyRequest> implements IHttpBodyRequest {

    private BaseBodyRequest mBodyRequest;

    /**
     * 构造
     *
     * @param request 被兼容Request
     */
    public CompatBodyRequest(BaseBodyRequest request) {
        super(request);

        mBodyRequest = request;
    }

    @Override
    public IHttpBodyRequest addParams(String key, File file) {
        mBodyRequest.params(key, file);

        return this;
    }
}
