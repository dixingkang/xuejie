package com.dafen.xuejie.utils.httpCompat.okHttpCompat;

import android.support.annotation.Nullable;

import com.dafen.xuejie.utils.CheckUtils;
import com.dafen.xuejie.utils.httpCompat.base.ICompatHttpCallBack;
import com.dafen.xuejie.utils.httpCompat.base.IHttpCallBack;
import com.lzy.okhttputils.callback.AbsCallback;
import com.lzy.okhttputils.request.BaseRequest;

import java.io.IOException;

import okhttp3.Call;


/**
 * 三方网络请求框架二次封装 兼容回调
 * OkHttpUtils
 * Created by _Ms on 2016/10/10.
 */
public class CompatCallBack<T> extends AbsCallback<T> implements ICompatHttpCallBack<T> {

    private IHttpCallBack<T> mCompatCallBack;

    @Override
    public void setCompatCallBack(IHttpCallBack<T> callBack) {
        CheckUtils.notNull(callBack);

        mCompatCallBack = callBack;
    }

    @Override
    public final void onBefore(BaseRequest request) { }

    @Override
    public final T parseNetworkResponse(okhttp3.Response response) throws Exception {

        CompatResponse response2 = new CompatResponse(response);
        return mCompatCallBack.getResponseParser().parse(response2);
    }

    @Override
    public final void onSuccess(T t, Call call, okhttp3.Response response) {
        mCompatCallBack.onSucess(t);
    }
    @Override
    public final void onError(Call call, okhttp3.Response response, Exception e) {
        mCompatCallBack.onError(e);
    }
    @Override
    public final void onAfter(@Nullable T t, @Nullable Exception e) {
        mCompatCallBack.onAfter();
    }


    @Override
    public final void parseNetworkFail(Call call, IOException e) {super.parseNetworkFail(call, e);}
    @Override
    public final void onCacheSuccess(T t, Call call) {super.onCacheSuccess(t, call);}
    @Override
    public final void onCacheError(Call call, Exception e) {super.onCacheError(call, e);}
    @Override
    public final void upProgress(long currentSize, long totalSize, float progress, long networkSpeed) {super.upProgress(currentSize, totalSize, progress, networkSpeed);}
    @Override
    public final void downloadProgress(long currentSize, long totalSize, float progress, long networkSpeed) {super.downloadProgress(currentSize, totalSize, progress, networkSpeed);}
}
