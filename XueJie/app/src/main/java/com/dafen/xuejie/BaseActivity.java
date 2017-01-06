package com.dafen.xuejie;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;

import com.dafen.xuejie.activity.MainApplication;
import com.dafen.xuejie.utils.ContentValues;
import com.dafen.xuejie.utils.LogUtils;
import com.dafen.xuejie.utils.UiUtils;
import com.dafen.xuejie.widget.AppInterface;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by Administrator on 2016/11/15.
 */
public abstract class BaseActivity extends FragmentActivity implements AppInterface {
    /**标示*/
    protected static final String TAG = "BaseActivity";

    public static SharedPreferences sharedPreferences;

    /** 所有已存在的Activity */
    protected static ConcurrentLinkedQueue<Activity> allActivity = new ConcurrentLinkedQueue<Activity>();
    /** 同时有效的界面数量 */
    protected static final int validActivityCount = 15;
    /**屏幕的宽高*/
    protected int mScreenWidth;
    protected int mScreenHeight;
    /**
     * ActivityContext,
     */
    protected Activity mActivity;

    /** Context对象 */
    protected Context context;



    protected final String HTTP_TASK_KEY = "HttpTaskKey_" + hashCode();
    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        sharedPreferences = UiUtils.getContext().getSharedPreferences(ContentValues.SP_NAME, 0);

        //禁止横屏切换
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        context = MainApplication.getContext();
        mActivity = this;
        //Activity队列管理，如果超出指定个数，获取并移除此队列的头（队列中时间最长的）。
        if (allActivity.size() >= validActivityCount) {
            Activity act = allActivity.poll();
            act.finish();// 结束
        }
        allActivity.add(this);
        printAllActivityName();
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        try {//设置布局文件、初始化布局文件中的控件、初始化控件的监听、进行数据初始化。（子类重写这些方法）
            setContentView();
            initViews();
            initListeners();
            initData();
        } catch (Exception e) {
            LogUtils.e(TAG, e.getMessage(), e);
        }
    }
    /**
     * 设置布局文件
     */
    public abstract void setContentView();
    /**
     * 初始化布局文件中的控件
     */
    public abstract void initViews();
    /**
     * 初始化控件的监听
     */
    public abstract void initListeners();
    /** 进行数据初始化
     * initData
     */
    public abstract void initData();

    /**
     * 获取当前屏幕宽高
     */
    public void getScreenWidthAndHeight(){
        //获取当前屏幕宽高
        DisplayMetrics metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);
        mScreenWidth = metric.widthPixels;
        mScreenHeight = metric.heightPixels;
    }
    /**
     * 控制台上打印 {@link BaseActivity#allActivity}
     */
    private static void printAllActivityName() {
        for (Activity activity : allActivity) {
            LogUtils.d(TAG, activity.getClass().getName());
        }
    }

//    /**
//     * 退出系统
//     */
//    public void exitAppUseDialog() {
//        MessageDialog dialog = new MessageDialog(this,
//                LOGOUT,
//                LOGOUT,
//                CANCEL,
//                LOGOUTINFO);
//        dialog.setBtn1ClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finishAll();
//            }
//        });
//        dialog.show();
//    }
    /**
     * 退出当前activity
     * @see Activity#finish()
     */
    public void finish() {
        try {
            super.finish();
            //软键盘隐藏
            if (null != this.getCurrentFocus() && null != this.getCurrentFocus().getWindowToken()) {
                InputMethodManager in = ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE));
                in.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
            }
            // 从Activity集合中清理出已结束的Activity
            if (allActivity != null && allActivity.size() > 0 && allActivity.contains(this)) {
                allActivity.remove(this);
            }
            for (Activity a : allActivity) {
                LogUtils.d("finish", a.getClass().getName());
            }
        } catch (Exception e) {
            LogUtils.e(TAG, e.getMessage(), e);
        }
    }
    /**
     * 结束所有activity
     */
    public static void finishAll() {
        // 结束Activity
        try {
            for (Activity act : allActivity) {
                allActivity.remove(act);
                act.finish();
                printAllActivityName();
            }
        } catch (Exception e) {
            LogUtils.e(TAG, e.getMessage(), e);
        }
    }
    /**
     * 判断activity是否已经结束
     * @param act
     * @return
     */
    public static boolean contains(Class<?> act) {
        // 结束Activity
        try {
            for (Activity ele : allActivity) {
                if (ele.getClass().getName().equals(act.getName())) {
                    return Boolean.TRUE;
                }
            }
        } catch (Exception e) {
            LogUtils.e(TAG, e.getMessage(), e);
        }
        return Boolean.FALSE;
    }
}
