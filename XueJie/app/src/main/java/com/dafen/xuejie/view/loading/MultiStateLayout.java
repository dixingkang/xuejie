package com.dafen.xuejie.view.loading;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import java.lang.ref.SoftReference;
import java.util.LinkedHashMap;

/**
 * 多状态Layout
 * Created by _Ms on 2016/9/23.
 */
public class MultiStateLayout extends FrameLayout {

    /**
     * 状态View获取接口
     */
    public interface StateViewAdapter {
        View getMultiStateView(Context context, int position, View convertView);
    }

    /**
     * Context
     */
    protected Context mContext;

    /**
     * 缓存存取容器
     */
    private LinkedHashMap<Integer, SoftReference<View>> mCacheMap;

    /**
     * 当前状态
     */
    private int mCurrentState;

    /**
     * 状态获取器
     */
    private StateViewAdapter mViewAdapter;

    public MultiStateLayout(Context context) {
        this(context, null);
    }
    public MultiStateLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
    public MultiStateLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
    }

    /**
     * 设置View适配器
     *
     * @param adapter StateViewAdapter
     */
    public void setViewAdapter(StateViewAdapter adapter) {
        mViewAdapter = adapter;
        setState(mCurrentState);
    }

    /**
     * 设置Layout状态
     *
     * @param position 更新缓存状态
     */
    public void setState(int position) {
        initCacheMap();

        mCurrentState = position;

        View view = null;

        // 试图获取缓存View
        SoftReference<View> reference = mCacheMap.get(position);
        if (reference != null) {
            view = reference.get();
        }

        if (mViewAdapter == null) {

            throw new IllegalStateException("未设置ViewAdapter");
        }

        view = mViewAdapter.getMultiStateView(mContext, position, view);

        if (view == null) {
            throw new NullPointerException();
        }

        doCacheView(position, view);

        changeViewState(view);
    }

    /**
     * 获取当前状态
     *
     * @return 当前状态代表position
     */
    public int getState() {
        return mCurrentState;
    }

    /**
     * 更改View状态展示
     *
     * @param view 被展示的View
     */
    private void changeViewState(View view) {
        removeAllViews();
        addView(view);
    }

    /**
     * 缓存状态View
     *
     * @param position 状态position
     * @param view     状态View
     */
    private void doCacheView(int position, View view) {

        initCacheMap();

        SoftReference<View> viewReference = new SoftReference<View>(view);

        mCacheMap.put(position, viewReference);
    }

    private void initCacheMap() {
        if (mCacheMap == null) {
            mCacheMap = new LinkedHashMap<>();
        }
    }

}
