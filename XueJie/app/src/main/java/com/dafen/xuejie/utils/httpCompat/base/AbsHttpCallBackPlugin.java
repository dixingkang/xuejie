package com.dafen.xuejie.utils.httpCompat.base;

/**
 * HttpPlugin适配器
 * Created by _Ms on 2016/12/9.
 */
public class AbsHttpCallBackPlugin implements IHttpCallBackPlugin {

    @Override
    public void onBefore() {}

    @Override
    public void onSucess(Object result) {}

    @Override
    public void onError(Exception e) {}

    @Override
    public void onAfter() {}

}
