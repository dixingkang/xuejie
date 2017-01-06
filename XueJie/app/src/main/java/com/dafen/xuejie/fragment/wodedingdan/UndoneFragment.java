package com.dafen.xuejie.fragment.wodedingdan;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.dafen.xuejie.R;
import com.dafen.xuejie.adapter.dingdan.UndoneAdapter;
import com.dafen.xuejie.bean.IndentBean;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2016/11/18.
 */
public class UndoneFragment extends Fragment implements AdapterView.OnItemClickListener {
    private ListView lv_undone;
    List<IndentBean> list;
    IndentBean bean;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_undone,null);
        initView(view);
        initData();
        initListener();
        return view;
    }

    private void initView(View view) {
        lv_undone = (ListView) view.findViewById(R.id.lv_undone);
    }
    private void  initData(){
        list =getData();
        UndoneAdapter adapter  =new UndoneAdapter(getActivity(),list);
        lv_undone.setAdapter(adapter);
    }

    private void initListener() {
        lv_undone.setOnItemClickListener(this);
    }
    public  List<IndentBean>getData(){
        list =new ArrayList<IndentBean>();
        bean =new IndentBean();
        bean.setTime("2016-11-20");
        bean.setName("巧乐兹");
        bean.setAt("6");
        bean.setSum("5");
        bean.setAll("查看全部");
        bean.setMoney("18");
        bean.setPay("去支付");
        for (int i =0; i < 6; i++){
            list.add(bean);
        }
        return list;
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
