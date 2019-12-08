package com.lenovo.example.zhihu_project.fragment.zhihu;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lenovo.example.zhihu_project.R;
import com.lenovo.example.zhihu_project.adapters.ZhihuApdater;
import com.lenovo.example.zhihu_project.fragment.zhihu.child.ColumnFragment;
import com.lenovo.example.zhihu_project.fragment.zhihu.child.HotFragment;
import com.lenovo.example.zhihu_project.fragment.zhihu.child.RibaoFragment;
import com.lenovo.example.zhihu_project.fragment.zhihu.child.ThemeFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by lenovo on 2019/9/5.
 */

public class ZhihuFragment extends Fragment {


    @BindView(R.id.tab_zhihu)
    TabLayout tabZhihu;
    @BindView(R.id.vp_zhihu)
    ViewPager vpZhihu;
    Unbinder unbinder;

    private List<Fragment> list=new ArrayList<>();
    private List<String> title=new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.layout_zhihu, container, false);

        unbinder = ButterKnife.bind(this, view);
        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ColumnFragment columnFragment = new ColumnFragment();
        HotFragment hotFragment = new HotFragment();
        RibaoFragment ribaoFragment = new RibaoFragment();
        ThemeFragment themeFragment = new ThemeFragment();
        list.add(ribaoFragment);
        list.add(themeFragment);
        list.add(columnFragment);
        list.add(hotFragment);
            title.add("日报");
            title.add("主题");
            title.add("专栏");
            title.add("热门");
        ZhihuApdater zhihuApdater = new ZhihuApdater(getFragmentManager(), list, title);
        vpZhihu.setAdapter(zhihuApdater);
        tabZhihu.setupWithViewPager(vpZhihu);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
