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
public class PhoneAdapter extends BaseAdapter {
    Context context;
    List<Map<String,Object>>list;
    public PhoneAdapter(Context context, List<Map<String,Object>>list){
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
        if (view==null){
            holder =new ViewHolder();
            view = LayoutInflater.from(context).inflate(R.layout.adapter_phone_item,null);
            holder.iv_phone = (ImageView) view.findViewById(R.id.iv_phone);
            holder.tv_title = (TextView) view.findViewById(R.id.tv_title);
            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
            holder.iv_phone.setImageResource((Integer) list.get(position).get("img"));
            holder.tv_title.setText((String) list.get(position).get("text"));
        }
        return view;
    }
    final class ViewHolder{
        public ImageView iv_phone;
        public TextView tv_title;
    }
}
