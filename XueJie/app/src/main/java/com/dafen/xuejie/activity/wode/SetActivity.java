package com.dafen.xuejie.activity.wode;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dafen.xuejie.BaseActivity;
import com.dafen.xuejie.R;



public class SetActivity extends BaseActivity implements View.OnClickListener {
    private RelativeLayout rl_back;
    private TextView       tv_eliminate,tv_Version;

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_set);
    }

    /**
     * 实例化控件
     */
    @Override
    public void initViews() {
        rl_back = (RelativeLayout) findViewById(R.id.rl_back);
        tv_Version = (TextView) findViewById(R.id.tv_Version);
        tv_eliminate = (TextView) findViewById(R.id.tv_eliminate);
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
