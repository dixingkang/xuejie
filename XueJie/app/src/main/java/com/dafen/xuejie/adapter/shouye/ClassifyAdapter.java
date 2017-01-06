package com.dafen.xuejie.adapter.shouye;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dafen.xuejie.R;
import com.dafen.xuejie.bean.FruitBean;

import java.util.List;

/**
 * Created by Administrator on 2016/11/30.
 */
public class ClassifyAdapter extends BaseAdapter {
    private final  String[] mMenus;
    private final Context context;
    private int selectIndex;

    public ClassifyAdapter(String[] mMenus, Context context, int selectIndex){
        this.mMenus = mMenus;
        this.context =context;
        this.selectIndex = selectIndex;
    }
    @Override
    public int getCount() {
        return mMenus.length;
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
            view = LayoutInflater.from(context).inflate(R.layout.adapter_classify_item,null);
            holder.tv = (TextView) view.findViewById(R.id.tv_title);
            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }
        LinearLayout.LayoutParams selectParams =new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        //左上右下
        selectParams.setMargins(1,1,0,0);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        params.setMargins(1,1,1,0);
        if (position == selectIndex){
            holder.tv.setBackgroundColor(Color.parseColor("#ffffff"));
            holder.tv.setLayoutParams(selectParams);
        }else {
            holder.tv.setBackgroundColor(Color.parseColor("#dedede"));
            holder.tv.setLayoutParams(params);
        }
        holder.tv.setText(mMenus[position]);
        return view;
    }

    public void setIndex(int index) {
        selectIndex = index;
    }

   class  ViewHolder{
      TextView tv;
    }
}
