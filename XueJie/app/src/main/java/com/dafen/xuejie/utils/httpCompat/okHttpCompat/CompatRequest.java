package com.dafen.xuejie.utils.httpCompat.okHttpCompat;


import com.dafen.xuejie.utils.CheckUtils;
import com.dafen.xuejie.utils.httpCompat.base.AbsHttpRequest;
import com.dafen.xuejie.utils.httpCompat.base.IHttpCallBack;
import com.dafen.xuejie.utils.httpCompat.base.IHttpRequest;
import com.dafen.xuejie.utils.httpCompat.base.IHttpResponse;
import com.lzy.okhttputils.request.BaseRequest;

import java.io.IOException;

import okhttp3.Response;


/**
 * 请求配置兼容类 OKHttpUtils
 * Created by _Ms on 2016/10/11.
 */
public class CompatRequest<R extends IHttpRequest> extends AbsHttpRequest<R> {

    /**
     * 兼容Request
     */
    private BaseRequest mRequest;

    /**
     * 构造
     * @param request    被兼容Request
     */
    public CompatRequest(BaseRequest request) {
        CheckUtils.notNull(request);

        mRequest = request;
    }

    @Override
    public R url(String url) {
        mRequest.url(url);
        return (R) this;
    }

    @Override
    public String url() {
        return mRequest.getUrl();
    }

    @Override
    public String method() {
        return mRequest.getMethod();
    }

    @Override
    public R addHeader(String key, String value) {
        mRequest.headers(key, value);
        return (R) this;
    }

    @Override
    public R removeHeader(String key) {
        mRequest.removeHeader(key);
        return (R) this;
    }

    @Override
    public R removeAllHeader() {
        mRequest.removeAllHeaders();
        return (R) this;
    }

    /**
     * 给请求添加POST参数
     * @param key params key
     *  @param value params value
     * @return CompatRequest 链式编程
     */
    public R addParams(String key, CharSequence value) {
        CheckUtils.notNull(key, value);

        mRequest.params(key, value.toString());
        return (R) this;
    }

    @Override
    public R removeParams(String key) {
        mRequest.removeParam(key);
        return (R) this;
    }

    /**
     * 同步执行网络请求
     * @throws IOException
     */
    public IHttpResponse excute() throws IOException {
        Response response = mRequest.execute();

        return new CompatResponse(response);
    }

    @Override
    protected void excuteRun(IHttpCallBack callBack) {

        CompatCallBack compatCallBack = new CompatCallBack();
        compatCallBack.setCompatCallBack(callBack);

        mRequest.execute(compatCallBack);
    }

}
