package com.dafen.xuejie.adapter.shouye;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dafen.xuejie.R;
import com.dafen.xuejie.bean.FruitBean;

import java.util.List;

/**
 * Created by Administrator on 2016/11/30.
 */
public class GoodAdapter extends BaseAdapter {
    private String [][] allData;
    private Context context;
    private  int selectIndex;

    public GoodAdapter(String [][] allData,Context context,int selectIndex){
        this.allData =allData;
        this.context =context;
        this.selectIndex =selectIndex;
    }
    @Override
    public int getCount() {
        return allData[selectIndex].length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder =null;
        if (null ==view){
            holder =new ViewHolder();
            view = LayoutInflater.from(context).inflate(R.layout.adapter_shop_good_item,null);
            holder.tv = (TextView) view.findViewById(R.id.tv_title);
            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }

        holder.tv.setText(allData[selectIndex][position]);
        return view;
    }

    public void setIndex(int index) {
        
        selectIndex = index;
    }


    final class ViewHolder{
        TextView tv;
        public ImageView iv_picture;
        public TextView tv_title;
        public TextView tv_price;
        public  ImageView iv_reduce;
        public ImageView iv_add;
        public  TextView tv_quantity;
    }
}
