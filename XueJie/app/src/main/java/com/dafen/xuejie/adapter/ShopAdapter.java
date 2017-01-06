package com.dafen.xuejie.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dafen.xuejie.R;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/24.
 */
public class ShopAdapter extends BaseAdapter {
    List<Map<String,Object>> list;
    Context context;
    public  ShopAdapter( List<Map<String,Object>> list,Context context){
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
        if (null==view){
            holder =new ViewHolder();
            view = LayoutInflater.from(context).inflate(R.layout.gridview_list_item,null);
            holder.iv_tu = (ImageView) view.findViewById(R.id.iv_tu);
            holder.tv_tuming = (TextView) view.findViewById(R.id.tv_tuming);
            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }
        holder.iv_tu.setBackgroundResource((Integer) list.get(position).get("img"));
        holder.tv_tuming.setText((String) list.get(position).get("text"));
        return view;
    }

    final class  ViewHolder{
        public ImageView iv_tu;
        public TextView tv_tuming;
    }
}
