package com.dafen.xuejie.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.dafen.xuejie.R;
import com.dafen.xuejie.bean.CartBean;

import java.util.List;


/**
 * Created by Administrator on 2016/11/17.
 */
public class CartAdapter extends BaseAdapter {
    Context context;
    List<CartBean> list;
    public CartAdapter(Context context, List<CartBean> list){
        this.context = context;
        this.list = list;
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
        if(view==null ){
            holder =new ViewHolder();
            view = LayoutInflater.from(context).inflate(R.layout.adapter_cart_item,null);
            holder.iv_img = (ImageView) view.findViewById(R.id.iv_img);
            holder.iv_reduce = (ImageView) view.findViewById(R.id.iv_reduce);
            holder.iv_add = (ImageView) view.findViewById(R.id.iv_add);
            holder.tv_price = (TextView) view.findViewById(R.id.tv_price);
            holder.tv_title = (TextView) view.findViewById(R.id.tv_title);
            holder.tv_quantity = (TextView) view.findViewById(R.id.tv_quantity);
            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }
        holder.tv_title.setText(list.get(position).getTitle());
        holder.tv_price.setText(list.get(position).getPrice());
        holder.tv_quantity.setText(list.get(position).getQuantity());
        return view;
    }
    final class ViewHolder{
        public ImageView iv_img,iv_reduce,iv_add;
        public TextView tv_title,tv_price,tv_quantity;
    }
}
