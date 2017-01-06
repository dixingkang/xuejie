package com.dafen.xuejie.adapter.dingdan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.dafen.xuejie.R;
import com.dafen.xuejie.bean.IndentBean;

import java.util.List;

/**
 * Created by Administrator on 2016/11/26.
 */
public class DoneAdapter extends BaseAdapter {
    Context context;
    List<IndentBean> list;
    public  DoneAdapter( Context context,List<IndentBean> list){
        this.context=context;
        this.list= list;
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
            view = LayoutInflater.from(context).inflate(R.layout.adapter_done_item,null);
            holder.tv_time = (TextView) view.findViewById(R.id.tv_time);
            holder.tv_name = (TextView) view.findViewById(R.id.tv_name);
            holder.tv_at = (TextView) view.findViewById(R.id.tv_at);
            holder.tv_sum = (TextView) view.findViewById(R.id.tv_sum);
            holder.tv_all = (TextView) view.findViewById(R.id.tv_all);
            holder.tv_money = (TextView) view.findViewById(R.id.tv_money);
            holder.iv_picture = (ImageView) view.findViewById(R.id.iv_picture);
            holder.but_delete = (Button) view.findViewById(R.id.but_delete);
            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }
        holder.tv_time.setText(list.get(position).getTime());
        holder.tv_name.setText(list.get(position).getName());
        holder.tv_at.setText(list.get(position).getAt());
//        holder.tv_all.setText(list.get(position).getAll());
        holder.tv_money.setText(list.get(position).getMoney());
        holder.tv_sum.setText(list.get(position).getSum());
//        holder.but_delete.setText(list.get(position).getDelete());
        return view;
    }
    final class ViewHolder{
        public TextView tv_time;
        public  TextView tv_name;
        public ImageView iv_picture;
        public  TextView tv_at;
        public  TextView  tv_sum;
        public  TextView tv_all;
        public  TextView tv_money;
        public Button but_delete;
    }
}
