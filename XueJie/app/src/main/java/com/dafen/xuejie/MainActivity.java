package com.dafen.xuejie;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.dafen.xuejie.fragment.CartFragment;
import com.dafen.xuejie.fragment.FindFragment;
import com.dafen.xuejie.fragment.MineFragment;
import com.dafen.xuejie.fragment.HomeFragment;
import com.dafen.xuejie.view.FragmentUtils;


/**
 * Created by _Ms on 2016/10/29.
 */

public class MainActivity extends BaseActivity implements View.OnClickListener {

//    private Button btn_loading;
//    private Button btn_fail;
//    private Button btn_empty;
//    private Button btn_unknow;
//    private Button btn_success;
    private long mDoubleClickTimeExit = -1;
    private RadioGroup tab_menu;
    private FragmentManager mFragmentManager;
    private Fragment myCurrentFragment;
    private RadioButton bottom_btn_first;

    private RelativeLayout root_view;

    private HomeFragment firstPageFragment;
    private FindFragment farmerFragment;
    private CartFragment ShoppingCarFargment;
    private MineFragment mySelfFragemt;

    // 底部导航栏使用Bean

    private class MyOnCheckedChangeListener implements RadioGroup.OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            switch (i) {
                //点击“首页”
                case R.id.bottom_btn_first:
                    replaceFragment(HomeFragment.class);
                    break;

                //点击“发现”
                case R.id.bottom_btn_bussniss:
                    replaceFragment(FindFragment.class);
                    break;

                //点击“购物车”
                case R.id.bottom_btn_shopcar:
                    replaceFragment(CartFragment.class);
                    break;

                //点击“我的”
                case R.id.bottom_btn_me:
                    replaceFragment(MineFragment.class);
                    break;
            }
        }
    }



    public void replaceFragment(Class<? extends Fragment> newFragment) {

        myCurrentFragment = FragmentUtils.switchFragment(
                mFragmentManager,
                R.id.frameLayout_main,
                myCurrentFragment,
                newFragment,
                null,
                false);
    }

    @Override
    public void onBackPressed() {

        /*
        处理双击返回键退出逻辑
         */
        int doubleClickExitTime = 2000;

        if (mDoubleClickTimeExit == -1 || System.currentTimeMillis() - mDoubleClickTimeExit > doubleClickExitTime) {
            Toast toast = Toast.makeText(this, "双击退出", Toast.LENGTH_SHORT);
            toast.setDuration(doubleClickExitTime);
            toast.show();
            mDoubleClickTimeExit = System.currentTimeMillis();
        } else {
            finish();
        }

    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_main);
    }

    @Override
    public void initViews() {
        tab_menu = (RadioGroup) findViewById(R.id.tab_menu);
        mFragmentManager = getSupportFragmentManager();

        //默认选中第一个图标
        bottom_btn_first = (RadioButton) findViewById(R.id.bottom_btn_first);
        bottom_btn_first.setChecked(true);

        root_view = (RelativeLayout) findViewById(R.id.root_view);

        //初始化第一个fragment
        firstPageFragment = new HomeFragment();
        FragmentUtils.replaceFragment(mFragmentManager, R.id.frameLayout_main, HomeFragment.class, null, false);

        tab_menu = (RadioGroup) findViewById(R.id.tab_menu);
        bottom_btn_first = (RadioButton) findViewById(R.id.bottom_btn_first);
    }

    @Override
    public void initListeners() {
        tab_menu.setOnCheckedChangeListener(new MyOnCheckedChangeListener());

    }

    @Override
    public void initData() {

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        }
    }
}


