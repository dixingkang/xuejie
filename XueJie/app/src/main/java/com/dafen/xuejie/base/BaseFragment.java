package com.dafen.xuejie.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dafen.xuejie.activity.MainApplication;


/**
 * 基准Fragment
 * Created by _Ms on 2016/9/21.
 */
public abstract class BaseFragment<T extends View> extends Fragment implements View.OnClickListener {

    /**
     * ApplicationContext
     */
    protected Context mContext;

    /**
     * ActivityContext
     */
    protected Activity mActivity;

    /**
     * Activity根View
     */
    protected T mRootView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = MainApplication.getContext();
        mActivity = getActivity();

        // 初始化Fragment操作
        initFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        /*
        如果initLayoutView返回为null则执行initLayoutId设置到Fragment
        并将其根View设置到RootView变量
         */
        mRootView = (T) initLayoutView();
        if (mRootView == null) {
            mRootView = (T) inflater.inflate(initLayoutId(), null);
        }

        // 初始化VIew
        initView(mRootView);

        // 初始化监听器、适配器
        initListener();

        // 更新数据
        refreshData();

        // 根据数据刷新View
        refreshView();

        return mRootView;

    }

    /**
     * 初始化Fragment
     */
    protected void initFragment() {

    }

    /**
     * 获取FragmentLayoutView·setContentView
     * 如本方法返回为null则执行initLayoutId
     *
     * @return 被setContentView的View
     */
    protected View initLayoutView() {
        return null;
    }

    /**
     * 获取FragmentLayoutId·setContentView
     * 如initLayoutView返回不为null则跳过执行此方法
     *
     * @return 被setContentView的id
     */
    protected abstract int initLayoutId();

    /**
     * 初始化View
     * @param rootView    根View
     */
    protected abstract void initView(T rootView);

    /**
     * 初始化View监听器
     */
    protected abstract void initListener();

    /**
     * 初始化View数据
     */
    protected abstract void refreshData();

    /**
     * 根据数据刷新View
     */
    protected abstract void refreshView();

    @Override
    public void onDestroy() {

        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
    }
}
