package com.lenovo.example.zhihu_project.fragment.juejin;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.lenovo.example.zhihu_project.R;
import com.lenovo.example.zhihu_project.adapters.juejin.JuejinAdapter;
import com.lenovo.example.zhihu_project.model.bean.StateInfo;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by lenovo on 2019/9/5.
 */

public class JuejinFragment extends Fragment {

    ArrayList<StateInfo> list = new ArrayList<>();
    ArrayList<Fragment> ft = new ArrayList<Fragment>();
    @BindView(R.id.tab_juejin)
    TabLayout tabJuejin;
    @BindView(R.id.vp_juejin)
    ViewPager vpJuejin;
    Unbinder unbinder;
    @BindView(R.id.iv_selector)
    ImageView ivSelector;
    private JuejinAdapter juejinAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.layout_juejin, container, false);

        unbinder = ButterKnife.bind(this, view);
        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initList();
        initData();


    }

    private void initList() {
        //初始化
        list.add(new StateInfo("Android", true));
        list.add(new StateInfo("ios", true));
        list.add(new StateInfo("前端", true));
        list.add(new StateInfo("后端", true));
        list.add(new StateInfo("设计", false));
        list.add(new StateInfo("产品", false));
        list.add(new StateInfo("阅读", false));
        list.add(new StateInfo("工具资源", false));

    }

    private void initData() {

        for (int i = 0; i < list.size(); i++) {

            if (list.get(i).isState()) {

                JueJinChild jueJinChild = new JueJinChild();
                ft.add(jueJinChild);
            }

        }
        juejinAdapter = new JuejinAdapter(getFragmentManager(), list, ft);
        vpJuejin.setAdapter(juejinAdapter);
        tabJuejin.setupWithViewPager(vpJuejin);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.iv_selector)
    public void onViewClicked() {

        Intent intent = new Intent(getContext(), SelectorActivity.class);
        intent.putParcelableArrayListExtra("stateInfo",(ArrayList<? extends Parcelable>) list);
        startActivityForResult(intent,100);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==100&&resultCode==101) {
            ArrayList<StateInfo> back = data.getParcelableArrayListExtra("back");
            list.clear();
            list.addAll(back);
            ft.clear();
            initData();


        }
    }
}
