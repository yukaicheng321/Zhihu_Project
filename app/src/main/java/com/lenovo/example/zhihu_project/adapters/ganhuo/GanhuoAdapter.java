package com.lenovo.example.zhihu_project.adapters.ganhuo;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

/**
 * Created by lenovo on 2019/9/9.
 */

public class GanhuoAdapter extends FragmentStatePagerAdapter {

    private ArrayList<Fragment> list;
        private  String[] tabTitle;

    public GanhuoAdapter(FragmentManager fm, ArrayList<Fragment> list, String[] tabTitle) {
        super(fm);
        this.list = list;
        this.tabTitle = tabTitle;
    }

    public GanhuoAdapter(FragmentManager fm) {
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
        return tabTitle[position];
    }
}
