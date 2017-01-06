package com.dafen.xuejie.utils.httpCompat.plugin;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;


import com.dafen.xuejie.R;
import com.dafen.xuejie.utils.CheckUtils;
import com.dafen.xuejie.utils.UiUtils;
import com.dafen.xuejie.utils.httpCompat.base.AbsHttpCallBackPlugin;


/**
 * Http请求插件·异步请求对话框
 * Created by User on 2016/10/8.
 */
public class DialogTipCallBackPlugin extends AbsHttpCallBackPlugin {

    /**
     * Context
     */
    private Context mContext;

    /**
     * 请求对话框显示内容
     */
    private String mDialogMessage;

    /**
     * 网络连接中对话框对象
     */
    private AlertDialog mDialog;

    /**
     * 简单构造
     *
     * @param activity ActivityContext
     */
    public DialogTipCallBackPlugin(Activity activity) {
        this(activity, UiUtils.getString(R.string.default_network_loading));
    }

    /**
     * 构造
     *
     * @param activity      Context
     * @param dialogMessage 加载中对话框内容
     */
    public DialogTipCallBackPlugin(Activity activity, String dialogMessage) {
        CheckUtils.notNull(activity, dialogMessage);

        /*
        参数记录
         */
        mContext = activity;
        mDialogMessage = dialogMessage;
    }

    @Override
    public void onBefore() {

      /*
        显示提示对话框
         */
        if (mDialog != null) {
            if (mDialog.isShowing()) {
                mDialog.dismiss();
            }
        }

        mDialog = new AlertDialog.Builder(mContext)
                .setMessage(mDialogMessage)
                .setCancelable(false) // 点击其他位置不关闭dialog
                .setOnKeyListener(
                        new DialogInterface.OnKeyListener() {
                            @Override
                            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {

                                // 点击返回键关闭dialog
                                switch (keyCode) {
                                    case KeyEvent.KEYCODE_BACK:
                                        if (mDialog.isShowing()) {
                                            mDialog.dismiss();
                                        }
                                        break;
                                }
                                return true;
                            }
                        }
                )
                .create();


        mDialog.show();
    }

    @Override
    public void onAfter() {

        /*
        关闭提示对话框
         */
        if (mDialog != null) {

            if (mDialog.isShowing()) {

                mDialog.dismiss();
            }

            mDialog = null;
        }
    }

    @Override
    public int hashCode() {
        return mDialogMessage.hashCode();
    }

}
