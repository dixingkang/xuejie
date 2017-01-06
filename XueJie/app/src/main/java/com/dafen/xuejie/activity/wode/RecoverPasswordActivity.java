package com.dafen.xuejie.activity.wode;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.dafen.xuejie.BaseActivity;
import com.dafen.xuejie.R;
import com.dafen.xuejie.bean.RecoverPassBean;
import com.dafen.xuejie.bean.RegisterBean;
import com.dafen.xuejie.utils.ContentValues;
import com.dafen.xuejie.utils.ToastUtils;
import com.dafen.xuejie.utils.httpCompat.Http;
import com.dafen.xuejie.utils.httpCompat.base.BaseHttpCallBack;
import com.dafen.xuejie.utils.httpCompat.plugin.DialogTipCallBackPlugin;
import com.dafen.xuejie.utils.httpCompat.plugin.ExceptionToastCallBackPlugin;
import com.dafen.xuejie.utils.httpCompat.response_parser.GsonResponseParser;

//修改密码
public class RecoverPasswordActivity extends BaseActivity  implements View.OnClickListener{
    private LinearLayout ll_verify;
    private RelativeLayout rl_back;
    private EditText et_tel,et_verify,et_password,et_again_password;
    private Button but_verify;

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_recover_password);
    }

    @Override
    public void initViews() {
        rl_back = (RelativeLayout) findViewById(R.id.rl_back);
        ll_verify = (LinearLayout) findViewById(R.id.ll_verify);
        et_again_password = (EditText) findViewById(R.id.et_again_password);
        et_verify = (EditText) findViewById(R.id.et_verify);
        et_password = (EditText) findViewById(R.id.et_password);
        et_tel = (EditText) findViewById(R.id.et_tel);
        but_verify = (Button) findViewById(R.id.but_verify);
    }

    @Override
    public void initListeners() {
        but_verify.setOnClickListener(this);
        rl_back.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
            switch (v.getId()){
                case R.id.rl_back:
                    finish();
                    break;

                case R.id.but_verify:
                    getNetWork("","'");
                    break;
            }
    }

    public void getNetWork(String tel, String username) {
        Http
                .post(ContentValues.BASE_COMMON_pass)
                .addParams("Uid", "16")
                .addParams("user_pass", tel)
                .addParams("password", username)
                .excute(
                        new GsonResponseParser(RecoverPassBean.class),
                        new BaseHttpCallBack<RecoverPassBean>() {
                            @Override
                            public void onSucess(RecoverPassBean result) {
                                super.onSucess(result);
                                if ("success".equals(result.getStatus())) {

                                }
                                ToastUtils.showLong(result.getExplain());

                            }

                        }.addPlugin(new ExceptionToastCallBackPlugin(context))
                                .addPlugin(new DialogTipCallBackPlugin(mActivity))
                );


    }
}
