package com.dafen.xuejie.activity.wode;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.dafen.xuejie.BaseActivity;
import com.dafen.xuejie.R;


public class WithdrawActivity extends BaseActivity implements View.OnClickListener {
    private EditText et_amount_entered,et_name,et_id;
    private ImageView iv_alipay,iv_bank_card;
    private RelativeLayout rl_back;
    private Button         but_withdraw;

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_withdraw);
    }

    @Override
    public void initViews() {
        rl_back = (RelativeLayout) findViewById(R.id.rl_back);
        but_withdraw = (Button) findViewById(R.id.but_withdraw);
        iv_alipay = (ImageView) findViewById(R.id.iv_alipay);
        iv_bank_card = (ImageView) findViewById(R.id.iv_bank_card);
        et_amount_entered = (EditText) findViewById(R.id.et_amount_entered);
        et_name = (EditText) findViewById(R.id.et_name);
        et_id = (EditText) findViewById(R.id.et_id);
    }

    @Override
    public void initListeners() {
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
            }
    }
}
