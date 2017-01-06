package com.dafen.xuejie.activity.wode;

import android.view.View;
import android.widget.RelativeLayout;

import com.dafen.xuejie.BaseActivity;
import com.dafen.xuejie.R;



public class IndexActivity extends BaseActivity implements View.OnClickListener{
    private RelativeLayout rl_back;
    @Override
    public void setContentView() {
        setContentView(R.layout.activity_guan_yu_wo_men);
    }

    @Override
    public void initViews() {
        rl_back = (RelativeLayout) findViewById(R.id.rl_back);

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
