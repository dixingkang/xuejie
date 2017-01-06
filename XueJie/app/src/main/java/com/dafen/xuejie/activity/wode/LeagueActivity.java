package com.dafen.xuejie.activity.wode;

import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.dafen.xuejie.BaseActivity;
import com.dafen.xuejie.R;
import com.dafen.xuejie.adapter.JoinAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class LeagueActivity extends BaseActivity implements View.OnClickListener,AdapterView.OnItemClickListener{
    private RelativeLayout rl_back;
    private ListView       lv;
    List<Map<String,Object>>list;
    @Override
    public void setContentView() {
        setContentView(R.layout.activity_league);
    }

    @Override
    public void initViews() {
        rl_back = (RelativeLayout) findViewById(R.id.rl_back);
        lv = (ListView) findViewById(R.id.lv);
    }

    @Override
    public void initListeners() {
        rl_back.setOnClickListener(this);
        lv.setOnItemClickListener(this);
    }

    @Override
    public void initData() {
        list =getData();
        JoinAdapter adapter =new JoinAdapter(LeagueActivity.this,list);
        lv.setAdapter(adapter);
    }
    public List<Map<String,Object>> getData(){
        list =new ArrayList<Map<String,Object>>();
        Map<String,Object> map =new HashMap<String,Object>();
        map.put("img",R.drawable.hzrz);
        map.put("text","合作入驻");
        Map<String,Object>map1 =new HashMap<String, Object>();
        map1.put("img",R.drawable.xydl);
        map1.put("text","校园代理申请");
        Map<String,Object> map2 =new HashMap<String, Object>();
        map2.put("img",R.drawable.psong);
        map2.put("text","配送代理申请");
        Map<String,Object> map3 =new HashMap<String, Object>();
        map3.put("img",R.drawable.mthz);
        map3.put("text","媒体合作");

        list.add(map);
        list.add(map1);
        list.add(map2);
        list.add(map3);
        return list;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl_back:
                finish();
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
