package com.dafen.xuejie.activity.shuiguo;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dafen.xuejie.BaseActivity;
import com.dafen.xuejie.R;
import com.dafen.xuejie.adapter.FruitAdapter;
import com.dafen.xuejie.adapter.dingdan.ListAdapter;
import com.dafen.xuejie.bean.FruitBean;
import com.dafen.xuejie.utils.PixUtil;
import com.dafen.xuejie.view.MyScrollView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FruitActivity extends BaseActivity  implements AdapterView.OnItemClickListener,View.OnClickListener{
    private ImageView iv_fanhui;
    private EditText et_input,et_adv;
    private TextView tv_sales,tv_cost,tv_drop;
    private ListView lv_fruit;
    List<FruitBean> list;
    FruitBean bean;
    private ImageView iv_back;
    //标题
    private MyScrollView myScrollView;
    private RelativeLayout rl_fruit,rl_title;
    private TextView tv_title;
    int imgHeight;


    //下拉窗体
    WindowManager windowManager;
    int screenWidth, screenHeight;
    private PopupWindow popupWindow = null;
    List<String> lists = new ArrayList<>();

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_fruit);

        windowManager = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        screenWidth = windowManager.getDefaultDisplay().getWidth();
        screenHeight = windowManager.getDefaultDisplay().getHeight();
        for (int i=0;i< 6;i++){
            lists.add("我是商品"+i);
        }
    }

    /**
     * 实例化控件
     */
    @Override
    public void initViews() {
        iv_back = (ImageView) findViewById(R.id.iv_back);
        et_input = (EditText) findViewById(R.id.et_input);
        et_adv = (EditText) findViewById(R.id.et_adv);

        tv_drop = (TextView) findViewById(R.id.tv_drop);
        tv_cost = (TextView) findViewById(R.id.tv_cost);
        tv_sales = (TextView) findViewById(R.id.tv_sales);

        lv_fruit = (ListView) findViewById(R.id.lv_fruit);
        //标题
        myScrollView = (MyScrollView) findViewById(R.id.scrollView);
        rl_fruit = (RelativeLayout) findViewById(R.id.rl_fruit);
        rl_title = (RelativeLayout) findViewById(R.id.rl_title);
        tv_title = (TextView) findViewById(R.id.tv_title);
    }

    /**
     * 实例化监听
     */
    @Override
    public void initListeners() {
        iv_back.setOnClickListener(this);
        lv_fruit.setOnItemClickListener(this);

        tv_drop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_drop.setTextColor(FruitActivity.this.getResources().getColor(R.color.colorPrimary));
                showPopWindow();
            }
        });
        ViewTreeObserver vto = rl_fruit.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                rl_fruit.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                imgHeight = rl_fruit.getHeight();
                myScrollView.setScrollViewListener(new MyScrollView.ScrollViewListener() {
                    @Override
                    public void onScrollChanged(MyScrollView scrollView, int x, int y, int oldl, int oldt) {
                        if (y <= 0) {
                            rl_title.setBackgroundColor(Color.argb((int) 0, 227, 29, 26));//AGB由相关工具获得，或者美工提供
                        } else if (y > 0 && y <= imgHeight) {
                            float scale = (float) y / imgHeight;
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

    /**
     * f商品分类
     */
    private   void showPopWindow() {
        ListView list;
        View view = LinearLayout.inflate(this, R.layout.layout_fenlei, null);
        list = (ListView) view.findViewById(R.id.list);
        list.setAdapter(new ListAdapter(this,lists));

        if (popupWindow==null){
            popupWindow =new PopupWindow(this);
            popupWindow.setWidth(screenWidth/3);
            popupWindow.setHeight(PixUtil.dip2px(this,150));
            popupWindow.setContentView(view);
            ColorDrawable dw =new ColorDrawable(Color.YELLOW);

            //设置SelectPicPopupWindow弹出窗体的背景
            popupWindow.setBackgroundDrawable(dw);
            //设置可获得焦点
            popupWindow.setFocusable(true);
            }
            popupWindow.showAsDropDown(tv_drop);
        //点击选择器后初始化条目内选中信息
//        int str = sharedPreferences.getInt(ContentValues.SELECTOR_THIRD_WORD, -1);

//
//        if (adapter3 != null) {
//            adapter3.notifyDataSetChanged();
//        }

        //gridview的条目点击事件
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //点击之后让popupwindow消失
                if (popupWindow !=null && popupWindow.isShowing()){
                    popupWindow.dismiss();
                    Toast.makeText(FruitActivity.this,position+"", Toast.LENGTH_SHORT).show();
                    //如果变为Null会出现小bug
                    //  popupWindow3 = null;
                }
            }
        });
        /**
         * popupwindow消失的监听
         */
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                tv_drop.setTextColor(FruitActivity.this.getResources().getColor(R.color.hongse));
            }
        });

    }

    /**
     * 适配器
     */
    @Override
    public void initData() {
        list =getDatat();
        FruitAdapter adapter =new FruitAdapter(FruitActivity.this,list);
        lv_fruit.setAdapter(adapter);
//

    }

    /**
     * 数据源
     * @return
     */
    public  List<FruitBean> getDatat(){
        list =new ArrayList<FruitBean>();
        bean =new FruitBean();
        bean.setPrice("8");
        bean.setQuantity("6");
        for(int i = 0 ; i < 6;i++){
            list.add(bean);
        }
        return list;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                finish();
                break;
        }
    }
}
