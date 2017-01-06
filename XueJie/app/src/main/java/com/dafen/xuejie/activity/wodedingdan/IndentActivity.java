package com.dafen.xuejie.activity.wodedingdan;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.dafen.xuejie.BaseActivity;
import com.dafen.xuejie.R;
import com.dafen.xuejie.adapter.IndentAdapter;
import com.dafen.xuejie.fragment.wodedingdan.CanceledFragment;
import com.dafen.xuejie.fragment.wodedingdan.DoneFragment;
import com.dafen.xuejie.fragment.wodedingdan.UndoneFragment;

import java.util.ArrayList;
import java.util.List;

public class IndentActivity extends BaseActivity implements View.OnClickListener {
    private TextView tv_undone,tv_done,tv_canceled;
    private RelativeLayout rl_back;//返回按钮
    private ViewPager      vp;
    List<Fragment> list;
    @Override
    public void setContentView() {
        setContentView(R.layout.activity_indent);
    }

    /**
     * 实例化控件
     */
    @Override
    public void initViews() {
        tv_undone = (TextView) findViewById(R.id.tv_undone);
        tv_done = (TextView) findViewById(R.id.tv_done);
        tv_canceled = (TextView) findViewById(R.id.tv_canceled);

        rl_back = (RelativeLayout) findViewById(R.id.rl_back);
        vp = (ViewPager) findViewById(R.id.vp);
        initState();
    }

    /**
     * 实例化监听
     */
    @Override
    public void initListeners() {
        rl_back.setOnClickListener(this);
        tv_undone.setOnClickListener(this);
        tv_done.setOnClickListener(this);
        tv_canceled.setOnClickListener(this);
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (state==2){
                    int position = vp.getCurrentItem();
                    clear();
                    switch (position){
                        case 0:
                            change(R.id.tv_undone);
                            break;
                        case 1:
                            change(R.id.tv_done);
                            break;
                        case 2:
                            change(R.id.tv_canceled);
                            break;
                        default:break;
                    }
                }
            }
        });
    }

    /**
     * 数据初始化
     */
    @Override
    public void initData() {
        list =getData();
        IndentAdapter indentAdapter =new IndentAdapter(getSupportFragmentManager(),list);
        vp.setAdapter(indentAdapter);
    }

    /**
     * 数据源
     * @return
     */
    public  List<Fragment> getData(){
        list = new ArrayList<Fragment>();
        UndoneFragment uf = new UndoneFragment();//未完成的
        DoneFragment df =new DoneFragment(); //已完成的
        CanceledFragment cdf = new CanceledFragment();//已取消的
        list.add(uf);
        list.add(df);
        list.add(cdf);
        return list;
    }

    /**
     * 获得点击ID
     * @param v
     */
    @Override
    public void onClick(View v) {
        clear();
        change(v.getId());
    switch (v.getId()){
        case R.id.rl_back:
            finish();
            break;
    }
    }

    /**
     * 初始化颜色
     */
    public  void  clear(){
        tv_undone.setTextColor(this.getResources().getColor(R.color.hui));
        tv_canceled.setTextColor(this.getResources().getColor(R.color.hui));
        tv_done.setTextColor(this.getResources().getColor(R.color.hui));
    }

    /**
     * 初始化字体的颜色
     */
    public void  initState(){
        clear();
        change(R.id.tv_undone);
        tv_undone.setTextColor(this.getResources().getColor(R.color.hui));
    }

    /**
     * 点击id改变字体颜色
     * @param intId
     */
    public void  change(int intId){
        switch (intId){
            case R.id.tv_undone:
                tv_undone.setTextColor(this.getResources().getColor(R.color.hongse));
                vp.setCurrentItem(0);
                break;
            case R.id.tv_done:
                tv_done.setTextColor(this.getResources().getColor(R.color.hongse));
                vp.setCurrentItem(1);
                break;
            case R.id.tv_canceled:
                tv_canceled.setTextColor(this.getResources().getColor(R.color.hongse));
                vp.setCurrentItem(2);
                break;
            default:break;
        }
    }
}
