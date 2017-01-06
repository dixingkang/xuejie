package com.dafen.xuejie.activity.wode;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.dafen.xuejie.BaseActivity;
import com.dafen.xuejie.R;
import com.dafen.xuejie.bean.RegisterBean;
import com.dafen.xuejie.utils.ContentValues;
import com.dafen.xuejie.utils.MatchUtils;
import com.dafen.xuejie.utils.TimeButton;
import com.dafen.xuejie.utils.ToastUtils;
import com.dafen.xuejie.utils.httpCompat.Http;
import com.dafen.xuejie.utils.httpCompat.base.BaseHttpCallBack;
import com.dafen.xuejie.utils.httpCompat.plugin.DialogTipCallBackPlugin;
import com.dafen.xuejie.utils.httpCompat.plugin.ExceptionToastCallBackPlugin;
import com.dafen.xuejie.utils.httpCompat.response_parser.GsonResponseParser;

//注册页面
public class RegisterActivity extends BaseActivity implements View.OnClickListener {
    private EditText et_tel, et_verify, et_password, et_name;
    private TimeButton tv_get_ensure_num;
    private RelativeLayout rl_back;
    private Button but_register;

    private String tel;
    private String name;
    private String pass;
    private String toPass;

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_register);
    }

    @Override
    public void initViews() {
        et_name = (EditText) findViewById(R.id.et_name);
        et_tel = (EditText) findViewById(R.id.et_tel);
        et_verify = (EditText) findViewById(R.id.et_verify);
        et_password = (EditText) findViewById(R.id.et_password);

        rl_back = (RelativeLayout) findViewById(R.id.rl_back);
        tv_get_ensure_num = (TimeButton) findViewById(R.id.tv_get_ensure_num);

        but_register = (Button) findViewById(R.id.but_register);
    }

    @Override
    public void initListeners() {
        but_register.setOnClickListener(this);
        tv_get_ensure_num.setOnClickListener(this);
        rl_back.setOnClickListener(this);
        tv_get_ensure_num.setEnabled(false);
        tv_get_ensure_num.setTextBefore("获取验证码");
        tv_get_ensure_num.setTextColor(getResources().getColor(R.color.divider));
        //手机号文本监听
        et_tel.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                tv_get_ensure_num.setEnabled(false);
                tv_get_ensure_num.setTextColor(getResources().getColor(R.color.divider));
                if (MatchUtils.isEmpty(editable.toString())) {
                    ToastUtils.showText("手机号码不能为空");
                    return;
                } else {
                    String pwd = editable.toString();
                    if (pwd.length() >= 11) {
                        boolean if_right = MatchUtils.isPhoneNumberValid(pwd);
                        if (!if_right) {
                            ToastUtils.showShort("请输入正确的手机号码");
                            tv_get_ensure_num.setEnabled(false);
                            tv_get_ensure_num.setTextColor(getResources().getColor(R.color.divider));
                        } else {
                            tv_get_ensure_num.setEnabled(true);
                            tv_get_ensure_num.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                        }
                    }
                }

            }

        });

        //验证码输入框监听
        et_verify.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (TextUtils.isEmpty(editable)) {
                    ToastUtils.showText("请输入验证码");
                }

            }
        });

//密码监听
        et_password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (TextUtils.isEmpty(editable)) {
                    ToastUtils.showText("密码不能为空");
                }
            }
        });

    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_back:
                finish();
                break;

            case R.id.tv_get_ensure_num:
                String tl = et_tel.getText().toString();
//TODO  获取验证码

                if (TextUtils.isEmpty(tl)) {
                    ToastUtils.showShort("请输入您的手机号码");
                    return;
                } else if (!MatchUtils.isPhoneNumberValid(tl)) {
                    ToastUtils.showShort("请输入正确手机号码");
                    return;
                } else {
                    tv_get_ensure_num.setTextAfter("秒").setLenght(5 * 1000);
//
                    //获取验证码
                }

                break;
            case R.id.but_register:
                //点击注册按钮
                //TODO  判断账号密码验证码是否为空，还要判断格式是否正确
                String tel = et_tel.getText().toString().trim();
                String ensurenum = et_verify.getText().toString().trim();
//                String mail = et_user_mail.getText().toString().trim();
                String pwd = et_password.getText().toString().trim();
                String referee = et_name.getText().toString().trim();
                if (TextUtils.isEmpty(tel)) {
                    ToastUtils.showShort("请输入您的手机号码");
                    return;
                } else if (!MatchUtils.isPhoneNumberValid(tel)) {
                    ToastUtils.showShort("请输入正确手机号码");
                    return;
                }
//
                if (TextUtils.isEmpty(ensurenum)) {
                    ToastUtils.showShort("请输入验证码");
                    return;
                }
                if (TextUtils.isEmpty(pwd)) {
                    ToastUtils.showShort("密码不能为空");
                    return;
                } else if (pwd.length() < 6) {
                    ToastUtils.showShort("密码长度至少为六位");
                    return;
                }

                if (referee.length() != 0 || referee != null) {
                    if (!MatchUtils.isPhoneNumberValid(tel)) {
                        ToastUtils.showShort("请输入正确手机号码");
                        return;
                    }
                } else {
                    referee = "";
                }


                getNetWork(tel, pwd);
                break;
        }
    }

    public void getNetWork(String tel, String username) {
        Http
                .post(ContentValues.BASE_COMMON)
                .addParams("user_login", tel)
                .addParams("user_pass", username)
                .excute(
                        new GsonResponseParser(RegisterBean.class),
                        new BaseHttpCallBack<RegisterBean>() {
                            @Override
                            public void onSucess(RegisterBean result) {
                                super.onSucess(result);
                                if ("success".equals(result.getStatus())) {
                                    Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                                    startActivity(intent);
                                }
                                ToastUtils.showLong(result.getExplain());

                            }

                        }.addPlugin(new ExceptionToastCallBackPlugin(context))
                                .addPlugin(new DialogTipCallBackPlugin(mActivity))
                );


    }
}
