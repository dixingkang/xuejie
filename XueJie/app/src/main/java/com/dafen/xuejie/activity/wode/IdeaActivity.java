package com.dafen.xuejie.activity.wode;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dafen.xuejie.BaseActivity;
import com.dafen.xuejie.R;



public class IdeaActivity extends BaseActivity implements View.OnClickListener {
    private RelativeLayout rl_back;
    private EditText       et_input,et_tel;
    private TextView tv_submit;
    private ImageView iv_photo;

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_idea);
    }

    @Override
    public void initViews() {
        rl_back = (RelativeLayout) findViewById(R.id.rl_back);
        et_input = (EditText) findViewById(R.id.et_input);
        et_tel = (EditText) findViewById(R.id.et_tel);
        tv_submit = (TextView) findViewById(R.id.tv_submit);
        iv_photo = (ImageView) findViewById(R.id.iv_photo);
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
