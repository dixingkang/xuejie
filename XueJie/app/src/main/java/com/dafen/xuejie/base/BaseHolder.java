package com.dafen.xuejie.base;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.dafen.xuejie.utils.CheckUtils;


/**
 * Holder 基类
 * Created by _Ms on 2016/9/22.
 */
public abstract class BaseHolder<RootView extends View> implements View.OnClickListener, IBaseHolderDoIt {

    /**
     * 根View
     */
    protected RootView mRootView;

    /**
     * ApplicationContext
     */
    protected Context mContext;

    /**
     * DoIt
     */
    protected IBaseHolderDoIt mDoIt;

    /**
     * 外部参数
     */
    protected Bundle mParam;

    public BaseHolder(Context context) {
        this(context, null);
    }
    public BaseHolder(Context context, IBaseHolderDoIt doIt) {this(context, doIt, null);}
    public BaseHolder(Context context, IBaseHolderDoIt doIt, Bundle param) {
        mDoIt = doIt;
        mParam = param;
        CheckUtils.notNull(context);

        mContext = context;

        onInitHolder();

        /*
        如果initLayoutView返回为null则执行initLayoutId设置到
        并将其根View设置到RootVIew变量
         */
        mRootView = (RootView) initLayoutView();
        if (mRootView == null) {
            mRootView = (RootView) View.inflate(mContext, initLayoutId(), null);
        }

        mRootView.setTag(this);

        // 准备View
        onReadyView(mRootView);

        // 初始化VIew
        onInitView();

        // 初始化监听器、适配器
        onInitListener();

        // 初始化数据
        onInitData();

        // 刷新数据
        onRefreshData();

        // 刷新View
        onRefreshView();

    }

    /**
     * 获取Holder包含View
     * @return HolderView
     */
    public final RootView getContentView() {
        return mRootView;
    }

    /**
     * 初始化Holder
     */
    protected abstract void onInitHolder();

    /**
     * 获取HolderLayoutView·setContentView
     * 如本方法返回为null则执行initLayoutId
     *
     * @return 返回被设置的View
     */
    protected View initLayoutView() {
        return null;
    }

    /**
     * 获取HolderLayoutId·setContentView
     * 如initLayoutView返回不为null则跳过执行此方法
     *
     * @return 被setContentView的id
     */
    protected abstract int initLayoutId();

    /**
     * 准备View
     * @param rootView
     */
    protected abstract void onReadyView(RootView rootView);

    /**
     * 初始化View
     */
    protected abstract void onInitView();

    /**
     * 初始化View监听器
     */
    protected abstract void onInitListener();

    /**
     * 初始化数据
     */
    protected abstract void onInitData();

    /**
     * 刷新数据
     */
    protected abstract void onRefreshData();

    /**
     * 根据数据刷新View
     */
    protected abstract void onRefreshView();

    /**
     * 获取传入的Holder参数
     * @return
     */
    protected final Bundle getArguments() {
        return mParam;
    }

    @Override
    public void onClick(View v) {}

}
