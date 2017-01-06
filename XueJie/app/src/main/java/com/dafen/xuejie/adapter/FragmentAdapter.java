package com.dafen.xuejie.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2016/11/15.
 */
public class FragmentAdapter extends FragmentPagerAdapter {
    private List<Fragment> list;
    public FragmentAdapter(FragmentManager fm, List<Fragment> list){
        super(fm);
        this.list=list;
    }
    public FragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
