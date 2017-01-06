package com.dafen.xuejie.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;


import com.dafen.xuejie.R;
import com.dafen.xuejie.activity.SettlementActivity;
import com.dafen.xuejie.activity.wodedizhi.AddSiteActivity;
import com.dafen.xuejie.adapter.CartAdapter;
import com.dafen.xuejie.bean.CartBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/15.
 */
public class CartFragment extends Fragment implements View.OnClickListener ,AdapterView.OnItemClickListener{
    private LinearLayout ll_add;
    private Button btn_next;
    private ListView lv_cart;
    List<CartBean> list;
    CartBean bean;
    CartAdapter cartAdapter;
    Intent intent;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart,null);
        initView(view);
        initData();
        initListener();
        return view;
    }
    private void initView(View view){
        ll_add = (LinearLayout) view.findViewById(R.id.ll_add);
        btn_next = (Button) view.findViewById(R.id.btn_next);
        lv_cart = (ListView) view.findViewById(R.id.lv_cart);
    }
    private void initData(){
        list =getData();
        cartAdapter = new CartAdapter(getActivity(),list);
        lv_cart.setAdapter(cartAdapter);

    }
    private void initListener(){
        lv_cart.setOnItemClickListener(this);
        ll_add.setOnClickListener(this);
        btn_next.setOnClickListener(this);
    }
    public List<CartBean>getData(){
        list =new ArrayList<CartBean>();
        bean = new CartBean();
        bean.setTitle("巧乐兹雪糕经典巧脆棒");
        bean.setQuantity("2");
        bean.setPrice("10.00");
        for (int i = 0 ; i < 6 ;i++){
            list.add(bean);
        }

        return list;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_add://添加地址
                intent = new Intent(getActivity(), AddSiteActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_next:// 订单结算
                intent =new Intent(getActivity(), SettlementActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}



