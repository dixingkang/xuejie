package com.dafen.xuejie.fragment;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dafen.xuejie.R;
import com.dafen.xuejie.activity.chaoshi.ShopActivity;
import com.dafen.xuejie.activity.jiaxiao.DrivingActivity;
import com.dafen.xuejie.activity.shouji.PhoneActivity;
import com.dafen.xuejie.activity.shouye.BerserkActivity;
import com.dafen.xuejie.activity.shuiguo.FruitActivity;
import com.dafen.xuejie.utils.ToastUtils;
import com.dafen.xuejie.utils.UiUtils;
import com.dafen.xuejie.view.FlyBanner;
import com.dafen.xuejie.view.GridMenu;
import com.dafen.xuejie.view.MyScrollView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


/**
 * Created by Administrator on 2016/11/15.
 */
public class HomeFragment extends BaseFragment implements View.OnClickListener, AdapterView.OnItemClickListener {
    private ImageView iv_picture;
    private ListView listView;
    private FlyBanner bannerTop;
    private List<String> bigPics;
    private List<Integer> mDatas;
    private ListViewAdapter mListViewAdapter;
    private ArrayList<ArrayList<HashMap<String, Object>>> mArrayList;
    private String[] gridMenuTitles = new String[]{"超市", "水果", "驾校", "手机", "飞猪旅行", "领金币", "到家", "分类"};
    private Intent intent;
    // 渐变
    private MyScrollView scrollView;
    private TextView textView;
    private int imageHeight;// 图片高度

    private RelativeLayout bg, bar;

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public int getLayoutResID() {
        mDatas = new ArrayList<>(Arrays.asList(R.drawable.cz,
                R.drawable.cz, R.drawable.cz, R.drawable.cz, R.drawable.cz,
                R.drawable.cz, R.drawable.cz, R.drawable.cz, R.drawable.cz));

        return R.layout.fragment_shouye;
    }

    @Override
    public void initView(View view) {
        //渐变
        scrollView = (MyScrollView) view.findViewById(R.id.scrollview);
        textView = (TextView) view.findViewById(R.id.tv111);

        listView = (ListView) view.findViewById(R.id.listView);
        bar = (RelativeLayout) view.findViewById(R.id.rel_bar);
        View add = LinearLayout.inflate(UiUtils.getContext(), R.layout.add_title, null);

        bg = (RelativeLayout) add.findViewById(R.id.rel_Banner);// 轮播的高度
        addInitData(add);
        listView.addHeaderView(add);

        initDat();
        mListViewAdapter = new ListViewAdapter(mArrayList, UiUtils.getContext());
        listView.setAdapter(mListViewAdapter);

    }

    //渐变
    @Override
    public void initListener() {
        iv_picture.setOnClickListener(this);

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


    @Override
    public void initData() {

    }

    /**
     * 轮播
     *
     * @param view
     */
    public void addInitData(View view) {


        iv_picture = (ImageView) view.findViewById(R.id.iv_picture);
        bannerTop = (FlyBanner) view.findViewById(R.id.bannerTop);
        initBigPics();
        initGridMenu(view);
        bannerTop.setImagesUrl(bigPics);
        bannerTop.setOnItemClickListener(new FlyBanner.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                ToastUtils.showLong( position + "");
            }
        });
    }

    /**
     * 初始化10个子菜单
     */
    private void initGridMenu(View gridMenu) {

        List<GridMenu> gridMenus = new ArrayList<>();
        GridMenu grid_shop = (GridMenu) gridMenu.findViewById(R.id.grid_shop);
        GridMenu grid_fruit = (GridMenu) gridMenu.findViewById(R.id.grid_fruit);
        GridMenu grid_driving = (GridMenu) gridMenu.findViewById(R.id.grid_driving);
        GridMenu grid_phone = (GridMenu) gridMenu.findViewById(R.id.grid_phone);

        GridMenu grid_czzx = (GridMenu) gridMenu.findViewById(R.id.grid_czzx);
        GridMenu grid_fzlx = (GridMenu) gridMenu.findViewById(R.id.grid_fzlx);
        GridMenu grid_ljb = (GridMenu) gridMenu.findViewById(R.id.grid_ljb);
        GridMenu grid_dj = (GridMenu) gridMenu.findViewById(R.id.grid_dj);
        gridMenus.add(grid_shop);
        gridMenus.add(grid_fruit);
        gridMenus.add(grid_driving);
        gridMenus.add(grid_phone);
        gridMenus.add(grid_czzx);
        gridMenus.add(grid_fzlx);
        gridMenus.add(grid_ljb);
        gridMenus.add(grid_dj);

        initGridMenuAttr(gridMenus);
        initOnCLick(gridMenus);
    }

    /**
     * 设置10个子菜单的图片和文字
     */
    private void initGridMenuAttr(List<GridMenu> gridMenus) {
        for (int i = 0; i < gridMenus.size(); i++) {
            GridMenu gridMenu = gridMenus.get(i);
            gridMenu.setAttr(R.drawable.ic_launcher, gridMenuTitles[i]);
        }
    }

    private void initOnCLick(List<GridMenu> gridMenus) {
        for (GridMenu gridMenu : gridMenus) {
            gridMenu.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.grid_shop://超市
                intent = new Intent(getActivity(), ShopActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.zoom_enter,R.anim.zoom_exit);
                break;
            case R.id.grid_fruit://水果
                intent = new Intent(getActivity(), FruitActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.zoom_enter,R.anim.zoom_exit);
                break;
            case R.id.grid_driving://驾校
                intent = new Intent(getActivity(), DrivingActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.zoom_enter,R.anim.zoom_exit);
                break;
            case R.id.grid_phone://手机
                intent = new Intent(getActivity(), PhoneActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.zoom_enter,R.anim.zoom_exit);
                break;

            case R.id.grid_czzx://充值中心
                ToastUtils.showShort("充值中心");
                break;
            case R.id.grid_fzlx://飞猪旅行
                break;
            case R.id.grid_ljb://领金币
                break;
            case R.id.grid_dj://到家
                break;
            case R.id.iv_picture:
                intent = new Intent(getActivity(), BerserkActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.zoom_enter,R.anim.zoom_exit);
                break;
        }
    }

    //数据源

    private void initDat() {
        mArrayList = new ArrayList<ArrayList<HashMap<String, Object>>>();
        HashMap<String, Object> hashMap = null;
        ArrayList<HashMap<String, Object>> arrayListForEveryGridView;

        for (int i = 0; i < 10; i++) {
            arrayListForEveryGridView = new ArrayList<HashMap<String, Object>>();
            for (int j = 0; j < 5; j++) {
                hashMap = new HashMap<String, Object>();
                hashMap.put("content", "i=" + i + " ,j=" + j);
                arrayListForEveryGridView.add(hashMap);
            }
            mArrayList.add(arrayListForEveryGridView);
        }

    }

    /**
     * 添加大图，伪造数据
     */
    private void initBigPics() {
        bigPics = new ArrayList<>();
//        bigPics.add("https://gd2.alicdn.com/imgextra/i2/380101244/TB2HHzZdNmJ.eBjy0FhXXbBdFXa_!!380101244.jpg");
        bigPics.add("https://gd4.alicdn.com/imgextra/i4/380101244/TB2qUNua4mI.eBjy0FlXXbgkVXa_!!380101244.jpg");
        bigPics.add("https://gd1.alicdn.com/imgextra/i1/380101244/TB2REFwa9qJ.eBjy1zbXXbx8FXa_!!380101244.jpg");
        bigPics.add("https://gd4.alicdn.com/imgextra/i4/380101244/TB2Ye4taZeK.eBjSszgXXczFpXa_!!380101244.jpg");
        bigPics.add("http://ob9thtnhs.bkt.clouddn.com/tuisong/da386d4d7872451ca346ba6e37da17b6.jpg?e=1477544913&token=m2BF8x75sZF4DIfwnxFri5sT51HeuFRmU2Ue0uVf:LWGBH77qhKA_BEcdgUA5u5AebR0=");

    }


    class ListViewAdapter extends BaseAdapter {
        private ArrayList<ArrayList<HashMap<String, Object>>> mList;
        private Context mContext;

        public ListViewAdapter(ArrayList<ArrayList<HashMap<String, Object>>> mList, Context mContext) {
            super();
            this.mList = mList;
            this.mContext = mContext;
        }

        @Override
        public int getCount() {
            if (mList == null) {
                return 0;
            } else {
                return this.mList.size();
            }
        }

        @Override
        public Object getItem(int position) {
            if (mList == null) {
                return null;
            } else {
                return this.mList.get(position);
            }
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = LayoutInflater.from
                        (this.mContext).inflate(R.layout.listview_item, null, false);
                holder.imageView = (ImageView) convertView.findViewById(R.id.listview_item_imageview);
                holder.id_recyclerview_horizontal = (RecyclerView) convertView.findViewById(R.id.id_recyclerview_horizontal);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            if (this.mList != null) {
                if (holder.imageView != null) {
                    holder.imageView.setImageDrawable
                            (mContext.getResources().getDrawable(R.drawable.tupian_2));
                }
                holder.imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(mContext, position + "", Toast.LENGTH_LONG).show();
                    }
                });
                if (holder.id_recyclerview_horizontal != null) {
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
                    linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                    holder.id_recyclerview_horizontal.setLayoutManager(linearLayoutManager);
                    //设置适配器
                    GalleryAdapter mAdapter = new GalleryAdapter(mContext, mDatas);
                    holder.id_recyclerview_horizontal.setAdapter(mAdapter);
                }

            }

            return convertView;

        }


        private class ViewHolder {
            ImageView imageView;
            RecyclerView id_recyclerview_horizontal;
        }

        public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ViewHolder> {
            private LayoutInflater mInflater;
            private List<Integer> mDatas;

            public GalleryAdapter(Context context, List<Integer> datats) {
                mInflater = LayoutInflater.from(context);
                mDatas = datats;
            }

            public class ViewHolder extends RecyclerView.ViewHolder {
                public ViewHolder(View arg0) {
                    super(arg0);
                }

                ImageView mImg;
                TextView mTxt;
            }

            @Override
            public int getItemCount() {
                return mDatas.size();
            }

            /**
             * 创建ViewHolder
             */
            @Override
            public ViewHolder onCreateViewHolder(ViewGroup viewGroup, final int i) {
                View view = mInflater.inflate(R.layout.activity_recycler_item,
                        viewGroup, false);
                ViewHolder viewHolder = new ViewHolder(view);

                viewHolder.mImg = (ImageView) view.findViewById(R.id.id_index_gallery_item_image);
                return viewHolder;
            }


            /**
             * 设置值
             */
            @Override
            public void onBindViewHolder(final ViewHolder viewHolder, final int i) {
                viewHolder.mImg.setImageResource(mDatas.get(i));
                viewHolder.mImg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(mContext, i + "", Toast.LENGTH_LONG).show();
                    }
                });
            }

        }
    }
}
