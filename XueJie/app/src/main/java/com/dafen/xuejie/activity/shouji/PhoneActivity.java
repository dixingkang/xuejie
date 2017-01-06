package com.dafen.xuejie.activity.shouji;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dafen.xuejie.BaseActivity;
import com.dafen.xuejie.MainActivity;
import com.dafen.xuejie.R;
import com.dafen.xuejie.adapter.PhoneAdapter;
import com.dafen.xuejie.bean.PhoneBean;
import com.dafen.xuejie.view.MyScrollView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class PhoneActivity extends BaseActivity implements AdapterView.OnItemClickListener,View.OnClickListener{
    private LinearLayout ll_service,ll_type;
    private TextView tv_digit;
    private Button   but_Orders;
    private ListView lv_phone;
    List<Map<String,Object>>list;
    PhoneAdapter adapter;
    private ImageView iv_back;
    //标题
    private MyScrollView myScrollView;
    private TextView tv_title;
    private LinearLayout ll_bei;
    private RelativeLayout rl_title;
    int imageHeight;
    @Override
    public void setContentView() {
        setContentView(R.layout.activity_phone);

    }

    @Override
    public void initViews() {
        iv_back = (ImageView) findViewById(R.id.iv_back);
        ll_service = (LinearLayout) findViewById(R.id.ll_service); //手机维修
        ll_type = (LinearLayout) findViewById(R.id.ll_type);// 更多机型
        tv_digit = (TextView) findViewById(R.id.tv_digit);// 维修数量
        but_Orders = (Button) findViewById(R.id.but_Orders);// 下单
        lv_phone = (ListView) findViewById(R.id.lv_phone);//
        //标题
        myScrollView = (MyScrollView) findViewById(R.id.scrollView);
        tv_title = (TextView) findViewById(R.id.tv_title);
        ll_bei = (LinearLayout) findViewById(R.id.ll_bei);
        rl_title = (RelativeLayout) findViewById(R.id.rl_title);
    }

    @Override
    public void initListeners() {
        iv_back.setOnClickListener(this);
        lv_phone.setOnItemClickListener(this);
        but_Orders.setOnClickListener(this);
        ViewTreeObserver vto = ll_bei.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                ll_bei.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                imageHeight = ll_bei.getHeight();
                myScrollView.setScrollViewListener(new MyScrollView.ScrollViewListener() {
                    @Override
                    public void onScrollChanged(MyScrollView scrollView, int x, int y, int oldl, int oldt) {
                        if (y <= 0) {
                            rl_title.setBackgroundColor(Color.argb((int) 0, 227, 29, 26));//AGB由相关工具获得，或者美工提供
                        } else if (y > 0 && y <= imageHeight) {
                            float scale = (float) y / imageHeight;
                            float alpha = (255 * scale);
                            // 只是layout背景透明(仿知乎滑动效果)
                            tv_title.setTextColor(Color.argb((int) alpha, 255, 255, 255));
                            rl_title.setBackgroundColor(Color.argb(99, 255, 14, 129));
                        } else {    //滑动到banner下面设置普通颜色
                            rl_title.setBackgroundColor(Color.argb(255, 255, 0, 129));
                        }
                    }
                });
            }
        });
    }
    @Override
    public void initData() {
        list =getData();
        adapter =new PhoneAdapter(PhoneActivity.this,list);
        lv_phone.setAdapter(adapter);
    }
    public List<Map<String,Object>>getData(){
        list =new ArrayList<Map<String, Object>>();
        Map<String,Object> map =new HashMap<String,Object>();
        map.put("img",R.drawable.sjt);
        map.put("text","iPhone6");
        for (int i =0; i < 6; i++){
            list.add(map);
        }
        return list;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.but_Orders:
                Intent intent =new Intent(PhoneActivity.this, MaintainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.scale_translate,R.anim.my_alpha_action);
                break;
            case R.id.iv_back:
                finish();
                break;
        }

    }


}
