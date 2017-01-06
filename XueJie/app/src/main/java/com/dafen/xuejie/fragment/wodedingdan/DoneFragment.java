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
import com.dafen.xuejie.adapter.dingdan.DoneAdapter;
import com.dafen.xuejie.bean.IndentBean;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2016/11/18.
 */
public class DoneFragment extends Fragment implements AdapterView.OnItemClickListener {
    private ListView lv_done;
    List<IndentBean> list;
    IndentBean bean;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_done,null);
        initView(view);
        initData();
        initListener();
        return view;
    }

    private void initView(View view) {
        lv_done = (ListView) view.findViewById(R.id.lv_done);
    }

    private void initData() {
        list =getData();
        DoneAdapter adapter =new DoneAdapter(getActivity(),list);
        lv_done.setAdapter(adapter);
    }

    private void initListener() {
        lv_done.setOnItemClickListener(this);
    }
    public List<IndentBean>getData(){
        list =new ArrayList<IndentBean>();
        bean =new IndentBean();
        bean.setTime("2016-11-24");
        bean.setName("特仑苏");
        bean.setAt("5.0");
        bean.setSum("5");
        bean.setMoney("25");
        for (int i =0; i < 6; i++){
            list.add(bean);
        }
        return list;
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
