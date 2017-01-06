package com.dafen.xuejie.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2016/11/18.
 */
public class IndentAdapter extends FragmentStatePagerAdapter{
    List<Fragment> list;
    public IndentAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        this.list =list;
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
