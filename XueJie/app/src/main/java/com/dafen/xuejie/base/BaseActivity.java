package com.dafen.xuejie.base;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Toast;

import com.dafen.xuejie.utils.ContentValues;
import com.dafen.xuejie.utils.UiUtils;


/**
 * @author song
 */
public abstract class BaseActivity extends FragmentActivity implements OnClickListener {
    public static SharedPreferences sharedPreferences;
    public static int screenWidth;
    public static int screenHeight;
    public static String cityname;
    private WindowManager windowManager;
    public Context context;
    protected Activity mActivity;
    public Bundle savedInstanceState;
    private boolean isNeedFresh;
    public static double wei;
    public static double jing;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.savedInstanceState = savedInstanceState;

        sharedPreferences = UiUtils.getContext().getSharedPreferences(ContentValues.SP_NAME, 0);
        windowManager = (WindowManager) UiUtils.getContext().getSystemService(Context.WINDOW_SERVICE);
        screenWidth = windowManager.getDefaultDisplay().getWidth();
        screenHeight = windowManager.getDefaultDisplay().getHeight();
        context = UiUtils.getContext();
        mActivity=this;
        setContentView(getLayoutResId());

        init();
        initView();
        initListener();
        initData();

    }

    /**
     * 获取当前界面的布局
     *
     * @return
     */
    public abstract int getLayoutResId();

    /**
     * 初始化Activity
     */
    protected void init() {
    }

    /**
     * 查找控件
     */
    public abstract void initView();

    /**
     * 给控件添加监听
     */
    public abstract void initListener();

    /**
     * 初始化数据
     * 给控件填充内容
     */
    public abstract void initData();

    /**
     * 子类界面处理按钮的单击事件
     *
     * @param v
     */
    public void onInnerClick(View v) {

    }

    /**
     * 弹出吐司
     */
    public void showToast(String msg) {
        Toast.makeText(getApplicationContext(),
                msg, Toast.LENGTH_SHORT).show();
    }

    public void showToast(int msgResId) {
        Toast.makeText(getApplicationContext(),
                msgResId, Toast.LENGTH_SHORT).show();
    }
}