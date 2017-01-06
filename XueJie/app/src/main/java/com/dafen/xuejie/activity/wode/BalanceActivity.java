package com.dafen.xuejie.activity.wode;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.dafen.xuejie.BaseActivity;
import com.dafen.xuejie.R;


public class BalanceActivity extends BaseActivity implements View.OnClickListener {
    private RelativeLayout rl_back;
    private Button         but_withdraw;

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_balance);
    }

    @Override
    public void initViews() {
        rl_back = (RelativeLayout) findViewById(R.id.rl_back);
        but_withdraw = (Button) findViewById(R.id.but_withdraw);
    }

    @Override
    public void initListeners() {
        rl_back.setOnClickListener(this);
        but_withdraw.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.but_withdraw:
                Intent intent =new Intent(BalanceActivity.this,WithdrawActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.scale_translate,R.anim.my_alpha_action);
                break;
            case R.id.rl_back:
                finish();
                break;
            default:break;
        }
    }
}
