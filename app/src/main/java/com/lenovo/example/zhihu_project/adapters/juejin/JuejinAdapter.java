package com.lenovo.example.zhihu_project.adapters.juejin;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.lenovo.example.zhihu_project.model.bean.StateInfo;

import java.util.ArrayList;

/**
 * Created by lenovo on 2019/9/10.
 */

public class JuejinAdapter extends FragmentStatePagerAdapter {

    private ArrayList<StateInfo> title;

    private ArrayList<Fragment> list;

    public JuejinAdapter(FragmentManager fm, ArrayList<StateInfo> title, ArrayList<Fragment> list) {
        super(fm);
        this.title = title;
        this.list = list;
    }

    public JuejinAdapter(FragmentManager fm) {
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

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return title.get(position).getType();
    }
}
