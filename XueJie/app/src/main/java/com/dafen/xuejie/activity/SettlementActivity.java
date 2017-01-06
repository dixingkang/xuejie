package com.dafen.xuejie.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dafen.xuejie.BaseActivity;
import com.dafen.xuejie.R;


public class SettlementActivity extends BaseActivity implements View.OnClickListener {
    private LinearLayout ll_Online_Pay,ll_AliPay,ll_WeChat_Pay;
    private RelativeLayout rl_back;
    private TextView tv_title,tv_tel,tv_time;
    private EditText et_number,et_content;
    private TextView tv_amount, tv_money,tv_total_sum,tv_freight,tv_Cope;
    private Button but_refer;

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_settlement);
    }

    @Override
    public void initViews() {
        rl_back = (RelativeLayout) findViewById(R.id.rl_back);
        ll_Online_Pay = (LinearLayout) findViewById(R.id.ll_Online_Pay);
        ll_AliPay = (LinearLayout) findViewById(R.id.ll_AliPay);
        ll_WeChat_Pay = (LinearLayout) findViewById(R.id.ll_WeChat_Pay);

        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_tel = (TextView) findViewById(R.id.tv_tel);
        tv_time = (TextView) findViewById(R.id.tv_time);
        tv_amount = (TextView) findViewById(R.id.tv_amount);
        tv_money = (TextView) findViewById(R.id.tv_money);
        tv_total_sum = (TextView) findViewById(R.id.tv_total_sum);
        tv_freight = (TextView) findViewById(R.id.tv_freight);
        tv_Cope = (TextView) findViewById(R.id.tv_Cope);

        et_content = (EditText) findViewById(R.id.et_content);
        et_number = (EditText) findViewById(R.id.et_number);
        but_refer = (Button) findViewById(R.id.but_refer);
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
            default:
                break;
        }
    }
}
