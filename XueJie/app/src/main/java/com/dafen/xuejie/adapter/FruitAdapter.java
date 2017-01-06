package com.dafen.xuejie.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.dafen.xuejie.R;
import com.dafen.xuejie.bean.FruitBean;

import java.util.List;

/**
 * Created by Administrator on 2016/11/25.
 */
public class FruitAdapter extends BaseAdapter {
    Context context;
    List<FruitBean> list;
    public  FruitAdapter(Context context,List<FruitBean> list){
        this.context=context;
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
        if (null ==view){
            holder =new ViewHolder();
            view = LayoutInflater.from(context).inflate(R.layout.adapter_fruit_item,null);
            holder.iv_picture = (ImageView) view.findViewById(R.id.iv_picture);
            holder.iv_reduce = (ImageView) view.findViewById(R.id.iv_reduce);
            holder.iv_add = (ImageView) view.findViewById(R.id.iv_add);
            holder.et_quantity = (EditText) view.findViewById(R.id.et_quantity);
            holder.tv_price = (TextView) view.findViewById(R.id.tv_price);
            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }
        holder.et_quantity.setText(list.get(position).getQuantity());
        holder.tv_price.setText(list.get(position).getPrice());
        return view;
    }
    final class ViewHolder{
        public ImageView iv_picture;
        public TextView tv_price;
        public  ImageView iv_reduce;
        public EditText et_quantity;
        public  ImageView iv_add;
    }
}
