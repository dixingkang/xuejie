package com.dafen.xuejie.adapter.shouye;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dafen.xuejie.R;
import com.dafen.xuejie.bean.CartBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/3.
 */
public class GridViewAdapter extends BaseAdapter {
    private Context context;
    int count;
    private List<CartBean> listData=new ArrayList<CartBean>();
    public  GridViewAdapter(Context context, int count){
        super();
        this.context =context;
        this.count =count;
    }
    public List<CartBean>getListData(){
        return listData;
    }
    public void setListData(List<CartBean> listData) {
        this.listData = listData;
    }
    @Override
    public int getCount() {
        return count;
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
        if (view == null){
            holder =new ViewHolder();
            view = LayoutInflater.from(context).inflate(R.layout.adapter_gridview_item,null);
            holder.iv_picture = (ImageView) view.findViewById(R.id.iv_picture);
            holder.iv_add = (ImageView) view.findViewById(R.id.iv_add);
            holder.tv_title = (TextView) view.findViewById(R.id.tv_title);
            holder.tv_price = (TextView) view.findViewById(R.id.tv_price);
            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }
        CartBean cartBean =listData.get(position);
        holder.tv_title.setText(cartBean.getTitle());

        return view;
    }
    //列表数据
    public final class ViewHolder {
        //列表图片
        ImageView iv_picture;
        //列表名
        TextView tv_title;
        //列表产家
        ImageView iv_add;
        //价格
        TextView tv_price;
    }
}
