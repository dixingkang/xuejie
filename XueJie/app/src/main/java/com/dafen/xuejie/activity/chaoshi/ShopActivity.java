package com.dafen.xuejie.activity.chaoshi;

import android.graphics.Color;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.dafen.xuejie.BaseActivity;
import com.dafen.xuejie.R;
import com.dafen.xuejie.adapter.shouye.ClassifyAdapter;
import com.dafen.xuejie.adapter.shouye.GoodAdapter;
import com.dafen.xuejie.bean.FruitBean;
import com.dafen.xuejie.utils.ToastUtils;
import com.dafen.xuejie.view.FlyBanner;
import com.dafen.xuejie.view.MyScrollView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShopActivity extends BaseActivity implements AdapterView.OnItemClickListener,View.OnClickListener {
    private int selectIndex;
    private  static  final  String[] mMenus ={"推荐商品","新鲜水果","个护化妆","牛奶","面包","零食饮料", "生活用品","新鲜水果","个护化妆","牛奶","面包","零食饮料", "生活用品","新鲜水果","个护化妆"};
    private String [] strs1 = {"【促销】味全苹果汁","【促销】味全苹果汁","【促销】味全苹果汁","【促销】味全苹果汁","【促销】味全苹果汁","【促销】味全苹果汁","【促销】味全苹果汁","【促销】味全苹果汁","【促销】味全苹果汁"};
    private String [] strs2 ={"【促销】味全苹果汁","【促销】味全苹果汁","【促销】味全苹果汁","【促销】味全苹果汁","【促销】味全苹果汁","【促销】味全苹果汁"};
    private String [] strs3 = {"【促销】味全苹果汁","【促销】味全苹果汁","【促销】味全苹果汁","【促销】味全苹果汁","【促销】味全苹果汁"};
    private String [] strs4 = {"【促销】味全苹果汁","【促销】味全苹果汁","【促销】味全苹果汁","【促销】味全苹果汁","【促销】味全苹果汁","【促销】味全苹果汁"};
    private String [] strs5 ={"【促销】味全苹果汁","【促销】味全苹果汁","【促销】味全苹果汁","【促销】味全苹果汁","【促销】味全苹果汁","【促销】味全苹果汁","【促销】味全苹果汁"};
    private String [][] allData = {strs1,strs2,strs3,strs4,strs5,strs1,strs2,strs3,strs4,strs5,strs1,strs2,strs3,strs4,strs5};
    private ListView mListeView1,mListeView2;
    GoodAdapter goodAdapter;
    ClassifyAdapter classifyAdapter;
    private ImageView iv_back;
    //轮播图
    private FlyBanner    bannerTop;
    private   List<String> bigPics;
    List<Integer> mDatas;
    //标题
    private MyScrollView myScrollView;
    private RelativeLayout rl_lun,rl_title;
    private TextView tv_title;
    int imgheight;
    @Override
    public void setContentView() {
        setContentView(R.layout.activity_shop);
    }

    @Override
    public void initViews() {
        iv_back = (ImageView) findViewById(R.id.iv_back);
        mListeView1 = (ListView) findViewById(R.id.lv_classify);//分类
        mListeView2 = (ListView) findViewById(R.id.lv_good);//商品
        // 轮播
        bannerTop = (FlyBanner)findViewById(R.id.bannerTop);
        initBannerTop();
        //标题
        myScrollView = (MyScrollView) findViewById(R.id.scrollview);
        rl_lun = (RelativeLayout) findViewById(R.id.rl_lun);
        rl_title = (RelativeLayout) findViewById(R.id.rl_title);
        tv_title = (TextView) findViewById(R.id.tv_title);
    }

    /**
     * 双listView 滑动
     */
    @Override
    public void initListeners() {
        iv_back.setOnClickListener(this);
        mListeView1.setOnItemClickListener(this);
        mListeView2.setOnItemClickListener(this);
        mListeView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectIndex = position;
                //把下标传过去，然后刷新adapter
                classifyAdapter.setIndex(position);
                classifyAdapter.notifyDataSetChanged();
                //当点击某个item的时候让这个item自动滑到listview的顶部（下面item够多，如果点击的是最后一个就不能达到顶部）
                mListeView1.smoothScrollToPositionFromTop(position,0);

                goodAdapter.setIndex(position);
                mListeView2.setAdapter(goodAdapter);
            }
        });
        mListeView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ToastUtils.showLong(allData[selectIndex][position]);
            }
        });
        //标题
        ViewTreeObserver vto = rl_lun.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                rl_lun.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                imgheight = rl_lun.getHeight();
                myScrollView.setScrollViewListener(new MyScrollView.ScrollViewListener() {
                    @Override
                    public void onScrollChanged(MyScrollView scrollView, int x, int y, int oldl, int oldt) {
                        if (y <= 0) {
                            rl_title.setBackgroundColor(Color.argb((int) 0, 227, 29, 26));//AGB由相关工具获得，或者美工提供
                        } else if (y > 0 && y <= imgheight) {
                            float scale = (float) y / imgheight;
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
     * 添加大图，伪造数据
     */
    private void initBigPics() {
        bigPics = new ArrayList<>();
        bigPics.add("https://gd2.alicdn.com/imgextra/i2/380101244/TB2HHzZdNmJ.eBjy0FhXXbBdFXa_!!380101244.jpg");
        bigPics.add("https://gd4.alicdn.com/imgextra/i4/380101244/TB2qUNua4mI.eBjy0FlXXbgkVXa_!!380101244.jpg");
        bigPics.add("https://gd1.alicdn.com/imgextra/i1/380101244/TB2REFwa9qJ.eBjy1zbXXbx8FXa_!!380101244.jpg");
        bigPics.add("https://gd4.alicdn.com/imgextra/i4/380101244/TB2Ye4taZeK.eBjSszgXXczFpXa_!!380101244.jpg");
        bigPics.add("http://ob9thtnhs.bkt.clouddn.com/tuisong/da386d4d7872451ca346ba6e37da17b6.jpg?e=1477544913&token=m2BF8x75sZF4DIfwnxFri5sT51HeuFRmU2Ue0uVf:LWGBH77qhKA_BEcdgUA5u5AebR0=");
        mDatas = new ArrayList<>(Arrays.asList(R.drawable.ic_launcher,
                R.drawable.ic_launcher, R.drawable.ic_launcher, R.drawable.ic_launcher, R.drawable.ic_launcher,
                R.drawable.ic_launcher, R.drawable.ic_launcher, R.drawable.ic_launcher, R.drawable.ic_launcher));
    }
    /**
     * 轮播图
     */
    private void initBannerTop() {
        initBigPics();
        bannerTop.setImagesUrl(bigPics);
        bannerTop.setOnItemClickListener(new FlyBanner.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                ToastUtils.showShort("position--->" + position);
            }
        });
    }
    @Override
    public void initData() {
        classifyAdapter = new ClassifyAdapter( mMenus,this,selectIndex);//分类
        goodAdapter =new GoodAdapter(allData,this,selectIndex);//商品
        mListeView1.setAdapter(classifyAdapter);
        mListeView2.setAdapter(goodAdapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            // 返回键
            case R.id.iv_back:
                finish();
                break;
        }
    }
}
