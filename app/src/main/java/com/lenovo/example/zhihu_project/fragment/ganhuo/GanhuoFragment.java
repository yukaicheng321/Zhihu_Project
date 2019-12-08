package com.lenovo.example.zhihu_project.fragment.ganhuo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lenovo.example.zhihu_project.R;
import com.lenovo.example.zhihu_project.adapters.ganhuo.GanhuoAdapter;
import com.lenovo.example.zhihu_project.constants.Constant;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by lenovo on 2019/9/5.
 */

public class GanhuoFragment extends Fragment implements TabLayout.OnTabSelectedListener {
    @BindView(R.id.tab_ganhuo)
    TabLayout tabGanhuo;
    @BindView(R.id.vp_ganhuo)
    ViewPager vpGanhuo;
    Unbinder unbinder;


    private ArrayList<Fragment> list = new ArrayList<>();
    private String[] tabTitle = new String[]{"Android","iOS","前端","福利"};
    private String searchString;
    private AndroidFragment androidFragment;
    private IosFragment iosFragment;
    private WebFragment webFragment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.layout_ganhuo, container, false);

        unbinder = ButterKnife.bind(this, view);
        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        androidFragment = new AndroidFragment();
        iosFragment = new IosFragment();
        webFragment = new WebFragment();
        FuliFragment fuliFragment = new FuliFragment();

        list.add(androidFragment);
        list.add(iosFragment);
        list.add(webFragment);
        list.add(fuliFragment);

        GanhuoAdapter ganhuoAdapter = new GanhuoAdapter(getFragmentManager(), list, tabTitle);
        vpGanhuo.setAdapter(ganhuoAdapter);
        tabGanhuo.setupWithViewPager(vpGanhuo);

        tabGanhuo.addOnTabSelectedListener(this);
    }





    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public void searchWord(String query) {
        searchString=query;
        if (searchString != null) {

            if (TextUtils.equals(title, Constant.TECH_ANDROID)) {
                androidFragment.searchWord(searchString);
            }
            if (TextUtils.equals(title, Constant.TECH_IOS)) {
                iosFragment.searchWord(searchString);
            }
            if (TextUtils.equals(title, Constant.TECH_FIRST)) {
                webFragment.searchWord(searchString);
            }
            Log.i("tag", "onTabSelected: " + searchString);
            searchString = null;
        }

    }
    private String title=Constant.TECH_ANDROID;

    @Override
    public void onTabSelected(TabLayout.Tab tab) {

        CharSequence text = tab.getText();
        title=text.toString();
        Log.i("tag", "onTabSelected: +" + text);


    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
