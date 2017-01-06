package com.dafen.xuejie.adapter.dingdan;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dafen.xuejie.R;

import java.util.List;

/**
 * Created by 94239 on 2016/11/30.
 */

public class ListAdapter extends BaseAdapter {
    List<String> mList;
    Context mContext;

    public ListAdapter(Context context, List<String> list) {
       this.mList = list;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;

        if (view == null) {
            viewHolder = new ViewHolder();
            view = LinearLayout.inflate(mContext, R.layout.adapter_list_item, null);
            viewHolder.tv_good = (TextView) view.findViewById(R.id.tv_good);
            view.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) view.getTag();
        }
            viewHolder.tv_good.setText(mList.get(i));
        return view;
    }


    public class ViewHolder {
        public View rootView;
        public TextView tv_good;

    }
}
