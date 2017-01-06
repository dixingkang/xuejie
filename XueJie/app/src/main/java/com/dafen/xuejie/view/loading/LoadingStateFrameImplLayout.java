package com.dafen.xuejie.view.loading;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.dafen.xuejie.R;


/**
 * 已实现多种加载状态填充的加载状态Layout
 * Created by _Ms on 2016/10/20.
 */
public abstract class LoadingStateFrameImplLayout extends AbsLoadingStateFrame {

    public LoadingStateFrameImplLayout(Context context) {super(context);}
    public LoadingStateFrameImplLayout(Context context, AttributeSet attrs) {super(context, attrs);}
    public LoadingStateFrameImplLayout(Context context, AttributeSet attrs, int defStyleAttr) {super(context, attrs, defStyleAttr);}

    /**
     * 获取成功页面
     * @param convertView
     * @return
     */
    protected abstract View getSucessView(View convertView);

    /**
     * 创建未知页面
     * @param convertView
     * @return
     */
    protected View getUnknowView(View convertView) {
        if (convertView != null) {
            return convertView;
        }

        return inflate(mContext, R.layout.state_unknow, null);
    }

    /**
     * 创建加载中页面
     * @param convertView
     * @return
     */
    protected View getLoadingView(View convertView) {
        if (convertView != null) {
            return convertView;
        }

        return inflate(mContext, R.layout.state_loading, null);
    }

    /**
     * 创建失败页面
     *
     * @param convertView
     * @return
     */
    protected View getFailView(View convertView) {
        if (convertView != null) {
            return convertView;
        }

        View view = inflate(mContext, R.layout.state_error, null);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadData();
            }
        });

        return view;
    }

    /**
     * 加载数据
     */
    protected abstract void loadData();

    /**
     * 创建结果为空页面
     *
     * @param convertView
     * @return
     */
    protected View getEmptyView(View convertView) {
        if (convertView != null) {
            return convertView;
        }

        return inflate(mContext, R.layout.state_empty, null);
    }


}
