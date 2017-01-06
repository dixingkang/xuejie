package com.dafen.xuejie.activity.jiaxiao;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dafen.xuejie.BaseActivity;
import com.dafen.xuejie.R;
import com.dafen.xuejie.adapter.DrivingAdapter;
import com.dafen.xuejie.view.MyScrollView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DrivingActivity extends BaseActivity implements AdapterView.OnItemClickListener,View.OnClickListener {
    private LinearLayout ll_service,ll_skill;
    private Button   but_apply;
    private TextView tv_digit;
    private ListView lv_Driving;
    List<Map<String,Object>>list;
    DrivingAdapter drivingAdapter;
    private ImageView iv_back;
    //标题
    private MyScrollView myScrollView;
    private LinearLayout ll_jia;
    private RelativeLayout rl_title;
    private TextView tv_title;
    int imgHeight;
    public void setContentView() {
        setContentView(R.layout.activity_driving);
    }

    /**
     * 实例化控件
     */
    @Override
    public void initViews() {
        iv_back = (ImageView) findViewById(R.id.iv_back);
        ll_service = (LinearLayout) findViewById(R.id.ll_service); // 报名须知
        ll_skill = (LinearLayout) findViewById(R.id.ll_skill);      //考试技巧
        but_apply = (Button) findViewById(R.id.but_apply);// 我要报名
        tv_digit = (TextView) findViewById(R.id.tv_digit);// 数量
        lv_Driving = (ListView) findViewById(R.id.lv_Driving);//
        //标题
        myScrollView = (MyScrollView) findViewById(R.id.scrollView);
        ll_jia = (LinearLayout) findViewById(R.id.ll_jia);
        rl_title = (RelativeLayout) findViewById(R.id.rl_title);
        tv_title = (TextView) findViewById(R.id.tv_title);
    }

    /**
     * 实例化监听
     */
    @Override
    public void initListeners() {
        iv_back.setOnClickListener(this);
        lv_Driving.setOnItemClickListener(this);
        but_apply.setOnClickListener(this);
        ViewTreeObserver vto = ll_jia.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                //图片的高度
               ll_jia.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                imgHeight = ll_jia.getHeight();
                myScrollView.setScrollViewListener(new MyScrollView.ScrollViewListener() {
                    @Override
                    public void onScrollChanged(MyScrollView scrollView, int x, int y, int oldl, int oldt) {
                        if (y <= 0){
                            rl_title.setBackgroundColor(Color.argb((int) 0, 227, 29, 26));//AGB由相关工具获得，或者美工提供
                        }else if (y > 0 && y <= imgHeight){
                            float scale = (float) y / imgHeight;
                            float alpha = (255 * scale);
                            // 只是layout背景透明(仿知乎滑动效果)
                            tv_title.setTextColor(Color.argb((int) alpha, 255, 255, 255));
                            rl_title.setBackgroundColor(Color.argb(99, 255, 14, 129));
                        }else {//滑动到banner下面设置普通颜色
                            rl_title.setBackgroundColor(Color.argb(255, 255, 0, 129));
                        }
                    }
                });
            }
        });
    }

    /**
     *  适配器
     */
    @Override
    public void initData() {
        list =getData();
        drivingAdapter =new DrivingAdapter(DrivingActivity.this,list);
        lv_Driving.setAdapter(drivingAdapter);
    }

    /**
     * 数据源
     * @return
     */
    public List<Map<String,Object>> getData(){
        list =new ArrayList<Map<String, Object>>();
        Map<String,Object> map =new HashMap<String, Object>();
        map.put("img",R.drawable.jz);
        map.put("text","C1驾驶本");
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
            case R.id.but_apply:
                Intent intent =new Intent(DrivingActivity.this,ApplyActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.scale_translate,R.anim.my_alpha_action);
                break;
            case R.id.iv_back:
                finish();
                break;
        }
    }
}
