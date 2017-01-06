package com.dafen.xuejie.base;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

/**
 * 简单Holder基类
 * Created by _Ms on 2016/10/19.
 */
public abstract class BaseSimpleHolder<RootView extends View> extends BaseHolder<RootView> {

    public BaseSimpleHolder(Context context) {
        super(context);
    }
    public BaseSimpleHolder(Context context, IBaseHolderDoIt doIt) {
        super(context, doIt);
    }
    public BaseSimpleHolder(Context context, IBaseHolderDoIt doIt, Bundle param) {super(context, doIt, param);}

    @Override
    protected void onInitHolder() {}

    @Override
    protected void onInitView() {}

    @Override
    protected void onInitListener() {}

    @Override
    protected void onRefreshData() {}

    @Override
    protected void onRefreshView() {}
}
