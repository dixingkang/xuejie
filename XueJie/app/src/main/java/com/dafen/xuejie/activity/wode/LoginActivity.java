package com.dafen.xuejie.activity.wode;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dafen.xuejie.BaseActivity;
import com.dafen.xuejie.R;
import com.dafen.xuejie.bean.RecoverPassBean;
import com.dafen.xuejie.bean.RegisterBean;
import com.dafen.xuejie.utils.ContentValues;
import com.dafen.xuejie.utils.MatchUtils;
import com.dafen.xuejie.utils.ToastUtils;
import com.dafen.xuejie.utils.httpCompat.Http;
import com.dafen.xuejie.utils.httpCompat.base.BaseHttpCallBack;
import com.dafen.xuejie.utils.httpCompat.plugin.DialogTipCallBackPlugin;
import com.dafen.xuejie.utils.httpCompat.plugin.ExceptionToastCallBackPlugin;
import com.dafen.xuejie.utils.httpCompat.response_parser.GsonResponseParser;


public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private LinearLayout ll_qq_login,ll_WeChat_login;
    private RelativeLayout rl_back;
    private EditText et_tel,et_password;
    private Button but_loginto;
    private TextView tv_register,tv_forget_password;
    private Class nextActivity;
    private Boolean SkipState;

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_login);
    }

    @Override
    public void initViews() {
        rl_back = (RelativeLayout) findViewById(R.id.rl_back);
        ll_qq_login = (LinearLayout) findViewById(R.id.ll_qq_login);
        ll_WeChat_login = (LinearLayout) findViewById(R.id.ll_WeChat_login);
        et_password = (EditText) findViewById(R.id.et_password);
        tv_register = (TextView) findViewById(R.id.tv_register);
        tv_forget_password = (TextView) findViewById(R.id.tv_forget_password);
        but_loginto = (Button) findViewById(R.id.but_loginto);
        et_tel = (EditText) findViewById(R.id.et_tel);

        nextActivity = (Class) getIntent().getExtras().getSerializable("CLASS");
        SkipState = getIntent().getExtras().getBoolean("SkipState");


    }

    @Override
    public void initListeners() {
        rl_back.setOnClickListener(this);
        tv_register.setOnClickListener(this);
        tv_forget_password.setOnClickListener(this);
        but_loginto.setOnClickListener(this);
        et_tel.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (TextUtils.isEmpty(editable)) {
                    ToastUtils.showShort("请输入手机号");
                } else {
                    String tel = editable.toString();
                    if (tel.length() >= 11) {
                        boolean if_tel_right = MatchUtils.isPhoneNumberValid(tel);
                        if (!if_tel_right) {
                            ToastUtils.showShort("请输入正确的手机号码");
                        }
                    }
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
                    ToastUtils.showShort("请输入密码");
                } else {
                    String pwd = editable.toString();
                    if (pwd.length() < 6) {
                        ToastUtils.showShort("密码长度至少六位");
                    }
                    if (pwd.length() > 12) {
                        ToastUtils.showShort("密码过长");
                    }
                }
            }
        });
    }

    @Override
    public void initData() {

    }

    /**
     * 实例化控件
     */


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //点击跳转注册页面
            case R.id.tv_register:
                Intent intent =new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.scale_translate,R.anim.my_alpha_action);
                break;
            //点击跳转重设密码页面
            case R.id.tv_forget_password:
                Intent intent1 =new Intent(LoginActivity.this,RecoverPasswordActivity.class);
                startActivity(intent1);
                overridePendingTransition(R.anim.scale_translate,R.anim.my_alpha_action);
                break;
            case R.id.rl_back:
                finish();
                break;
            case R.id.but_loginto:
                String username = et_tel.getText().toString().trim();
                String password = et_password.getText().toString().trim();
                if (TextUtils.isEmpty(username)) {
                    ToastUtils.showShort("手机号不能为空");
                    return;
                } else if (!MatchUtils.isPhoneNumberValid(username)) {
                    ToastUtils.showShort("手机号格式不正确");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    ToastUtils.showShort("密码不能为空");
                    return;
                } else if (password.length() < 6) {
                    ToastUtils.showShort("密码长度至少六位");
                    return;
                }
                getNetWork(username,password);
                break;
            default:break;
        }
    }

    public void getNetWork(String tel, String pass) {
        Http
                .post(ContentValues.BASE_COMMON_LOGIN)
                .addParams("user_login", tel)
                .addParams("user_pass", pass)
                .addParams("type", "1")
                .excute(
                        new GsonResponseParser(RecoverPassBean.class),
                        new BaseHttpCallBack<RecoverPassBean>() {
                            @Override
                            public void onSucess(RecoverPassBean result) {
                                super.onSucess(result);
                                if ("success".equals(result.getStatus())) {
                                    //登录成功后将记录登录状态
                                    SharedPreferences.Editor spEdit = sharedPreferences.edit();
                                    spEdit.putBoolean(ContentValues.IF_IS_LOGINED, true).apply();
                                    // 记录用户id
                                    spEdit.putString(ContentValues.USER_ID, String.valueOf(result.getValue().getId())).apply();

                                    // 记录TOKEN
//                                    spEdit.putString(ContentValues.USER_TOKEN, String.valueOf(result.token == null ? "" : response.token));
                                    spEdit.apply();
                                    if (SkipState) {
                                        Intent intent1 = new Intent(context, nextActivity);
                                        startActivity(intent1);
                                    }

                                    finish();
                                }
                                ToastUtils.showLong(result.getExplain());

                            }

                        }.addPlugin(new ExceptionToastCallBackPlugin(context))
                                .addPlugin(new DialogTipCallBackPlugin(mActivity))
                );


    }
}
