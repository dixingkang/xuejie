package com.dafen.xuejie.utils.httpCompat.plugin;

import android.content.Context;
import android.widget.Toast;

import com.dafen.xuejie.R;
import com.dafen.xuejie.utils.CheckUtils;
import com.dafen.xuejie.utils.UiUtils;
import com.dafen.xuejie.utils.httpCompat.base.AbsHttpCallBackPlugin;


/**
 * Http请求插件·请求错误时提示
 * Created by User on 2016/10/8.
 */
public class ExceptionToastCallBackPlugin extends AbsHttpCallBackPlugin {

    /**
     * Context
     */
    private Context mContext;


    /**
     * 连接错误时提示内容
     */
    private String mErrorMessage;

    /**
     * 简单构造
     *
     * @param context             Context
     */
    public ExceptionToastCallBackPlugin(Context context) {
        this(context, UiUtils.getString(R.string.default_network_exception));
    }


    /**
     * 构造
     *
     * @param context             Context
     * @param errorMessage        错误提示内容
     */
    public ExceptionToastCallBackPlugin(Context context, String errorMessage) {

        CheckUtils.notNull(context, errorMessage);

        /*
        参数记录
         */
        mContext = context;
        mErrorMessage = errorMessage;
    }

    @Override
    public void onError(Exception e) {
        Toast.makeText(mContext, mErrorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public int hashCode() {
        return mErrorMessage.hashCode();
    }

}
