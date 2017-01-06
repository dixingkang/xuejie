package com.dafen.xuejie.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.dafen.xuejie.R;
import com.dafen.xuejie.utils.Debug;


/**
 * time: 2016/10/28
 * description:
 *
 * @author F_Ms
 */
public class LoadingView extends FrameLayout {

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

        public View getUnknowView() {
            return inflate(mContext, R.layout.state_unknow, null);
        }

        public View getLoadingView() {
            return inflate(mContext, R.layout.state_loading, null);
        }

        public View getFailView() {
            return inflate(mContext, R.layout.state_error, null);
        }

        public View getEmptyView() {
            return inflate(mContext, R.layout.state_empty, null);
        }

    }

    public LoadingView(Context context) {this(context, null);}
    public LoadingView(Context context, AttributeSet attrs) {this(context, attrs, 0);}
    public LoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init();
    }

    /**
     * 状态枚举
     */
    public enum STATE {
        LOADING, // 加载中
        SUCCESS, // 成功
        EMPTY, // 空
        FAIL, // 失败
        UNKNOW // 未知状态
    }

    private Context mContext;
    private LoadingViewAdapter mAdapter;
    private View mChildView;
    private ViewGroup.LayoutParams mParams;

    /**
     * 初始化View
     */
    private void init() {
        setAdapter(new AbsLoadingViewAdapter());
        setState(STATE.LOADING);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {

        Debug.e("%s", "protected void onLayout(boolean changed, int left, int top, int right, int bottom) {");

        getChildAt(0).layout(0, 0, 300, 300);

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
            case SUCCESS:
                view = mChildView;
                if (view == null) {
                    view = new TextView(mContext);
                    ((TextView) view).setText(R.string.success);
                }
                break;
            case UNKNOW:
                view = mAdapter.getUnknowView();
                break;
        }

        addToViewGroup(view);
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
                LoadingView.super.removeAllViewsInLayout();

                ViewGroup.LayoutParams params = mParams;
                if (params == null) {
                    params = new ViewGroup.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT
                    );
                }

                LoadingView.super.addView(view, -1, params);
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
    private void removeChildView() {

        if (getChildCount() == 0) {
            setState(STATE.LOADING);
            mChildView = null;
            mParams = null;
        }
    }


    @Override
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        Debug.e("%s:", "public void addView(View child, int index, ViewGroup.LayoutParams params) {");
        if (mChildView != null) {
            throw new IllegalStateException("LoadingView can host only one direct child");
        }
        mChildView = child;
        super.addView(child, index, params);
    }

    @Override
    public void removeView(View view) {
        Debug.e("%s:", "public void removeView(View view) {");
        super.removeView(view);
        removeChildView();
    }

    @Override
    public void removeViews(int start, int count) {
        Debug.e("%s:", "public void removeViews(int start, int count) {");
        super.removeViews(start, count);
        removeChildView();
    }

    @Override
    public void removeViewAt(int index) {
        Debug.e("%s:", "public void removeViewAt(int start, int count) {");
        super.removeViewAt(index);
        removeChildView();
    }

    @Override
    public void removeViewsInLayout(int start, int count) {
        Debug.e("%s:", "public void removeViewsInLayout(int start, int count) {");
        super.removeViewsInLayout(start, count);
        removeChildView();
    }

    @Override
    public void removeViewInLayout(View view) {
        Debug.e("%s:", "public void removeViewInLayout(View view) {");
        super.removeViewInLayout(view);
        removeChildView();
    }

    @Override
    public void removeAllViews() {
        Debug.e("%s:", "public void removeAllViews() {");
        super.removeAllViews();
        removeChildView();
    }

    @Override
    public void removeAllViewsInLayout() {
        Debug.e("%s:", "public void removeAllViewsInLayout() {");
        super.removeAllViewsInLayout();
        removeChildView();
    }

}
