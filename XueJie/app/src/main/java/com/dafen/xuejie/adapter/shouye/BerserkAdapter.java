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
 * Created by Administrator on 2016/12/2.
 */
public class BerserkAdapter extends BaseAdapter {
    Context context;
    List<FruitBean>list;
    public  BerserkAdapter (Context context,List<FruitBean>list){
        this.context =context;
        this.list =list;
    }
    @Override
    public int getCount() {
        return list.size();
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
        if (view ==null){
            holder =new ViewHolder();
            view = LayoutInflater.from(context).inflate(R.layout.adapter_berserk_item,null);
            holder.iv_picture = (ImageView) view.findViewById(R.id.iv_picture);
            holder.iv_reduce = (ImageView) view.findViewById(R.id.iv_reduce);
            holder.iv_add = (ImageView) view.findViewById(R.id.iv_add);
            holder.tv_price = (TextView) view.findViewById(R.id.tv_price);
            holder.tv_quantity = (TextView) view.findViewById(R.id.tv_quantity);
            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }
        holder.tv_price.setText(list.get(position).getPrice());
        holder.tv_quantity.setText(list.get(position).getQuantity());
        return view;
    }
    final class ViewHolder{
        public ImageView iv_picture;//图片
        public  TextView tv_price; //价钱
        public  ImageView iv_reduce;//减少键
        public  ImageView iv_add;//添加键
        public  TextView tv_quantity;//数量
    }
}
