package com.dafen.xuejie.activity.wodedizhi;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.dafen.xuejie.BaseActivity;
import com.dafen.xuejie.R;




public class AddSiteActivity extends BaseActivity implements View.OnClickListener{
    private RelativeLayout rl_back;
    private EditText       et_name,et_tel,et_write;
    private LinearLayout ll_sir,ll_lady;
    private ImageView iv_location;
    private Button but_dorm,but_classroom,but_found;

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_add_site);
    }

    @Override
    public void initViews() {
        rl_back = (RelativeLayout) findViewById(R.id.rl_back);
        et_name = (EditText) findViewById(R.id.et_name);
        et_tel = (EditText) findViewById(R.id.et_tel);
        et_write = (EditText) findViewById(R.id.et_write);
        ll_sir = (LinearLayout) findViewById(R.id.ll_sir);
        ll_lady = (LinearLayout) findViewById(R.id.ll_lady);
        iv_location = (ImageView) findViewById(R.id.iv_location);
        but_dorm = (Button) findViewById(R.id.but_dorm);
        but_classroom = (Button) findViewById(R.id.but_classroom);
        but_found = (Button) findViewById(R.id.but_found);

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
