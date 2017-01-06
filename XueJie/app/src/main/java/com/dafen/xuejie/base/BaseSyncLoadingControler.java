package com.dafen.xuejie.base;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;

import com.dafen.xuejie.utils.ThreadUtils;
import com.dafen.xuejie.view.loading.LoadingStateFrameImplLayout;
import com.dafen.xuejie.view.loading.LoadingStateFrameLayout;

/**
 * LoadingHolder基类
 * Created by _Ms on 2016/10/19.
 */
public abstract class BaseSyncLoadingControler extends BaseHolder<LoadingStateFrameImplLayout> {

    /**
     * 当控制器请求数据后
     */
    public interface OnSuccessHolderCreated {
        void onCreateSuccess(BaseHolder successHolder);
    }

    private class SelfMulitLayout extends LoadingStateFrameImplLayout {

        public SelfMulitLayout(Context context) {super(context);}
        public SelfMulitLayout(Context context, AttributeSet attrs) {super(context, attrs);}
        public SelfMulitLayout(Context context, AttributeSet attrs, int defStyleAttr) {super(context, attrs, defStyleAttr);}

        @Override
        protected View getSucessView(View convertView) {

            BaseHolder convertHolder = null;

            if (convertView != null) {
                convertHolder = (BaseHolder) convertView.getTag();
            }

            convertHolder = BaseSyncLoadingControler.this.getSucessView(convertHolder);

            if (mOnSuccessHolderCreated != null) {
                mOnSuccessHolderCreated.onCreateSuccess(convertHolder);
            }

            return convertHolder.getContentView();
        }

        @Override
        protected void loadData() {
            reLoadData(true, FLAG_CREATE);
        }
    }

    /**
     * 创建
     */
    public static final int FLAG_CREATE = 0;

    private OnSuccessHolderCreated mOnSuccessHolderCreated;

    public BaseSyncLoadingControler(Context context) {
        super(context);
    }
    public BaseSyncLoadingControler(Context context, IBaseHolderDoIt doIt) {super(context, doIt);}
    public BaseSyncLoadingControler(Context context, IBaseHolderDoIt doIt, Bundle param) {super(context, doIt, param);}

    /**
     * 重新加载数据
     * @param isShowLoading    是否显示加载中框
     */
    public final void reLoadData(boolean isShowLoading, final int flag) {

        if (isShowLoading) {
            mRootView.setState(LoadingStateFrameLayout.STATE.LOADING);
        }

        ThreadUtils
                .runOnBackground(new Runnable() {
                    @Override
                    public void run() {
                        /*
                        子线程请求数据
                         */
                        onLoadData(flag);
                        final LoadingStateFrameLayout.STATE state = getLocalState();

                        /*
                        主线程请求更新UI
                         */
                        ThreadUtils.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mRootView.setState(state);
                            }
                        });
                    }
                });

    }

    /**
     * 加载数据, 同步获取, 子线程
     * @param flag    加载标记
     */
    protected abstract void onLoadData(int flag);

    /**
     * 获取状态，在加载数据后回调，将根据此状态对页面进行调整
     * @return
     */
    protected abstract LoadingStateFrameLayout.STATE getLocalState();

    /**
     * 获取成功状态View
     * @param convertHolder
     * @return
     */
    protected abstract BaseHolder getSucessView(BaseHolder convertHolder);

    /**
     * 设置当真正Holder创建完成后的Holder
     * @param onSuccessHolderCreated
     */
    public void setOnSuccessHolderCreated(OnSuccessHolderCreated onSuccessHolderCreated) {
        this.mOnSuccessHolderCreated = onSuccessHolderCreated;
    }

    @Override
    protected final void onInitHolder() {}

    @Override
    protected final View initLayoutView() {return new SelfMulitLayout(mContext);}

    @Override
    protected final int initLayoutId() {return 0;}

    @Override
    protected final void onReadyView(LoadingStateFrameImplLayout loadingStateFrameImplLayout) {}

    @Override
    protected final void onInitView() {}

    @Override
    protected final void onInitListener() {}

    @Override
    protected final void onInitData() {}

    @Override
    protected final void onRefreshData() {
        reLoadData(true, FLAG_CREATE);
    }

    @Override
    protected final void onRefreshView() {}

    @Override
    public Object doIt(int flag, Object o) {
        return null;
    }
}
