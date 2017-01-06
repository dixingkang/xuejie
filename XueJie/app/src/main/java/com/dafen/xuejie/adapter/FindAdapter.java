package com.dafen.xuejie.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.dafen.xuejie.R;
import com.dafen.xuejie.bean.CartBean;

import java.util.List;


/**
 * Created by Administrator on 2016/11/21.
 */
public class FindAdapter extends BaseAdapter {
    Context context;
    List<String> list;
    public FindAdapter(Context context, List<String> list){
        this.context= context;
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
        if(view ==null){
            holder =new ViewHolder();
            view = LayoutInflater.from(context).inflate(R.layout.adapter_find_item,null);
            holder.iv_picture = (ImageView) view.findViewById(R.id.iv_picture);
            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }
        holder.iv_picture.setImageResource(list.hashCode());
        return view;
    }
    final class  ViewHolder{
        public ImageView iv_picture;
    }
}
