package com.dafen.xuejie.activity;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.dafen.xuejie.BaseActivity;
import com.dafen.xuejie.R;


public class ProductActivity extends BaseActivity {
    private ImageView iv_vt,iv_tu,iv_cvs_tu,iv_cart_rook;
    private TextView tv_title,tv_content,tv_cvs_title,tv_time,tv_times,tv_good_details,tv_good_norms,tv_good_content,tv_good_introduce,tv_shop_introduce,tv_money;
    private Button but_shop,but_add;
    @Override
    public void setContentView() {
        setContentView(R.layout.activity_product);
    }

    /**
     * 实例化控件
     */
    @Override
    public void initViews() {
        // 图片控件
        iv_vt = (ImageView) findViewById(R.id.iv_vt);
        iv_tu = (ImageView) findViewById(R.id.iv_tu);
        iv_cvs_tu = (ImageView) findViewById(R.id.iv_cvs_tu);
        iv_cart_rook = (ImageView) findViewById(R.id.iv_cart_rook);
        //文字控件
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_content = (TextView) findViewById(R.id.tv_content);
        tv_cvs_title = (TextView) findViewById(R.id.tv_cvs_title);
        tv_time = (TextView) findViewById(R.id.tv_time);
        tv_times = (TextView) findViewById(R.id.tv_times);
        tv_good_details = (TextView) findViewById(R.id.tv_good_details);
        tv_good_norms = (TextView) findViewById(R.id.tv_good_norms);
        tv_good_content = (TextView) findViewById(R.id.tv_good_content);
        tv_good_introduce = (TextView) findViewById(R.id.tv_good_introduce);
        tv_shop_introduce = (TextView) findViewById(R.id.tv_shop_introduce);
        tv_money = (TextView) findViewById(R.id.tv_money);
        //按钮控件
        but_shop = (Button) findViewById(R.id.but_shop);
        but_add = (Button) findViewById(R.id.but_add);
    }

    @Override
    public void initListeners() {

    }

    @Override
    public void initData() {

    }
}
