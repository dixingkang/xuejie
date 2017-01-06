package com.dafen.xuejie.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.dafen.xuejie.R;
import com.dafen.xuejie.activity.wode.LoginActivity;
import com.dafen.xuejie.activity.wode.RegisterActivity;
import com.dafen.xuejie.adapter.FindAdapter;
import com.dafen.xuejie.bean.RegisterBean;
import com.dafen.xuejie.utils.ContentValues;
import com.dafen.xuejie.utils.ToastUtils;
import com.dafen.xuejie.utils.httpCompat.Http;
import com.dafen.xuejie.utils.httpCompat.base.BaseHttpCallBack;
import com.dafen.xuejie.utils.httpCompat.plugin.DialogTipCallBackPlugin;
import com.dafen.xuejie.utils.httpCompat.plugin.ExceptionToastCallBackPlugin;
import com.dafen.xuejie.utils.httpCompat.response_parser.GsonResponseParser;
import com.dafen.xuejie.view.FlyBanner;
import com.dafen.xuejie.view.MyScrollView;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * 首页
 * Created by Administrator on 2016/11/15.
 */
public class FindFragment extends BaseFragment implements View.OnClickListener,AdapterView.OnItemClickListener{
    private ListView lv_find;
    List<String> list;
    FindAdapter adapter;
    private FlyBanner bannerTop;
    private List<String> bigPics;
    List<Integer> mDatas;
    //渐变
    private MyScrollView scrollView;
    private TextView     textView;
    private int          imageHeight;// 图片高度

    private RelativeLayout bg, bar;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_find,null);
        initView(view);
        initData();
        initListener();
        initBannerTop();
        getNetWork();
        return view;
    }

    @Override
    public int getLayoutResID() {
        return R.layout.fragment_find;
    }

    /**
     * 实例化控件
     * @param view
     */
    @Override
    public void initView(View view){
        lv_find = (ListView) view.findViewById(R.id.lv_find);
        // 轮播
        bannerTop = (FlyBanner) view.findViewById(R.id.bannerTop);
        //渐变
        scrollView = (MyScrollView) view.findViewById(R.id.scrollview);
        textView = (TextView) view.findViewById(R.id.tv_text);
        bg = (RelativeLayout) view.findViewById(R.id.rel_ber);// 轮播高度
        bar = (RelativeLayout) view.findViewById(R.id.rel_baare);//标题栏高度
    }
    public void getNetWork() {
        Http
                .post(ContentValues.BASE_STORE_STORE)
                .addParams("storeid", "1")
                .excute(
                        new GsonResponseParser(RegisterBean.class),
                        new BaseHttpCallBack<RegisterBean>() {
                            @Override
                            public void onSucess(RegisterBean result) {
                                super.onSucess(result);
                                if ("success".equals(result.getStatus())) {

                                }
                                ToastUtils.showLong(result.getExplain());

                            }

                        }.addPlugin(new ExceptionToastCallBackPlugin(context))
                                .addPlugin(new DialogTipCallBackPlugin(getActivity()))
                );


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
    /**
     * 实例化监听
     */
    @Override
    public   void  initListener(){
        lv_find.setOnItemClickListener(this);
        //渐变
        // 获取顶部图片高度后，设置滚动监听
        ViewTreeObserver vto = bg.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                bg.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                imageHeight = bg.getHeight();
                scrollView.setScrollViewListener(new MyScrollView.ScrollViewListener() {
                    @Override
                    public void onScrollChanged(MyScrollView scrollView, int x, int y, int oldl, int oldt) {
                        if (y <= 0) {
                            bar.setBackgroundColor(Color.argb((int) 0, 227, 29, 26));//AGB由相关工具获得，或者美工提供
                        } else if (y > 0 && y <= imageHeight) {
                            float scale = (float) y / imageHeight;
                            float alpha = (255 * scale);
                            // 只是layout背景透明(仿知乎滑动效果)
                            textView.setTextColor(Color.argb((int) alpha, 255, 255, 255));
                            bar.setBackgroundColor(Color.argb(99, 255, 14, 129));
                        } else {    //滑动到banner下面设置普通颜色
                            bar.setBackgroundColor(Color.argb(255, 255, 0, 129));
                        }
                    }
                });
            }
        });

    }

    /**
     * 适配器
     */
    @Override
    public void initData(){
        list =getData();
        adapter =new FindAdapter(getActivity(),list);
        lv_find.setAdapter(adapter);
    }

    /**
     * 数据源
     * @return
     */
    public List<String> getData(){
        list =new ArrayList<String>();
        for (int i = 0; i< 6;i++){
            list.add(""+i);
        }
        return list;
    }
    @Override
    public void onClick(View v) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
