package com.dafen.xuejie.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.dafen.xuejie.R;


/**
 * time: 2016/10/28
 * description:
 *
 * @author F_Ms
 */
public class LoadingView2 extends FrameLayout {

    public interface LoadingViewAdapter {
        /**
         * 创建未知页面
         */
        View getUnknowView();

        /**
         * 创建加载中页面
         */
        View getLoadingView();

        /**
         * 创建失败页面
         */
        View getFailView();

        /**
         * 创建结果为空页面
         */
        View getEmptyView();
    }

    /**
     * 简单LoadingAdapter实现类
     */
    public class AbsLoadingViewAdapter implements LoadingViewAdapter {

        public View getUnknowView() {return inflate(mContext, R.layout.state_unknow, null);}

        public View getLoadingView() {return inflate(mContext, R.layout.state_loading, null);}

        public View getFailView() {
            return inflate(mContext, R.layout.state_error, null);
        }

        public View getEmptyView() {
            return inflate(mContext, R.layout.state_empty, null);
        }

    }

    public LoadingView2(Context context) {this(context, null);}
    public LoadingView2(Context context, AttributeSet attrs) {this(context, attrs, 0);}
    public LoadingView2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init();
    }

    /**
     * 状态枚举
     */
    public enum STATE {
        LOADING, // 加载中
        EMPTY, // 空
        FAIL, // 失败
        SUCCESS, // 成功
        UNKNOW // 未知状态
    }

    private Context mContext;
    private LoadingViewAdapter mAdapter;

    /**
     * 初始化View
     */
    private void init() {
        setAdapter(new AbsLoadingViewAdapter());
        setState(STATE.LOADING);
    }

    /**
     * 设置View状态
     *
     * @param state 状态
     */
    public void setState(STATE state) {
        if (state == null) {
            return;
        }

        View view = null;
        switch (state) {
            case EMPTY:
                view = mAdapter.getEmptyView();
                break;
            case FAIL:
                view = mAdapter.getFailView();
                break;
            case LOADING:
                view = mAdapter.getLoadingView();
                break;
            case UNKNOW:
                view = mAdapter.getUnknowView();
                break;
            case SUCCESS:
                setVisibility(View.INVISIBLE);
                break;
        }

        if (view != null) {
            setVisibility(View.VISIBLE);
            addToViewGroup(view);
        }
    }

    /**
     * 设置各状态适配器
     *
     * @param adapter
     */
    public void setAdapter(LoadingViewAdapter adapter) {
        if (adapter == null) {
            throw new NullPointerException();
        }

        mAdapter = adapter;
    }

    /**
     * 添加View到布局
     *
     * @param view
     */
    private void addToViewGroup(final View view) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                LoadingView2.super.removeAllViewsInLayout();

                ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                );

                LoadingView2.super.addView(view, -1, params);
            }
        };

        if (Thread.currentThread().getId() == 1) {
            runnable.run();
        } else {
            post(runnable);
        }
    }

    /**
     * 移除子View
     */
    private void refreshView() {
        if (getChildCount() == 0) {
            setState(STATE.LOADING);
        }
    }

    @Override
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        throw new IllegalStateException("LoadingView can't add child view");
    }

    @Override
    public void removeView(View view) {
        super.removeView(view);
        refreshView();
    }

    @Override
    public void removeViews(int start, int count) {
        super.removeViews(start, count);
        refreshView();
    }

    @Override
    public void removeViewAt(int index) {
        super.removeViewAt(index);
        refreshView();
    }

    @Override
    public void removeViewsInLayout(int start, int count) {
        super.removeViewsInLayout(start, count);
        refreshView();
    }

    @Override
    public void removeViewInLayout(View view) {
        super.removeViewInLayout(view);
        refreshView();
    }

    @Override
    public void removeAllViews() {
        super.removeAllViews();
        refreshView();
    }

    @Override
    public void removeAllViewsInLayout() {
        super.removeAllViewsInLayout();
        refreshView();
    }

}
